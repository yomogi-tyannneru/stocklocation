package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.PlantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository;

/**
 * 工場情報 Service
 */
@Service
public class PlantService implements AbstractPlantService{

	/**
	 * 工場情報 Repository
	 */
	@Autowired
	private PlantRepository plantRepository;

	/**
	 * 工場情報 全検索
	 *
	 * @return 検索結果
	 */
	public List<Plant> searchAll() {
		return plantRepository.findAll();
	}

	/**
	 * 工場情報 新規登録
	 *
	 * @param plantDto
	 *             工場情報
	 */
	public void createPlant(PlantDto plantDto) {
		LocalDateTime localDateTime = LocalDateTime.now();
		Plant plant = new Plant();
		plant.setName(plantDto.getName());
		plant.setFurigana(plantDto.getFurigana());
		plant.setCreatedAt(localDateTime);
		//	TODO　ログイン機能実装後 カラム追加
		plantRepository.save(plant);
	}

	/**
	 * 工場情報 新規登録
	 *
	 * @param id
	 *          id
	 */
	public PlantDto getPlant(Long id) {
		// idを指定して工場の情報を取得する
		//	orElseThrow()メソッドにnullを渡すと、値が存在しない場合にNullPointerExceptionがスローされます。
		Plant plantId = plantRepository.findById(id).orElseThrow();
		// PlantDtoに値を設定する
		PlantDto plantDto = new PlantDto();
		plantDto.setId(plantId.getId());
		plantDto.setName(plantId.getName());
		plantDto.setFurigana(plantId.getFurigana());

		return plantDto;
	}

	/**
	 * 工場情報 新規登録
	 *
	 * @param id
	 *　　       id
	 * @param plantDto
	 *             工場情報
	 */
	public void updatePlant(long id, PlantDto plantDto) {
		// データベースに登録する値を保持するインスタンスの作成
		LocalDateTime localDateTime = LocalDateTime.now();
		Plant plantId = plantRepository.findById(id).orElseThrow();
		Plant plant = new Plant();
		// 画面から受け取った値を設定する
		plant.setName(plantDto.getName());
		plant.setFurigana(plantDto.getFurigana());
		plant.setCreatedAt(plantId.getCreatedAt());
		plant.setUpdatedAt(localDateTime);
		//	TODO　ログイン機能実装後 カラム追加
		// データベースを更新する
		plantRepository.save(plant);
	}
}
