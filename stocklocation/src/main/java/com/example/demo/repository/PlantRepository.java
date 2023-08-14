package com.example.demo.repository;

import com.example.demo.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 工場情報 Repository
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>, PagingAndSortingRepository<Plant, Long> { }
