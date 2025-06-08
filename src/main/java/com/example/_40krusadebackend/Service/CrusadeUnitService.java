package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.CrusadeUnit;

import java.util.List;
import java.util.Optional;

public interface CrusadeUnitService {

    Optional<CrusadeUnit> createCrusadeUnit(Integer orderOfBattleId, CrusadeUnit crusadeUnit);

    List<CrusadeUnit> getUnitsByOrderOfBattle(Integer orderOfBattleId);

    Optional<CrusadeUnit> createCrusadeUnit(CrusadeUnit crusadeUnit);

    Optional<CrusadeUnit> getCrusadeUnitById(Integer id);

    List<CrusadeUnit> getAllCrusadeUnits();

    CrusadeUnit updateCrusadeUnit(Integer id, CrusadeUnit updatedCrusadeUnit);

    void deleteCrusadeUnit(Integer id);
}
