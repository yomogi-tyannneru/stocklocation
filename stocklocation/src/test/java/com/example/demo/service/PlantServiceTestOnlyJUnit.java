package com.example.demo.service;

import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PlantServiceTestOnlyJUnit {

	// テスト対象
	private AbstractPlantService testTarget = new PlantService();

	// モック
	private PlantRepository mockRepository = new PlantRepositoryMock();


	@BeforeEach
	public void setup() throws Exception {
		// PlantServiceのplantRepositoryにモック実装を設定
		//   privateメンバーなのでリフレクションで無理矢理設定
		Field field = testTarget.getClass().getDeclaredField("plantRepository");
		// private変数へのアクセス制限を解除
		field.setAccessible(true);
		// private変数に値を設定
		field.set(testTarget, mockRepository);
	}

	@Test
	void searchAll() {
		// テスト実行
		List<Plant> plants = testTarget.searchAll();
		// 検証
		assertEquals(1, plants.size());
		assertEquals(1L, plants.get(0).getId());
		assertEquals("工場名", plants.get(0).getName());
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

	private class PlantRepositoryMock implements PlantRepository {
		@Override
		public List<Plant> findAll() {
			return createTestData();
		}
		@Override
		public void flush() {

		}

		@Override
		public <S extends Plant> S saveAndFlush(S entity) {
			return null;
		}

		@Override
		public <S extends Plant> List<S> saveAllAndFlush(Iterable<S> entities) {
			return null;
		}

		@Override
		public void deleteAllInBatch(Iterable<Plant> entities) {

		}

		@Override
		public void deleteAllByIdInBatch(Iterable<Long> longs) {

		}

		@Override
		public void deleteAllInBatch() {

		}

		@Override
		public Plant getOne(Long aLong) {
			return null;
		}

		@Override
		public Plant getById(Long aLong) {
			return null;
		}

		@Override
		public Plant getReferenceById(Long aLong) {
			return null;
		}

		@Override
		public <S extends Plant> Optional<S> findOne(Example<S> example) {
			return Optional.empty();
		}

		@Override
		public <S extends Plant> List<S> findAll(Example<S> example) {
			return null;
		}

		@Override
		public <S extends Plant> List<S> findAll(Example<S> example, Sort sort) {
			return null;
		}

		@Override
		public <S extends Plant> Page<S> findAll(Example<S> example, Pageable pageable) {
			return null;
		}

		@Override
		public <S extends Plant> long count(Example<S> example) {
			return 0;
		}

		@Override
		public <S extends Plant> boolean exists(Example<S> example) {
			return false;
		}

		@Override
		public <S extends Plant, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
			return null;
		}

		@Override
		public <S extends Plant> S save(S entity) {
			return null;
		}

		@Override
		public <S extends Plant> List<S> saveAll(Iterable<S> entities) {
			return null;
		}

		@Override
		public Optional<Plant> findById(Long aLong) {
			return Optional.empty();
		}

		@Override
		public boolean existsById(Long aLong) {
			return false;
		}

		@Override
		public List<Plant> findAllById(Iterable<Long> longs) {
			return null;
		}

		@Override
		public long count() {
			return 0;
		}

		@Override
		public void deleteById(Long aLong) {

		}

		@Override
		public void delete(Plant entity) {

		}

		@Override
		public void deleteAllById(Iterable<? extends Long> longs) {

		}

		@Override
		public void deleteAll(Iterable<? extends Plant> entities) {

		}

		@Override
		public void deleteAll() {

		}

		@Override
		public List<Plant> findAll(Sort sort) {
			return null;
		}

		@Override
		public Page<Plant> findAll(Pageable pageable) {
			return null;
		}
	}
}

