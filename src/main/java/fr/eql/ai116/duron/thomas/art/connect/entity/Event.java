package fr.eql.ai116.duron.thomas.art.connect.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private LocalDate creation_date;
    private LocalDate cloturation_date;
    private LocalDate archivation_date;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToOne
    private Programation programation;

    @OneToOne
    private Ticketing ticketing;

    @OneToMany(mappedBy = "event")
    private List<ArtistParticipation> artistParticipations = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<AutoShare> autoShares;

    @ManyToMany
    @JoinTable(
            name = "user_followed_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> followers = new ArrayList<>();

    @ManyToOne
    @JoinTable(
            name = "event_address",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "event_page_images",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Image> images = new ArrayList<>();

    @ElementCollection(targetClass = ArtTag.class)
    @JoinTable(name = "events_tags", joinColumns = @JoinColumn(name = "event_id"))
    @Enumerated(EnumType.STRING)
    private List<ArtTag> tags = new ArrayList<>();
}
