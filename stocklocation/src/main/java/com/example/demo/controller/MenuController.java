package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー Controller
 */
@Controller
public class MenuController {
	/**
	 * メニュー画面を表示
	 * @return メニュー画面
	 *
	 */

	@GetMapping("/")
	public String index() {
		return "menu";
	}
}
