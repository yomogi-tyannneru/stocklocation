package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
