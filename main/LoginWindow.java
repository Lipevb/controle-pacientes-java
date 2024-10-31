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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login | Clinica odontologica");

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

        // Parte direita: Formulário de login
        VBox rightPane = new VBox(15);
        rightPane.setPadding(new Insets(40));
        rightPane.setAlignment(Pos.CENTER_LEFT);

        Label loginLabel = new Label("Login");
        loginLabel.setFont(new Font("Arial", 20));
        loginLabel.setTextFill(Color.BLACK);

        // Campos de login
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(320);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(320);

        // Botão de login
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #00ced1; -fx-text-fill: white; -fx-font-size: 14px;");
        loginButton.setMaxWidth(100);

        // Ação do botão de login
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                // Abre a nova tela HomeScreen
                MainPage MainPage = new MainPage();
                try {
                    MainPage.start(new Stage());  
                    primaryStage.close(); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                showAlert("Falha no Login", "Usuário ou senha incorretos.");
            }
        });

        // Texto para mostrar que o servidor está funcionando
        Label serverStatusLabel = new Label("                           ");
        serverStatusLabel.setTextFill(Color.GREEN);
        serverStatusLabel.setFont(new Font("Arial", 12));

        // Label "Esqueci a Senha" no formato de link
        Label forgotPasswordLabel = new Label("Esqueci a senha");
        forgotPasswordLabel.setTextFill(Color.BLUE);
        forgotPasswordLabel.setFont(new Font("Arial", 12));
        forgotPasswordLabel.setStyle("-fx-underline: true;");
        forgotPasswordLabel.setTextAlignment(TextAlignment.RIGHT);

        // Ação para o link "Esqueci a senha"
        forgotPasswordLabel.setOnMouseClicked(e -> {
            showAlert("Recuperação de senha", "Redirecionar para recuperação de senha.");
        });

        HBox statusBox = new HBox(10, serverStatusLabel, forgotPasswordLabel);
        statusBox.setAlignment(Pos.CENTER_RIGHT);

        // Botão de registro (Redireciona para a página de cadastro)
        Button registerButton = new Button("Cadastrar");
        registerButton.setStyle("-fx-background-color: #00ced1; -fx-text-fill: white; -fx-font-size: 14px;");
        registerButton.setMaxWidth(100);

        // Transição para a página de cadastro
        registerButton.setOnAction(e -> {
            UserRegistration registration = new UserRegistration();
            try {
                registration.start(new Stage());  
                primaryStage.close(); 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Adicionando componentes ao lado direito
        rightPane.getChildren().addAll(loginLabel, usernameField, passwordField, statusBox, loginButton, registerButton);

        // Organizando tudo em um HBox
        HBox root = new HBox();
        root.getChildren().addAll(leftPane, rightPane);
        root.setPrefSize(600, 400);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para autenticar usuário
    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:sqlite:poo_java.db";
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro de conexão", "Não foi possível conectar ao banco de dados.");
            return false;
        }
    }

    // Método auxiliar para exibir alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
