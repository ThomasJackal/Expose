package fr.eql.ai116.duron.thomas.art.connect.service;

import fr.eql.ai116.duron.thomas.art.connect.entity.Artist;
import fr.eql.ai116.duron.thomas.art.connect.entity.Event;
import fr.eql.ai116.duron.thomas.art.connect.entity.dto.EventCreationDto;

public interface EventService {

    Event createEvent(Artist creator, EventCreationDto dto);
}
