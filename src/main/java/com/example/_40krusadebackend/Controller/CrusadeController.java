package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Dto.Auth.CrusadeDto;
import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Service.CrusadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/crusades")
@RequiredArgsConstructor
public class CrusadeController {

    private final CrusadeService crusadeService;
    private final ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<Crusade> createCrusade(@RequestBody Crusade crusade) {
        return ResponseEntity.ok(crusadeService.createCrusade(crusade));
    }

    @GetMapping
    public ResponseEntity<List<CrusadeDto>> getAllCrusades() {
        List<CrusadeDto> dtos = crusadeService.getAllCrusades().stream()
                .map(crusade -> objectMapper.convertValue(crusade, CrusadeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Crusade>> getAllCrusadesForUser(@PathVariable Long userId) {
        List<Crusade> crusades = crusadeService.getCrusadesByUserId(userId);
        return ResponseEntity.ok(crusades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrusadeDto> getCrusadeById(@PathVariable Integer id) {
        return crusadeService.getCrusadeById(id)
                .map(crusade -> ResponseEntity.ok(new CrusadeDto(
                        crusade.getCrusadeId(),
                        crusade.getCrusadeName(),
                        crusade.getCrusadeDescription(),
                        crusade.getOwner().getUsername(),
                        crusade.getForces()
                )))
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
