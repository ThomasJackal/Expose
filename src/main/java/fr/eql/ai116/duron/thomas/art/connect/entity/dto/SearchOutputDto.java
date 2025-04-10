package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtistParticipation;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;

import java.time.LocalDate;
import java.util.List;

public record SearchOutputDto(
        long id,
        double latitude,
        double longitude,
        String name,
        String description,
        LocalDate start,
        LocalDate end,
        List<ArtistParticipationDto> featuredArtists,
        float distance,
        EventType eventType,
        List<ArtTag> tags,
        String image
) {
}
