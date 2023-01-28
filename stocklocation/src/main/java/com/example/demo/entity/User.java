package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "plants")
public class User implements Serializable {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 名前
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 住所
	 */
	@Column(name = "furigana")
	private String furigana;
	/**
	 * 更新日時
	 */
	@Column(name = "created_at")
	private Date created_at;
	/**
	 * 登録日時
	 */
	@Column(name = "updated_at")
	private Date updated_at;
	/**
	 * 削除日時
	 */
	@Column(name = "create_user_id")
	private Long create_user_id;
	/**
	 * 削除日時
	 */
	@Column(name = "update_user_id")
	private Long update_user_id;
}
