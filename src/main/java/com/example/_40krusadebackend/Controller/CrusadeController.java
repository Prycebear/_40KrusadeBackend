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
    public ResponseEntity<Crusade> createCrusade(@RequestBody Crusade crusade) {
        return ResponseEntity.ok(crusadeService.createCrusade(crusade));
    }

    @GetMapping
    public ResponseEntity<List<Crusade>> getAllCrusadesForUser() {
        return ResponseEntity.ok(crusadeService.getCrusadesForCurrentUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Crusade> getCrusadeById(@PathVariable Integer id) {
        return crusadeService.getCrusadeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrusade(@PathVariable Integer id) {
        if (crusadeService.deleteCrusadeById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
