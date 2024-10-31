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
        sidebar.setStyle("-fx-background-color: #ADD8E6; -fx-border-color: #ddd; -fx-border-width: 0 1 0 0;");

        Label title = new Label("App Dental");
        title.setFont(new Font("Arial", 24));
        title.setStyle("-fx-padding: 0 0 20 0; -fx-text-fill: white;");

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
        ModalPacientes patientManagement = new ModalPacientes(contentArea);
        patientManagement.display();
    }

    private void showProfessionalManagement() {
        contentArea.getChildren().clear();
        ModalProfi professionalManagement = new ModalProfi(contentArea);
        professionalManagement.display();
    }

    private void showConsultationManagement() {
        contentArea.getChildren().clear();
        ModalConsulta consultationManagement = new ModalConsulta(contentArea);
        consultationManagement.display();
    }

    private void showCalendar() {
        contentArea.getChildren().clear();
        Label calendarLabel = new Label("Calendário (funcionalidade a ser implementada)");
        calendarLabel.setFont(new Font("Arial", 16));
        contentArea.getChildren().add(calendarLabel);
    }
}
