package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Service.Impl.OrderOfBattleServiceImpl;
import com.example._40krusadebackend.Service.OrderOfBattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-of-battles")
@RequiredArgsConstructor
public class OrderOfBattleController {

    private final OrderOfBattleServiceImpl orderOfBattleService;

    @PostMapping
    public ResponseEntity<OrderOfBattle> createOrderOfBattle(@RequestBody OrderOfBattle orderOfBattle) {
        OrderOfBattle created = orderOfBattleService.createOrderOfBattle(orderOfBattle);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOfBattle> getOrderOfBattleById(@PathVariable Integer id) {
        return orderOfBattleService.getOrderOfBattleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrderOfBattle>> getAllOrderOfBattles() {
        return ResponseEntity.ok(orderOfBattleService.getAllOrderOfBattles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderOfBattle> updateOrderOfBattle(@PathVariable Integer id, @RequestBody OrderOfBattle orderOfBattle) {
        try {
            OrderOfBattle updated = orderOfBattleService.updateOrderOfBattle(id, orderOfBattle);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderOfBattle(@PathVariable Integer id) {
        if (orderOfBattleService.deleteOrderOfBattle(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
