package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphCanvas;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public GraphCanvas canvas;

    private Graph graph = new Graph();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] al = new int[]{0, 0, 1, 1, 3, 3, 2};

        graph.checkNumberSequence(al);

        canvas.drawGraph(graph);
    }
}