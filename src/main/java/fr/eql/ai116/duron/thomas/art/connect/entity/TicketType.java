package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private int max_places;
    private LocalDate creation_date;
    private double price_per_unit;

    @JsonIgnore
    @ManyToOne
    private Ticketing ticketing;

    public TicketType() {
    }

    public TicketType(String name, String description, int max_places, double price_per_unit) {
        this.name = name;
        this.description = description;
        this.max_places = max_places;
        this.price_per_unit = price_per_unit;
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

    public int getMax_places() {
        return max_places;
    }

    public void setMax_places(int max_places) {
        this.max_places = max_places;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public double getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public Ticketing getTicketing() {
        return ticketing;
    }

    public void setTicketing(Ticketing ticketing) {
        this.ticketing = ticketing;
    }

    //endregion
}
