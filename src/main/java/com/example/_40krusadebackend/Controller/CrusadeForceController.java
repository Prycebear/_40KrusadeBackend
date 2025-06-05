package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Dto.CrusadeForceDto;
import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Service.CrusadeForceService;
import com.example._40krusadebackend.Translator.CrusadeForceTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/crusade-force")
@RequiredArgsConstructor
public class CrusadeForceController {

    private final CrusadeForceService crusadeForceService;


    @PostMapping
    public ResponseEntity<CrusadeForce> createCrusadeForce(@RequestBody CrusadeForce crusadeForceRequest) {
        CrusadeForce savedCrusadeForce = crusadeForceService.createCrusade(crusadeForceRequest);
        return ResponseEntity.ok(savedCrusadeForce);
    }//works

    @GetMapping
    public ResponseEntity<?> getAllCrusadeForces() {
        try {
            List<CrusadeForce> forces = crusadeForceService.getAllCrusades();

            if (forces.isEmpty()) {
                log.info("No crusade forces found in the database.");
                return ResponseEntity.ok(Collections.emptyList());
            }

            List<CrusadeForceDto> dtos = forces.stream()
                    .map(CrusadeForceTranslator::toDto)
                    .toList();

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            log.error("Error retrieving all crusade forces: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not retrieve crusade forces.");
        }
    }//works

    @GetMapping("/user-forces")
    public ResponseEntity<List<CrusadeForceDto>> getAllCrusadeForcesByUser() {
        List<CrusadeForceDto> crusadeForces = crusadeForceService.getCrusadesForCurrentUser().stream()
                .map(force -> CrusadeForceTranslator.toDto(force)).toList();
        return ResponseEntity.ok(crusadeForces);
    }//works

    @GetMapping("/{id}")
    public ResponseEntity<CrusadeForceDto> getCrusadeForceById(@PathVariable Integer id) {
        return crusadeForceService.getCrusadeById(id)
                .map(force -> ResponseEntity.ok(CrusadeForceTranslator.toDto(force)))
                .orElseGet(() -> {
                    CrusadeForce stub = new CrusadeForce();
                    stub.setCrusadeId(id);
                    stub.setCrusadeForceName("Stub Crusade");
                    stub.setBattlesPlayed(0);
                    stub.setBattlesWon(0);
                    stub.setRequisitionPoints(0);
                    stub.setSupplyLimit(0);
                    stub.setCrusadePoints(0);
                    stub.setUser(null); // Username will be null in stub
                    return ResponseEntity.ok(CrusadeForceTranslator.toDto(stub));
                });
    }//works

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrusadeForceById(@PathVariable Integer id) {
        boolean deleted = crusadeForceService.deleteCrusadeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }//works

    @PutMapping("/{id}")
    public ResponseEntity<CrusadeForceDto> updateCrusadeForce(@PathVariable Integer id, @RequestBody CrusadeForce crusadeForceUpdate) {
        return crusadeForceService.updateCrusadeForce(id, crusadeForceUpdate)
                .map(CrusadeForceTranslator::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }//works
}
