package com.example._40krusadebackend.Model;


import com.example._40krusadebackend.Enum.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIT_DETAILS_ID")
    private Integer unitDetailsId;

    @Column(name = "UNIT_OFFICIAL_NAME")
    private String unitOfficialName;

    @Column(name = "UNIT_OFFICIAL_NAME")
    private UnitType unitType;

    @Column(name = "MOVEMENT_DISTANCE")
    private int movementDistance;

    @Column(name = "TOUGHNESS_VALUE")
    private int toughnessValue;

    @Column(name = "UARMOUR_SAVE")
    private int armourSave;

    @Column(name = "MAX_WOUNDS")
    private int maxWounds;

    @Column(name = "LEADERSHIP_VALUE")
    private int leadershipValue;

    @Column(name = "OBJECTIVE_CONTROL")
    private int objectiveControl;

//    @ElementCollection
//    @Column(name = "WEAPONS_AVAILABLE")
//    private List<Weapon> weaponsAvailable;

    //Abilities

    //WargearOptions

    //UnitComposition

    @ElementCollection
    @Column(name = "WEAPON_ABILITIES")
    private List<String> weaponAbilities;
}
