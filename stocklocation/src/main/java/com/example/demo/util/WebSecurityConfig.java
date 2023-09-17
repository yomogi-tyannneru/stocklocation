package com.example.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	/**
	 * パスワードのハッシュアルゴリズムを指定する
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		Pbkdf2PasswordEncoder passwordEncoder =
				Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
		passwordEncoder.setEncodeHashAsBase64(true);
		return passwordEncoder;
	}

	/**
	 * ログインチェックを除外する設定など
	 * 例えばユーザー登録ページはログインチェック外す
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/login").permitAll()
						.requestMatchers("/signup").permitAll()
						.requestMatchers("/error").permitAll()
						.requestMatchers("/common/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/js/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login"))
				.build();
	}
}