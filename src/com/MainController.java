package com;

import data.ReadData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainController {

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private LineChart<Number,Number> lineChart;

    @FXML
    private NumberAxis ox;

    @FXML
    private NumberAxis oy;

    int index = 0;

    public void initialize() {

        FileChooser fileChooser = new FileChooser();
        Stage s = Main.getPrimaryStage();
        File selected = fileChooser.showOpenDialog(s);

        ReadData rd = new ReadData(selected);
        rd.start();
        s.close();

        toggleButton.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if( newValue ) {
                loadData(index, rd, false);
                toggleButton.setText("Logarithmic");
            } else {
                loadData(index, rd, true);
                toggleButton.setText("Decimal");
            }
        }));
        createChart(rd);
    }

    public void createChart(ReadData rd) {
        oy.setAutoRanging(false);

        oy.setLowerBound((int)rd.getStrt());
        oy.setUpperBound((int)rd.getStop());

        oy.setTickUnit(10);
        lineChart.rotateProperty().setValue(180);

        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.Y_AXIS);
        lineChart.getYAxis().setTickLabelRotation(-180);
        lineChart.getXAxis().setTickLabelRotation(-180);
        List<String> nameList = new ArrayList<>();
        nameList = rd.getArr();
        choiceBox.setValue(nameList.get(0));
        for (int i = 0; i < rd.getNumb(); i++) {
            choiceBox.getItems().add(nameList.get(i));
        }

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                index = newValue.intValue();
                loadData( newValue.intValue(), rd, true);
            }
        });


        Float[][] arr = rd.getValues();

        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

        for (int i = 0; i < (int)((rd.getStop()-rd.getStrt())/rd.getStep()+1); i++) {
            if( arr[1][i] == rd.getNullVal() )
                continue;
            series.getData().add(new XYChart.Data<Number, Number>(arr[1][i],arr[0][i]));
        }
        lineChart.getData().add(series);
    }

    public void loadData( int n,ReadData rd, Boolean isDec ) {
        lineChart.setData(FXCollections.observableArrayList());

        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        Float[][] arr = rd.getValues();
        if( isDec ) {
            for (int i = 0; i < (int) ((rd.getStop() - rd.getStrt()) / rd.getStep()); i++) {
                if(arr[n][i] == rd.getNullVal())
                    continue;
                series.getData().add(new XYChart.Data<Number, Number>(arr[n][i], arr[0][i]));
            }
        } else {
            Float[][] logArr = new Float[][]{};
                for (int i = 0; i < (int) ((rd.getStop() - rd.getStrt()) / rd.getStep()); i++) {
                    if(arr[n][i] == rd.getNullVal())
                        continue;
                    series.getData().add(new XYChart.Data<Number, Number>(Math.log10(arr[n][i]), arr[0][i]));
            }
            ox.setTickLabelFormatter(new StringConverter<Number>() {
                @Override
                public String toString(Number object) {
                    if ( object.doubleValue() == 0.0 ){
                        return "10^0";
                    }
                    if ( object.doubleValue() == 1.0 ){
                        return "10^1";
                    }
                    if ( object.doubleValue() == 2.0 ){
                        return "10^2";
                    }
                    if ( object.doubleValue() == 3.0 ){
                        return "10^3";
                    }
                    if ( object.doubleValue() == 4.0 ){
                        return "10^4";
                    }
                    if ( object.doubleValue() == 5.0 ){
                        return "10^5";
                    }
                    if ( object.doubleValue() == 6.0 ){
                        return "10^6";
                    }
                    if ( object.doubleValue() == 7.0 ){
                        return "10^7";
                    }
                    if ( object.doubleValue() == 8.0 ){
                        return "10^8";
                    }
                    if ( object.doubleValue() == 9.0 ){
                        return "10^9";
                    }
                    return null;
                }

                @Override
                public Number fromString(String string) {
                    return null;
                }
            });
        }
        lineChart.getData().add(series);
    }
}
