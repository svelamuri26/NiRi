package com.example.NiRi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@SpringBootApplication
public class NiRiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(NiRiApplication.class, args);
	}


	public void run(String... args) {

		insertUserData("Mike Brown", "mike.brown@example.com", "secretPassword123");
	}

	private void insertUserData(String name, String email, String password) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/your_database_name";
		String username = "root";
		 password = "niri2214";

		try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
			String insertSql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, password);

				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0) {
					System.out.println("User data inserted successfully.");
				} else {
					System.err.println("Failed to insert user data.");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error executing SQL query: " + e.getMessage());
		}


	}
}
