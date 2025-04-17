package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.Address;
import fr.eql.ai116.duron.thomas.art.connect.entity.ArtTag;
import fr.eql.ai116.duron.thomas.art.connect.entity.EventType;
import fr.eql.ai116.duron.thomas.art.connect.entity.Image;
import fr.eql.ai116.duron.thomas.art.connect.entity.Programation;
import fr.eql.ai116.duron.thomas.art.connect.entity.Ticketing;

public record EventCreationDto (
        String name,
        String description,
        Programation programation,
        EventType eventType,
        Ticketing ticketing,
        Address address,
        ArtTag[] tags,
        Image[] images,
        String owner_artist_role
) {
}
