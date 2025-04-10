package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.UnitDetails;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    UnitDetails createUser(UnitDetails unitDetails);
    Optional<UnitDetails> getUserById(Long unitDetailsId);
    Optional<UnitDetails> getUserByUsername(String unitOfficialName);
    List<UnitDetails> getAllUnits();
    UnitDetails updateUser(Long unitDetailsId, UnitDetails unitOfficialName);
    void deleteUser(Long unitDetailsId);
}
