package sistema; 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraficaRR extends JFrame {

    public GraficaRR(ArrayList<Procesos> procesos, ArrayList<String> ordenEjecucion, ArrayList<Integer> tiemposEjecucion, int quantum) {
        setTitle("Gráfica de Round Robin");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        XYSeriesCollection dataset = new XYSeriesCollection();

        // Crear una serie para cada proceso
        for (Procesos proceso : procesos) {
            XYSeries series = new XYSeries(proceso.getNombre());
            int remainingBurst = proceso.getOriginalBurst();
            int accumulatedTime = proceso.getTiempoLlegada();

            // Recorrer los tiempos de ejecución para representar cada fragmento en la serie
            for (int i = 0; i < ordenEjecucion.size(); i++) {
                if (ordenEjecucion.get(i).equals(proceso.getNombre())) {
                    int startTime = (i == 0) ? proceso.getTiempoLlegada() : tiemposEjecucion.get(i - 1);
                    int endTime = tiemposEjecucion.get(i);
                    int quantumUsed = Math.min(remainingBurst, quantum);

                    // Añadir el punto inicial del quantum en el tiempo exacto de ejecución
                    series.add(startTime, 100.0 * (1.0 - (double) remainingBurst / proceso.getOriginalBurst()));

                    // Reducir el tiempo de ráfaga restante
                    remainingBurst -= quantumUsed;

                    // Añadir el punto final del fragmento en el eje X
                    series.add(endTime, 100.0 * (1.0 - (double) remainingBurst / proceso.getOriginalBurst()));

                    if (remainingBurst <= 0) {
                        // El proceso ha terminado, agregar el último punto en 100%
                        series.add(endTime, 100.0);
                        break;
                    }
                }
            }

            dataset.addSeries(series);
        }

        // Crear la gráfica de líneas
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Ejecución de Procesos (Round Robin)",
                "Tiempo",
                "Progreso (%)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Personalizar la gráfica
        XYPlot plot = lineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer);

        // Configurar la ventana con el gráfico
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }
}
