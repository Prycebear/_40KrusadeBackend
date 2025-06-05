package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Repository.CrusadeForceRepository;
import com.example._40krusadebackend.Repository.UserRepository;
import com.example._40krusadebackend.Service.CrusadeForceService;
import com.example._40krusadebackend.Model.User.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrusadeForceServiceImpl implements CrusadeForceService {

    private final CrusadeForceRepository crusadeForceRepository;
    private final UserRepository userRepository;

    @Override
    public CrusadeForce createCrusade(CrusadeForce crusadeForce) {
        AppUser currentUser = getCurrentUser();
        crusadeForce.setUser(currentUser);
        return crusadeForceRepository.save(crusadeForce);
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
}