package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

import java.time.LocalDateTime;

public record PostDto (
        String title,
        String message,
        LocalDateTime post_datetime

) {
}
