package com.main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GerenciamentoPacientesApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Painel lateral (Sidebar)
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #f2f2f2; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 5, 0, 2, 0);");

        Label clinicTitle = new Label("CLÍNICA LIFE");
        clinicTitle.setFont(new Font(24));
        sidebar.getChildren().add(clinicTitle);

        // Menu da sidebar
        String[] menuItems = {"Agenda", "Paciente", "Profissional", "Consulta", "Avaliação", "Usuário", "Procedimento", "Sair"};
        for (String item : menuItems) {
            Button menuItem = new Button(item);
            menuItem.setPrefWidth(160);
            menuItem.setStyle("-fx-background-color: transparent; -fx-text-fill: #006400; -fx-font-size: 18;");
            sidebar.getChildren().add(menuItem);
        }

        // Painel de conteúdo principal
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        
        // Cabeçalho
        HBox header = new HBox(10);
        Label headerTitle = new Label("Home > Paciente");
        headerTitle.setFont(new Font(20));
        
        HBox actions = new HBox(10);
        Button novoBtn = new Button("Novo");
        novoBtn.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 5px 15px;");
        Button editarBtn = new Button("Editar");
        editarBtn.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 5px 15px;");
        
        actions.getChildren().addAll(novoBtn, editarBtn);

        HBox searchBox = new HBox(5);
        Label searchLabel = new Label("Pesquisar:");
        TextField searchField = new TextField();
        Button searchButton = new Button("Pesquisar");
        searchButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white;");
        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);

        header.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().addAll(headerTitle, actions, searchBox);

        // Tabela de Pacientes
        TableView<Paciente> table = new TableView<>();
        
        TableColumn<Paciente, Integer> codigoCol = new TableColumn<>("Código");
        codigoCol.setPrefWidth(100);
        TableColumn<Paciente, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setPrefWidth(200);
        TableColumn<Paciente, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setPrefWidth(150);
        TableColumn<Paciente, String> dataNascimentoCol = new TableColumn<>("Data de Nascimento");
        dataNascimentoCol.setPrefWidth(150);

        table.getColumns().addAll(codigoCol, nomeCol, cpfCol, dataNascimentoCol);
        
        // Exemplo de dados
        table.getItems().add(new Paciente(1, "João da Silva", "", "21/05/1985"));
        table.getItems().add(new Paciente(2, "Luana Frigério de Souza", "", "11/04/1987"));

        content.getChildren().addAll(header, table);
        
        // Layout principal
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, content);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Gerenciamento de Pacientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Classe para representar um paciente
    public class Paciente {
        private final Integer codigo;
        private final String nome;
        private final String cpf;
        private final String dataNascimento;

        public Paciente(Integer codigo, String nome, String cpf, String dataNascimento) {
            this.codigo = codigo;
            this.nome = nome;
            this.cpf = cpf;
            this.dataNascimento = dataNascimento;
        }

        public Integer getCodigo() { return codigo; }
        public String getNome() { return nome; }
        public String getCpf() { return cpf; }
        public String getDataNascimento() { return dataNascimento; }
    }
}
