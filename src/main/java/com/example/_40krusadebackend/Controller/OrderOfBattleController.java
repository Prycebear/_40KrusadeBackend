package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Dto.OrderOfBattleDto;
import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Service.OrderOfBattleService;
import com.example._40krusadebackend.Translator.OrderOfBattleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-of-battle")
@RequiredArgsConstructor
@Slf4j
public class OrderOfBattleController {

    private final OrderOfBattleService orderOfBattleService;

    @PostMapping
    public ResponseEntity<?> createOrderOfBattle(@RequestBody OrderOfBattle orderOfBattle) {
        try {
            OrderOfBattle created = orderOfBattleService.createOrderOfBattle(orderOfBattle);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            log.error("Failed to create Order of Battle: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not create Order of Battle.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderOfBattles() {
        try {
            List<OrderOfBattle> all = orderOfBattleService.getAllOrderOfBattles();
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            log.error("Failed to fetch all Orders of Battle: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not fetch Orders of Battle.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderOfBattleById(@PathVariable Integer id) {
        try {
            Optional<OrderOfBattle> ob = orderOfBattleService.getOrderOfBattleById(id);
            return ob.map(orderOfBattle -> {
                        OrderOfBattleDto dto = OrderOfBattleMapper.toDto(orderOfBattle);
                        log.info("Successfully retrieved Order of Battle with ID {}", id);
                        return ResponseEntity.ok(dto);
                    })
                    .orElseGet(() -> {
                        log.warn("Order of Battle not found with ID {}", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            log.error("Error fetching Order of Battle by ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not fetch Order of Battle.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderOfBattle(
            @PathVariable Integer id,
            @RequestBody OrderOfBattle updatedOrderOfBattle) {
        try {
            OrderOfBattle updated = orderOfBattleService.updateOrderOfBattle(id, updatedOrderOfBattle);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            log.warn("Order of Battle not found with ID {} for update.", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Failed to update Order of Battle ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not update Order of Battle.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderOfBattle(@PathVariable Integer id) {
        try {
            boolean deleted = orderOfBattleService.deleteOrderOfBattle(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                log.warn("Order of Battle not found for deletion with ID {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Failed to delete Order of Battle ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not delete Order of Battle.");
        }
    }

    @PostMapping("/{id}/units")
    public ResponseEntity<?> addUnitToOrderOfBattle(@PathVariable Integer id,
                                                    @RequestBody CrusadeUnit unit) {
        try {
            CrusadeUnit created = orderOfBattleService.addUnitToOrderOfBattle(id, unit);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error adding unit to Order of Battle: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Internal server error occurred.");
        }
    }

    @GetMapping("/crusade-force/{crusadeForceId}/order-of-battle")
    public ResponseEntity<?> getOrderOfBattleByCrusadeForce(@PathVariable Integer crusadeForceId) {
        try {
            Optional<OrderOfBattle> obOpt = orderOfBattleService.getOrderOfBattleByCrusadeForceId(crusadeForceId);

            if (obOpt.isPresent()) {
                OrderOfBattleDto dto = OrderOfBattleMapper.toDto(obOpt.get());
                log.info("Retrieved Order of Battle for Crusade Force ID {}", crusadeForceId);
                return ResponseEntity.ok(dto);
            } else {
                log.warn("Order of Battle not found for Crusade Force ID {}", crusadeForceId);
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            log.error("Error retrieving Order of Battle for Crusade Force ID {}: {}", crusadeForceId, e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Could not fetch Order of Battle.");
        }
    }
}
