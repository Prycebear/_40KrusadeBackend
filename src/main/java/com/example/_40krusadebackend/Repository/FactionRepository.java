package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface FactionRepository extends JpaRepository<Faction, Integer> {
    Optional<Faction> getFactionByFactionName(String factionName);
}
