package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.Crusade;
import com.example._40krusadebackend.Model.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrusadeRepository extends JpaRepository<Crusade, Integer> {
    List<Crusade> findAllByOwner(AppUser user);
    Optional<Crusade> findByCrusadeNameAndOwner(String crusadeName, AppUser owner);

}
