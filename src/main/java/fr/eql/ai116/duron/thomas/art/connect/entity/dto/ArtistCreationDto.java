package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.AccountType;

public record ArtistCreationDto(
        String displayed_name,
        String profession,
        AccountType accountType
) {
}
