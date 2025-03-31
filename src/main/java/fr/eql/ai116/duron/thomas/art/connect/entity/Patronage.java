package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Patronage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double amount_per_month;
    private LocalDateTime start;
    private LocalDateTime end;

    @JsonIgnore
    @ManyToOne
    private User patron;

    @JsonIgnore
    @ManyToOne
    private Artist artist;
}
