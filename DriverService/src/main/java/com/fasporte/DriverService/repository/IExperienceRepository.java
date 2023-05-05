package com.fasporte.DriverService.repository;

import com.fasporte.DriverService.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByDriverId(Long driverId);
}
