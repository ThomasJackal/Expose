package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;

public record SearchInputDto(
        float latitude,
        float longitude,
        String field,
        float radius,
        EventType eventType,
        ArtTag[] tags) {
}
