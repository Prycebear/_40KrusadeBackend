package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrusadeUnitRepository extends JpaRepository<CrusadeUnit, Integer> {
    List<CrusadeUnit> findByOrderOfBattle(OrderOfBattle orderOfBattle);

}
