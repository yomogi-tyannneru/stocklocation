package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Plant;

/**
 * 工場情報 Repository
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {}
