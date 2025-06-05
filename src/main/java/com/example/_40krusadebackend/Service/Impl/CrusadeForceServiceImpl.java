package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.CrusadeForce;
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
            crusadeForce.setSupplyLimit(1000);
            crusadeForce.setRequisitionPoints(5);

            // Validate and attach Crusade
            Integer crusadeId = Optional.ofNullable(crusadeForce.getCrusade())
                    .map(Crusade::getCrusadeId)
                    .orElseThrow(() -> new IllegalArgumentException("Crusade must be provided and have a valid ID."));
            crusadeForce.setCrusade(crusadeRepository.findById(crusadeId)
                    .orElseThrow(() -> new EntityNotFoundException("Crusade with ID " + crusadeId + " not found.")));

            // Validate and attach Faction (optional)
            if (crusadeForce.getCrusadeFaction() != null) {
                Integer factionId = crusadeForce.getCrusadeFaction().getFactionId();
                if (factionId == null) {
                    throw new IllegalArgumentException("Faction must have a valid ID.");
                }
                crusadeForce.setCrusadeFaction(factionRepository.findById(factionId)
                        .orElseThrow(() -> new EntityNotFoundException("Faction with ID " + factionId + " not found.")));
            }

            // Set bidirectional link if order of battle is provided
            if (crusadeForce.getOrderOfBattle() != null) {
                crusadeForce.getOrderOfBattle().setCrusadeForce(crusadeForce);
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
    public List<CrusadeForce> getAllCrusades() {
        return crusadeForceRepository.findAll();
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
    public Optional<CrusadeForce> updateCrusadeForce(Integer id, CrusadeForce updates) {
        return getCrusadeById(id).map(existing -> {
            existing.setCrusadeForceName(updates.getCrusadeForceName());
            existing.setCrusadeFaction(updates.getCrusadeFaction());
            existing.setOrderOfBattle(updates.getOrderOfBattle());
            existing.setBattlesPlayed(updates.getBattlesPlayed());
            existing.setBattlesWon(updates.getBattlesWon());
            existing.setRequisitionPoints(updates.getRequisitionPoints());
            existing.setSupplyLimit(updates.getSupplyLimit());
            existing.setCrusadePoints(updates.getCrusadePoints());

            if (existing.getOrderOfBattle() != null) {
                existing.getOrderOfBattle().setCrusadeForce(existing); // Ensure bi-directional link
            }

            return crusadeForceRepository.save(existing);
        });
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

}