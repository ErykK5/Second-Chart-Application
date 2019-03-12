package com;

import data.ReadData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ReadData rd = new ReadData();
        rd.start();


        primaryStage.setTitle("JavaFX");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);

        final URL location = ChartController.class.getResource("Chart.fxml");
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        final Parent root = loader.load(location.openStream());

        final Scene scene = new Scene(root, 1200, 700);

        final String fancyChartCss = "css/chart.css";

        scene.getStylesheets().addAll(fancyChartCss);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
