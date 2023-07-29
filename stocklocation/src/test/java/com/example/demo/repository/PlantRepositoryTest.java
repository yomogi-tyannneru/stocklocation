package com.example.demo.repository;

import com.example.demo.entity.Plant;
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

    @Test
    public void findById() {
        // テストデータ準備
        Plant testPlant = new Plant();
        //testPlant.setId(10L);	// @GeneratedValueなので値を指定するとエラーになる プライマリキーカラムにユニークな値を自動で生成，付与する方法を指定するアノテーション
        testPlant.setName("工場１");
        testPlant.setFurigana("こうじょういち");
        testPlant.setSearchName(null);
        testPlant.setSearchFurigana(null);
        testPlant.setCreatedAt(LocalDateTime.of(2023, 6, 12, 21, 1));
        testPlant.setUpdatedAt(null);
        testPlant.setCreateUserId(null);
        testPlant.setUpdateUserId(null);
        Plant persisted = testEntityManager.persist(testPlant);

        // テスト実行
        Optional<Plant> plant = plantRepository.findById(persisted.getId());

        // 検証
//        isPresent　値が存在する場合は true 、存在しない場合は false を返すメソッド
        assertEquals(true, plant.isPresent());
        assertEquals(persisted.getId(), plant.get().getId());
        assertEquals("工場１", plant.get().getName());
        assertEquals("こうじょういち", plant.get().getFurigana());
        assertEquals(null, plant.get().getSearchName());
        assertEquals(null, plant.get().getSearchFurigana());
        assertEquals(LocalDateTime.of(2023, 6, 12, 21, 1), plant.get().getCreatedAt());
        assertEquals(null, plant.get().getUpdatedAt());
        assertEquals(null, plant.get().getCreateUserId());
        assertEquals(null, plant.get().getUpdateUserId());
    }
}
