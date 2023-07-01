package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 工場情報 Entity
 */
@Entity
@Data
@Table(name = "plants")
public class Plant implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * ふりがな
	 */
	@Column(name = "furigana")
	private String furigana;
	/**
	 * 検索用名称
	 */
	@Column(name = "search_name")
	private String searchName;
	/**
	 * 検索用ふりがな
	 */
	@Column(name = "search_furigana")
	private String searchFurigana;
	/**
	 * 作成日時
	 */
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	/**
	 * 更新日時
	 */
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	/**
	 * 作成者
	 */
	@Column(name = "create_user_id")
	private Long createUserId;
	/**
	 * 更新者
	 */
	@Column(name = "update_user_id")
	private Long updateUserId;
}
