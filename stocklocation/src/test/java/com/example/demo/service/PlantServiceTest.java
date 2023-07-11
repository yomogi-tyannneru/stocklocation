package com.example.demo.service;

import com.example.demo.dto.PlantDto;
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
import static org.mockito.Mockito.*;


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
		assertEquals(1, plants.size());
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

	@Test
	void createPlant() {
		// モック定義
//		when(mockRepository.save(null)).thenReturn(expectedParam);


		// テスト実行
		PlantDto inputDto = new PlantDto();
		inputDto.setId(10L);
		inputDto.setName("工場1");
		inputDto.setFurigana("こうじょういち");
		testTarget.createPlant(inputDto);

		// 検証（パラメータが想定通りかと呼び出し回数）
		Plant expectedParam = new Plant();
		expectedParam.setId(10L);
		expectedParam.setName("工場1");
		expectedParam.setFurigana("こうじょういち");
		verify(mockRepository, times(1)).save(expectedParam);
		// パラメータの検証はモック定義でやっている
	}

//	@Test
//	void getPlant() {
//	}
//
//	@Test
//	void updatePlant() {
//	}
}