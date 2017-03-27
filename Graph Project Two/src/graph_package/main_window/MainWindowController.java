package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphCanvas;
import graph_package.graph_classes.GraphNode;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public GraphCanvas canvas;
    public TextField numberSequenceInput;
    public TextField kValueInput;
    public TextField numbersOfVerctices;

    //glowny gosc programu - nasz graf
    private Graph graph = new Graph();
    //flaga mowiaca czy zostal wygenerowany graf (jesli nie to nie przeprowadzamy na nim operacji)
    private boolean wasGraphGenerated = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //int[] numberSequence = new int[]{0, 2, 2, 2, 5, 4, 2, 3, 4, 2, 1, 4, 3, 4, 3, 4, 5};
        //int[] numberSequence = new int[]{0, 0, 1, 2, 2, 2, 3};
        //int[] numberSequence = new int[]{0, 2, 2, 2, 5, 4, 2, 3, 4, 2, 1, 4, 3, 4, 3, 4, 5};
        //int[] numberSequence = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 6};
        //int[] numberSequence = new int[]{1, 1, 1, 2, 3, 3, 4, 4, 5};
        //int[] numberSequence = new int[]{1, 2, 2, 2, 3};
        //int[] numberSequence = new int[]{1, 1, 2, 2, 3, 3, 3, 5, 5, 7};
        //int[] numberSequence = new int[]{1, 1, 2, 2, 2, 3, 3, 3, 3, 5, 5, 5, 8};

        int[] numberSequence = new int[]{3, 3, 3, 3, 3, 3};
        int[][] tempMatrix = graph.checkNumberSequence(numberSequence);

        //jesli sie udalo znalesc graf o takiej sekwencji
        if (tempMatrix != null) {
            graph.setGraphMatrix(tempMatrix);
            ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
            graph.setNodeGraph(tempGraph);

            wasGraphGenerated = true;
            //JAK CHCECIE WSTAWIC JAKIES AKCJE ZWIĄZANE Z GRAFEM (CHCECIE NP PRZETESTOWAC CZY DZIALA) TO TUTAJ POD TYM KOMENTARZEM
            //graph.createAndFindEulerGraph();
            graph.kReguralGraphs(6, 3);
            canvas.drawGraph(graph);
            canvas.drawGraph(graph);

            System.out.println(graph.isHamiltonianGraph(graph));
            //System.out.println(graph.getNumberOfVertices());
        }
    }

    public void createKReguralGraphButtonClick() {
        int KValueInput = Integer.parseInt(kValueInput.getText());
        int numbersOfVercices = Integer.parseInt(numbersOfVerctices.getText());
        graph.kReguralGraphs(numbersOfVercices, KValueInput);
        canvas.drawGraph(graph);
    }

    public void createGraphFromNumberSequenceButtonClick() {
        String[] inputStringArray = numberSequenceInput.getText().replaceAll(" ", "").split(",");
        int[] numberSequenceArray = new int[inputStringArray.length];

        for (int i = 0; i < inputStringArray.length; i++) {
            numberSequenceArray[i] = Integer.parseInt(inputStringArray[i]);
        }

        int[][] tempMatrix = graph.checkNumberSequence(numberSequenceArray);

        if (tempMatrix != null) {
            graph.setGraphMatrix(tempMatrix);
            ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
            graph.setNodeGraph(tempGraph);
        }

        canvas.drawGraph(graph);
    }

    public void randomizeGraphButtonClick() {
        graph.randomizeGraph();
        canvas.drawGraph(graph);
    }

    public void getBiggestConsistentComponentButtonClick() {
        ArrayList<GraphNode> consistentGraph = graph.checkBiggestConsistentComponent();
        graph.setNodeGraph(consistentGraph);
        canvas.drawGraph(graph);
    }
}