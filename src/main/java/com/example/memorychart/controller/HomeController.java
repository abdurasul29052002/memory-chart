package com.example.memorychart.controller;

import com.example.memorychart.ChartApplication;
import com.example.memorychart.model.Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import static com.example.memorychart.ChartApplication.*;

public class HomeController {
    public JFXTextField textFieldSize;
    public JFXButton buttonNext;

    @SneakyThrows
    public void buttonNextOnClicked(){
        long size = Long.parseLong(textFieldSize.getText());
        HelloController.availableSpace=new Data("Available space",size);
        FXMLLoader fxmlLoader = new FXMLLoader(ChartApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        staticStage.setScene(scene);
        staticStage.setTitle("Memory Chart");
        staticStage.show();
    }
}
