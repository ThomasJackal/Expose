package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtistParticipation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchInputDto;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.SearchOutputDto;
import fr.eql.ai116.duron.thomas.art.connect.repository.EventRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

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
                Arrays.asList(dto.images())
        );
        event.getArtistParticipations().add(new ArtistParticipation(dto.owner_artist_role(),event, creator));
        dto.programation().setEvent(event);
        dto.ticketing().setEvent(event);
        dto.programation().updateSlots();
        dto.ticketing().updateTicketTypes();
        return eventRepository.save(event);
    }

    @Override
    public List<SearchOutputDto> searchEvent(SearchInputDto input) {

        List<SearchOutputDto> output = new ArrayList<>();

        List<Object[]> eventsId = eventRepository.search(input.latitude(), input.longitude());
        List<Long> ids = eventsId.stream().map(x -> (long) x[0]).toList();

        for (int i = 0; i < ids.size(); i++) {
            Optional<Event> result = eventRepository.findById(ids.get(i));
            if (result.isEmpty()) continue;

            Event event = result.get();

            output.add(new SearchOutputDto(
                    event.getAddress().getLatitude(),
                    event.getAddress().getLongitude(),
                    event.getName(),
                    event.getDescription(),
                    event.getProgramation().getStart_date(),
                    event.getProgramation().calculateEndDate(),
                    null, //TODO
                    ((Double) eventsId.get(i)[1]).floatValue(),
                    event.getEventType(),
                    null //TODO
            ));
        }

        return output;
    }
}
