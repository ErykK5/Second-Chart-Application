package com;

import data.ReadData;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("JavaFX");
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(200);

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        final Scene scene = new Scene(root, 600, 300);
        final String fancyChartCss = "css/chart.css";

        scene.getStylesheets().addAll(fancyChartCss);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
