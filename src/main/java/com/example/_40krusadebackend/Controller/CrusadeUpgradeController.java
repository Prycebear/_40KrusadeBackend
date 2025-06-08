package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.CrusadeUpgrade;
import com.example._40krusadebackend.Service.CrusadeUpgradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/upgrades")
public class CrusadeUpgradeController {

    private final CrusadeUpgradeService upgradeService;

    public CrusadeUpgradeController(CrusadeUpgradeService upgradeService) {
        this.upgradeService = upgradeService;
    }

    @PostMapping
    public ResponseEntity<CrusadeUpgrade> createUpgrade(@RequestBody CrusadeUpgrade upgrade) {
        return ResponseEntity.ok(upgradeService.createUpgrade(upgrade));
    }

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<List<CrusadeUpgrade>> getUpgradesByUnit(@PathVariable int unitId) {
        return ResponseEntity.ok(upgradeService.getUpgradesForUnit(unitId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpgrade(@PathVariable int id) {
        upgradeService.deleteUpgrade(id);
        return ResponseEntity.noContent().build();
    }
}
