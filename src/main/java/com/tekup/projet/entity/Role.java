package com.tekup.projet.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true, columnDefinition = "VARCHAR(50) DEFAULT 'Employe'")
    private String name;
}