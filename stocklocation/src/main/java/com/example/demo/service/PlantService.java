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
}
