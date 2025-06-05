package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Repository.OrderOfBattleRepository;
import com.example._40krusadebackend.Service.OrderOfBattleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderOfBattleServiceImpl implements OrderOfBattleService {

    private final OrderOfBattleRepository orderOfBattleRepository;

    public OrderOfBattleServiceImpl(OrderOfBattleRepository orderOfBattleRepository) {
        this.orderOfBattleRepository = orderOfBattleRepository;
    }

    @Override
    public OrderOfBattle createOrderOfBattle(OrderOfBattle orderOfBattle) {
        return orderOfBattleRepository.save(orderOfBattle);
    }

    @Override
    public Optional<OrderOfBattle> getOrderOfBattleById(Integer id) {
        return orderOfBattleRepository.findById(id);
    }

    @Override
    public List<OrderOfBattle> getAllOrderOfBattles() {
        return orderOfBattleRepository.findAll();
    }

    @Override
    public OrderOfBattle updateOrderOfBattle(Integer id, OrderOfBattle updatedOrderOfBattle) {
        return orderOfBattleRepository.findById(id)
                .map(existing -> {
                    // Update any fields you want here; example:
                    // existing.setSomeField(updatedOrderOfBattle.getSomeField());
                    return orderOfBattleRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("OrderOfBattle not found with id: " + id));
    }

    @Override
    public boolean deleteOrderOfBattle(Integer id) {
        if (orderOfBattleRepository.existsById(id)) {
            orderOfBattleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
