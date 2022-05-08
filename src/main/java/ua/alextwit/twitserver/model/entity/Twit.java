package ua.alextwit.twitserver.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Table
@Data
@Accessors(chain = true)
public class Twit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String message;
    Boolean important;
    Boolean heart;
    @Column(name = "date_of_creation")
    LocalDate dateOfCreation;
}
