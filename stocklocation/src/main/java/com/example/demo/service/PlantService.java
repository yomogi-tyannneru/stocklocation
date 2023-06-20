package com.example.demo.service;

import java.util.Date;
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
public class PlantService {

	/**
	 * 工場情報 Repository
	 */
	@Autowired
	private PlantRepository plantRepository;

	/**
	 * 工場情報 全検索
	 * @return 検索結果
	 */
	public List<Plant> searchAll() {
		return plantRepository.findAll();
	}

	/**
	 * 工場情報 新規登録
	 * @param plantDto 工場情報
	 */
	public void plantCreate(PlantDto plantDto) {
		Date now = new Date();
		Plant plant = new Plant();
		plant.setName(plantDto.getName());
		plant.setFurigana(plantDto.getFurigana());
//		時刻も追加
		plant.setCreatedAt(now);
		plant.setUpdatedAt(now);
//		TODO　ログイン機能実装後 カラム追加
		plantRepository.save(plant);
	}

	// 受け取ったidからデータを取得して、Formを返却する
	public PlantDto getPlant(Long id) {
		// idを指定して工場の情報を取得する
//		orElseThrow()メソッドにnullを渡すと、値が存在しない場合にNullPointerExceptionがスローされます。
		Plant plant = plantRepository.findById(id).orElseThrow();

		// PlantDtoに値を設定する
		PlantDto editPlant = new PlantDto();
		editPlant.setId(plant.getId());
		editPlant.setName(plant.getName());
		editPlant.setFurigana(plant.getFurigana());

		return editPlant;
	}

	// 本を更新する
	public void plantUpdate(PlantDto editPlant) {

		// データベースに登録する値を保持するインスタンスの作成
		Plant plant = new Plant();

		// 画面から受け取った値を設定する
		plant.setId(editPlant.getId());
		plant.setName(editPlant.getName());
		plant.setFurigana(editPlant.getFurigana());

		// データベースを更新する
		plantRepository.save(plant);
	}
}
