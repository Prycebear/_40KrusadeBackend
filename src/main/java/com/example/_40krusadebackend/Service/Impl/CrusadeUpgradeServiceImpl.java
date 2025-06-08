package com.example._40krusadebackend.Service.Impl;


import com.example._40krusadebackend.Enum.UpgradeType;
import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.CrusadeUpgrade;
import com.example._40krusadebackend.Repository.CrusadeUnitRepository;
import com.example._40krusadebackend.Repository.CrusadeUpgradeRepository;
import com.example._40krusadebackend.Service.CrusadeUpgradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CrusadeUpgradeServiceImpl implements CrusadeUpgradeService {

    private final CrusadeUpgradeRepository upgradeRepository;
    private final CrusadeUnitRepository unitRepository;

    public CrusadeUpgradeServiceImpl(CrusadeUpgradeRepository upgradeRepository, CrusadeUnitRepository unitRepository) {
        this.upgradeRepository = upgradeRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    public CrusadeUpgrade createUpgrade(CrusadeUpgrade upgrade) {
        return upgradeRepository.save(upgrade);
    }

    @Override
    public List<CrusadeUpgrade> getUpgradesForUnit(int unitId) {
        CrusadeUnit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new NoSuchElementException("CrusadeUnit with ID " + unitId + " not found"));

        List<CrusadeUpgrade> upgrades = upgradeRepository.findByCrusadeUnitId(unitId);

        if (upgrades == null || upgrades.isEmpty()) {
            List<CrusadeUpgrade> defaultUpgrades = getDefaultUpgrades(unit);
            return upgradeRepository.saveAll(defaultUpgrades);
        }

        return upgrades;
    }

    @Override
    public void deleteUpgrade(int id) {
        upgradeRepository.deleteById((long) id);
    }

    private List<CrusadeUpgrade> getDefaultUpgrades(CrusadeUnit unit) {
        return List.of(
                new CrusadeUpgrade(null, UpgradeType.BATTLE_HONOUR, "Veteran of Taros Reach", "Fought in the siege of Taros Reach.", unit),
                new CrusadeUpgrade(null, UpgradeType.BATTLE_SCAR, "Lingering Wound", "Suffers a -1 to Advance rolls.", unit),
                new CrusadeUpgrade(null, UpgradeType.ENHANCEMENT, "Blessed Cogitator", "Once per battle, reroll one Hit roll.", unit)
        );
    }
}
