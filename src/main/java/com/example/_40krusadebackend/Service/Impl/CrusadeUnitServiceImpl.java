package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Repository.CrusadeUnitRepository;
import com.example._40krusadebackend.Service.CrusadeUnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrusadeUnitServiceImpl implements CrusadeUnitService {

    private final CrusadeUnitRepository crusadeUnitRepository;

    @Override
    public Optional<CrusadeUnit> createCrusadeUnit(Integer orderOfBattleId, CrusadeUnit crusadeUnit) {
        return Optional.of(crusadeUnitRepository.save(crusadeUnit));
    }

    @Override
    public List<CrusadeUnit> getUnitsByOrderOfBattle(Integer orderOfBattleId) {
        return List.of();
    }

    @Override
    public Optional<CrusadeUnit> createCrusadeUnit(CrusadeUnit crusadeUnit) {
        // Optional: Check for existing unit by unique field (e.g. crusadeUnitName)
        return Optional.of(crusadeUnitRepository.save(crusadeUnit));
    }

    @Override
    public Optional<CrusadeUnit> getCrusadeUnitById(Integer id) {
        return crusadeUnitRepository.findById(id);
    }

    @Override
    public List<CrusadeUnit> getAllCrusadeUnits() {
        return crusadeUnitRepository.findAll();
    }

    @Override
    public CrusadeUnit updateCrusadeUnit(Integer id, CrusadeUnit updatedCrusadeUnit) {
        CrusadeUnit existing = crusadeUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CrusadeUnit not found with id " + id));

        existing.setCrusadeUnitName(updatedCrusadeUnit.getCrusadeUnitName());
        existing.setExperience(updatedCrusadeUnit.getExperience());
        existing.setKills(updatedCrusadeUnit.getKills());
        existing.setUpgrades(updatedCrusadeUnit.getUpgrades());
        existing.setUnitOfficialName(updatedCrusadeUnit.getUnitOfficialName());
        existing.setMovementDistance(updatedCrusadeUnit.getMovementDistance());
        existing.setToughnessValue(updatedCrusadeUnit.getToughnessValue());
        existing.setArmourSave(updatedCrusadeUnit.getArmourSave());
        existing.setMaxWounds(updatedCrusadeUnit.getMaxWounds());
        existing.setLeadershipValue(updatedCrusadeUnit.getLeadershipValue());
        existing.setObjectiveControl(updatedCrusadeUnit.getObjectiveControl());
        existing.setBaseSize(updatedCrusadeUnit.getBaseSize());
        existing.setFaction(updatedCrusadeUnit.getFaction());
        existing.setUnitAbility(updatedCrusadeUnit.getUnitAbility());
        existing.setUnitWargearOptions(updatedCrusadeUnit.getUnitWargearOptions());
        existing.setUnitComposition(updatedCrusadeUnit.getUnitComposition());
        existing.setUnitSize(updatedCrusadeUnit.getUnitSize());
        existing.setUnitCost(updatedCrusadeUnit.getUnitCost());

        existing.setOrderOfBattle(updatedCrusadeUnit.getOrderOfBattle());

        return crusadeUnitRepository.save(existing);
    }

    @Override
    public void deleteCrusadeUnit(Integer id) {
        crusadeUnitRepository.deleteById(id);
    }
}
