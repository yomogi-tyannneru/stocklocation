package com.example.demo.controller;

import com.example.demo.form.SignupForm;
import com.example.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/signup")
	public String viewSignup(SignupForm signupForm) {
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@Validated SignupForm signupForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// SignupFormのアノテーションによるバリデーションでエラーがあった場合
			return "signup";
		}
		try {
			userDetailsServiceImpl.register(signupForm.getUsername(), signupForm.getPassword(), "ROLE_USER");
		} catch (DataAccessException e) {
			model.addAttribute("signupError", "ユーザー登録に失敗しました");
			return "signup";
		}
		// ユーザー登録成功後、ログイン状態にすることも可能だが
		// （参考:https://qiita.com/t-yama-3/items/a538d47b8f0a27639d23#7-%E3%83%A6%E3%83%BC%E3%82%B6%E3%83%BC%E7%99%BB%E9%8C%B2%E3%82%92%E3%81%97%E3%81%A6%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%99%E3%82%8B%E8%BF%BD%E5%8A%A0%E4%BA%8B%E9%A0%85）
		// 今回はログイン画面に遷移させる
		return "redirect:/login";
	}
}
