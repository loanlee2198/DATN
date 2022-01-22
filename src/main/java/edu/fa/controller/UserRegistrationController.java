package edu.fa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fa.dto.CustomerRegisterDto;
import edu.fa.model.Customer;
import edu.fa.service.AdminService;
import edu.fa.service.UserService;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
	@Autowired
	UserService customerService;
	@Autowired
	AdminService adminService;

	@ModelAttribute("user")
	public CustomerRegisterDto userRegistrationDto() {
		return new CustomerRegisterDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "user/registration";

	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") CustomerRegisterDto customerRegisterDto) {
//		List<String> usernameList = adminService.findAllCustomer().stream().map(Customer::getUsername).toList();
		System.out.println("==============" + customerRegisterDto.toString());
		List<String> usernameList = new ArrayList<>();
		for (Customer customer : adminService.findAllCustomer()) {
			String username = customer.getUsername();
			usernameList.add(username);
		}
		if (usernameList.contains(customerRegisterDto.getUsername())) {
			return "redirect:/register?err";
		} else {
			customerService.save(customerRegisterDto);
			System.out.println(customerRegisterDto.toString());
			return "redirect:/register?success";
		}

	}

}
