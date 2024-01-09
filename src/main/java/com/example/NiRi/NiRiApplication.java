package com.example.NiRi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class NiRiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiRiApplication.class, args);

		String url = "jdbc:mysql://localhost:3306/NiRi";
		String username = "root";
		String password = "niri2214";



	}

}
