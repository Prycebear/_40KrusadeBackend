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
public class Detachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETACHMENT_ID")
    private Integer detachmentId;

    @Column(name = "DETACHMENT_NAME")
    private String detachmentName;

    @Column(name = "DETACHMENT_DESCRIPTION")
    private String detachmentDescription;

    @Column(name = "DETACHMENT_RULES")
    private String detachmentCost;

//    @Column(name = "DETACHMENT_STRATAGEMS")
//    private String detachmentStratagems;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Faction detachmentFaction;
}
