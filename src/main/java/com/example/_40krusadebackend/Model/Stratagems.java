package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "UNIT_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class Stratagems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STRATAGEM_ID")
    private Integer stratagemId;

    @Column(name = "STRATAGEM_NAME")
    private String stratagemName;

    @Column(name = "STRATAGEM_DESCRIPTION")
    private String stratagemDescription;
}
