package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Service.CrusadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crusades")
@RequiredArgsConstructor
public class CrusadeController {

    private final CrusadeService crusadeService;

    @PostMapping
    public ResponseEntity<Crusade> createCrusade(@RequestBody Crusade crusadeRequest) {
        Crusade savedCrusade = crusadeService.createCrusade(crusadeRequest);
        return ResponseEntity.ok(savedCrusade);
    }

    @GetMapping
    public ResponseEntity<List<Crusade>> getAllCrusades() {
        List<Crusade> crusades = crusadeService.getCrusadesForCurrentUser();
        return ResponseEntity.ok(crusades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crusade> getCrusadeById(@PathVariable Integer id) {
        return crusadeService.getCrusadeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    Crusade stub = new Crusade();
                    stub.setCrusadeId(id);
                    stub.setCrusadeForceName("Stub Crusade");
                    stub.setBattlesPlayed(0);
                    stub.setBattlesWon(0);
                    stub.setRequisitionPoints(0);
                    stub.setSupplyLimit(0);
                    stub.setCrusadePoints(0);

                    return ResponseEntity.ok(stub);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrusadeById(@PathVariable Integer id) {
        boolean deleted = crusadeService.deleteCrusadeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Crusade> updateCrusade(@PathVariable Integer id, @RequestBody Crusade crusadeUpdate) {
        return crusadeService.getCrusadeById(id)
                .map(existing -> {
                    existing.setCrusadeForceName(crusadeUpdate.getCrusadeForceName());
                    existing.setCrusadeFaction(crusadeUpdate.getCrusadeFaction());
                    existing.setOrderOfBattle(crusadeUpdate.getOrderOfBattle());
                    existing.setBattlesPlayed(crusadeUpdate.getBattlesPlayed());
                    existing.setBattlesWon(crusadeUpdate.getBattlesWon());
                    existing.setRequisitionPoints(crusadeUpdate.getRequisitionPoints());
                    existing.setSupplyLimit(crusadeUpdate.getSupplyLimit());
                    existing.setCrusadePoints(crusadeUpdate.getCrusadePoints());

                    Crusade updated = crusadeService.createCrusade(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
