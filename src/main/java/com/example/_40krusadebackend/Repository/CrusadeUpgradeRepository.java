package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.CrusadeUpgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrusadeUpgradeRepository extends JpaRepository<CrusadeUpgrade, Long> {
    List<CrusadeUpgrade> findByCrusadeUnitId(int crusadeUnitId);
}
