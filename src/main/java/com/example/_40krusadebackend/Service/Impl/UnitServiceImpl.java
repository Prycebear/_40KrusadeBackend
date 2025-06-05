package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Repository.UnitDetailsRepository;
import com.example._40krusadebackend.Service.UnitDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UnitServiceImpl implements UnitDetailsService {

    private final UnitDetailsRepository unitDetailsRepository;

    public UnitServiceImpl(UnitDetailsRepository unitDetailsRepository) {
        this.unitDetailsRepository = unitDetailsRepository;
    }

    @Override
    public Optional<UnitDetails> createUnit(UnitDetails unit) {
        Optional<UnitDetails> existingUnit = Optional.ofNullable(unitDetailsRepository.findByUnitOfficialName(unit.getUnitOfficialName()));
        if (existingUnit.isPresent()) {
            return existingUnit;
        }
        UnitDetails savedUnit = unitDetailsRepository.save(unit);
        return Optional.of(savedUnit);
    }

    @Override
    public Optional<UnitDetails> getUnitById(Long unitDetailsId) {
        return Optional.empty();
    }

    @Override
    public Optional<UnitDetails> findByUnitOfficialName(String unitOfficialName) {
        return Optional.empty();
    }

    @Override
    public List<UnitDetails> getAllUnits() {
        return unitDetailsRepository.findAll();
    }

    @Override
    public UnitDetails updateUnit(int unitDetailsId, UnitDetails updatedUnitDetails) {
        UnitDetails existingUnitDetails = unitDetailsRepository.findById(unitDetailsId)
                .orElseThrow(() -> new RuntimeException("Unit not found with id " + unitDetailsId));

        existingUnitDetails.setUnitOfficialName(updatedUnitDetails.getUnitOfficialName());
        existingUnitDetails.setUnitType(updatedUnitDetails.getUnitType());
        existingUnitDetails.setMovementDistance(updatedUnitDetails.getMovementDistance());
        existingUnitDetails.setToughnessValue(updatedUnitDetails.getToughnessValue());
        existingUnitDetails.setArmourSave(updatedUnitDetails.getArmourSave());
        existingUnitDetails.setMaxWounds(updatedUnitDetails.getMaxWounds());
        existingUnitDetails.setLeadershipValue(updatedUnitDetails.getLeadershipValue());
        existingUnitDetails.setObjectiveControl(updatedUnitDetails.getObjectiveControl());
        existingUnitDetails.setBaseSize(updatedUnitDetails.getBaseSize());
        existingUnitDetails.setFaction(updatedUnitDetails.getFaction());
        existingUnitDetails.setUnitAbility(updatedUnitDetails.getUnitAbility());
        existingUnitDetails.setUnitWargearOptions(updatedUnitDetails.getUnitWargearOptions());
        existingUnitDetails.setUnitComposition(updatedUnitDetails.getUnitComposition());
        existingUnitDetails.setUnitSize(updatedUnitDetails.getUnitSize());
        existingUnitDetails.setUnitCost(updatedUnitDetails.getUnitCost());

        return unitDetailsRepository.saveAndFlush(existingUnitDetails);
    }


    @Override
    public void deleteUnit(Long unitDetailsId) {

    }
}
