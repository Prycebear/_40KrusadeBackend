package com.example._40krusadebackend.Model;

import com.example._40krusadebackend.Model.User.AppUser;
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

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTION_ID")
    private Faction crusadeFaction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_OF_BATTLE_ID", referencedColumnName = "ORDER_OF_BATTLE_ID")
    private OrderOfBattle orderOfBattle;

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
