package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
// UserDetailsServiceはSpring Securityのインターフェース
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	// 定義しておくとSpringSecurityによってログイン時などに呼ばれる
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Accountは自前のentity, UserはSpring Securityのクラス
		Account account = accountRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		return User.withUsername(account.getUsername())
				.password(account.getPassword())
				.roles("USER")
				.build();
	}

	@Transactional
	public void register(String username, String password, String authority) {
		accountRepository.save(new Account(username, passwordEncoder.encode(password)));
	}
}