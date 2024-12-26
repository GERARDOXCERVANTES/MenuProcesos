package sistema;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.labels.StandardXYToolTipGenerator;

import javax.swing.*;
import java.util.ArrayList;

public class parametrosFCFS extends JPanel {

    private XYSeries seriesEspera;
    private XYSeries seriesTfinal;
    private XYSeries seriesServicio;


    private ArrayList<Procesos> procesos;

    @SuppressWarnings("deprecation")
    public parametrosFCFS(ArrayList<Procesos> procesos) {
        this.procesos = procesos;

        // Crear series para la gráfica
        seriesEspera = new XYSeries("Tiempo de Espera");
        seriesTfinal = new XYSeries("Tiempo Final");
        seriesServicio = new XYSeries("Tiempo de Servicio");


        

        XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(seriesTfinal);
        dataSet.addSeries(seriesEspera);
        dataSet.addSeries(seriesServicio);

        // Añadir los procesos a las series
        for (Procesos proceso : procesos) {
            
            seriesTfinal.add( proceso.getTiempoF() , proceso.getTiempoLlegada());
            seriesEspera.add(proceso.getTiempoE() , proceso.getTiempoLlegada()); 

            seriesServicio.add(proceso.getTiempoS() , proceso.getTiempoLlegada());
        }

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createXYLineChart(
                "GRAFICA DE LOS PROCESOS",  
                "Tiempos",                   
                "Tiempo de llegada  ",     
                dataSet,                    
                PlotOrientation.VERTICAL,    
                true,                       
                true,                       
                false                       
        );

        // Obtener el plot del gráfico
        XYPlot plot = chart.getXYPlot();

        
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);  // Hacer que las etiquetas sean visibles
        
        renderer.setItemLabelGenerator(new XYItemLabelGenerator() {
            @Override
            public String generateLabel(XYDataset dataset, int seriesIndex, int itemIndex) {
                Procesos proceso = procesos.get(itemIndex);
                String label = proceso.getNombre();
                return " " + label;
            }
        });
        
        renderer.setToolTipGenerator(new StandardXYToolTipGenerator());

        // Crear un marco para mostrar el gráfico
        ChartFrame frame = new ChartFrame("GRAFICA", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  // Centrar la ventana
    }
}
