package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;

public record SearchInputDto(long latitude, long longitude, String field, int perimeter, EventType eventType, ArtTag[] tags) {
}
