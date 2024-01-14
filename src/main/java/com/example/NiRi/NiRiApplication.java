package com.example.NiRi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.NiRi")
public class NiRiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(NiRiApplication.class, args);


	}
}

/*
		String jdbcUrl = "jdbc:mysql://localhost:3306/NiRi";
		String username = "root";
		String password = "niri2214";

		String sql = "INSERT INTO User (name, email , password ) VALUES (?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
			PreparedStatement pstmt = connection.prepareStatement(sql);{


				pstmt.setString(1, "Maria Haag");
				pstmt.setString(2, "maria.haag@example.com");
				pstmt.setString(3, "misc3876");

				pstmt.executeUpdate();
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows > 0) {
					System.out.println("User data inserted successfully.");
				} else {
					System.err.println("Failed to insert user data.");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error executing SQL query: " + e.getMessage());
		}*/
