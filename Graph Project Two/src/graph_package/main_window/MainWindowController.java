package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphCanvas;
import graph_package.graph_classes.GraphNode;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public GraphCanvas canvas;

    private Graph graph = new Graph();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] numberSequence = new int[]{2, 2, 2, 2};
        int[][] tempMatrix = graph.checkNumberSequence(numberSequence);

        //jesli sie udalo znalesc graf o takiej sekwencji
        if (tempMatrix != null) {
            graph.setGraphMatrix(tempMatrix);

            ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
            graph.setNodeGraph(tempGraph);

            //JAK CHCECIE WSTAWIC JAKIES AKCJE ZWIĄZANE Z GRAFEM (CHCECIE NP PRZETESTOWAC CZY DZIALA TO TUTAJ POD TYM KOMENTARZEM)

            canvas.drawGraph(graph);
        }
    }
}