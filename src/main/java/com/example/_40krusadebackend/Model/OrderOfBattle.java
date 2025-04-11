package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
