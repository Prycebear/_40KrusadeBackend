package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Service.Impl.UnitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit")
public class UnitController {

    private final UnitServiceImpl unitService;

    public UnitController(UnitServiceImpl unitService) {
        this.unitService = unitService;
    }

    // Create user
    @PostMapping
    public ResponseEntity<UnitDetails> createUnit(@RequestBody UnitDetails unitDetails) {
        UnitDetails createdUnit = unitService.createUnit(unitDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUnit);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UnitDetails> getUnitById(@PathVariable Long id) {
        return unitService.getUnitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user by username
    @GetMapping("/by-unit_name/{unitName}")
    public ResponseEntity<UnitDetails> getUnitByUnitName(@PathVariable String unitName) {
        return unitService.getUnitByUsername(unitName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UnitDetails>> getAllUsers() {
        List<UnitDetails> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UnitDetails> updateUser(@PathVariable Long id, @RequestBody UnitDetails unitDetails) {
        try {
            UnitDetails updated = unitService.updateUnit(id, unitDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }

}
