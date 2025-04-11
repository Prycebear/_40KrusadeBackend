package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.Faction;
import com.example._40krusadebackend.Model.UnitDetails;

import java.util.List;
import java.util.Optional;

public interface FactionService {
    Faction createFaction(Faction faction);
    Optional<Faction> getFactionById(Long factionId);
    Optional<Faction> getFactionByFactionName(String factionName);
    List<Faction> getAllFactions();
    Faction updateFaction(Long factionId, Faction faction);
    void deleteFaction(Long factionId);
}
