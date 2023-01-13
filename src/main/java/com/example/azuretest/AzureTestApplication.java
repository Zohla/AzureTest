package com.example.azuretest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public String getDatabase() throws SQLException {

		try (Connection conn = dataSource.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT 1")){
			return rs.getString("1");
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(AzureTestApplication.class, args);
	}

}
