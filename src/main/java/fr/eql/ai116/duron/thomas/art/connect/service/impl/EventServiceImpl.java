package fr.eql.ai116.duron.thomas.art.connect.service.impl;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtistParticipation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;
import fr.eql.ai116.duron.thomas.art.connect.repository.EventRepository;
import fr.eql.ai116.duron.thomas.art.connect.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
}
