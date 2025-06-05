package com.example._40krusadebackend.Service;

import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;

import java.util.List;
import java.util.Optional;

public interface OrderOfBattleService {

    OrderOfBattle createOrderOfBattle(OrderOfBattle orderOfBattle);

    Optional<OrderOfBattle> getOrderOfBattleById(Integer id);

    List<OrderOfBattle> getAllOrderOfBattles();

    OrderOfBattle updateOrderOfBattle(Integer id, OrderOfBattle updatedOrderOfBattle);

    boolean deleteOrderOfBattle(Integer id);

    CrusadeUnit addUnitToOrderOfBattle(Integer orderOfBattleId, CrusadeUnit unit);

    Optional<OrderOfBattle> getOrderOfBattleByCrusadeForceId(Integer crusadeForceId);
}
