package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int days_from_start;
    private LocalTime start_time;
    private LocalTime end_time;

    @JsonIgnore
    @ManyToOne
    private Programation programation;

    public Slot() {
    }

    public Slot(int days_from_start, LocalTime start_time, LocalTime end_time) {
        this.days_from_start = days_from_start;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    //region Getters/Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDays_from_start() {
        return days_from_start;
    }

    public void setDays_from_start(int days_from_start) {
        this.days_from_start = days_from_start;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public Programation getProgramation() {
        return programation;
    }

    public void setProgramation(Programation programation) {
        this.programation = programation;
    }

    //endregion
}
