package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.OrderOfBattle;

import java.util.List;
import java.util.Optional;

public interface CrusadeForceService {

    CrusadeForce createCrusade(CrusadeForce crusadeForce);

    List<CrusadeForce> getAllCrusades();

    List<CrusadeForce> getCrusadesForCurrentUser();

    Optional<CrusadeForce> getCrusadeById(Integer id);

    Optional<CrusadeForce> updateCrusadeForce(Integer id, CrusadeForce updates);

    boolean deleteCrusadeById(Integer id);

}
