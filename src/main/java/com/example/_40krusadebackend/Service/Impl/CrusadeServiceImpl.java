package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.User.AppUser;
import com.example._40krusadebackend.Repository.CrusadeRepository;
import com.example._40krusadebackend.Repository.UserRepository;
import com.example._40krusadebackend.Service.CrusadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrusadeServiceImpl implements CrusadeService {

    private final CrusadeRepository crusadeRepository;
    private final UserRepository userRepository;

    @Override
    public Crusade createCrusade(Crusade crusade) {
        try {
            AppUser currentUser = getCurrentUser();
            Optional<Crusade> existing = crusadeRepository.findByCrusadeNameAndOwner(
                    crusade.getCrusadeName(), currentUser
            );
            if (existing.isPresent()) {
                log.warn("User '{}' attempted to create a duplicate Crusade named '{}'",
                        currentUser.getUsername(), crusade.getCrusadeName());
                throw new IllegalArgumentException("A Crusade with this name already exists for the user");
            }
            crusade.setOwner(currentUser);
            Crusade saved = crusadeRepository.save(crusade);
            log.info("Crusade '{}' created for user '{}'", saved.getCrusadeName(), currentUser.getUsername());
            return saved;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while creating Crusade '{}': {}", crusade.getCrusadeName(), e.getMessage(), e);
            throw new RuntimeException("Failed to create Crusade");
        }
    }

    @Override
    public List<Crusade> getAllCrusades() {
        return crusadeRepository.findAll();
    }

    @Override
    public List<Crusade> getCrusadesByUserId(Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return crusadeRepository.findAllByOwner(user);
    }


    @Override
    public Optional<Crusade> getCrusadeById(Integer id) {
        try {
            AppUser currentUser = getCurrentUser();
            Optional<Crusade> crusade = crusadeRepository.findById(id)
                    .filter(c -> c.getOwner().getId().equals(currentUser.getId()));
            if (crusade.isPresent()) {
                log.info("Crusade ID {} retrieved for user '{}'", id, currentUser.getUsername());
            } else {
                log.warn("Crusade ID {} not found or does not belong to user '{}'", id, currentUser.getUsername());
            }
            return crusade;
        } catch (Exception e) {
            log.error("Failed to retrieve crusade by ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error retrieving crusade by ID");
        }
    }

    @Override
    public boolean deleteCrusadeById(Integer id) throws AccessDeniedException {
        try {
            AppUser currentUser = getCurrentUser();
            Optional<Crusade> optionalCrusade = crusadeRepository.findById(id);

            if (optionalCrusade.isEmpty()) {
                log.warn("Attempted to delete Crusade ID {} but it was not found", id);
                return false;
            }

            Crusade crusade = optionalCrusade.get();

            if (!crusade.getOwner().getId().equals(currentUser.getId())) {
                log.warn("User '{}' attempted to delete Crusade ID {} they do not own", currentUser.getUsername(), id);
                throw new AccessDeniedException("You do not have permission to delete this Crusade.");
            }

            crusadeRepository.delete(crusade);
            log.info("Crusade ID {} deleted successfully by user '{}'", id, currentUser.getUsername());
            return true;

        } catch (AccessDeniedException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to delete Crusade ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error deleting Crusade");
        }
    }

    private AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("Authenticated user '{}' not found in database", username);
                    return new RuntimeException("User not found: " + username);
                });
    }
}
