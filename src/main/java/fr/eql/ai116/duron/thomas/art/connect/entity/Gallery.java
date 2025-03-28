package fr.eql.ai116.duron.thomas.art.connect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private LocalDate creation_date;
    private LocalDate archivation_date;

    @ManyToOne
    private Artist owner;

    @OneToMany(mappedBy = "gallery")
    private List<Artwork> artworks;
}
