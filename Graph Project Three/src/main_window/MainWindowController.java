package main_window;

import graph_classes.Graph;
import graph_classes.GraphEdge;
import graph_classes.GraphNode;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public TextField numberSequenceInput;
    public TextField beginIndexInput;


    public Canvas graphCanvas;
    private Graph graph = new Graph();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        int[] numberSequence = new int[]{0, 2, 2, 2, 5, 4, 2, 3, 4, 2, 1, 4, 3, 4, 3, 4, 5};
//        int[] numberSequence = new int[]{0, 0, 1, 2, 2, 2, 3};
//        int[] numberSequence = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 6};
//        int[] numberSequence = new int[]{1, 1, 1, 2, 3, 3, 4, 4, 5};
//        int[] numberSequence = new int[]{1, 2, 2, 2, 3};
        int[] numberSequence = new int[]{1, 1, 2, 2, 3, 3, 3, 5, 5, 7};
//        int[] numberSequence = new int[]{1, 1, 2, 2, 2, 3, 3, 3, 3, 5, 5, 5, 8};

//        int[] numberSequence = new int[]{3, 3, 3, 3, 3, 3};
        int[][] tempMatrix = graph.checkNumberSequence(numberSequence);

        //jesli sie udalo znalesc graf o takiej sekwencji
        if (tempMatrix != null) {
            graph.setGraphMatrix(tempMatrix);
            ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
            graph.setNodeGraph(tempGraph);
            graph.createConsistentGraphWithWeights();
            graph.dijkstra(0);
            graph.createDistanceMatrix();
            graph.findDistanceCentre();
            graph.findMinMaxCentre();
            graph.prim(0);
            drawGraph(graph);
        }
    }

    /**
     * Wyrysowwuje graf na canvasie.
     *
     * @param graph graf do wyrysowania
     */
    public void drawGraph(Graph graph) {
        double canvasWidth = graphCanvas.getWidth();
        double canvasHeight = graphCanvas.getHeight();
        double graphSize = (canvasWidth <= canvasHeight) ? canvasWidth : canvasHeight;
        int dotSize = 15;
        ArrayList<GraphNode> nodeGraph = graph.getNodeGraph();
        ArrayList<GraphEdge> edgeGraph = graph.getEdgeGraph();
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

        //rysuje linie i wagi
        for (int i = 0; i < dotCount; i++) {
            for (GraphNode node : nodeGraph.get(i).getConnectionList()) {
                double angle1 = i * 360 / dotCount * Math.PI / 180;
                double x1 = canvasWidth / 2 + Math.sin(angle1) * graphSize * 2 / 5;
                double y1 = canvasHeight / 2 + Math.cos(angle1) * graphSize * 2 / 5;
                int index = nodeGraph.indexOf(node);
                double angle2 = nodeGraph.indexOf(node) * 360 / dotCount * Math.PI / 180;
                double x2 = canvasWidth / 2 + Math.sin(angle2) * graphSize * 2 / 5;
                double y2 = canvasHeight / 2 + Math.cos(angle2) * graphSize * 2 / 5;
                context.strokeLine(x1, y1, x2, y2);
//            ??    context.fillText(Integer.toString(edgeGraph.get(i).getConnectionEdgeList().get(0).getWeight()),(x1+x2)/2, (y1+y2)/2);
            }
        }
    }

}
