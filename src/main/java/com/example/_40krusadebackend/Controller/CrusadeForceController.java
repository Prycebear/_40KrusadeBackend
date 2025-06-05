package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Service.CrusadeForceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    }

    @GetMapping
    public ResponseEntity<List<CrusadeForce>> getAllCrusadeForces() {
        List<CrusadeForce> crusadeForces = crusadeForceService.getCrusadesForCurrentUser();
        return ResponseEntity.ok(crusadeForces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrusadeForce> getCrusadeForceById(@PathVariable Integer id) {
        return crusadeForceService.getCrusadeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    CrusadeForce stub = new CrusadeForce();
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
    public ResponseEntity<Void> deleteCrusadeForceById(@PathVariable Integer id) {
        boolean deleted = crusadeForceService.deleteCrusadeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrusadeForce> updateCrusadeForce(@PathVariable Integer id, @RequestBody CrusadeForce crusadeForceUpdate) {
        return crusadeForceService.getCrusadeById(id)
                .map(existing -> {
                    existing.setCrusadeForceName(crusadeForceUpdate.getCrusadeForceName());
                    existing.setCrusadeFaction(crusadeForceUpdate.getCrusadeFaction());
                    existing.setOrderOfBattle(crusadeForceUpdate.getOrderOfBattle());
                    existing.setBattlesPlayed(crusadeForceUpdate.getBattlesPlayed());
                    existing.setBattlesWon(crusadeForceUpdate.getBattlesWon());
                    existing.setRequisitionPoints(crusadeForceUpdate.getRequisitionPoints());
                    existing.setSupplyLimit(crusadeForceUpdate.getSupplyLimit());
                    existing.setCrusadePoints(crusadeForceUpdate.getCrusadePoints());

                    CrusadeForce updated = crusadeForceService.createCrusade(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/orderofbattle")
    public ResponseEntity<?> addOrderOfBattleToCrusadeForce(@PathVariable Integer id,
                                                            @RequestBody OrderOfBattle orderOfBattle) {
        try {
            CrusadeForce updatedForce = crusadeForceService.addOrderOfBattleToCrusadeForce(id, orderOfBattle);
            return ResponseEntity.ok(updatedForce);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding OrderOfBattle: " + e.getMessage());
        }
    }

}
