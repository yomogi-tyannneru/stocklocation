package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工場情報 リクエストデータ
 */
@Data
public class PlantDto implements Serializable {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 名前
	 */
	@NotEmpty(message = "名前を入力してください")
	private String name;
	/**
	 * ふりがな
	 */
	private String furigana;
	/**
	 * 作成日時
	 */
	private LocalDateTime createdAt;
}