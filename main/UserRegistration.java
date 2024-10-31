package com.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistration extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration | Clinica odontologica");

        // Parte esquerda: Logo e Descrição
        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(40));
        leftPane.setBackground(new Background(new BackgroundFill(Color.web("#00ced1"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label logoLabel = new Label("Clinica odontologica");
        logoLabel.setTextFill(Color.WHITE);
        logoLabel.setFont(new Font("Arial", 24));

        Label descriptionLabel = new Label("A melhor para o seu dente");
        descriptionLabel.setTextFill(Color.WHITE);
        descriptionLabel.setFont(new Font("Arial", 14));

        leftPane.getChildren().addAll(logoLabel, descriptionLabel);

        // Parte direita: Formulário de cadastro
        VBox rightPane = new VBox(15);
        rightPane.setPadding(new Insets(40));
        rightPane.setAlignment(Pos.CENTER_LEFT);

        Label registerLabel = new Label("Cadastrar");
        registerLabel.setFont(new Font("Arial", 20));
        registerLabel.setTextFill(Color.BLACK);

        // Campos de cadastro
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(250);

        TextField usernameField = new TextField();
        usernameField.setPromptText("First Name");
        usernameField.setMaxWidth(250);

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.setMaxWidth(250);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(250);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setMaxWidth(250);

        // Botão de registro
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #00ced1; -fx-text-fill: white; -fx-font-size: 14px;");
        registerButton.setMaxWidth(100);

        // Ação do botão de registro
        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String username = usernameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (validateRegistration(email, username, lastName, password, confirmPassword)) {
                System.out.println("Registrando usuário: " + email + ", " + username + ", " + lastName);
                if (registerUser(email, username, lastName, password)) {
                    showAlert(Alert.AlertType.INFORMATION, "Cadastro bem-sucedido", "Usuário registrado com sucesso!");
                    primaryStage.close(); // Fecha a tela de registro
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao registrar usuário. Tente novamente.");
                }
            }
        });

        // Botão para voltar à tela de login
        Button backButton = new Button("Voltar para Login");
        backButton.setStyle("-fx-background-color: #ff7f50; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setMaxWidth(100);
        
        // Ação do botão de voltar
        backButton.setOnAction(e -> {
            primaryStage.close(); // Fecha a tela de registro
            // Aqui você deve abrir a tela de login novamente
            LoginWindow loginWindow = new LoginWindow(); // Supondo que você tenha uma classe chamada LoginWindow
            loginWindow.start(new Stage()); // Inicia a tela de login
        });

        // Adicionando os campos ao painel da direita
        rightPane.getChildren().addAll(registerLabel, emailField, usernameField, lastNameField, passwordField, confirmPasswordField, registerButton, backButton);

        // Organizando tudo em um HBox
        HBox root = new HBox();
        root.getChildren().addAll(leftPane, rightPane);
        root.setPrefSize(600, 400);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para registrar o usuário no banco de dados
    private boolean registerUser(String email, String username, String lastName, String password) {
        String url = "jdbc:sqlite:poo_java.db";  // Caminho do banco de dados
        String sql = "INSERT INTO users (email, username, last_name, password) VALUES (?, ?, ?, ?)";
    
        try {
            // Registra o driver JDBC
            Class.forName("org.sqlite.JDBC");
            
            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, username);
                pstmt.setString(3, lastName);
                pstmt.setString(4, password);
    
                pstmt.executeUpdate();
                return true;  // Registro bem-sucedido
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;  // Erro no registro
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do SQLite não encontrado.");
            e.printStackTrace();
            return false;  // Erro no registro
        }
    }

    // Método de validação do formulário
    private boolean validateRegistration(String email, String username, String lastName, String password, String confirmPassword) {
        if (email.isEmpty() || username.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Todos os campos devem ser preenchidos!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "As senhas não coincidem!");
            return false;
        }

        // Adicione outras verificações aqui, como o formato do email ou a força da senha.
        return true;
    }

    // Método para exibir alertas
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
