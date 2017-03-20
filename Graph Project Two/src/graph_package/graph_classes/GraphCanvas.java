package graph_package.graph_classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-10.
 */
public class GraphCanvas extends Canvas {

    public GraphCanvas() {
        loadFXML();
    }

    //laduje plik .fxml
    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GraphCanvas.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void drawGraph(Graph graph) {
        double canvasWidth = getWidth();
        double canvasHeight = getHeight();
        double graphSize = (canvasWidth < canvasHeight) ? canvasWidth : canvasHeight;
        int dotSize = 15;
        ArrayList<GraphNode> nodeGraph = graph.getNodeGraph();
        int dotCount = nodeGraph.size();
        GraphicsContext context = getGraphicsContext2D();

        context.clearRect(0, 0, canvasWidth, canvasHeight);
        context.setFill(Color.web("#673ab7"));
        context.setStroke(Color.web("#673ab7"));
        context.setLineWidth(3);

        //rysuje kolka
        for (int i = 0; i < dotCount; i++) {
            double angle = i * 360 / dotCount * Math.PI / 180;
            double x = canvasWidth / 2 + Math.sin(angle) * graphSize * 2 / 5 - dotSize / 2;
            double y = canvasHeight / 2 + Math.cos(angle) * graphSize * 2 / 5 - dotSize / 2;

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