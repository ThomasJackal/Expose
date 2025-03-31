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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Programation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate start_date;

    @JsonIgnore
    @OneToOne
    private Event event;

    @OneToMany(mappedBy = "programation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Slot> slots = new ArrayList<>();

    public Programation() {
    }

    public Programation(LocalDate start_date, List<Slot> slots) {
        this.start_date = start_date;
        this.slots = slots;
    }

    public void updateSlots() {
        for (Slot slot : slots) {
            slot.setProgramation(this);
        }
    }

    public LocalDate calculateEndDate() {
        int end = 0;
        for (Slot slot : slots) {
            if (slot.getDays_from_start() > end) end = slot.getDays_from_start();
        }
        return start_date.plusDays(end);
    }

    //region Getters/Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    //endregion
}
