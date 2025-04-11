package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ABILITIES")
@AllArgsConstructor
@NoArgsConstructor
public class Abilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ABILITY_ID")
    private Integer abilityId;

    @Column(name = "ABILITY_NAME")
    private String abilityName;

    @Column(name = "ABILITY_DESCRIPTION")
    private String abilityDescription;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "ABILITY_TYPE")
//    private enum abilityType;

}
