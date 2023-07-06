package com.example.demo.service;

import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class PlantServiceTest {

	@TestConfiguration
	static class PlantServiceTestConfiguration {

		// テスト対象クラスのBean生成
		@Bean
		public AbstractPlantService testTarget() {
			return new PlantService();
		}
	}

	// モックの作成
	@MockBean
	PlantRepository mockRepository;

	// テスト対象クラスのインスタンスにモックを注入
	@Autowired
	private AbstractPlantService testTarget;
	@Test
	void searchAll() {
		// どんなGoods型の引数でもtrueを返すようにmockを定義
		when(mockRepository.findAll()).thenReturn(createTestData());

		// テスト実行
		List<Plant> plants = testTarget.searchAll();
		// 検証
		assertEquals(2, plants.size());
	}

	private List<Plant> createTestData() {
		List<Plant> plants = new ArrayList<>();
		Plant plant = new Plant();
		plant.setId(1L);
		plant.setName("工場名");
		plant.setFurigana("こうじょうめい");
		plants.add(plant);
		return plants;
	}

//	@Test
//	void createPlant() {
//	}
//
//	@Test
//	void getPlant() {
//	}
//
//	@Test
//	void updatePlant() {
//	}
}