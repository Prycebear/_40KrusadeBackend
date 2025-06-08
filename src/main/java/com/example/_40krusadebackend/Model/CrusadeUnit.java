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
public class CrusadeUnit extends UnitDetails {

    @Column(name = "CRUSADE_UNIT_NAME")
    private String crusadeUnitName;

    @Column(name = "XP")
    private int experience;

    @Column(name = "KILLS")
    private int kills;

    @OneToMany(mappedBy = "crusadeUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CrusadeUpgrade> upgrades;

    @ManyToOne
    @JoinColumn(name = "ORDER_OF_BATTLE_ID")
    private OrderOfBattle orderOfBattle;
}
