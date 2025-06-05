package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.CrusadeForce;
import com.example._40krusadebackend.Model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrusadeForceRepository extends JpaRepository<CrusadeForce, Integer> {
    List<CrusadeForce> findAllByUser(AppUser user);

}
