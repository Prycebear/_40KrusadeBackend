package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Service.Impl.UnitServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/unit")
public class UnitController {

    private final UnitServiceImpl unitService;

    public UnitController(UnitServiceImpl unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<Optional<UnitDetails>> createUnit(@RequestBody UnitDetails unitDetails) {
        Optional<UnitDetails> createdUnit = unitService.createUnit(unitDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUnit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDetails> getUnitById(@PathVariable Long id) {
        return unitService.getUnitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-unit_name/{unitName}")
    public ResponseEntity<UnitDetails> getUnitByUnitName(@PathVariable String unitName) {
        return unitService.findByUnitOfficialName(unitName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UnitDetails>> getAllUnits() {
        List<UnitDetails> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDetails> updateUnit(@PathVariable int id, @RequestBody UnitDetails unitDetails) {
        try {
            UnitDetails updated = unitService.updateUnit(id, unitDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }
}
