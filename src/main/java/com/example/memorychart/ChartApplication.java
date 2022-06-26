package com.example.memorychart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class ChartApplication extends Application {
    public static Stage staticStage;

    @SneakyThrows
    @Override
    public void start(Stage stage) {
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