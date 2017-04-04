package main_window;

import graph_classes.Graph;
import graph_classes.GraphNode;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Canvas graphCanvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Wyrysowwuje graf na canvasie.
     *
     * @param graph graf do wyrysowania
     */
    public void drawGraph(Graph graph) {
        double canvasWidth = graphCanvas.getWidth();
        double canvasHeight = graphCanvas.getHeight();
        double graphSize = (canvasWidth < canvasHeight) ? canvasWidth : canvasHeight;
        int dotSize = 15;
        ArrayList<GraphNode> nodeGraph = graph.getNodeGraph();
        int dotCount = nodeGraph.size();
        GraphicsContext context = graphCanvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvasWidth, canvasHeight);
        context.setFill(Color.web("#673ab7"));
        context.setStroke(Color.web("#673ab7"));
        context.setLineWidth(3);

        //rysuje kółka
        for (int i = 0; i < dotCount; i++) {
            double angle = i * 360 / dotCount * Math.PI / 180;
            double x = canvasWidth / 2 + Math.sin(angle) * graphSize * 2 / 5 - dotSize / 2;
            double y = canvasHeight / 2 + Math.cos(angle) * graphSize * 2 / 5 - dotSize / 2;
            context.fillText(Integer.toString(graph.getNodeGraph().get(i).getId()), x + dotSize, y + dotSize);
            context.fillOval(x, y, dotSize, dotSize);
        }

        //rysuje linie
        for (int i = 0; i < dotCount; i++) {
            for (GraphNode node : nodeGraph.get(i).getConnectionList()) {
                double angle1 = i * 360 / dotCount * Math.PI / 180;
                double x1 = canvasWidth / 2 + Math.sin(angle1) * graphSize * 2 / 5;
                double y1 = canvasHeight / 2 + Math.cos(angle1) * graphSize * 2 / 5;

                double angle2 = nodeGraph.indexOf(node) * 360 / dotCount * Math.PI / 180;
                double x2 = canvasWidth / 2 + Math.sin(angle2) * graphSize * 2 / 5;
                double y2 = canvasHeight / 2 + Math.cos(angle2) * graphSize * 2 / 5;

                context.strokeLine(x1, y1, x2, y2);
            }
        }
    }
}
