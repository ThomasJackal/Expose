package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ticketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate creation_date;
    private LocalDateTime closing_datetime;

    @JsonIgnore
    @OneToOne
    private Event event;

    @OneToMany(mappedBy = "ticketing", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TicketType> ticketTypes;

    public Ticketing() {
    }

    public Ticketing(LocalDateTime closing_datetime, List<TicketType> ticketTypes) {
        this.closing_datetime = closing_datetime;
        this.ticketTypes = ticketTypes;
    }

    public void updateTicketTypes() {
        for (TicketType ticketType : ticketTypes) {
            ticketType.setTicketing(this);
        }
    }

    //region Getters/Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDateTime getClosing_datetime() {
        return closing_datetime;
    }

    public void setClosing_datetime(LocalDateTime closing_datetime) {
        this.closing_datetime = closing_datetime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    //endregion
}
