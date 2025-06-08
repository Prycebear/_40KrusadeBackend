package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Dto.AppUserUsernameDto;
import com.example._40krusadebackend.Dto.CrusadeDto;
import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Service.CrusadeService;
import com.example._40krusadebackend.Translator.CrusadeForceTranslator;
import com.example._40krusadebackend.Translator.CrusadeTranslator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/crusades")
@RequiredArgsConstructor
public class CrusadeController {

    private final CrusadeService crusadeService;
    private final CrusadeTranslator crusadeTranslator;
    private final ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<Crusade> createCrusade(@RequestBody Crusade crusade) {
        return ResponseEntity.ok(crusadeService.createCrusade(crusade));
    }//works

    @GetMapping
    public ResponseEntity<List<CrusadeDto>> getAllCrusades() {
        return ResponseEntity.ok(crusadeService.getAllCrusades().stream()
                .map(crusade -> crusadeTranslator.crusadeToCrusadeDto(crusade))
                .collect(Collectors.toList()));
    } //works

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CrusadeDto>> getAllCrusadesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(crusadeService.getCrusadesByUserId(userId).stream()
                .map(crusade -> crusadeTranslator.crusadeToCrusadeDto(crusade))
                .collect(Collectors.toList()));
    }//works

    @GetMapping("/{id}")
    public ResponseEntity<CrusadeDto> getCrusadeById(@PathVariable Integer id) {
        return crusadeService.getCrusadeById(id)
                .map(crusadeTranslator::crusadeToCrusadeDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }//works for user


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrusade(@PathVariable Integer id) {
        try {
            if (crusadeService.deleteCrusadeById(id)) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error deleting crusade: " + ex.getMessage());
        }
    }//works
}
