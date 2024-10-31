package com.main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainPage extends Application {

    private BorderPane root;
    private StackPane contentArea;
    private LocalDate currentDate = LocalDate.now();
    private Label consultationDateLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        contentArea = new StackPane();

        VBox sidebar = createSidebar();
        root.setLeft(sidebar);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Gerenciamento de Pacientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f2f2f2; -fx-border-color: #ddd; -fx-border-width: 0 1 0 0;");

        Label title = new Label("CLÍNICA LIFE");
        title.setFont(new Font("Arial", 24));
        title.setStyle("-fx-padding: 0 0 20 0;");

        sidebar.getChildren().add(title);
        sidebar.getChildren().addAll(
                createSidebarItem("Agenda", this::showCalendar),
                createSidebarItem("Paciente", this::showPatientManagement),
                createSidebarItem("Profissional", this::showProfessionalManagement),
                createSidebarItem("Consulta", this::showConsultationManagement),
                createSidebarItem("Sair", null)
        );

        return sidebar;
    }

    private HBox createSidebarItem(String text, Runnable action) {
        HBox item = new HBox(10);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(10));
        item.setStyle("-fx-cursor: hand;");

       
        Label label = new Label(text);
        label.setFont(new Font("Arial", 18));
        label.setStyle("-fx-text-fill: white; ");

        
        Label arrow = new Label("▶");
        arrow.setFont(new Font("Arial", 18));
        arrow.setStyle("-fx-text-fill: white; ");

        item.getChildren().addAll(label, arrow);

      
        if (action != null) {
            item.setOnMouseClicked(e -> action.run());
        }

        return item;
    }

    private void showPatientManagement() {
        contentArea.getChildren().clear();
        VBox patientBox = new VBox(10);
        patientBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Pacientes");
        header.setFont(new Font("Arial", 20));

        Button newButton = new Button("Novo");
        newButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 10px 20px;");
        newButton.setOnAction(e -> openModal("Novo Paciente"));

        Button editButton = new Button("Editar");
        editButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 10px 20px;");

        HBox actions = new HBox(10, newButton, editButton);
        actions.setAlignment(Pos.CENTER_LEFT);
        patientBox.getChildren().addAll(header, actions);
        contentArea.getChildren().add(patientBox);
    }

    private void showProfessionalManagement() {
        contentArea.getChildren().clear();
        VBox professionalBox = new VBox(10);
        professionalBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Profissionais");
        header.setFont(new Font("Arial", 20));

        Button newButton = new Button("Novo");
        newButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 10px 20px;");
        newButton.setOnAction(e -> openModal("Novo Profissional"));

        Button editButton = new Button("Editar");
        editButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white; -fx-padding: 10px 20px;");

        HBox actions = new HBox(10, newButton, editButton);
        actions.setAlignment(Pos.CENTER_LEFT);
        professionalBox.getChildren().addAll(header, actions);
        contentArea.getChildren().add(professionalBox);
    }

    private void showConsultationManagement() {
        contentArea.getChildren().clear();
        VBox consultationBox = new VBox(10);
        consultationBox.setPadding(new Insets(20));

        Label header = new Label("Gerenciamento de Consultas");
        header.setFont(new Font("Arial", 20));

        HBox navigation = new HBox(10);
        Button prevButton = new Button("Anterior");
        prevButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white;");
        
        Button nextButton = new Button("Próximo");
        nextButton.setStyle("-fx-background-color: #006400; -fx-text-fill: white;");

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

    private void openModal(String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText("Funcionalidade ainda não implementada.");
        alert.showAndWait();
    }

    private void showCalendar() {
        contentArea.getChildren().clear();
        Label calendarLabel = new Label("Calendário (funcionalidade a ser implementada)");
        calendarLabel.setFont(new Font("Arial", 16));
        contentArea.getChildren().add(calendarLabel);
    }
}
