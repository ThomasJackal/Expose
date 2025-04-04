package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import fr.eql.ai116.duron.thomas.art.connect.entity.TicketType;

import java.time.LocalDateTime;
import java.util.List;

public record TicketingDto (
        LocalDateTime closing_datetime,
        List<TicketTypeDto> ticketTypes
){
}
