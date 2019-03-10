package main.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="")
public class IndexController {
	
	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("")
	public String getIndex() {
//		return bCryptPasswordEncoder.encode("12345678");
		return "Hello";
	}
	
	@GetMapping("index")
	public String getForFun(@RequestParam("id") Long id,
							@RequestParam("name") String name) {

		return id.toString() + name;
	}
	
	@GetMapping("signin")
	public void login() {
		
	}
	
	@GetMapping("signout")
	public void logout() {
	}
}