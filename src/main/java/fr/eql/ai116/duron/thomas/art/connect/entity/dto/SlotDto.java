package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import java.time.LocalTime;

public record SlotDto (
        String label,
        int days_from_start,
        LocalTime start_time,
        LocalTime end_time
){
}
