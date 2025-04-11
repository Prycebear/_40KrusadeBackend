package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.UnitDetails;

import java.util.List;
import java.util.Optional;

public interface UnitDetailsService {
    UnitDetails createUnit(UnitDetails unitDetails);
    Optional<UnitDetails> getUnitById(Long unitDetailsId);
    Optional<UnitDetails> getUnitByUsername(String unitOfficialName);
    List<UnitDetails> getAllUnits();
    UnitDetails updateUnit(Long unitDetailsId, UnitDetails unitOfficialName);
    void deleteUnit(Long unitDetailsId);
}
