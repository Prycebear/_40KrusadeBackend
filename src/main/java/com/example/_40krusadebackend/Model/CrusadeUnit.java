package com.example._40krusadebackend.Model;

import com.example._40krusadebackend.Enum.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "CRUSADE_UNITS")
@AllArgsConstructor
@NoArgsConstructor
public class CrusadeUnit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRUSADE_UNIT_ID")
    private Integer crusadeUnitId;

    @Column(name = "CRUSADE_UNIT_NAME")
    private String crusadeUnitName;

    @Column(name = "UNIT_SIZE")
    private int unitSize;

    @ManyToMany
    @Column(name = "EQUIPPED_ENHANCMENTS")
    private List<Enhancements> equippedEnhancements;

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
}
