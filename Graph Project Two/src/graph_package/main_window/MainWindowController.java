package graph_package.main_window;

import graph_package.graph_classes.Graph;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Graph graph = new Graph();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int[] al = new int[]{0, 0, 0, 0};

        System.out.print(graph.checkNumberSeqence(al));
    }
}