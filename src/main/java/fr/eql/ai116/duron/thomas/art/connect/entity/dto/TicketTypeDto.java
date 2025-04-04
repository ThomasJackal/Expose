package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import java.time.LocalDate;

public record TicketTypeDto (
        String name,
        String description,
        int max_places,
        double price_per_unit
){
}
