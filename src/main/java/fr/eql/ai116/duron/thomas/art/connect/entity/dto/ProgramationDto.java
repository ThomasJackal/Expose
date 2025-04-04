package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import java.time.LocalDate;
import java.util.List;

public record ProgramationDto (
        LocalDate start_date,
        List<SlotDto> slots
){
}
