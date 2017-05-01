package graph_classes;

/**
 * Created by Mateusz on 10.04.2017.
 */

import java.util.*;

/**
 * przechowuje DiGraf w postaci macierzy oraz listy sąsiedztwa i metody z nim związane
 */
public class DiGraph {

    public DiGraph() {
        //Testowo
        graphMatrix = new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {0, 1, 0, 0}
        };
//        graphMatrix = new int[][]{
//                {0, 0, 0, 0},
//                {1, 0, 0, 0},
//                {0, 1, 0, 1},
//                {0, 0, 1, 0},
//        };
        // generujemy tablice węzłów
        generateNodeArray();


//        graphMatrix = new int[4][4]{{1,2,3,4},{},{},{}};
//        graphMatrix[1][0] = graphMatrix[2][1] = graphMatrix[2][3] = graphMatrix[3][2] = 1;
    }

    /**
     * Aktualizujemy digraf mając nową macierz sąsiedztwa
     * @param matrix macierz sąsiedztwa
     */
    public void updateDigraph(int[][] matrix){
        setGraphMatrix(matrix);
        generateNodeArray();
        generateEdgeArray();
    }


    /**
     * DiGraf w formie macierzy
     */
    private int[][] graphMatrix;

    /**
     * Przechowuje graf w formie krawedzi sasiadujacych z wierzcholkiem.
     */
    private ArrayList<GraphEdge> edgeGraph = new ArrayList<>();

    /**
     * Przechowuje graf w formie wierzchołków.
     */
    private ArrayList<GraphNode> nodeGraph;
    /**
     * macierz która przechowuje odległości między wierzchołkami
     */
    private int[][] wagesMatrix;


    /**
     * Zwraca węzeł według id
     */
    public GraphNode getGraphNode(int id) {
        for (GraphNode eachNode : nodeGraph) {
            if (eachNode.getId() == id) {
                return eachNode;
            }
        }
        return null;
    }


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
     * Wypisuje macierz sąsiedztwa digrafu
     */
    public void printMatrix() {
        System.out.println("Wypisuje macierz sąsiedztwa digrafu:");
        for (int i = 0; i < graphMatrix.length; ++i) {
            System.out.print("|");
            for (int j = 0; j < graphMatrix.length; ++j) {
                System.out.print(" ");
                System.out.print(graphMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");
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


        graphMatrix = new int[numberOfVertices][numberOfVertices];
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
        // generujemy tablice węzłów
        generateNodeArray();
    }


    /**
     * Zwraca listę wierzchołków grafu.
     */
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }

    /**
     * funkcja ktora generuje tablice krawedzi na podstawie tablicy wierzcholkow
     */
    public void generateEdgeArray() {
        for (int i = 0; i < nodeGraph.size(); i++) {
            edgeGraph.add(new GraphEdge());
            edgeGraph.get(i).convertNodesToEdges(nodeGraph, i);
        }
    }


    public int[][] getWagesMatrix() {
        return wagesMatrix.clone();
    }


    /**
     * generuje losowy silnie spójny digraf z losowymi wagami z zakresu [-5,10]
     */
    public void generateRandomSCCdigraphWithWages() {
        // Tworzymy losowy graf (w poleceniu nie jest podana wielkosc)
        //generateProbabilityMatrix(5, 0.4);
        // uzywamy algorytmu Kosaraju do znalezienia najwiekszej spojna skladowej
        Kosaraju kosaraju = new Kosaraju(this);
        kosaraju.getSCComponents();
        // tutaj zapisjemy najwieksza spojna skladowa
        List<Integer> theBiggestSCCComponent = kosaraju.getTheBiggestSCComponent();


        // robimy płytką kopie naszej macierzy
        int[][] tmpGraphMatrix = graphMatrix.clone();
        // tutaj modyfikujemy graph matrix
        int size = theBiggestSCCComponent.size();

        graphMatrix = new int[size][size];


        //iteruje po macierzy
        int first_index = 0;
        int second_index = 0;
        boolean flaga = false;

        for (int i = 0; i < tmpGraphMatrix.length; ++i) {
            for (int j = 0; j < tmpGraphMatrix.length; ++j) {
                // iteruje po liscie
                for (int k = 0; k < theBiggestSCCComponent.size(); ++k) {
                    for (int l = 0; l < theBiggestSCCComponent.size(); ++l) {
                        if (theBiggestSCCComponent.get(k) == i && theBiggestSCCComponent.get(l) == j) {
//                            System.out.println("(" +i + " " + j + ")");
//                            System.out.println("first, second (" +first_index + " " + second_index + ")");
                            graphMatrix[first_index][second_index] = tmpGraphMatrix[i][j];
                            second_index++;
                            flaga = true;
                        }
                    }
                }
            }
            if(flaga){
                first_index++;
                second_index = 0;
                flaga = false;
            }
        }

        printMatrix();

        // aktualizujemy
        generateNodeArray();

        generateEdgeArray();
        Random r = new Random();

        //dla testów
        int tmptab[]={2,-3,1,-2,8,-1};
        int counter=0;

        for (int i = 0; i < edgeGraph.size(); i++) {
            for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                        int temp = r.nextInt(16) - 5;
                        edgeGraph.get(i).getConnectionEdgeList().get(j).setWeight(tmptab[counter++]); //(temp); //(tmptab[counter++]);

            }
        }
        //test wag (krawedz) - waga
        for (int i = 0; i < edgeGraph.size(); i++) {
            for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                System.out.printf("(%d, %d) : %d, ", edgeGraph.get(i).getConnectionEdgeList().get(j).getFirst().getId(),
                        edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId(),
                        edgeGraph.get(i).getConnectionEdgeList().get(j).getWeight());
            }
            System.out.println(" ");
        }
//        for (int i = 0; i < nodeGraph.size(); i++) {
//            System.out.printf("%d -> ", nodeGraph.get(i).getId());
//            for (int j = 0; j < nodeGraph.get(i).getConnectionList().size(); j++) {
//                System.out.printf("%d ", nodeGraph.get(i).getConnectionList().get(j).getId());
//            }
//            System.out.println(" ");
//        }

        //generujemy macierz do bellmana forda
        generateWagesMatrix(graphMatrix.length);

        System.out.println("Koniec Generowania SCC digrafu z wagami");
    }

    private void generateWagesMatrix(int size){
        wagesMatrix = new int[size][size];
        for (int i = 0; i < edgeGraph.size(); i++) {
            for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                int first = edgeGraph.get(i).getConnectionEdgeList().get(j).getFirst().getId();
                int second = edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId();
                wagesMatrix[first][second] = edgeGraph.get(i).getConnectionEdgeList().get(j).getWeight();
            }
        }
        System.out.println("Wypisuje macierz z wagami:");
        for (int i = 0; i < wagesMatrix.length; ++i) {
            System.out.print("|");
            for (int j = 0; j < wagesMatrix.length; ++j) {
                System.out.print(" ");
                System.out.print(wagesMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");
    }


    /**
     * metoda ktora generuje tablice wezlow na podstawie macierzy
     */
    public void generateNodeArray() {
        nodeGraph = new ArrayList<>();

        for (int i = 0; i < graphMatrix.length; i++) {
            nodeGraph.add(new GraphNode(i));
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == 1)
                    nodeGraph.get(i).addConnection(nodeGraph.get(j));
            }
        }
    }

    /**
     * wyswietla liste sąsiedztwa
     */
    public void printNodeArray() {
        for (GraphNode eachNode : nodeGraph) {
            System.out.print(eachNode.getId() + 1 + ": ");
            for (GraphNode e : eachNode.getConnectionList()) {
                System.out.print(e.getId() + 1 + " -> ");
            }
            System.out.println("NULL");
            System.out.println();
        }
    }

    /**
     * generuje graf w postaci macierzy z zadanym prawdopodobienstwem wstapienia krawedzi
     */
    public void generateProbabilityMatrix(int size, double probability) {
        graphMatrix = new int[size][size];
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                // nie interesują nas pętle od wierzchołka skierowane na ten sam wierzchołek
                if (i == j) {
                    graphMatrix[i][j] = 0;
                    continue;
                }
                if (Math.random() <= probability) {
                    graphMatrix[i][j] = 1;
                } else
                    graphMatrix[i][j] = 0;
            }
        }
        // musimy zaaktualizować naszą listę
        generateNodeArray();
        // wypisuje, dla testów!
        printMatrix();
    }


}
