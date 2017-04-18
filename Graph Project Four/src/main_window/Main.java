package main_window;

import graph_classes.BellmanFord;
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
//        diGraph.printMatrix();
//        diGraph.printNodeArray();
//        for (int i = 0; i < 1; ++i) {
//            System.out.println(i + ": ------------------");
//            diGraph.generateProbabilityMatrix(5, 0.35);
//        }
        //Zadanie 2
//        Kosaraju kosaraju = new Kosaraju(diGraph);
//        kosaraju.getSCComponents();

        //Zadanie 3
        diGraph.generateRandomSCCdigraphWithWages();
        BellmanFord bellmanFord = new BellmanFord(diGraph.getGraphMatrix().length);
        int infinity = 999;
        // tworze macierz która wypełniam od indeksu 1
        int[][] tab = new int[diGraph.getGraphMatrix().length+1][diGraph.getGraphMatrix().length+1];
        for(int i=0;i<diGraph.getGraphMatrix().length;++i){
            for(int j=0;j<diGraph.getGraphMatrix().length;++j){
                tab[i+1][j+1]= diGraph.getWagesMatrix()[i][j];
                //
                if(tab[i+1][j+1] == 0)
                    tab[i+1][j+1] = infinity;
            }
        }
        System.out.println("Wypisuje macierz z wagami:");
        for (int i = 1; i < tab.length; ++i) {
            System.out.print("|");
            for (int j = 1; j < tab.length; ++j) {
                System.out.print(" ");
                System.out.print(tab[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");

        bellmanFord.BellmanFordEvaluation(2,tab);

    }

    public static void main(String[] args) {
        launch(args);
    }

}