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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 工場 Controller
 */
//@Componentがbeanの役割　@Controllerに含まれている
@Controller
public class PlantController {
    //	他のクラスを呼び出す省略記法　インスタンス化したものをセットしてる　newのみした場合、リポジトリなどがつかえなくなる
    @Autowired
    private AbstractPlantService abstractPlantService;

    private static final int showPageSize = 3;

    private static final int limit = 5;
    /**
     * 工場マスタ一覧画面を表示
     *
     * @param model Model
     * @return 工場マスタ一覧画面
     */
    //	実際のパスを記載
    //	modelについて調べる
    @GetMapping("/plant")
    public String viewPlantListPage(Model model, @RequestParam(defaultValue = "1") int page) {
        // データ総数を取得
        int total = abstractPlantService.count();
        // データ一覧を取得
        List<Plant> plantlist = abstractPlantService.findAll(page, limit);

        // pagination処理
        // "総数/1ページの表示数"から総ページ数を割り出す
//        int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
        int totalPage = (total + limit) / limit;
        // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
        // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
        int startPage = page - (page - 1) % showPageSize;
        // 表示する最後のページ番号を算出
        int endPage = (startPage + showPageSize - 1 > totalPage) ? totalPage : startPage + showPageSize - 1;
        model.addAttribute("plantlist", plantlist);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
