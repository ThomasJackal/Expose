package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.AccountType;

public record UpgradeDto (String displayed_name, String description, String contact, String location, String profession, AccountType accountType) {
}
