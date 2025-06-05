package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Repository.CrusadeRepository;
import com.example._40krusadebackend.Repository.UserRepository;
import com.example._40krusadebackend.Service.CrusadeService;
import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.User.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrusadeServiceImpl implements CrusadeService {

    private final CrusadeRepository crusadeRepository;
    private final UserRepository userRepository;

    @Override
    public Crusade createCrusade(Crusade crusade) {
        AppUser currentUser = getCurrentUser();
        crusade.setUser(currentUser);
        return crusadeRepository.save(crusade);
    }

    @Override
    public List<Crusade> getCrusadesForCurrentUser() {
        AppUser currentUser = getCurrentUser();
        return crusadeRepository.findAllByUser(currentUser);
    }

    @Override
    public Optional<Crusade> getCrusadeById(Integer id) {
        AppUser currentUser = getCurrentUser();
        return crusadeRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()));
    }

    @Override
    public boolean deleteCrusadeById(Integer id) {
        Optional<Crusade> crusade = getCrusadeById(id);
        if (crusade.isPresent()) {
            crusadeRepository.delete(crusade.get());
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