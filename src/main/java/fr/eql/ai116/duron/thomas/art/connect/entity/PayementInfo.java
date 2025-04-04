package fr.eql.ai116.duron.thomas.art.connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class PayementInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate creation_date;

    private long cardNumbers;
    private String cardExpirationDate;
    private int CVV;

    @JsonIgnore
    @OneToOne
    private User user;


}
