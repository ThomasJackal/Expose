package fr.eql.ai116.duron.thomas.art.connect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ArtistParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String artist_displayed_name;
    private String artist_role;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Event event;
}
