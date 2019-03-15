package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        setPrimaryStage(primaryStage);
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
