import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage extends Application {

    private BorderPane root;
    private VBox sidebar;
    private VBox submenuSidebar;
    private StackPane formContainer;

    @Override
    public void start(Stage primaryStage) {
    
        root = new BorderPane();
        root.setPadding(new Insets(10));

        
        sidebar = new VBox(10);
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");

        
        HBox logo = new HBox();
        logo.setAlignment(Pos.CENTER);
        Label logoIcon = new Label("\uf1eb"); 
        logoIcon.setStyle("-fx-font-size: 24px; -fx-font-family: 'FontAwesome';");
        logo.getChildren().add(logoIcon);

        
        HBox menuItem = new HBox(10);
        menuItem.setAlignment(Pos.CENTER_LEFT);
        Label menuItemIcon = new Label("\uf2bd"); 
        menuItemIcon.setStyle("-fx-font-size: 18px; -fx-font-family: 'FontAwesome';");
        Label menuItemText = new Label("Pacientes ▶");
        menuItem.getChildren().addAll(menuItemIcon, menuItemText);

        submenuSidebar = new VBox(10);
        submenuSidebar.setPrefWidth(200);
        submenuSidebar.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
        submenuSidebar.setVisible(false);

        
        HBox submenuItem = new HBox(10);
        submenuItem.setAlignment(Pos.CENTER_LEFT);
        Label submenuItemIcon = new Label("\uf234"); 
        submenuItemIcon.setStyle("-fx-font-size: 18px; -fx-font-family: 'FontAwesome';");
        Label submenuItemText = new Label("Cadastrar pacientes ▶");
        submenuItem.getChildren().addAll(submenuItemIcon, submenuItemText);

        
        formContainer = new StackPane();
        formContainer.setPrefSize(400, 300);
        formContainer.setStyle("-fx-background-color: #fff; -fx-border-color: #ccc;");
        formContainer.setVisible(false);

        //form
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));
        Label formTitle = new Label("Cadastrar Pacientes");
        formTitle.setStyle("-fx-font-size: 18px;");
        
        TextField nome = new TextField();
        nome.setPromptText("Nome");
        TextField nascimento = new TextField();
        nascimento.setPromptText("Data de nascimento");
        TextField endereco = new TextField();
        endereco.setPromptText("Endereço");
        TextField cep = new TextField();
        cep.setPromptText("CEP");
        TextField estado = new TextField();
        estado.setPromptText("Estado");
        TextField cidade = new TextField();
        cidade.setPromptText("Cidade");
        TextField bairro = new TextField();
        bairro.setPromptText("Bairro");
        TextField cpf = new TextField();
        cpf.setPromptText("CPF");
        TextField email = new TextField();
        email.setPromptText("Email");
        TextField telefoneResidencial = new TextField();
        telefoneResidencial.setPromptText("Telefone Residencial");
        TextField celular = new TextField();
        celular.setPromptText("Celular");
        TextField dataNascimento = new TextField();
        dataNascimento.setPromptText("Data de Nascimento");
        TextField estadoCivil = new TextField();
        estadoCivil.setPromptText("Estado Civil");
        TextField genero = new TextField();
        genero.setPromptText("Gênero");
        TextField rg = new TextField();
        rg.setPromptText("RG");
        TextField codigo = new TextField();
        codigo.setPromptText("Código");
        
        Button cadastrar = new Button("Cadastrar");
        form.getChildren().addAll(formTitle, nome, nascimento, endereco, cep, estado,cidade,bairro,cpf, email, telefoneResidencial, celular, 
        dataNascimento, estadoCivil, genero, rg,codigo, cadastrar);
        //from
    
        sidebar.getChildren().addAll(logo, menuItem);
        submenuSidebar.getChildren().addAll(submenuItem);
        formContainer.getChildren().add(form);
        root.setLeft(sidebar);
        root.setCenter(submenuSidebar);
        root.setRight(formContainer);
        menuItem.setOnMouseClicked(e -> {
            submenuSidebar.setVisible(!submenuSidebar.isVisible());
        });
        submenuItem.setOnMouseClicked(e -> {
            formContainer.setVisible(!formContainer.isVisible());
        });

         //scrollbar
         ScrollPane scrollPane = new ScrollPane();
         scrollPane.setContent(form);
         scrollPane.setFitToWidth(true);
         scrollPane.setFitToHeight(true);
         scrollPane.setPrefWidth(400);
         scrollPane.setPrefHeight(300);
 
         formContainer.getChildren().clear();
         formContainer.getChildren().add(scrollPane);
         
       

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Protótipo app dental");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    
    }
}
