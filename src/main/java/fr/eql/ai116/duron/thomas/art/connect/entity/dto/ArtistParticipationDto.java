package fr.eql.ai116.duron.thomas.art.connect.entity.dto;

public record ArtistParticipationDto (
        String artist_displayed_name,
        String artist_role,
        FollowerDto artist
){
}
