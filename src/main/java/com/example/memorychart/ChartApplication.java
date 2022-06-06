package com.example.memorychart;

import com.example.memorychart.controller.HelloController;
import com.example.memorychart.model.Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChartApplication extends Application {
    public static Stage staticStage;

    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(ChartApplication::showErrorDialog);
        stage.setResizable(false);
        staticStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ChartApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            System.exit(0);
        });
    }

    private static void showErrorDialog(Thread thread, Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Iltimos \"Size\" bo`limiga raqam formatidagi text kiriting", ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}