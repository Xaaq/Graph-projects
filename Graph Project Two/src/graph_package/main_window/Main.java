package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Graph application");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        // **** Testy Mateusz
        //Testowy graf
        Graph graph = new Graph();
        //int[] numberSequence = new int[]{0, 0, 1, 1, 3, 3, 2};
        //dla sekwencji {2,2,2,2,2,2} - pokazało false
        int[] numberSequence = new int[]{2,2,3,3};
        int[][] tempMatrix = graph.checkNumberSequence(numberSequence);
        graph.setGraphMatrix(tempMatrix);
        ArrayList<GraphNode> tempGraph = graph.generateNodeArray();
        graph.setNodeGraph(tempGraph);

        System.out.println(graph.isHamiltonianGraph(graph));
    }

    public static void main(String[] args) {
        launch(args);
    }
}