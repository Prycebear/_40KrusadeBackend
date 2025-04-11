package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "WEAPONS")
@AllArgsConstructor
@NoArgsConstructor
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WEAPON_ID")
    private Integer weaponId;

    @Column(name = "WEAPON_RANGE")
    private String weaponRange;

    @Column(name = "ATTACKS_AVAILABLE")
    private String attacksAvailable;

    @Column(name = "ROLL_REQUIRED")
    private int rollRequired;

    @Column(name = "STRENGTH_VALUE")
    private int strengthValue;

    @Column(name = "ARMOUR_PIERCING_VALUE")
    private int armourPiercingValue;

    @Column(name = "DAMAGE_VALUE")
    private String damageValue;

    @ElementCollection
    @Column(name = "UNIT_KEYWORDS")
    private List<String> unitKeywords;
}
