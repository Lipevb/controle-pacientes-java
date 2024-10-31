package com.main;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ModalProfi {
    private StackPane contentArea;
    private List<String> professionals; 

    public ModalProfi(StackPane contentArea) {
        this.contentArea = contentArea;
        this.professionals = new ArrayList<>(); 
    }

    public void display() {
        VBox professionalBox = new VBox(10);
        professionalBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Profissionais");
        header.setFont(new Font("Arial", 20));

        Button newButton = new Button("Novo");
        newButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-padding: 10px 20px;");
        newButton.setOnAction(e -> openModal("Cadastro de Profissional"));

        Button editButton = new Button("Editar");
        editButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-padding: 10px 20px;");

        HBox actions = new HBox(10, newButton, editButton);
        actions.setAlignment(Pos.CENTER_LEFT);
        professionalBox.getChildren().addAll(header, actions);

        // Criação da tabela de profissionais
        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableView.getColumns().add(nameColumn);
        professionalBox.getChildren().add(tableView);

        contentArea.getChildren().add(professionalBox);
    }

    private void openModal(String title) {
        // Criando uma nova janela para o cadastro de profissionais
        Stage modalStage = new Stage();
        modalStage.setTitle(title);

        VBox form = new VBox(10);
        form.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Nome");

        
        Button saveButton = new Button("Salvar");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                professionals.add(name); 
                updateTable(); 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Profissional cadastrado com sucesso.");
                alert.showAndWait();
                modalStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha o nome.");
                alert.showAndWait();
            }
        });

        form.getChildren().addAll(nameField, saveButton);
        
        Scene scene = new Scene(form, 300, 200);
        modalStage.setScene(scene);
        modalStage.show();
    }

    private void updateTable() {
        
        TableView<String> tableView = (TableView<String>) ((VBox) contentArea.getChildren().get(0)).getChildren().get(2);
        tableView.getItems().clear(); 
        
        tableView.getItems().addAll(professionals);
    }
}
