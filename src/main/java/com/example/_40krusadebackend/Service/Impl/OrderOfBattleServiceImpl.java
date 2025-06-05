package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Repository.CrusadeForceRepository;
import com.example._40krusadebackend.Repository.OrderOfBattleRepository;
import com.example._40krusadebackend.Service.OrderOfBattleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOfBattleServiceImpl implements OrderOfBattleService {

    private final OrderOfBattleRepository orderOfBattleRepository;
    private final CrusadeForceRepository crusadeForceRepository;

    @Override
    public OrderOfBattle createOrderOfBattle(OrderOfBattle orderOfBattle) {
        try {
            OrderOfBattle saved = orderOfBattleRepository.save(orderOfBattle);
            log.info("Created Order of Battle with ID {}", saved.getOrderOfBattleId());
            return saved;
        } catch (Exception e) {
            log.error("Failed to create Order of Battle: {}", e.getMessage(), e);
            throw new RuntimeException("Could not create Order of Battle");
        }
    }

    @Override
    public List<OrderOfBattle> getAllOrderOfBattles() {
        return orderOfBattleRepository.findAll();
    }

    @Override
    public Optional<OrderOfBattle> getOrderOfBattleById(Integer id) {
        return orderOfBattleRepository.findById(id);
    }


    @Override
    public OrderOfBattle updateOrderOfBattle(Integer id, OrderOfBattle updatedOrderOfBattle) {
        return orderOfBattleRepository.findById(id).map(existing -> {
            existing.setUnits(updatedOrderOfBattle.getUnits());
            log.info("Updated Order of Battle with ID {}", id);
            return orderOfBattleRepository.save(existing);
        }).orElseThrow(() -> {
            log.warn("Attempted to update non-existent Order of Battle with ID {}", id);
            return new RuntimeException("Order of Battle not found");
        });
    }

    @Override
    public boolean deleteOrderOfBattle(Integer id) {
        return orderOfBattleRepository.findById(id).map(ob -> {
            orderOfBattleRepository.delete(ob);
            log.info("Deleted Order of Battle with ID {}", id);
            return true;
        }).orElseGet(() -> {
            log.warn("Attempted to delete non-existent Order of Battle with ID {}", id);
            return false;
        });
    }

    @Override
    public CrusadeUnit addUnitToOrderOfBattle(Integer orderOfBattleId, CrusadeUnit unit) {
        OrderOfBattle order = orderOfBattleRepository.findById(orderOfBattleId)
                .orElseThrow(() -> new RuntimeException("Order of Battle not found with ID: " + orderOfBattleId));

        CrusadeForce force = order.getCrusadeForce();
        if (force == null || force.getCrusadeFaction() == null) {
            throw new RuntimeException("Crusade Force or its faction is not set for this Order of Battle.");
        }

        if (unit.getFaction() == null) {
            throw new RuntimeException("Unit must have a faction.");
        }

        Integer forceFactionId = force.getCrusadeFaction().getFactionId();
        Integer unitFactionId = unit.getFaction().getFactionId();

        if (!forceFactionId.equals(unitFactionId)) {
            throw new IllegalArgumentException(String.format(
                    "Unit faction (ID: %d) does not match Order of Battle's faction (ID: %d)",
                    unitFactionId, forceFactionId
            ));
        }

        unit.setOrderOfBattle(order);
        order.getUnits().add(unit);
        orderOfBattleRepository.save(order);

        return unit;
    }

    @Override
    public Optional<OrderOfBattle> getOrderOfBattleByCrusadeForceId(Integer crusadeForceId) {
        return crusadeForceRepository.findById(crusadeForceId)
                .map(CrusadeForce::getOrderOfBattle);
    }
}
