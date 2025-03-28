package fr.eql.ai116.duron.thomas.art.connect.entity;

import fr.eql.ai116.duron.thomas.art.connect.security.entity.Role;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Artist extends User {

    private String displayed_name;
    private String description;
    private String contact;
    private String location;
    private String profession;
    private LocalDate upgrade_date;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "artist")
    private List<Patronage> patrons;

    @OneToMany(mappedBy = "artist")
    private List<ArtistParticipation> participations;

    @OneToMany(mappedBy = "owner")
    private List<Gallery> galleries;

    @ManyToMany
    @JoinTable(
            name = "user_followed_artists",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> followers;

    @ManyToMany
    @JoinTable(
            name = "artist_page_images",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;


    public Artist(User user, String displayed_name, String description, String contact, String location, String profession, AccountType accountType) {
        super(user.getUsername(), user.getPassword(), user.getRoles(), user.getEmail(), user.getFirstname(), user.getLastname());
        this.displayed_name = displayed_name;
        this.description = description;
        this.contact = contact;
        this.location = location;
        this.profession = profession;
        this.accountType = accountType;

        this.upgrade_date = LocalDate.now();

        getRoles().add(new Role(RoleName.ARTIST));

        this.id = user.getId();
    }
}
