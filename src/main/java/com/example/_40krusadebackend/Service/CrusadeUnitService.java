package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.CrusadeUnit;

import java.util.List;
import java.util.Optional;

public interface CrusadeUnitService {

    CrusadeUnit createCrusadeUnit(Integer orderOfBattleId, CrusadeUnit crusadeUnit);

    List<CrusadeUnit> getUnitsByOrderOfBattle(Integer orderOfBattleId);

    Optional<CrusadeUnit> getCrusadeUnitById(Integer id);

    void deleteCrusadeUnit(Integer id);
}
