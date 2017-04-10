package graph_classes;

/**
 * Created by Mateusz on 10.04.2017.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * przechowuje DiGraf w postaci macierzy oraz listy sąsiedztwa i metody z nim związane
 */
public class DiGraph {

    /**
     * DiGraf w formie macierzy
     */
    private int[][] graphMatrix;

    /**
     * zwraca DiGraf w posatci macierzy
     */
    public int[][] getGraphMatrix() {
        return graphMatrix.clone();
    }

    /**
     * ustawia macierz tego grafu na podana
     */
    public void setGraphMatrix(int[][] inputMatrix) {
        graphMatrix = inputMatrix.clone();
    }

    /**
     * wiadomość wyświetlana dla użytkownika przy wpisywaniu DiGrafu
     */
    private static void message() {
        System.out.println("Uzupełnij sąsiadów: wpisując 1, jeżeli dany wierzchołek wskazuje na inny, oraz 0 jeżeli nie.");
        System.out.println("Zaczynamy od wierzchołka nr. 1. Każde cyfry oddziel spacją.");
        System.out.println("0 kończy wpisywanie w danym wierzchołku.");
    }

    /**
     * Wypisuje macierz sąsiedztwa digrafu
     */
    public void printMatrix(){
        System.out.println("Wypisuje macierz sąsiedztwa digrafu:");
        for(int i=0;i<graphMatrix.length;++i){
            System.out.print("|");
            for(int j=0;j<graphMatrix.length;++j){
                System.out.print(" ");
                System.out.print(graphMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");
    }


    /**
     * Tworzy graf z inputu użytkownika i zapisuje do macierzy
     */
    public void createGraph() {
        StringTokenizer strToken;
        String input;
        int[] array;
        int toArray;
        // 2 Scannery - jak pracowałem na jednym wyskakiwał błąd(?)
        Scanner reader = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);

        int numberOfVertices = 0;
        System.out.println("Podaj liczbę wierzchołków: ");
        try {
            numberOfVertices = reader.nextInt();
        } catch (NumberFormatException ex) {
            System.out.println("nie jest liczbą !");
        }


        array = new int[numberOfVertices];
        message();

        for (int i = 0; i < numberOfVertices; ++i) {
            System.out.println("Podaj wierzchołki na które wskazuje wierzchołek: " + (i + 1) + ": ");
            input = reader2.nextLine();
            strToken = new StringTokenizer(input);

            for (int j = 0; j < numberOfVertices; ++j) {
                toArray = Integer.parseInt((String) strToken.nextElement());
                if (toArray == 0)
                    break;
                array[j] = toArray;

                //Zapisujemy do naszej macierzy
                for (int wsp2 : array) {
                    // wsp2 - 1, bo indeksujemy od 0 w tablicy. Warunek if, żeby nie wpisać -1 do macierzy
                    if (wsp2 - 1 >= 0)
                        graphMatrix[i][wsp2 - 1] = 1;
                    //na przekątnych 0, bo nie można być sąsiadem samego siebie
                    if (wsp2 - 1 == i)
                        graphMatrix[i][wsp2 - 1] = 0;
                }
                //Zerujemy pomocniczą tablicę po każdej pętli
                for (int x = 0; x < numberOfVertices; ++x)
                    array[x] = 0;
            }
        }
    }


    /**
     * Przechowuje graf w formie wierzchołków.
     */
    private ArrayList<GraphNode> nodeGraph;

    /**
     * Zwraca listę wierzchołków grafu.
     */
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }

    /**
     * metoda ktora generuje tablice wezlow na podstawie macierzy
     */
    // poprawić
    public ArrayList<GraphNode> generateNodeArray() {
        ArrayList<GraphNode> nodeArray = new ArrayList<>();

        for (int i = 0; i < graphMatrix.length; i++) {
            nodeArray.add(new GraphNode(i));
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == 1)
                    nodeArray.get(i).addConnection(nodeArray.get(j));
            }
        }


        return nodeArray;
    }


}
