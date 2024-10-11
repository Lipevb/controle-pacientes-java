import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Page");

        //Criando o campo para o login 
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label userLabel = new Label("USUÁRIO:");
        userLabel.setTextFill(Color.web("#00c4cc"));
        userLabel.setFont(new Font("Arial", 14));
        TextField userTextField = new TextField();

        Label passwordLabel = new Label("SENHA:");
        passwordLabel.setTextFill(Color.web("#00c4cc"));
        passwordLabel.setFont(new Font("Arial", 14));
        PasswordField passwordField = new PasswordField();

        
        Button loginButton = new Button("Entrar");
        loginButton.setStyle("-fx-background-color: #00c4cc; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 20px;");
        loginButton.setOnMouseEntered(e -> loginButton.setStyle("-fx-background-color: #00a3a3; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 20px;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle("-fx-background-color: #00c4cc; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-background-radius: 20px;"));

        // Ativando função de verificação(Lembra)
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            Login_logic login = new Login_logic(username, password);
            if (login.validateLogin()) {
                showLoginMessage("Login válido!", "Você está logado com sucesso!");
            } else {
                showLoginMessage("Login inválido!", "Verifique seu usuário e senha.");
            }
        });

        // Adicionando as coisas na tela
        grid.add(userLabel, 0, 0);
        grid.add(userTextField, 0, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 0, 3);
        grid.add(loginButton, 0, 4);

        // Quadrado do campo de login
        VBox vbox = new VBox(grid);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(40));
        vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        vbox.setMaxWidth(400); 
        vbox.setMaxHeight(300); 

        //Fundo
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#00c4cc"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(vbox);

        //Tamanho(Pesquisar depois como fazer automático)
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLoginMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}