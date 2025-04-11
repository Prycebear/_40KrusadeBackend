package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CRUSADES")
@AllArgsConstructor
@NoArgsConstructor
public class Crusade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRUSADE_ID")
    private Integer crusadeId;

    @ManyToOne(fetch = FetchType.LAZY)  // Many crusades can be associated with one faction
    @JoinColumn(name = "FACTION_ID")
    private Faction crusadeFaction;

    @Column(name = "CRUSADE_FORCE_NAME")
    private String crusadeForceName;

    @Column(name = "BATTLES_PLAYED")
    private int battlesPlayed;

    @Column(name = "BATTLES_WON")
    private int battlesWon;

    @Column(name = "REQUISITION_POINTS")
    private int requisitionPoints;

    @Column(name = "SUPPLY_LIMIT")
    private int supplyLimit;

    @Column(name = "CRUSADE_POINTS")
    private int crusadePoints;
}
