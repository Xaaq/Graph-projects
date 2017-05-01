package main_window;


import graph_classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Mateusz on 10.04.2017.
 */
public class MainWindowController implements Initializable {
    public Canvas graphCanvas;
    // tutaj użytkownik poda zakodowany graf

    // ZADANIE 1

    public TextArea codeGraph;
    // przycisk który użytkownik wciśnie jeśli zakoduje graf
    public Button buttonCodedGraph;
    //przechowywuje wartość p
    public TextField pValue;
    // przechowywuje wartość n
    public TextField nValue;
    //przycisk który generuje graf na podstawie wartości p oraz n
    public Button generateGgraph;

    // ZADANIE 2

    public Button runKosaraju;
    public Label SSComponents;

    // ZADANIE 3

    // przechowywuje wartość w do Belmanna-Forda
    public TextField wValueBelmanFord;
    // wywołuje algorytm Belmanna-Forda po wciśnięciu
    public Button runBelmannFord;
    // generuje losowy silnie spojny digraf
    public Button generateRandomSSDigraph;
    // wświetla najkrótszej ścieżki od danego wierzchołka
    public Label shortestPathToVertex;

    // ZADANIE 4

    // do wyświetlenia wierzchołków wraz z najkrótymi wartościami dotarcia do nich
    public Label showJohnson;
    public Button runJohnson;
    public TextArea showJohnsonTextArea;

    // Nasi goście specjalni
    public DiGraph diGraph = new DiGraph();
    public Kosaraju kosaraju;
    //
//    public BellmanFord bellmanFord;
    public BellmanFord bellmanFord;


    public Johnson johnsonsAlgorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * testy
         */

//        diGraph.printMatrix();
////      //   Zadanie 1
//        DiGraph diGraph = new DiGraph();
////        diGraph.createGraph();
//        diGraph.printMatrix();
//        diGraph.printNodeArray();
//        for (int i = 0; i < 1; ++i) {
//            System.out.println(i + ": ------------------");
//            diGraph.generateProbabilityMatrix(5, 0.35);
//        }
//      //  Zadanie 2
//        Kosaraju kosaraju = new Kosaraju(diGraph);
//        kosaraju.getSCComponents();
//
//        //Zadanie 3
//        diGraph.generateRandomSCCdigraphWithWages();
//        BellmanFord bellmanFord = new BellmanFord(diGraph.getGraphMatrix().length);
//        diGraph.setWagesMatixBelmannFord();
//
//        bellmanFord.BellmanFordEvaluation(1, diGraph.getWagesMatixBelmannFord());
//
//        int infinity = 999;
//        // tworze macierz która wypełniam od indeksu 1
//        int[][] tab = new int[diGraph.getGraphMatrix().length + 1][diGraph.getGraphMatrix().length + 1];
//        for (int i = 0; i < diGraph.getGraphMatrix().length; ++i) {
//            for (int j = 0; j < diGraph.getGraphMatrix().length; ++j) {
//                tab[i + 1][j + 1] = diGraph.getWagesMatrix()[i][j];
//                //odległość do samego siebie równa 0
//                if (i == j) {
//                    tab[i + 1][j + 1] = 0;
//                    continue;
//                }
//                // brak połączenia - odległość równa 'nieskończoność'
//                if (tab[i + 1][j + 1] == 0)
//                    tab[i + 1][j + 1] = infinity;
//            }
//        }
//
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


