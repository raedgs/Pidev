/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpromotion.Utils;

import java.util.HashMap;
import java.util.Map.Entry;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Amine
 */
public class JfreeChartApi {

    private static HashMap<String, Double> data;

    public JfreeChartApi(HashMap<String, Double> data) {
        this.data = data;
    }

    public HashMap<String, Double> getData() {
        return data;
    }

    public void setData(HashMap<String, Double> data) {
        this.data = data;
    }

    public static JFreeChart createChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Entry<String, Double> item : data.entrySet()) {
            dataset.setValue(item.getKey(), item.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Statistique Promotion", // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        return chart;
    }

    public void afficherStatistique() {
        ChartViewer viewer = new ChartViewer(createChart());
        Stage stage = new Stage();
        stage.setScene(new Scene(viewer));
        stage.setTitle("Statistique");
        stage.setWidth(600);
        stage.setHeight(400);
        stage.show();
    }

}
