package com.example._40krusadebackend.Service.Impl;

import com.example._40krusadebackend.Model.UnitDetails;
import com.example._40krusadebackend.Service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {


    @Override
    public UnitDetails createUser(UnitDetails unitDetails) {
        return null;
    }

    @Override
    public Optional<UnitDetails> getUserById(Long unitDetailsId) {
        return Optional.empty();
    }

    @Override
    public Optional<UnitDetails> getUserByUsername(String unitOfficialName) {
        return Optional.empty();
    }

    @Override
    public List<UnitDetails> getAllUnits() {
        return List.of();
    }

    @Override
    public UnitDetails updateUser(Long unitDetailsId, UnitDetails unitOfficialName) {
        return null;
    }

    @Override
    public void deleteUser(Long unitDetailsId) {

    }
}
