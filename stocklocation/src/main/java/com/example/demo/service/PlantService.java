package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.PlantDto;
import com.example.demo.util.Datetime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository;
import com.example.demo.response.Response;

/**
 * 工場情報 Service
 */
@Service
public class PlantService implements AbstractPlantService {

    @Autowired
    private Datetime datetime;

    /**
     * 工場情報 Repository
     */
    //	サービスnewだとnullになる　これをつかえるようにするのがbean　昔はxmlファイルにbeanを書く必要あり
    @Autowired
    private PlantRepository plantRepository;
    private Response response;

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
     * @param plantDto 工場情報
     */
    public void createPlant(PlantDto plantDto) {
        //　こちらが呼ばれたときにモックが動く
        LocalDateTime localDateTime = datetime.now();
        Plant plant = new Plant();
        //		idはセットしていないためこの時点ではnullで登録されている
        plant.setName(plantDto.getName());
        plant.setFurigana(plantDto.getFurigana());
        plant.setCreatedAt(localDateTime);
        //	TODO　ログイン機能実装後 カラム追加
        plantRepository.save(plant);
    }

    /**
     * 工場情報 編集画面
     *
     * @param id id
     */
    public PlantDto getPlant(Long id) {
        //　idを指定して工場の情報を取得する
        //　orElseThrow()メソッドにnullを渡すと、値が存在しない場合にNullPointerExceptionがスローされる　
        //　TODO　テスト項目に含まれる
        Plant plant = plantRepository.findById(id).orElseThrow();
        // PlantDtoに値を設定する
        PlantDto plantDto = new PlantDto();
        plantDto.setId(plant.getId());
        plantDto.setName(plant.getName());
        plantDto.setFurigana(plant.getFurigana());

        return plantDto;
    }

    /**
     * 工場情報 更新
     *
     * @param id       　　       id
     * @param plantDto 工場情報
     */
    public void updatePlant(long id, PlantDto plantDto) {
        // データベースに登録する値を保持するインスタンスの作成
        LocalDateTime localDateTime = datetime.now();
        Plant plant = plantRepository.findById(id).orElseThrow();
        // 画面から受け取った値を設定する
        plant.setName(plantDto.getName());
        plant.setFurigana(plantDto.getFurigana());
        plant.getCreatedAt();
        plant.setUpdatedAt(localDateTime);
        //	TODO　ログイン機能実装後 カラム追加
        // データベースを更新する
        plantRepository.save(plant);
    }
}
