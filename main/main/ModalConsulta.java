package com.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class ModalConsulta {
    private StackPane contentArea;
    private LocalDate currentDate = LocalDate.now();
    private Label consultationDateLabel;

    public ModalConsulta(StackPane contentArea) {
        this.contentArea = contentArea;
    }

    public void display() {
        VBox consultationBox = new VBox(10);
        consultationBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Consultas");
        header.setFont(new Font("Arial", 20));

        HBox navigation = new HBox(10);
        Button prevButton = new Button("Anterior");
        prevButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white;");

        Button nextButton = new Button("Próximo");
        nextButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white;");

        consultationDateLabel = new Label(currentDate.toString());
        consultationDateLabel.setFont(new Font("Arial", 16));

        prevButton.setOnAction(e -> {
            currentDate = currentDate.minusDays(1);
            consultationDateLabel.setText(currentDate.toString());
        });

        nextButton.setOnAction(e -> {
            currentDate = currentDate.plusDays(1);
            consultationDateLabel.setText(currentDate.toString());
        });

        navigation.getChildren().addAll(prevButton, consultationDateLabel, nextButton);
        consultationBox.getChildren().addAll(header, navigation, createSchedule());
        contentArea.getChildren().add(consultationBox);
    }

    private VBox createSchedule() {
        VBox schedule = new VBox(5);
        schedule.setPadding(new Insets(10));

        for (int i = 7; i <= 19; i++) {
            HBox hourBlock = new HBox();
            hourBlock.setStyle("-fx-border-color: #ccc; -fx-padding: 10px; -fx-alignment: center;");

            Label hourLabel = new Label(String.format("%02d:00 - %02d:00", i, i + 1));
            hourLabel.setPrefWidth(100);
            TextField consultationField = new TextField();
            consultationField.setPromptText("Consulta");
            consultationField.setPrefWidth(200);

            hourBlock.getChildren().addAll(hourLabel, consultationField);
            schedule.getChildren().add(hourBlock);
        }
        return schedule;
    }
}
