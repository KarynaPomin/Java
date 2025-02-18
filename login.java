package com.example.singletonproperties;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label responseLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String response = sendLoginRequest(username, password);
            responseLabel.setText(response);
        });

        VBox root = new VBox(10, usernameField, passwordField, loginButton, responseLabel);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("Login Test");
        primaryStage.show();
    }

    private String sendLoginRequest(String username, String password) {
        try {
            URL url = new URL("http://localhost:3001/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString(); // Return JSON response

        } catch (Exception e) {
            return "Login error: " + e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
}
