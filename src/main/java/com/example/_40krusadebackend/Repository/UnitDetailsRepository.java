package com.example._40krusadebackend.Repository;

import com.example._40krusadebackend.Model.UnitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.annotation.DurationFormat;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UnitDetailsRepository extends JpaRepository<UnitDetails, Integer> {

}
