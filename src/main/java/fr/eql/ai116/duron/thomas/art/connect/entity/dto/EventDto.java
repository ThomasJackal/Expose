package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;
import java.util.List;

public record EventDto(
        long id,
        String name,
        String description,
        EventType eventType,
        ProgramationDto programation,
        TicketingDto ticketing,
        List<ArtistParticipationDto> artistParticipations,
        List<PostDto> posts,
        List<FollowerDto> followers ,
        AddressDto address,
        List<String> images ,
        List<ArtTag> tags
        ) {
}
