package com.example._40krusadebackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "FACTIONS")
@AllArgsConstructor
@NoArgsConstructor
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACTION_ID")
    private Integer factionId;

    @Column(name = "FACTION_NAME")
    private String factionName;

    @Column(name = "FACTION_RULE")
    private String factionRule;

    @JsonIgnore
    @OneToMany(mappedBy = "crusadeFaction", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "CRUSADE_FACTIONS")
    private List<CrusadeForce> crusadeForces;
}