        //Zadanie 4
//        System.out.println("ZADANIE 4 ------------------");
//        Johnson johnsonsAlgorithm = new Johnson(diGraph.getWagesMatixBelmannFord().length - 1);
//        johnsonsAlgorithm.johnsonsAlgorithms(diGraph.getWagesMatixBelmannFord());
//
//        System.out.println("---------------------------------------------");

    }


    public void generateGgraphButtonClick() {
        float p = Float.parseFloat(pValue.getText());
        int size = Integer.parseInt(nValue.getText());
        diGraph.generateProbabilityMatrix(size, p);
        drawGraph(diGraph, false);
    }

    public void generateCodedGraphButtonClick() {
        int lenght = codeGraph.getText().split("\n").length;
        String codedGraph[] = codeGraph.getText().split("\n");

//         length * length = codedGraph.length
        String codedGraphSplitted[][] = new String[lenght][lenght];

        int counter = 0;
        for (String x : codedGraph) {
            codedGraphSplitted[counter++] = x.split(" ");
        }
        //  tutaj przechowujemy tymczasową macierz sąsiedztwa
        int[][] tmpMatrix = new int[lenght][lenght];
        int row = 0, column = 0;
        for (String x[] : codedGraphSplitted) {
            for (String y : x) {
                tmpMatrix[row][column] = Integer.parseInt(y);
                column++;
            }
            row++;
            column = 0;
        }
        // Aktualizujemy nasz graf
        diGraph.updateDigraph(tmpMatrix);
        diGraph.printMatrix();
        diGraph.printNodeArray();
        drawGraph(diGraph, false);
    }


    public void kosarajuButtonClick() {
        kosaraju = new Kosaraju(diGraph);
        String text = kosaraju.getSCComponents().toString();
        SSComponents.setText(text);
    }

// Labela zamiast TextArea do wyswietlania Johnsona, przeciagnac kontrolki na dol apki i pousuwac niepotrzebne linie


    public void drawGraph(DiGraph digraph, boolean withWages) {
        double canvasWidth = graphCanvas.getWidth();
        double canvasHeight = graphCanvas.getHeight();
        double graphSize = (canvasWidth <= canvasHeight) ? canvasWidth : canvasHeight;
        int dotSize = 15;
        ArrayList<GraphNode> nodeGraph = digraph.getNodeGraph();
        ArrayList<GraphEdge> edgeGraph = digraph.getEdgeGraph();
        int dotCount = nodeGraph.size();
        GraphicsContext context = graphCanvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvasWidth, canvasHeight);
//        context.setFill(Color.web("#673ab7"));
        context.setFill(Color.web("#673ab7"));
        context.setStroke(Color.web("#673ab7"));
        context.setLineWidth(3);

        //rysuje kółka
        for (int i = 0; i < dotCount; i++) {
            double angle = i * 360 / dotCount * Math.PI / 180;
            double x = canvasWidth / 2 + Math.sin(angle) * graphSize * 2 / 5 - dotSize / 2;
            double y = canvasHeight / 2 + Math.cos(angle) * graphSize * 2 / 5 - dotSize / 2;
            context.fillText(Integer.toString(digraph.getNodeGraph().get(i).getId()), x + dotSize, y + dotSize);
            context.fillOval(x, y, dotSize, dotSize);
        }

        //rysuje linie i opcjonalnie wagi krawędzi w zależności czy withWages == false czy == true
        for (int i = 0; i < dotCount; i++) {
            for (GraphNode node : nodeGraph.get(i).getConnectionList()) {
                // ten sam kat co wczesniej
                double angle1 = i * 360 / dotCount * Math.PI / 180;
                double x1 = canvasWidth / 2 + Math.sin(angle1) * graphSize * 2 / 5;
                double y1 = canvasHeight / 2 + Math.cos(angle1) * graphSize * 2 / 5;
                int index = nodeGraph.indexOf(node);
                double angle2 = nodeGraph.indexOf(node) * 360 / dotCount * Math.PI / 180;
                double x2 = canvasWidth / 2 + Math.sin(angle2) * graphSize * 2 / 5;
                double y2 = canvasHeight / 2 + Math.cos(angle2) * graphSize * 2 / 5;
                context.strokeLine(x1, y1, x2, y2);

//            ??    context.fillText(Integer.toString(edgeGraph.get(i).getConnectionEdgeList().get(0).getWeight()),(x1+x2)/2, (y1+y2)/2);
//                drawArrow(context,x1,y1,x2,y2);


                //rysuje grot strzałki
                double dx = x2 - x1, dy = y2 - y1;
                double angleDif = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx * dx + dy * dy);
                int arrowSize = 12;
//                context.fillPolygon(new double[]{len, len - arrowSize, len - arrowSize, len}, new double[]{0, -arrowSize, arrowSize, 0},
//                        4);
//                gc.fillPolygon(new double[]{len, len - arrowSize, len - arrowSize, len}, new double[]{0, -arrowSize, arrowSize, 0},
//                        4);

//                double angleDifference = Math.PI * 30 / 180;
//                context.strokeLine(x2, y2, x2 - Math.cos(angle2 + angleDifference) * 15, y2 - Math.sin(angle2 + angleDifference) * 15);
//                context.strokeLine(x2, y2, x2 - Math.cos(angle2 - angleDifference) * 15, y2 - Math.sin(angle2 - angleDifference) * 15);
            }
        }
    }

    // Z neta - nie działa jak się narysuje kolejny raz inny graf
    void drawArrow(GraphicsContext gc, double x1, double y1, double x2, double y2) {
        gc.setFill(Color.web("#673ab7"));

        double dx = x2 - x1, dy = y2 - y1;
        double angle3 = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(x1, y1);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle3), 0, 0));
        gc.setTransform(new Affine(transform));

        gc.strokeLine(0, 0, len, 0);
        int arrowSize = 12;
        gc.fillPolygon(new double[]{len, len - arrowSize, len - arrowSize, len}, new double[]{0, -arrowSize, arrowSize, 0},
                4);
    }

    public void generateRandomSSDigraphButtonClick() {
        System.out.println("HELLO!");
        diGraph.generateRandomSCCdigraphWithWages();
        // tutaj jeszcze wyrysowac na canvasie digraf z wagami!
//        drawGraph(diGraph, true);
    }

