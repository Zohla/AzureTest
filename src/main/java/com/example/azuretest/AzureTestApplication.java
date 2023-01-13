package com.example.azuretest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@SpringBootApplication
@RestController
public class AzureTestApplication {

	final
	DataSource dataSource;

	public AzureTestApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@GetMapping("/")
	public String helloWorld(@RequestParam(defaultValue = "Cloud") String name) {
		return "Hello " + name;
	}
	@GetMapping("/user-agent")
	public String userAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	@GetMapping("/db")
	public String getDatabase(){
		return "SELECT 1";

	}

	public static void main(String[] args) {
		SpringApplication.run(AzureTestApplication.class, args);
	}

}
