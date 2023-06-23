package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.PlantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Plant;
import com.example.demo.service.PlantService;

/**
 * 工場 Controller
 */
@Controller
public class PlantController {
	//	他のクラスを呼び出す省略記法
	@Autowired
	private PlantService plantService;

	/**
	 * 工場マスタ一覧画面を表示
	 *
	 * @return 工場マスタ一覧画面
	 */
	// 実際のパス
	@GetMapping("/plant")
	public String plant(Model model) {
		//	テーブルから取得したデータを変数に格納
		List<Plant> plantlist = plantService.searchAll();
		// "引数を渡す名称", 渡すデータ
		model.addAttribute("plantlist", plantlist);
		// templatesからのパス
		return "plant/plant";
	}

	/**
	 * 工場新規登録画面を表示
	 *
	 * @param model Model
	 * @return 工場マスタ一覧画面
	 */
	@GetMapping("/plant/new")
	public String viewCreatePage(Model model) {
		// 登録フォームとカラムの関連付け　バリデーション
		model.addAttribute("plantDto", new PlantDto());
		return "/plant/plant_new";
	}

	/**
	 * 工場新規登録
	 *
	 * @param plantDto リクエストデータ
	 * @param model       Model
	 * @return 工場マスタ一覧画面
	 */
	@PostMapping("/plant/create")
	public String create(@Validated @ModelAttribute PlantDto plantDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			// 入力チェックエラーの場合　
			// TODO　パスを/plant/newのままに変更　mysqlもnotnullに変更
			List<String> errorList = new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "plant/plant_new";
		}
		// 工場情報の登録
		plantService.createPlant(plantDto);
		return "redirect:/plant";
	}

	/**
	 * 工場編集画面を表示
	 *
	 * @param model Model
	 * @return 工場マスタ一覧画面
	 */
	@GetMapping("/plant/edit")
	public String viewEditPage(Model model, PlantDto editPlant) {
		// 登録フォームとカラムの関連付け　バリデーション
		editPlant = plantService.getPlant(editPlant.getId());
		model.addAttribute(editPlant);
		return "plant/plant_edit";
	}


	/**
	 * 工場更新
	 *
	 * @param plantDto リクエストデータ
	 * @param model       Model
	 * @return 工場マスタ一覧画面
	 */
	@PostMapping("/plant/update")
	public String update(@Validated @ModelAttribute PlantDto plantDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			// 入力チェックエラーの場合　
			// TODO　パスを/plant/newのままに変更　mysqlもnotnullに変更
			List<String> errorList = new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "plant/plant_edit";
		}
		// 工場情報の登録
		plantService.updatePlant(plantDto);
		return "redirect:/plant";
	}
}
