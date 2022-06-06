package com.example.memorychart.controller;

import com.example.memorychart.model.Data;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public static Data availableSpace;
    public ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    public ObservableList<String> choiceBoxItems = FXCollections.observableArrayList();
    public JFXTextField textFieldSizeOfDeletingItem;
    public Label notAvailable;
    private Long input = 0L;
    private Long output = 0L;
    private Long notAvailableSpace = 0L;
    private final String sIn = "Qo`shilgan: ";
    private final String sOut = "O`chirilgan: ";
    private final String sNot = "Band hajm: ";
    public ChoiceBox<String> choiceBox;
    public PieChart chart;
    public JFXButton buttonAddItem;
    public JFXButton buttonDeleteItem;
    public JFXTextField textFieldName;
    public JFXTextField textFieldSize;
    public AnchorPane root;
    public Label inputData;
    public Label deleteData;

    Label label = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.getChildren().add(label);
        data.add(new PieChart.Data(availableSpace.getName(), availableSpace.getSize()));
        loadData();
    }

    public void loadData() {
        chart.setData(data);
        choiceBox.setItems(choiceBoxItems);
    }

    @SneakyThrows
    public void addItem(MouseEvent mouseEvent) {
        if (textFieldSize.getText() == null) {
            showErrorMessage("Malumot o`lchamini kiriting");
        } else if (availableSpace.getSize() > 0 && availableSpace.getSize() - Long.parseLong(textFieldSize.getText()) > 0) {
            Data newData = new Data(textFieldName.getText(), Long.parseLong(textFieldSize.getText()));
            availableSpace.setSize(availableSpace.getSize() - newData.getSize());
            data.get(0).setPieValue(availableSpace.getSize());
            data.add(new PieChart.Data(newData.getName(), newData.getSize()));
            choiceBoxItems.add(newData.getName());
            textFieldName.setText(null);
            textFieldSize.setText(null);
            input += newData.getSize();
            notAvailableSpace+= newData.getSize();
            inputData.setText(sIn + input);
            notAvailable.setText(sNot+notAvailableSpace);
        } else {
            showErrorMessage("Diskda bo`sh joy yetmaydi");
        }
        loadData();
    }

    public void deleteItem(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Shu malumotni o`chirmoqchimisiz", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType buttonType = result.orElse(null);
        int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
        PieChart.Data selectedChartData = data.get(selectedIndex + 1);
        long deletingItemSize = Long.parseLong(textFieldSizeOfDeletingItem.getText());
        if (buttonType != null && buttonType == ButtonType.OK && deletingItemSize <= selectedChartData.getPieValue()) {
            if (selectedChartData.getPieValue() == deletingItemSize) {
                data.remove(selectedIndex + 1);
                choiceBoxItems.remove(selectedIndex);
                availableSpace.setSize((long) (availableSpace.getSize() + selectedChartData.getPieValue()));
            }else {
                selectedChartData.setPieValue(selectedChartData.getPieValue()- deletingItemSize);
                availableSpace.setSize(availableSpace.getSize()+ deletingItemSize);
            }
            output += deletingItemSize;
            notAvailableSpace-=deletingItemSize;
            data.get(0).setPieValue(availableSpace.getSize());
            choiceBox.getSelectionModel().select(null);
            deleteData.setText(sOut + output);
            notAvailable.setText(sNot+notAvailableSpace);
            textFieldSizeOfDeletingItem.setText(null);
            loadData();
        }else if(deletingItemSize>selectedChartData.getPieValue()){
            showErrorMessage("Malumot o`lchamidan katta o`lcham kiritildi");
        }
    }

    public void anchorPaneOnMoved(MouseEvent mouseEvent) {
        for (PieChart.Data chartDatum : chart.getData()) {
            chartDatum.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, mouseEvent1 -> {
                label.setTranslateX(mouseEvent1.getSceneX() + 10);
                label.setLayoutY(mouseEvent1.getSceneY() - 10);
                label.setText(String.valueOf((int) chartDatum.getPieValue()));
            });
            chartDatum.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent1 -> {
                label.setText(null);
            });
        }

        if (choiceBox.getSelectionModel().getSelectedItem() == null) {
            buttonDeleteItem.setDisable(true);
        } else {
            buttonDeleteItem.setDisable(false);
        }
    }

    public void showErrorMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
        alert.setHeaderText(null);
        alert.show();
    }
}