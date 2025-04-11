package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ENHANCEMENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Enhancements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENHANCEMENT_ID")
    private Integer enhancementId;

    @Column(name = "ENHANCEMENT_NAME")
    private String enhancementName;

    @Column(name = "ENHANCEMENT_DESCRIPTION")
    private String enhancementDescription;

    @Column(name = "ENHANCEMENT_COST")
    private int enhancementCost;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Faction enhancementFaction;

}
