package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Repository.CrusadeUnitRepository;
import com.example._40krusadebackend.Repository.OrderOfBattleRepository;
import com.example._40krusadebackend.Service.CrusadeUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrusadeUnitServiceImpl implements CrusadeUnitService {

    private final CrusadeUnitRepository crusadeUnitRepository;
    private final OrderOfBattleRepository orderOfBattleRepository;

    @Override
    public CrusadeUnit createCrusadeUnit(Integer orderOfBattleId, CrusadeUnit crusadeUnit) {
        OrderOfBattle orderOfBattle = orderOfBattleRepository.findById(orderOfBattleId)
                .orElseThrow(() -> new RuntimeException("OrderOfBattle not found"));

        crusadeUnit.setOrderOfBattle(orderOfBattle);
        return crusadeUnitRepository.save(crusadeUnit);
    }

    @Override
    public List<CrusadeUnit> getUnitsByOrderOfBattle(Integer orderOfBattleId) {
        OrderOfBattle orderOfBattle = orderOfBattleRepository.findById(orderOfBattleId)
                .orElseThrow(() -> new RuntimeException("OrderOfBattle not found"));

        return crusadeUnitRepository.findByOrderOfBattle(orderOfBattle);
    }

    @Override
    public Optional<CrusadeUnit> getCrusadeUnitById(Integer id) {
        return crusadeUnitRepository.findById(id);
    }

    @Override
    public void deleteCrusadeUnit(Integer id) {
        crusadeUnitRepository.deleteById(id);
    }
}
