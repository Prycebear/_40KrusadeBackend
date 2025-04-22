package com.example._40krusadebackend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ABILITIES")
@AllArgsConstructor
@NoArgsConstructor
public class Wargear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WARGEAR_ID")
    private Integer wargearId;

    @Column(name = "WARGEAR_NAME")
    private String wargearName;

    @Column(name = "WARGEAR_DESCRIPTION")
    private String wargearDescription;
}
