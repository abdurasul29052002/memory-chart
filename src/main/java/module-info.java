module com.example.memorychart {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.jfoenix;
    requires java.desktop;


    opens com.example.memorychart to javafx.fxml;
    exports com.example.memorychart;
    exports com.example.memorychart.controller;
    exports com.example.memorychart.model;
    opens com.example.memorychart.controller to javafx.fxml;
}