package EMIC_UI;


import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
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

public class EMIC_TRENDS extends BorderPane {
    private BorderPane bp  = new BorderPane();
    private String SceneName = "Patient_Trends";
    private EMIC_NAVIGATION navigation = null;
    private TrendHeader hdr = null;
    private BottomStats bts = null;
    private final TableView table = new TableView();
    private VBox Table_Container = new VBox();
    public final CategoryAxis xAxis = new CategoryAxis();
    public final NumberAxis yAxis = new NumberAxis();
    public final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
    public XYChart.Series series1 = new XYChart.Series();


    public final CategoryAxis xAxis1 = new CategoryAxis();
    public final NumberAxis yAxis2 = new NumberAxis();
    public final LineChart<String,Number> lineChart1 = new LineChart<String,Number>(xAxis1,yAxis2);
    public XYChart.Series series2 = new XYChart.Series();

    public final CategoryAxis xAxis3 = new CategoryAxis();
    public final NumberAxis yAxis4 = new NumberAxis();
    public LineChart<String,Number> lineChart3 = new LineChart<String,Number>(xAxis3,yAxis4);
    public XYChart.Series series3 = new XYChart.Series();
    EMIC_TRENDS(Stage PrimaryStage) throws URISyntaxException {
        navigation = new EMIC_NAVIGATION(PrimaryStage,SceneName);
        hdr = new TrendHeader();
        bts = new BottomStats(SceneName);
        table.setEditable(false);
        final Label label = new Label("Patient Vitals");
        label.setFont(new Font("Lato", 36));
        Text Emergency_Information = new Text();
        Emergency_Information.setText("Emergency ID: #KDehy^783jd            Paramedic: Dr Percy");
        Emergency_Information.setFont(Font.font("Lato", FontWeight.NORMAL, FontPosture.ITALIC,18));
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
        DateCol.setMinWidth(300);
        HeartRateCol.setMinWidth(200);
        OxygenSaturationCol.setMinWidth(150);
        GCSCol.setMinWidth(200);
        ETCO2Col.setMinWidth(200);
        table.getColumns().addAll(HeartRateCol, OxygenSaturationCol, ETCO2Col,GCSCol,TEMPCol,RateOfRespitationCol,TimeCol,DateCol);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lineChart.setMinWidth(700);
        //lineChart.setMaxHeight(400);
        lineChart.setAnimated(false);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        series1.setName("TTHOR");
        series1.getData().add(new XYChart.Data("one", 23));
        series1.getData().add(new XYChart.Data("two", 14));
        series1.getData().add(new XYChart.Data("three", 15));
        series1.getData().add(new XYChart.Data("four", 24));
        series1.getData().add(new XYChart.Data("five", 34));
        series1.getData().add(new XYChart.Data("six", 36));
        series1.getData().add(new XYChart.Data("seven", 22));
        series1.getData().add(new XYChart.Data("eight", 45));
        series1.getData().add(new XYChart.Data("nine", 43));
        series1.getData().add(new XYChart.Data("ten", 17));
        series1.getData().add(new XYChart.Data("eleven", 29));
        series1.getData().add(new XYChart.Data("twelve", 25));
        lineChart.getData().add(series1);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lineChart1.setMinWidth(700);
        //lineChart1.setMaxHeight(400);
        lineChart.setAnimated(false);
        xAxis1.setAnimated(false);
        yAxis2.setAnimated(false);
        xAxis1.setTickLabelsVisible(false);
        yAxis2.setTickLabelsVisible(false);
        series2.setName("Respiratory Rate");
        series2.getData().add(new XYChart.Data("one", 23));
        series2.getData().add(new XYChart.Data("two", 14));
        series2.getData().add(new XYChart.Data("three", 15));
        series2.getData().add(new XYChart.Data("four", 24));
        series2.getData().add(new XYChart.Data("five", 34));
        series2.getData().add(new XYChart.Data("six", 36));
        series2.getData().add(new XYChart.Data("seven", 22));
        series2.getData().add(new XYChart.Data("eight", 45));
        series2.getData().add(new XYChart.Data("nine", 43));
        series2.getData().add(new XYChart.Data("ten", 17));
        series2.getData().add(new XYChart.Data("eleven", 29));
        series2.getData().add(new XYChart.Data("twelve", 25));
        lineChart1.getData().add(series2);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lineChart3.setMinWidth(1400);
        //lineChart3.setMaxHeight(400);
        lineChart.setAnimated(false);
        xAxis3.setAnimated(false);
        yAxis4.setAnimated(false);
        xAxis3.setTickLabelsVisible(false);
        yAxis4.setTickLabelsVisible(false);
        series3.setName("ECG");
        series3.getData().add(new XYChart.Data("one", 23));
        series3.getData().add(new XYChart.Data("two", 14));
        series3.getData().add(new XYChart.Data("three", 15));
        series3.getData().add(new XYChart.Data("four", 24));
        series3.getData().add(new XYChart.Data("five", 34));
        series3.getData().add(new XYChart.Data("six", 36));
        series3.getData().add(new XYChart.Data("seven", 22));
        series3.getData().add(new XYChart.Data("eight", 45));
        series3.getData().add(new XYChart.Data("nine", 43));
        series3.getData().add(new XYChart.Data("ten", 17));
        series3.getData().add(new XYChart.Data("eleven", 29));
        series3.getData().add(new XYChart.Data("twelve", 25));
        lineChart3.getData().addAll(series3);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        getStylesheets().add("file:////home/katleho/Desktop/EMIC/EMIC_UI/src/main/java/EMIC_UI/styles.css");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        BorderPane chartHolder = new BorderPane();
        chartHolder.setCenter(lineChart3);
        chartHolder.setLeft(lineChart);
        chartHolder.setRight(lineChart1);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Table_Container.getChildren().addAll(label,Emergency_Information,chartHolder, table);
        Table_Container.setSpacing(10);
        Table_Container.setPadding(new Insets(10));
        ScrollPane s = new ScrollPane();
        s.setContent(Table_Container);
        s.setFitToWidth(true);
        s.setMaxHeight(954);
        setLeft(navigation);
        setCenter(bp);
        bp.setCenter(s);
        bp.setTop(hdr);
        bp.setBottom(bts);

    }
}
