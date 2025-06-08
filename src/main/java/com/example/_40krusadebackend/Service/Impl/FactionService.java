package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.Faction;
import com.example._40krusadebackend.Repository.FactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactionService implements com.example._40krusadebackend.Service.FactionService {

    private final FactionRepository factionRepository;

    public FactionService(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @Override
    public Faction createFaction(Faction faction) {
        return factionRepository.save(faction);
    }

    @Override
    public Optional<Faction> getFactionById(int factionId) {
        return factionRepository.findById(Math.toIntExact(factionId));
    }

    @Override
    public Optional<Faction> getFactionByFactionName(String unitOfficialName) {
        return factionRepository.getFactionByFactionName(unitOfficialName);
    }

    @Override
    public List<Faction> getAllFactions() {
        return factionRepository.findAll();
    }

    @Override
    public Faction updateFaction(int factionId, Faction unitOfficial) {
        Faction existingFaction = factionRepository.findById(factionId)
                .orElseThrow(() -> new EntityNotFoundException("Faction not found with ID: " + factionId));

        existingFaction.setFactionName(unitOfficial.getFactionName());
        return factionRepository.save(existingFaction);
    }

    @Override
    public void deleteFaction(int factionId) {
        factionRepository.findById(factionId)
                .ifPresentOrElse(
                        faction -> factionRepository.delete(faction),
                        () -> { throw new EntityNotFoundException("Faction not found with ID: " + factionId); }
                );
    }
}
