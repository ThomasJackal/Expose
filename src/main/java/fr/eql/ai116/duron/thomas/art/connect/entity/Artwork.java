package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import java.time.LocalDate;
import java.util.List;

@Entity
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String dimensions;
    private String processes;
    private LocalDate creation_date;
    private LocalDate artwork_date;

    @JsonIgnore
    @ManyToOne
    private Gallery gallery;

    @ManyToMany
    @JoinTable(
            name = "artwork_page_images",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;

    @ElementCollection(targetClass = ArtTag.class)
    @JoinTable(name = "artworks_tags", joinColumns = @JoinColumn(name = "artwork_id"))
    @Enumerated(EnumType.STRING)
    private List<ArtTag> tags;
}
