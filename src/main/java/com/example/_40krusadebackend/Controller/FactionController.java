package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.Faction;
import com.example._40krusadebackend.Service.Impl.FactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/faction")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @PostMapping
    public ResponseEntity<Faction> saveFaction(@RequestBody Faction faction) {
        log.info("Received request to create faction: {}", faction.getFactionName());
        try {
            Faction createdFaction = factionService.createFaction(faction);
            log.info("Faction created successfully with ID: {}", createdFaction.getFactionId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFaction);
        } catch (Exception e) {
            log.error("Error creating faction: {}", faction.getFactionName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faction> getFactionById(@PathVariable int id) {
        log.info("Received request to get faction by ID: {}", id);
        return factionService.getFactionById(id)
                .map(faction -> {
                    log.info("Faction found: {}", faction.getFactionName());
                    return ResponseEntity.ok(faction);
                })
                .orElseGet(() -> {
                    log.warn("Faction not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/faction-name/{factionName}")
    public ResponseEntity<Faction> getFactionByFactionName(@PathVariable String factionName) {
        log.info("Received request to get faction by name: {}", factionName);
        return factionService.getFactionByFactionName(factionName)
                .map(faction -> {
                    log.info("Faction found: {}", faction.getFactionName());
                    return ResponseEntity.ok(faction);
                })
                .orElseGet(() -> {
                    log.warn("Faction not found with name: {}", factionName);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping
    public ResponseEntity<List<Faction>> getAllFactions() {
        log.info("Received request to get all factions");
        List<Faction> factions = factionService.getAllFactions();
        log.info("Returning {} factions", factions.size());
        return ResponseEntity.ok(factions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faction> updateFactions(@PathVariable int id, @RequestBody Faction factionDetails) {
        log.info("Received request to update faction ID: {}", id);
        try {
            Faction updated = factionService.updateFaction(id, factionDetails);
            log.info("Faction updated successfully: ID {}", updated.getFactionId());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            log.warn("Faction not found or error updating faction ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        log.info("Received request to delete faction ID: {}", id);
        try {
            factionService.deleteFaction(id);
            log.info("Faction deleted successfully: ID {}", id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.warn("Faction not found or error deleting faction ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }
}
