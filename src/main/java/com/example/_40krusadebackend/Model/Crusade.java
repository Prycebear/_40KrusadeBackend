package com.example._40krusadebackend.Model;

import com.example._40krusadebackend.Model.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "CRUSADE")
@AllArgsConstructor
@NoArgsConstructor
public class Crusade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRUSADE_ID")
    private Integer crusadeId;

    @Column(name = "CRUSADE_NAME")
    private String crusadeName;

    @Column(name = "CRUSADE_DESCRIPTION")
    private String crusadeDescription;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppUser owner;

    @OneToMany(mappedBy = "crusade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CrusadeForce> forces;
}
