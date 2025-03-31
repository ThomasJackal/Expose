package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Programation programation;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Ticketing ticketing;

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<ArtistParticipation> artistParticipations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<AutoShare> autoShares;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_followed_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> followers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_address",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address address;

    @JsonIgnore
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

    public Event() {
    }

    public Event(String name, String description, Programation programation, EventType eventType, Ticketing ticketing, Address address, List<ArtTag> tags, List<Image> images) {
        this.name = name;
        this.description = description;
        this.programation = programation;
        this.eventType = eventType;
        this.ticketing = ticketing;
        this.address = address;
        this.tags = tags;
        this.images = images;

        this.creation_date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creation_date=" + creation_date +
                ", cloturation_date=" + cloturation_date +
                ", archivation_date=" + archivation_date +
                ", eventType=" + eventType +
                '}';
    }

    //region Getters/Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getCloturation_date() {
        return cloturation_date;
    }

    public void setCloturation_date(LocalDate cloturation_date) {
        this.cloturation_date = cloturation_date;
    }

    public LocalDate getArchivation_date() {
        return archivation_date;
    }

    public void setArchivation_date(LocalDate archivation_date) {
        this.archivation_date = archivation_date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Programation getProgramation() {
        return programation;
    }

    public void setProgramation(Programation programation) {
        this.programation = programation;
    }

    public Ticketing getTicketing() {
        return ticketing;
    }

    public void setTicketing(Ticketing ticketing) {
        this.ticketing = ticketing;
    }

    public List<ArtistParticipation> getArtistParticipations() {
        return artistParticipations;
    }

    public void setArtistParticipations(List<ArtistParticipation> artistParticipations) {
        this.artistParticipations = artistParticipations;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<AutoShare> getAutoShares() {
        return autoShares;
    }

    public void setAutoShares(List<AutoShare> autoShares) {
        this.autoShares = autoShares;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ArtTag> getTags() {
        return tags;
    }

    public void setTags(List<ArtTag> tags) {
        this.tags = tags;
    }
    //endregion
}
