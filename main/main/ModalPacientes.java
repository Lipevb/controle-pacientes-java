package com.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ModalPacientes {
    private StackPane contentArea;
    private TableView<PacientesTabela> table;

    public ModalPacientes(StackPane contentArea) {
        this.contentArea = contentArea;
        initializeTable(); // Inicializa a tabela
    }

    public void display() {
        VBox patientBox = new VBox(10);
        patientBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Pacientes");
        header.setFont(new Font("Arial", 20));

        Button newButton = new Button("Novo");
        newButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-padding: 10px 20px;");
        newButton.setOnAction(e -> openModal("Ficha de Cadastro de Paciente"));

        Button editButton = new Button("Editar");
        editButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-padding: 10px 20px;");

        HBox actions = new HBox(10, newButton, editButton);
        actions.setAlignment(Pos.CENTER_LEFT);
        patientBox.getChildren().addAll(header, actions, table); // Adiciona a tabela
        contentArea.getChildren().add(patientBox);
    }

    @SuppressWarnings("unchecked")
    private void initializeTable() {
        table = new TableView<>();
        TableColumn<PacientesTabela, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<PacientesTabela, String> cpfColumn = new TableColumn<>("CPF");
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<PacientesTabela, String> codeColumn = new TableColumn<>("Código");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        // Adiciona as colunas à tabela
        table.getColumns().addAll(nameColumn, cpfColumn, codeColumn);
        table.setItems(getPatients());
        table.setPrefHeight(200); // Define uma altura padrão para a tabela
    }

    private ObservableList<PacientesTabela> getPatients() {
        List<PacientesTabela> patientList = new ArrayList<>();
        
        // Apenas exemplos, futuramente será usado com banco de dados
        patientList.add(new PacientesTabela("Eduardo Silva", "123.456.789-00", 3));
        patientList.add(new PacientesTabela("Eduarda Oliveira", "987.654.321-00", 2));
        patientList.add(new PacientesTabela("Ana Pereira", "456.123.789-00", 1));
        
        // Ordena a lista de pacientes pelo nome
        Collections.sort(patientList, Comparator.comparing(PacientesTabela::getName));
        
        return FXCollections.observableArrayList(patientList);
    }

    private void openModal(String title) {
        // Criando uma nova janela para o formulário
        Stage modalStage = new Stage();
        modalStage.setTitle(title);

        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        // Campos do formulário
        TextField nameField = new TextField();
        nameField.setPromptText("Nome");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("Data de Nascimento (DD/MM/YYYY)");
        TextField addressField = new TextField();
        addressField.setPromptText("Endereço");
        TextField cepField = new TextField();
        cepField.setPromptText("CEP");
        TextField stateField = new TextField();
        stateField.setPromptText("Estado");
        TextField cityField = new TextField();
        cityField.setPromptText("Cidade");
        TextField neighborhoodField = new TextField();
        neighborhoodField.setPromptText("Bairro");
        TextField cpfField = new TextField();
        cpfField.setPromptText("CPF");
        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        TextField homePhoneField = new TextField();
        homePhoneField.setPromptText("Telefone Residencial");
        TextField mobileField = new TextField();
        mobileField.setPromptText("Celular (com DDD)");
        TextField civilStatusField = new TextField();
        civilStatusField.setPromptText("Estado Civil");
        TextField genderField = new TextField();
        genderField.setPromptText("Gênero");
        TextField rgField = new TextField();
        rgField.setPromptText("RG");
        TextField codeField = new TextField();
        codeField.setPromptText("Código");

        
        Button saveButton = new Button("Salvar");
        saveButton.setOnAction(e -> {
            // Adicionar aqui a parte de salvar os dados
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Paciente cadastrado com sucesso.");
            alert.showAndWait();
            modalStage.close();
        });

        form.getChildren().addAll(
                nameField, birthDateField, addressField, cepField, stateField,
                cityField, neighborhoodField, cpfField, emailField,
                homePhoneField, mobileField, civilStatusField, genderField,
                rgField, codeField, saveButton
        );

        Scene scene = new Scene(form, 400, 600);
        modalStage.setScene(scene);
        modalStage.show();
    }
}
