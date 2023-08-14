package com.example.demo.service;

import com.example.demo.dto.PlantDto;
import com.example.demo.entity.Plant;
import java.util.List;

/**
 * 工場情報 Service
 */
//  インタフェースでは定数とメソッドのみが定義できます。
//  インタフェースのメンバ変数は自動的にpublic static finalが付けられるので定数になります。
//インターフェースの特徴に多重継承がある。1つのクラスが複数の親クラスを持つことを言い、複数の性質を受け継ぐ事になります。

public interface AbstractPlantService {

    /**
     * 工場情報 全検索
     *
     * @return 検索結果
     */
    List<Plant> searchAll();

    /**
     * 工場情報 新規登録
     *
     * @param plantDto
     *             工場情報
     */
    void createPlant(PlantDto plantDto);

    /**
     * 工場情報 新規登録
     *
     * @param id
     *          id
     */
    PlantDto getPlant(Long id);

    /**
     * 工場情報 新規登録
     *
     * @param id
     *　　       id
     * @param plantDto
     *             工場情報
     */
    void updatePlant(long id, PlantDto plantDto);

    /**
     * 工場情報の総件数を取得
     * @return
     */
	int count();

    /**
     * 工場情報をページ指定で取得
     * @param pageNum
     * @param count
     * @return
     */
    List<Plant> findAll(int pageNum, int count);
}