// !! CZASAMI BELMANN FORD POKAZUJE ŹLE ???

    public void belmannFordButtonClick() {
        try {
            int w = Integer.parseInt(wValueBelmanFord.getText());
//            bellmanFord = new BellmanFord(diGraph.getGraphMatrix().length);
            bellmanFord = new BellmanFord(diGraph.getGraphMatrix().length);
            diGraph.setWagesMatrixBelmannFord();
            String tmp = "";
            int[] distances = bellmanFord.BellmanFordEvaluation(w, diGraph.getWagesMatixBelmannFord());
            for (int vertex = 1; vertex <= bellmanFord.numberOfVertices; vertex++) {
                tmp += "odległość od: " + w + " do: " + (vertex-1) + " wynosi: " + distances[vertex] + "\n";
            }
            shortestPathToVertex.setText(tmp);


        } catch (NullPointerException e) {
            System.out.println("Nie zainicjowano tablicy wag!");
            shortestPathToVertex.setText("Nie zainicjowano tablicy wag!");
        } catch (NumberFormatException e) {
            System.out.println("Nie podano wierzchołka!");
            shortestPathToVertex.setText("Nie podano wierzchołka!");
        }
    }

    public void johnsonButtonClick() {
        diGraph.setWagesMatrixBelmannFord();
        int numberOfNodes = diGraph.getWagesMatixBelmannFord().length - 1;

        johnsonsAlgorithm = new Johnson(numberOfNodes);

        int[][] allPairShortestPath = johnsonsAlgorithm.johnsonsAlgorithms(diGraph.getWagesMatixBelmannFord());
        String tmp = "";

        for (int i = 0; i < numberOfNodes; i++) {
            tmp += "\t" + i;
        }
        tmp += "\n";

        for (int source = 0; source < numberOfNodes; source++) {
            tmp += source + "\t";
            for (int destination = 0; destination < numberOfNodes; destination++) {
                tmp += allPairShortestPath[source+1][destination+1] + "\t";
            }
            tmp +="\n";
        }
//        showJohnson.setText(tmp);
//        lub na TextArea
        showJohnsonTextArea.setText(tmp);
    }


}
