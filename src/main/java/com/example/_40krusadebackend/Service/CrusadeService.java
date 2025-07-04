package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.Crusade;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface CrusadeService {
    Crusade createCrusade(Crusade crusade);

    List<Crusade> getAllCrusades();

    List<Crusade> getCrusadesByUserId(Long userId);
    Optional<Crusade> getCrusadeById(Integer id);

    boolean deleteCrusadeById(Integer id) throws AccessDeniedException;
}
