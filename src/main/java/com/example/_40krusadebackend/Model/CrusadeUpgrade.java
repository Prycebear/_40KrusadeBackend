package com.example._40krusadebackend.Model;

import com.example._40krusadebackend.Enum.UpgradeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CRUSADE_UPGRADES")
@AllArgsConstructor
@NoArgsConstructor
public class CrusadeUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private UpgradeType type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CRUSADE_UNIT_ID")
    private CrusadeUnit crusadeUnit;
}
