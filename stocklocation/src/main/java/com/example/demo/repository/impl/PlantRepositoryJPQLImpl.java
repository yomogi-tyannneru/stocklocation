package com.example.demo.repository.impl;

import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlantRepositoryJPQLImpl implements PlantRepository2 {

	@Autowired
	EntityManager entityManager;
	@Override
	public List<Plant> findAll(int page, int limit) {
		Query query = entityManager.createQuery("from Plant p order by id")
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit);
		return (List<Plant>)query.getResultList();
	}
}
