package main_window;


import graph_classes.BellmanFord;
import graph_classes.DiGraph;
import graph_classes.Johnson;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mateusz on 10.04.2017.
 */
public class MainWindowController implements Initializable {
    Canvas canvas;
    // tutaj użytkownik poda zakodowany graf

    TextArea codeGraph;
    // przycisk który użytkownik wciśnie jeśli zakoduje graf
    Button buttonCodedGraph;
    //przechowywuje wartość p

    TextField pValue;
    // przechowywuje wartość n
    TextField nValue;
    //przycisk który generuje graf na podstawie wartości p oraz n
    Button generateGgraph;

    // testowo
    Label labelek;
    Button testowyButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



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

//        //Zadanie 3
//        diGraph.generateRandomSCCdigraphWithWages();
//        BellmanFord bellmanFord = new BellmanFord(diGraph.getGraphMatrix().length);
//
//        int infinity = 999;
//        // tworze macierz która wypełniam od indeksu 1
//        int[][] tab = new int[diGraph.getGraphMatrix().length+1][diGraph.getGraphMatrix().length+1];
//        for(int i=0;i<diGraph.getGraphMatrix().length;++i){
//            for(int j=0;j<diGraph.getGraphMatrix().length;++j){
//                tab[i+1][j+1]= diGraph.getWagesMatrix()[i][j];
//                //odległość do samego siebie równa 0
//                if(i == j){
//                    tab[i+1][j+1] = 0;
//                    continue;
//                }
//                // brak połączenia - odległość równa 'nieskończoność'
//                if(tab[i+1][j+1] == 0)
//                    tab[i+1][j+1] = infinity;
//            }
//        }
//        System.out.println("Wypisuje macierz z wagami:");
//        for (int i = 1; i < tab.length; ++i) {
//            System.out.print("|");
//            for (int j = 1; j < tab.length; ++j) {
//                System.out.print(" ");
//                System.out.print(tab[i][j]);
//                System.out.print(" ");
//            }
//            System.out.print("|\n");
//        }
//        System.out.println("");
//
//        bellmanFord.BellmanFordEvaluation(3,tab);
//
//        //Zadanie 4
//        System.out.println("ZADANIE 4 ------------------");
//        Johnson johnsonsAlgorithm = new Johnson(tab.length-1);
//        johnsonsAlgorithm.johnsonsAlgorithms(tab);

    }

    public void generateGgraphButtonClick(){
        int p = Integer.parseInt(pValue.getText());
        int n = Integer.parseInt(nValue.getText());
        labelek.setText("p:");
        System.out.println("p: " + p + " n: " + n);
    }

    public void generateCodedGraphButtonClick(){
        labelek.setText("testowy text");
    }



//    public void generateCodedGraphButtonClick() {
//        int KValueInput = Integer.parseInt(kValueInput.getText());
//        int numbersOfVercices = Integer.parseInt(numbersOfVerctices.getText());
//        if (numbersOfVercices * KValueInput % 2 != 0) {
//            KRegularLabel.setText("Błąd! Liczba wierzchołków * k musi być liczbą parzysta!");
//        }
//        graph.kReguralGraphs(numbersOfVercices, KValueInput);
//        canvas.drawGraph(graph);
//    }






}
