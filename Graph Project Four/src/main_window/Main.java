package main_window;

import graph_classes.DiGraph;
import graph_classes.GraphNode;
import graph_classes.Kosaraju;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        /**
         * testy
         */
        // Zadanie 1
        DiGraph diGraph = new DiGraph();
//        diGraph.createGraph();
        diGraph.printMatrix();
        diGraph.printNodeArray();
//        for (int i = 0; i < 1; ++i) {
//            System.out.println(i + ": ------------------");
//            diGraph.generateProbabilityMatrix(5, 0.35);
//        }
        //Zadanie 2
        Kosaraju kosaraju = new Kosaraju(diGraph);
        kosaraju.getSCComponents();

        //Zadanie 3
        diGraph.generateRandomSCCdigraphWithWages();

    }

    public static void main(String[] args) {
        launch(args);
    }

}