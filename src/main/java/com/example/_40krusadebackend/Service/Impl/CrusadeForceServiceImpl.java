package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.Faction;
import com.example._40krusadebackend.Model.OrderOfBattle;
import com.example._40krusadebackend.Repository.*;
import com.example._40krusadebackend.Service.CrusadeForceService;
import com.example._40krusadebackend.Model.User.AppUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrusadeForceServiceImpl implements CrusadeForceService {

    private final CrusadeForceRepository crusadeForceRepository;
    private final OrderOfBattleRepository orderOfBattleRepository;
    private final CrusadeRepository crusadeRepository;
    private final FactionRepository factionRepository;

    private final UserRepository userRepository;

    @Override
    public CrusadeForce createCrusade(CrusadeForce crusadeForce) {
        try {
            AppUser currentUser = getCurrentUser();
            crusadeForce.setUser(currentUser);

            if (crusadeForce.getCrusade() == null || crusadeForce.getCrusade().getCrusadeId() == null) {
                throw new IllegalArgumentException("Crusade must be provided and must have a valid ID.");
            }

            Crusade managedCrusade = crusadeRepository.findById(crusadeForce.getCrusade().getCrusadeId())
                    .orElseThrow(() -> new EntityNotFoundException("Crusade with ID " + crusadeForce.getCrusade().getCrusadeId() + " not found."));
            crusadeForce.setCrusade(managedCrusade);

            if (crusadeForce.getCrusadeFaction() != null) {
                Integer factionId = crusadeForce.getCrusadeFaction().getFactionId();
                if (factionId == null) {
                    throw new IllegalArgumentException("Faction must have a valid ID.");
                }
                Faction managedFaction = factionRepository.findById(factionId)
                        .orElseThrow(() -> new EntityNotFoundException("Faction with ID " + factionId + " not found."));
                crusadeForce.setCrusadeFaction(managedFaction);
            }
            if (crusadeForce.getOrderOfBattle() != null) {
                OrderOfBattle ob = crusadeForce.getOrderOfBattle();
                ob.setCrusadeForce(crusadeForce); // Bidirectional safety
            }

            CrusadeForce saved = crusadeForceRepository.save(crusadeForce);
            log.info("Created CrusadeForce with ID {}", saved.getCrusadeId());
            return saved;

        } catch (EntityNotFoundException | IllegalArgumentException e) {
            log.warn("Failed to create CrusadeForce: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error creating CrusadeForce: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create CrusadeForce", e);
        }
    }


    @Override
    public List<CrusadeForce> getCrusadesForCurrentUser() {
        AppUser currentUser = getCurrentUser();
        return crusadeForceRepository.findAllByUser(currentUser);
    }

    @Override
    public Optional<CrusadeForce> getCrusadeById(Integer id) {
        AppUser currentUser = getCurrentUser();
        return crusadeForceRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()));
    }

    @Override
    public boolean deleteCrusadeById(Integer id) {
        Optional<CrusadeForce> crusade = getCrusadeById(id);
        if (crusade.isPresent()) {
            crusadeForceRepository.delete(crusade.get());
            return true;
        }
        return false;
    }

    private AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public CrusadeForce addOrderOfBattleToCrusadeForce(Integer crusadeForceId, OrderOfBattle orderOfBattle) {
        CrusadeForce crusadeForce = crusadeForceRepository.findById(crusadeForceId)
                .orElseThrow(() -> new RuntimeException("CrusadeForce not found: " + crusadeForceId));

        OrderOfBattle savedOrderOfBattle = orderOfBattleRepository.save(orderOfBattle);

        crusadeForce.setOrderOfBattle(savedOrderOfBattle);
        return crusadeForceRepository.save(crusadeForce);
    }
}