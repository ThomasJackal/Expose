package fr.eql.ai116.duron.thomas.art.connect.entity;

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

    @ManyToOne
    private Programation programation;
}
