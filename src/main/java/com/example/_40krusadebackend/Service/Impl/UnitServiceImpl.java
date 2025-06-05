package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Repository.UnitDetailsRepository;
import com.example._40krusadebackend.Service.UnitDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitServiceImpl implements UnitDetailsService {

    private final UnitDetailsRepository unitDetailsRepository;

    @Override
    public Optional<UnitDetails> createUnit(UnitDetails unit) {
        Optional<UnitDetails> existingUnit = Optional.ofNullable(unitDetailsRepository.findByUnitOfficialName(unit.getUnitOfficialName()));
        if (existingUnit.isPresent()) {
            log.info("Unit already exists with name {}", unit.getUnitOfficialName());
            return existingUnit;
        }
        UnitDetails saved = unitDetailsRepository.save(unit);
        log.info("Created new unit with ID {}", saved.getUnitDetailsId());
        return Optional.of(saved);
    }

    @Override
    public Optional<UnitDetails> getUnitById(int unitDetailsId) {
        return unitDetailsRepository.findById(unitDetailsId);
    }

    @Override
    public Optional<UnitDetails> findByUnitOfficialName(String unitOfficialName) {
        return Optional.ofNullable(unitDetailsRepository.findByUnitOfficialName(unitOfficialName));
    }

    @Override
    public List<UnitDetails> getAllUnits() {
        return unitDetailsRepository.findAll();
    }

    @Override
    public UnitDetails updateUnit(int unitDetailsId, UnitDetails updatedUnitDetails) {
        return unitDetailsRepository.findById(unitDetailsId).map(existing -> {
            existing.setUnitOfficialName(updatedUnitDetails.getUnitOfficialName());
            existing.setMovementDistance(updatedUnitDetails.getMovementDistance());
            existing.setToughnessValue(updatedUnitDetails.getToughnessValue());
            existing.setArmourSave(updatedUnitDetails.getArmourSave());
            existing.setMaxWounds(updatedUnitDetails.getMaxWounds());
            existing.setLeadershipValue(updatedUnitDetails.getLeadershipValue());
            existing.setObjectiveControl(updatedUnitDetails.getObjectiveControl());
            existing.setBaseSize(updatedUnitDetails.getBaseSize());
            existing.setFaction(updatedUnitDetails.getFaction());
            existing.setUnitAbility(updatedUnitDetails.getUnitAbility());
            existing.setUnitWargearOptions(updatedUnitDetails.getUnitWargearOptions());
            existing.setUnitComposition(updatedUnitDetails.getUnitComposition());
            existing.setUnitSize(updatedUnitDetails.getUnitSize());
            existing.setUnitCost(updatedUnitDetails.getUnitCost());
            UnitDetails saved = unitDetailsRepository.save(existing);
            log.info("Updated unit with ID {}", saved.getUnitDetailsId());
            return saved;
        }).orElseThrow(() -> {
            log.warn("Attempted to update non-existent unit with ID {}", unitDetailsId);
            return new EntityNotFoundException("Unit not found with id " + unitDetailsId);
        });
    }

    @Override
    public void deleteUnit(int unitDetailsId) {
        if (!unitDetailsRepository.existsById(unitDetailsId)) {
            log.warn("Attempted to delete non-existent unit with ID {}", unitDetailsId);
            throw new EntityNotFoundException("Unit not found with ID: " + unitDetailsId);
        }
        unitDetailsRepository.deleteById(unitDetailsId);
        log.info("Deleted unit with ID {}", unitDetailsId);
    }
}
