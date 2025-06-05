package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.UnitDetails;

import java.util.List;
import java.util.Optional;

public interface UnitDetailsService {
    Optional<UnitDetails> createUnit(UnitDetails unitDetails);
    Optional<UnitDetails> getUnitById(int unitDetailsId);
    Optional<UnitDetails> findByUnitOfficialName(String unitOfficialName);
    List<UnitDetails> getAllUnits();
    UnitDetails updateUnit(int unitDetailsId, UnitDetails unitOfficialName);
    void deleteUnit(int unitDetailsId);
}
