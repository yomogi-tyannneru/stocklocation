package com.example.demo.service;

import com.example.demo.dto.PlantDto;
import com.example.demo.entity.Plant;
import com.example.demo.repository.PlantRepository;
import com.example.demo.util.Datetime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//　@ExtendWith(MockitoExtension.class)　モックテスト
//　違いとして使えるアノテーションの差がある
//　@SpringBootTest　@TestConfigurationがいらなくなる

//　テストでMockitoを使う宣言
//　springのモックテスト　springの機能を使うため
@ExtendWith(SpringExtension.class)
class PlantServiceTest {

    //　自動検出からテスト用のコンフィグレーションクラスを除外？
    //　テスト用のBean定義　＠COMMONがJAVAクラス用のBean定義

    //　AbstractPlantServiceを使えるようにするため
    //　色んな設定ができる中のひとつにbeanがある　他にも設定できるものは多くある
    @TestConfiguration
    static class PlantServiceTestConfiguration {
        // テスト対象クラスのBean生成　spring管理化に置くため
        //　Beanとはspring管理化のインスタンス（new）のこと　springが色々してくれるようになる
        //　spring管理化に置くため
        //　昔の書き方<bean id="hellobean" class="com.tuyano.sample.HelloBean"/>
        @Bean
        public AbstractPlantService testTarget() {
            return new PlantService();
        }
    }

    //　モックの作成
    //　@MockBeanはMockitoではなくSpring Bootが提供するアノテーション
    //　@MockはMockitoが提供するアノテーション
    //　springtestの中のモック
    @MockBean
    PlantRepository mockRepository;

    //　インターフェースにすることによってモック化できる　より簡易的な方法で時刻の固定化ができる
    @MockBean
    Datetime datetime;

    // テスト対象クラスのインスタンスにモックを注入
    //　抽象メソッドを呼び出す理由？
    //　Autowiredはspringのもの
    @Autowired
    private AbstractPlantService testTarget;

    //　テストコードと認識し、実行できるようになる
    @Test
    void searchAll() {
        //　どんなGoods型の引数でもtrueを返すようにmockを定義?
        //　findAll()は定義のみで呼ばれない?
        //　whenのあとにthenReturnが呼ばれる
        //　DBにつながずにサービスのみでテスト
        when(mockRepository.findAll()).thenReturn(createTestData());

        // テスト実行
        List<Plant> plants = testTarget.searchAll();
        // 検証
        assertEquals(1, plants.size());
        //　中身一致かどうかの検証もしてもよい
    }

    //　テストの意義　バックのみで確認したいとき　テスト自動化　
    // リリース時は画面もバックのテスト両方行う
    private List<Plant> createTestData() {
        List<Plant> plants = new ArrayList<>();
        final var plant = new Plant();
        // Long型のためLが付く
        plant.setId(1L);
        plant.setName("工場1");
        plant.setFurigana("こうじょう1");
        plants.add(plant);
        return plants;
    }

    @Test
    void createPlant() {
        //　時刻の固定化　そのままだとずれるため テスト対象の時刻をモック化
        when(datetime.now()).thenReturn(LocalDateTime.of(2021, 10, 01, 10, 20));
        //　ダミーデータ
        final var inputData = new PlantDto();
        inputData.setId(1L);
        inputData.setName("工場1");
        inputData.setFurigana("こうじょういち");
        //　ダミーデータの登録
        //　テスト実行
        testTarget.createPlant(inputData);

        //　サービステストなのでDBの登録はしない　epository.saveをようぶところまでテスト
        //　登録確認　検証（パラメータが想定通りかと呼び出し回数）
        Plant expectedParam = new Plant();
        //　idは自動で設定しない自動採番になっているためnull　自動採番はDB側で設定してくれる
        expectedParam.setId(null);
        expectedParam.setName("工場1");
        expectedParam.setFurigana("こうじょういち");
        expectedParam.setCreatedAt(LocalDateTime.of(2021, 10, 01, 10, 20));

        //　モックの呼び出し回数の検証　引数の検証　Repository.saveの引数
        verify(mockRepository, times(1)).save(expectedParam);
    }

    @Test
    void getPlant() {
        final var dummyData = new Plant();
        dummyData.setId(1L);
        dummyData.setName("工場1");
        dummyData.setFurigana("こうじょう1");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(dummyData));
        //　テスト対象の実行
        PlantDto plantDto = testTarget.getPlant(1L);

        assertEquals("工場1", plantDto.getName());
    }

    @Test
    void updatePlant() {
        //　登録用のダミーデータ
        //　PlantがDB？
        final var dummyData = new Plant();
        dummyData.setId(1L);
        dummyData.setName("工場1");
        dummyData.setFurigana("こうじょう1");
        dummyData.setCreatedAt(LocalDateTime.of(2021, 10, 01, 10, 20));

        when(mockRepository.findById(1L)).thenReturn(Optional.of(dummyData));
        when(datetime.now()).thenReturn(LocalDateTime.of(2021, 11, 01, 10, 20));
        //　更新用のダミーデータ
        //　データを渡したいときDTO
        PlantDto inputData = new PlantDto();
        inputData.setName("工場2");
        inputData.setFurigana("こうじょうに");

        testTarget.updatePlant(1L, inputData);

        final var expectedParam = new Plant();
        expectedParam.setId(1L);
        expectedParam.setName("工場2");
        expectedParam.setFurigana("こうじょうに");
        expectedParam.setCreatedAt(LocalDateTime.of(2021, 10, 01, 10, 20));
        expectedParam.setUpdatedAt(LocalDateTime.of(2021, 11, 01, 10, 20));

        //　モックの呼び出し回数の検証　引数の検証　Repository.saveの引数
        verify(mockRepository, times(1)).save(expectedParam);
    }
    //　Mockitoの場合
    //　@InjectMocks
    //　PlantService plantService;
    //　mockRepositoryにわたってきた引数の確認
    //　@Captor
    //　ArgumentCaptor<ScheduledPlantService> scheduledPlantServiceCaptor;

    //　インナークラスの際
    //　@Nested
    //　doReturn(1).when(mockRepository).save()


}
