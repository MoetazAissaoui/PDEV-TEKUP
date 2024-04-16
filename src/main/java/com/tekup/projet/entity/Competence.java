package com.tekup.projet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Competences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id") // Base equals and hashCode methods on the 'id' field
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "description", nullable = true, length = 500)
    private String description;
}