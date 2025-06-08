package com.example._40krusadebackend.Service;


import com.example._40krusadebackend.Model.CrusadeUpgrade;

import java.util.List;

public interface CrusadeUpgradeService {
    CrusadeUpgrade createUpgrade(CrusadeUpgrade upgrade);
    List<CrusadeUpgrade> getUpgradesForUnit(int unitId);
    void deleteUpgrade(int id);
}
