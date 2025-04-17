package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtistParticipation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;
import fr.eql.ai116.duron.thomas.art.connect.entity.Image;
import fr.eql.ai116.duron.thomas.art.connect.entity.Programation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Ticketing;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.AddressDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.ArtistParticipationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.FollowerDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.PostDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.ProgramationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchInputDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchOutputDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SlotDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.TicketTypeDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.TicketingDto;
import fr.eql.ai116.duron.thomas.art.connect.repository.EventRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Artist creator, EventCreationDto dto) {

        Event event = new Event(
                dto.name(),
                dto.description(),
                dto.programation(),
                dto.eventType(),
                dto.ticketing(),
                dto.address(),
                Arrays.asList(dto.tags()),
                Arrays.stream(dto.images()).map(image ->
                        new Image(image.getImageLink(), LocalDateTime.now(), creator)).toList()
        );
        event.getArtistParticipations().add(new ArtistParticipation(dto.owner_artist_role(), event, creator));
        setupProgramation(dto.programation(), event);
        setupTicketing(dto.ticketing(), event);

        Event savedEvent = eventRepository.save(event);;
        logger.info("event saved: {}", savedEvent.toString());
        return savedEvent;
    }

    private void setupProgramation(Programation programation, Event event) {
        if (programation == null) return;

        programation.setEvent(event);
        programation.updateSlots();
    }

    private void setupTicketing(Ticketing ticketing, Event event) {
        if (ticketing == null) return;

        ticketing.setEvent(event);
        ticketing.updateTicketTypes();
    }

    @Transactional
    @Override
    public List<SearchOutputDto> searchEvent(SearchInputDto input) {

        List<SearchOutputDto> output = new ArrayList<>();

        List<Object[]> eventsId = eventRepository.search(input.latitude(), input.longitude());
        List<Long> ids = eventsId.stream().map(x -> (long) x[0]).toList();
        //List<Event> fetchedEvents = eventRepository.findByIds(ids); //TODO: trouver un moyen d'utiliser ça sans changer l'ordre de la list

        for (int i = 0; i < ids.size(); i++) {
            Optional<Event> result = eventRepository.findById(ids.get(i));
            if (result.isEmpty()) continue;

            Event event = result.get();

            //Force hibernate à loader
            event.getImages().size();
            event.getTags().size();

            output.add(new SearchOutputDto(
                    event.getId(),
                    event.getAddress().getLatitude(),
                    event.getAddress().getLongitude(),
                    event.getName(),
                    event.getDescription(),
                    event.getProgramation().getStart_date(),
                    event.getProgramation().calculateEndDate(),
                    event.getArtistParticipations().stream().map(participation ->
                            new ArtistParticipationDto(
                                    participation.getArtist_displayed_name(),
                                    participation.getArtist_role(),
                                    new FollowerDto(
                                            participation.getArtist().getId(),
                                            participation.getArtist().getUsername(),
                                            participation.getArtist().getProfilePicture().getImageLink()
                                    )
                            )
                    ).toList(),
                    ((Double) eventsId.get(i)[1]).floatValue(),
                    event.getEventType(),
                    event.getTags(),
                    event.getImages().get(0).getImageLink()
            ));
        }

        return output;
    }

    @Transactional
    @Override
    public EventDto getEvent(long eventId) {
        Event event = eventRepository.findById(eventId).get();

        //Force hibernate à loader les tags ? (je comprend pas pourquoi parce que ma fonction est transactionnale, ça devrait close la sessions à la fin de la méthode non ?)
        event.getTags().size();

        return new EventDto(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getEventType(),
                event.getProgramation() == null ? null : new ProgramationDto(
                        event.getProgramation().getStart_date(),
                        event.getProgramation().getSlots().stream().map(slot ->
                                new SlotDto(
                                        slot.getLabel(),
                                        slot.getDays_from_start(),
                                        slot.getStart_time(),
                                        slot.getEnd_time()
                                )).toList()
                ), event.getTicketing() == null ? null : new TicketingDto(
                event.getTicketing().getClosing_datetime(),
                event.getTicketing().getTicketTypes().stream().map(ticketTypes ->
                        new TicketTypeDto(
                                ticketTypes.getName(),
                                ticketTypes.getDescription(),
                                ticketTypes.getMax_places(),
                                ticketTypes.getPrice_per_unit()
                        )).toList()
        ),
                event.getArtistParticipations().stream().map(artistParticipation ->
                        new ArtistParticipationDto(
                                artistParticipation.getArtist_displayed_name(),
                                artistParticipation.getArtist_role(),
                                artistParticipation.getArtist() == null ?
                                        null :
                                        new FollowerDto(
                                                artistParticipation.getArtist().getId(),
                                                artistParticipation.getArtist().getUsername(),
                                                artistParticipation.getArtist().getProfilePicture().getImageLink()
                                        )
                        )).toList(),
                event.getPosts().stream().map(post ->
                        new PostDto(
                                post.getTitle(),
                                post.getMessage(),
                                post.getPost_datetime()
                        )).toList(),
                event.getFollowers().stream().map(user ->
                        new FollowerDto(
                                user.getId(),
                                user.getUsername(),
                                user.getProfilePicture().getImageLink()
                        )).toList(),
                new AddressDto(
                        event.getAddress().getLatitude(),
                        event.getAddress().getLongitude()
                ),
                event.getImages().stream().map(Image::getImageLink).toList(),
                event.getTags()
        );
    }
}
