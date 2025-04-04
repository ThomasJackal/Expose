package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.Role;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    private List<Patronage> patrons;

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    private List<ArtistParticipation> participations;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Gallery> galleries;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_followed_artists",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> followers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_page_images",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;

    public Artist() {
    }

    public Artist(User user, String displayed_name, String description, String contact, String location, String profession, AccountType accountType) {
        super(user.getUsername(), user.getPassword(), user.getRoles(), user.getEmail(), user.getProfilePicture()==null?"":user.getProfilePicture().getImageLink());
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

    @Override
    public String toString() {
        return "Artist{" +
                "displayed_name='" + displayed_name + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", location='" + location + '\'' +
                ", profession='" + profession + '\'' +
                ", upgrade_date=" + upgrade_date +
                ", accountType=" + accountType +
                ", id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", creation_date=" + getCreation_date() +
                ", cloturation_date=" + getCloturation_date() +
                ", payementInfo=" + getPayementInfo() +
                " (Potential lazy informations not displayed)" +
                '}';

    }

    //region Getters/Setters

    public String getDisplayed_name() {
        return displayed_name;
    }

    public void setDisplayed_name(String displayed_name) {
        this.displayed_name = displayed_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public LocalDate getUpgrade_date() {
        return upgrade_date;
    }

    public void setUpgrade_date(LocalDate upgrade_date) {
        this.upgrade_date = upgrade_date;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Patronage> getPatrons() {
        return patrons;
    }

    public void setPatrons(List<Patronage> patrons) {
        this.patrons = patrons;
    }

    public List<ArtistParticipation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ArtistParticipation> participations) {
        this.participations = participations;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    //endregion
}
