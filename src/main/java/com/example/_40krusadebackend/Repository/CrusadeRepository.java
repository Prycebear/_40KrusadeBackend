package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrusadeRepository extends JpaRepository<Crusade, Integer> {
    List<Crusade> findAllByUser(AppUser user);

}
