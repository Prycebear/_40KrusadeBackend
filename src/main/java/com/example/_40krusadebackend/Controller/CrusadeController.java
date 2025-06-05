package com.example._40krusadebackend.Controller;

import com.example._40krusadebackend.Dto.AppUserUsernameDto;
import com.example._40krusadebackend.Dto.CrusadeDto;
import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Service.CrusadeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
    }//works

    @GetMapping
    public ResponseEntity<List<CrusadeDto>> getAllCrusades() {
        List<CrusadeDto> dtos = crusadeService.getAllCrusades().stream()
                .map(crusade -> objectMapper.convertValue(crusade, CrusadeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    } //works

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Crusade>> getAllCrusadesForUser(@PathVariable Long userId) {
        List<Crusade> crusades = crusadeService.getCrusadesByUserId(userId);
        return ResponseEntity.ok(crusades);
    }//works

    @GetMapping("/{id}")
    public ResponseEntity<CrusadeDto> getCrusadeById(@PathVariable Integer id) {
        return crusadeService.getCrusadeById(id)
                .map(crusade -> {
                    AppUserUsernameDto ownerDto = new AppUserUsernameDto(crusade.getOwner().getUsername());
                    CrusadeDto dto = new CrusadeDto(
                            crusade.getCrusadeId(),
                            crusade.getCrusadeName(),
                            crusade.getCrusadeDescription(),
                            ownerDto,
                            crusade.getForces()
                    );
                    return ResponseEntity.ok(dto);
                })
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
