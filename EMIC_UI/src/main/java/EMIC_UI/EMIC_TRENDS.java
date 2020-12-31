package EMIC_UI;


import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class EMIC_TRENDS extends GridPane {
    private BorderPane bp  = new BorderPane();
    private String SceneName = "Patient_Trends";
    private EMIC_NAVIGATION navigation = null;
    private Header hdr = null;
    private BottomStats bts = null;
    private final TableView table = new TableView();
    private VBox Table_Container = new VBox();

    EMIC_TRENDS(Stage PrimaryStage) throws URISyntaxException {
        navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
        hdr = new Header(SceneName);
        bts = new BottomStats(SceneName);
        table.setEditable(false);
        final Label label = new Label("Patient Vitals");
        label.setFont(new Font("Segoe UI", 36));
        Text Emergency_Information = new Text();
        Emergency_Information.setText("Emergency ID: #KDehy^783jd            Paramedic: Dr Percy                  ");
        Emergency_Information.setFont(Font.font("Segoe UI", FontWeight.BLACK, FontPosture.ITALIC,18));
        TableColumn HeartRateCol = new TableColumn("Heart Rate");
        TableColumn OxygenSaturationCol = new TableColumn("SO2");
        TableColumn ETCO2Col = new TableColumn("ETCO2");
        TableColumn GCSCol = new TableColumn("GCS");
        TableColumn TEMPCol = new TableColumn("Temperature");
        TableColumn RateOfRespitationCol = new TableColumn("Rate Of Respiration");
        TableColumn TimeCol = new TableColumn("Time");
        TableColumn DateCol = new TableColumn("Date");
        RateOfRespitationCol.setMinWidth(300);
        TEMPCol.setMinWidth(300);
        TimeCol.setMinWidth(200);
        DateCol.setMinWidth(200);
        HeartRateCol.setMinWidth(200);
        OxygenSaturationCol.setMinWidth(150);
        GCSCol.setMinWidth(200);
        ETCO2Col.setMinWidth(200);
        table.getColumns().addAll(HeartRateCol, OxygenSaturationCol, ETCO2Col,GCSCol,TEMPCol,RateOfRespitationCol,TimeCol,DateCol);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // I copied this code to be modified later !!!!!!!!!!!!!!!!
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Heart Rate");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Heart Rate");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        lineChart.getData().add(series);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////






        Table_Container.getChildren().addAll(label,Emergency_Information,lineChart, table);
        Table_Container.setSpacing(5);
        Table_Container.setPadding(new Insets(10));
        ScrollPane s = new ScrollPane();
        s.setContent(Table_Container);
        s.setFitToWidth(true);

        add(navigation,0,0);
        add(bp,1,0);
        bp.setCenter(s);
        bp.setTop(hdr);
        bp.setBottom(bts);

    }
}
