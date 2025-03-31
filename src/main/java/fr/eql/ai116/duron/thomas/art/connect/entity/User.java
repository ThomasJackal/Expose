package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.Role;
import fr.eql.ai116.duron.thomas.art.connect.security.entity.SecuredUser;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends SecuredUser {

    private String email;
    private LocalDate creation_date;
    private LocalDate cloturation_date;
    private String firstname;
    private String lastname;

    @JsonIgnore
    @OneToOne
    private PayementInfo payementInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "patron")
    private List<Patronage> patronnages = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Ticket> tickets = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private List<Artist> followedArtists = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers")
    private List<Event> followedEvents = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, List<Role> roles, String email, String firstname, String lastname) {
        super(username,password,roles);
        this.email = email;
        this.creation_date = LocalDate.now();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + email + '\'' +
                ", creation_date=" + creation_date +
                ", cloturation_date=" + cloturation_date +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", payementInfo=" + payementInfo +
                " (Potential lazy informations not displayed)" +
                '}';
    }

    //region Getters/Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public LocalDate getCloturation_date() {
        return cloturation_date;
    }

    public void setCloturation_date(LocalDate cloturation_date) {
        this.cloturation_date = cloturation_date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public PayementInfo getPayementInfo() {
        return payementInfo;
    }

    public void setPayementInfo(PayementInfo payementInfo) {
        this.payementInfo = payementInfo;
    }

    public List<Patronage> getPatronnages() {
        return patronnages;
    }

    public void setPatronnages(List<Patronage> patronnages) {
        this.patronnages = patronnages;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Artist> getFollowedArtists() {
        return followedArtists;
    }

    public void setFollowedArtists(List<Artist> followedArtists) {
        this.followedArtists = followedArtists;
    }

    public List<Event> getFollowedEvents() {
        return followedEvents;
    }

    public void setFollowedEvents(List<Event> followedEvents) {
        this.followedEvents = followedEvents;
    }
    //endregion
}
