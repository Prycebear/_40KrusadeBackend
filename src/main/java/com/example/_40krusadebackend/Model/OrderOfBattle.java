package com.example._40krusadebackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ORDER_OF_BATTLE")
@AllArgsConstructor
@NoArgsConstructor
public class OrderOfBattle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OF_BATTLE_ID")
    private Integer orderOfBattleId;

    @JsonIgnore
    @OneToOne(mappedBy = "orderOfBattle")
    private Crusade crusade;

    @OneToMany(mappedBy = "orderOfBattle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CrusadeUnit> units = new ArrayList<>();
}
