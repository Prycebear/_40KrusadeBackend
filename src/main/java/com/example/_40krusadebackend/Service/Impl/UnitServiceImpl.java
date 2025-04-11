package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Service.UnitDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitDetailsService {

    @Override
    public UnitDetails createUnit(UnitDetails unitDetails) {
        return null;
    }

    @Override
    public Optional<UnitDetails> getUnitById(Long unitDetailsId) {
        return Optional.empty();
    }

    @Override
    public Optional<UnitDetails> getUnitByUsername(String unitOfficialName) {
        return Optional.empty();
    }

    @Override
    public List<UnitDetails> getAllUnits() {
        return List.of();
    }

    @Override
    public UnitDetails updateUnit(Long unitDetailsId, UnitDetails unitOfficialName) {
        return null;
    }

    @Override
    public void deleteUnit(Long unitDetailsId) {

    }
}
