package com.example.demo.repository;

import com.example.demo.entity.Plant;

import java.util.List;

public interface PlantRepository2 {
	List<Plant> findAll(int page, int limit);
}
