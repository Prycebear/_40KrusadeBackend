package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.Faction;
import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Service.Impl.FactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faction")
public class FactionController {

    private final FactionServiceImpl factionService;


    public FactionController(FactionServiceImpl factionService) {
        this.factionService = factionService;
    }

    @PostMapping("/faction/new-faction")
    public ResponseEntity<Faction> createFaction(@RequestBody Faction faction) {
        Faction createdFaction = factionService.createFaction(faction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faction> getFactionById(@PathVariable Long id) {
        return factionService.getFactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by username
    @GetMapping("/faction-name/{factionName}")
    public ResponseEntity<Faction> getFactionByFactionName(@PathVariable String factionName) {
        return factionService.getFactionByFactionName(factionName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<Faction>> getAllFactions() {
        List<Faction> units = factionService.getAllFactions();
        return ResponseEntity.ok(units);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<Faction> updateFactions(@PathVariable Long id, @RequestBody Faction factionDetails) {
        try {
            Faction updated = factionService.updateFaction(id, factionDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        factionService.deleteFaction(id);
        return ResponseEntity.noContent().build();
    }
}
