package com.tekup.projet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Evaluations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employee", "formation"}) // Exclude relations to avoid fetching during toString
@EqualsAndHashCode(of = "id") // Use only 'id' for equals and hashCode methods
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    @Column(name = "date_of_evaluation", nullable = false)
    private LocalDate dateOfEvaluation;

    @Column(name = "score")
    private double score;

    @Column(name = "comments", length = 500)
    private String comments;
}
