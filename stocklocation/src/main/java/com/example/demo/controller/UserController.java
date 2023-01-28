package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	//	@RequestMapping("/plant")
	//	public String plant() {
	//		return "plant/plant.html";
	//	}

	@Autowired
	private UserService userService;

	@RequestMapping("/plant")
	public String plant(Model model) {
		List<User> userlist = userService.searchAll();
		model.addAttribute("userlist", userlist);
		return "plant/plant.html";
	}

	@RequestMapping("/plant/new")
	public String plantnew() {
		return "plant/plant_new.html";
	}

}
