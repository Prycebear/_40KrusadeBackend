package com.example._40krusadebackend.Model;


import com.example._40krusadebackend.Enum.UnitType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "UNIT_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIT_DETAILS_ID")
    private Integer unitDetailsId;

    @Column(name = "UNIT_OFFICIAL_NAME")
    private String unitOfficialName;

    @Column(name = "MOVEMENT_DISTANCE")
    private int movementDistance;

    @Column(name = "TOUGHNESS_VALUE")
    private int toughnessValue;

    @Column(name = "ARMOUR_SAVE")
    private int armourSave;

    @Column(name = "MAX_WOUNDS")
    private int maxWounds;

    @Column(name = "LEADERSHIP_VALUE")
    private int leadershipValue;

    @Column(name = "OBJECTIVE_CONTROL")
    private int objectiveControl;

    @Column(name = "BASE_SIZE")
    private int baseSize;

    @ManyToOne
    @JoinColumn(name = "FACTION_ID")
    private Faction faction;

    @Column(name = "UNIT_ABILITY")
    private String unitAbility;

    @Column(name = "UNIT_WARGEAR_OPTIONS")
    private String unitWargearOptions;

    @Column(name = "UNIT_COMPOSITION")
    private String unitComposition;

    @Column(name = "UNIT_SIZE")
    private int unitSize;

    @Column(name = "UNIT_COST")
    private int unitCost;
}
