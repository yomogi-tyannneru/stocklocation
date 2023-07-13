package com.example.demo.repository;

import com.example.demo.entity.Plant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlantRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private PlantRepository plantRepository;

	@BeforeEach
	void setUp() {
	}

	@Test
	public void findById() {
		// テストデータ準備
		Plant testPlant = new Plant();
		//testPlant.setId(10L);	// @GeneratedValueなので値を指定するとエラーになる
		testPlant.setName("工場１");
		testPlant.setFurigana("こうじょういち");
		testPlant.setCreatedAt(LocalDateTime.of(2023, 6, 12, 21, 1));
		Plant persisted = testEntityManager.persist(testPlant);

		// テスト実行
		Optional<Plant> plant = plantRepository.findById(persisted.getId());

		// 検証
		Plant expected = new Plant();
		expected.setId(persisted.getId());
		expected.setName("工場１");
		expected.setFurigana("こうじょういち");
		expected.setCreatedAt(LocalDateTime.of(2023, 6, 12, 21, 1));
		assertEquals(expected.getId(), plant.get().getId());
	}
}