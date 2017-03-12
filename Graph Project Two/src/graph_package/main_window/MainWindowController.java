package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.HelperClass;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Graph graph = new Graph();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] al = new int[]{0, 0, 0, 0};
        int[][] al1 = new int[][]{{3, 0, 3, 0}, {0, 3, 0, 3}};
        int[][] al2 = new int[][]{{0, 3, 0}, {3, 0, 3}, {1, 1, 1}};
        int[][] output = HelperClass.addMatrixes(al1, al2);

        System.out.print(graph.checkNumberSeqence(al));
    }
}