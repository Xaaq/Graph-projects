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

    //glowny gosc programu - nasz graf
    private Graph graph = new Graph();
    //flaga mowiaca czy zostal wygenerowany graf (jesli nie to nie przeprowadzamy na nim operacji)
    private boolean wasGraphGenerated = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] numberSequence = new int[]{0, 2, 2, 2, 5, 4, 2, 3, 4, 2, 1, 4, 3, 4, 3, 4, 5};
        int[][] tempMatrix = graph.checkNumberSequence(numberSequence);

        //jesli sie udalo znalesc graf o takiej sekwencji
        if (tempMatrix != null) {
            graph.setGraphMatrix(tempMatrix);

            ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
            graph.setNodeGraph(tempGraph);

            //JAK CHCECIE WSTAWIC JAKIES AKCJE ZWIĄZANE Z GRAFEM (CHCECIE NP PRZETESTOWAC CZY DZIALA) TO TUTAJ POD TYM KOMENTARZEM

            canvas.drawGraph(graph);
            wasGraphGenerated = true;
        }
    }

    public void randomizeGraphButtonClick() {
        if (wasGraphGenerated) {
            graph.randomizeGraph();
            canvas.drawGraph(graph);
        }
    }
}