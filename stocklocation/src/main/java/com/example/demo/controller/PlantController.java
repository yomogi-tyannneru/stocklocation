package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.PlantDto;
import com.example.demo.service.AbstractPlantService;
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

/**
 * 工場 Controller
 */
//@Componentがbeanの役割　@Controllerに含まれている
@Controller
public class PlantController {
    //	他のクラスを呼び出す省略記法　インスタンス化したものをセットしてる　newのみした場合、リポジトリなどがつかえなくなる
    @Autowired
    private AbstractPlantService abstractPlantService;

    /**
     * 工場マスタ一覧画面を表示
     *
     * @param model Model
     * @return 工場マスタ一覧画面
     */
    //	実際のパスを記載
    //	modelについて調べる
    @GetMapping("/plant")
    public String viewPlantListPage(Model model) {
        //	テーブルから取得したデータを変数に格納
        List<Plant> plantlist = abstractPlantService.searchAll();
        //	"引数を渡す名称", 渡すデータ
        model.addAttribute("plantlist", plantlist);
        //	templatesからのパス
        return "plant/plant";
    }

    /**
     * 工場新規登録画面を表示
     *
     * @param model Model
     * @return 工場マスタ一覧画面
     */
    @GetMapping("/plant/new")
    public String viewCreatePlantPage(Model model) {
        //　TODO　登録フォームとカラムの関連付けまとめ
        //　TODO　バリデーション
        model.addAttribute("plantDto", new PlantDto());
        return "/plant/plant_new";
    }

    /**
     * 工場情報の新規登録
     *
     * @param plantDto リクエストデータ
     * @param model    Model
     * @return 工場マスタ一覧画面
     */
    @PostMapping("/plant/create")
    public String createPlant(@Validated @ModelAttribute PlantDto plantDto, BindingResult result, Model model) {
        // 入力チェックエラーの場合　
        if (result.hasErrors()) {
            // TODO　パスを/plant/newのままに変更
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "plant/plant_new";
        }
        // 工場情報の登録
        abstractPlantService.createPlant(plantDto);
        return "redirect:/plant";
    }

    /**
     * 工場編集画面を表示
     *
     * @param model Model
     * @return 工場マスタ一覧画面
     */
    @GetMapping("/plant/edit")
    public String viewEditPlantPage(Model model, PlantDto plantDto) {
        //　TODO　登録フォームとカラムの関連付けまとめ
        //　TODO　バリデーション
        plantDto = abstractPlantService.getPlant(plantDto.getId());
        model.addAttribute(plantDto);
        return "plant/plant_edit";
    }


    /**
     * 工場情報の更新
     *
     * @param plantDto リクエストデータ
     * @param model    Model
     * @return 工場マスタ一覧画面
     */
    @PostMapping("/plant/update")
    public String updatePlant(@Validated @ModelAttribute PlantDto plantDto, BindingResult result, Model model) {
        // 入力チェックエラーの場合
        if (result.hasErrors()) {
            // TODO　パスを/plant/newのままに変更
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "plant/plant_edit";
        }
        // 工場情報の更新
        abstractPlantService.updatePlant(plantDto.getId(), plantDto);
        return "redirect:/plant";
    }
}
