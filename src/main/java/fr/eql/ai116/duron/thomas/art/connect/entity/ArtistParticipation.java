package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private Artist artist;

    @JsonIgnore
    @ManyToOne
    private Event event;

    public ArtistParticipation() {
    }

    public ArtistParticipation(String artist_role, Event event, Artist artist) {
        this.artist_role = artist_role;
        this.event = event;
        this.artist = artist;
        this.artist_displayed_name = artist.getDisplayed_name();
    }

    public ArtistParticipation(String artist_role, Event event, String artist_displayed_name) {
        this.artist_role = artist_role;
        this.event = event;
        this.artist_displayed_name = artist_displayed_name;
    }

    //region Getters/Setters

    public long getId() {
        return id;
    }

    public String getArtist_displayed_name() {
        return artist_displayed_name;
    }

    public String getArtist_role() {
        return artist_role;
    }

    public Artist getArtist() {
        return artist;
    }

    public Event getEvent() {
        return event;
    }

    //endregion
}
