package graph_classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Xaaq333 on 2017-03-11.
 */

/**
 * przechowuje graf w postaci macierzy i funkcje z nim zwiazane
 */

public class Graph {

    /**
     * Przechowuje graf w formie wierzchołków.
     */
    private ArrayList<GraphNode> nodeGraph;


    public ArrayList<GraphEdge> getEdgeGraph() {
        return edgeGraph;
    }

    /**
     * Przechowuje graf w formie krawedzi sasiadujacych z wierzcholkiem.
     */
    private ArrayList<GraphEdge> edgeGraph = new ArrayList<>();
    /**
     * przechowuje graf w formie macierzy
     */

    private int[][] graphMatrix;

    /**
     * Zwraca listę wierzchołków grafu.
     *
     * @return Lista wierzchołków grafu
     */
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }

    /**
     * ustawia macierz tego grafu na podana
     */
    public void setGraphMatrix(int[][] inputMatrix) {
        graphMatrix = inputMatrix.clone();
    }

    /**
     * zwraca graf w posatci macierzy
     */
    public int[][] getGraphMatrix() {
        return graphMatrix.clone();
    }

    /**
     * ustawia graf w postaci wezlow na podany
     */
    public void setNodeGraph(ArrayList<GraphNode> inputNodeGraph) {
        nodeGraph = inputNodeGraph;
    }

    /**
     * sub-klasa ktora opakowywuje wierzcholek i liczbe polaczonych z nim krawedzi (potrzebna do tworzenia grafu)
     */

    private class Verticle {
        public final int verticleNumber;
        public int connectionCount;

        Verticle(int verticleNumber, int connectionCount) {
            this.verticleNumber = verticleNumber;
            this.connectionCount = connectionCount;
        }
    }

    /**
     * sub-klasa ktora sluzy do porownania obiektow klasy Verticle
     */

    private class VerticleComparator implements Comparator<Verticle> {
        @Override
        public int compare(Verticle verticle1, Verticle verticle2) {
            if (verticle1.connectionCount > verticle2.connectionCount)
                return 1;
            if (verticle1.connectionCount < verticle2.connectionCount)
                return -1;
            return 0;
        }
    }

    /**
     * robi graf z podanej sekwencji liczb jesli sie da
     */

    public int[][] checkNumberSequence(int[] inputNumberSequence) {
        ArrayList<Verticle> verticleSequece = new ArrayList<>();

        for (int i = 0; i < inputNumberSequence.length; i++) {
            verticleSequece.add(new Verticle(i, inputNumberSequence[i]));
        }

        return checkNumberSequence(verticleSequece);
    }

    /**
     * sprawdza czy mozna zrobic graf z podanej sekwencji liczb
     */
    private int[][] checkNumberSequence(ArrayList<Verticle> inputVerticleSequence) {
        ArrayList<Verticle> verticleSequence = (ArrayList<Verticle>) inputVerticleSequence.clone();
        int highestVerticleNumber = 0;

        //sprawdza jaki jest najwieksze id wierzcholka (zeby wiedziec jak duza tablice zrobic)
        for (Verticle verticle : verticleSequence) {
            if (verticle.verticleNumber > highestVerticleNumber)
                highestVerticleNumber = verticle.verticleNumber;
        }

        highestVerticleNumber++;
        int[][] outputArray = new int[highestVerticleNumber][highestVerticleNumber];

        //wypelnia tablice zerami
        for (int i = 0; i < highestVerticleNumber; i++) {
            for (int j = 0; j < highestVerticleNumber; j++) {
                outputArray[i][j] = 0;
            }
        }
        //usuwa wsyzstkie zerowe pozycje z listy
        for (int i = verticleSequence.size() - 1; i >= 0; i--) {
            if (verticleSequence.get(i).connectionCount <= 0)
                verticleSequence.remove(i);
        }

        //jesli lista jest pusta zwraca pusta tablice
        if (verticleSequence.isEmpty())
            return outputArray;

        verticleSequence.sort(new VerticleComparator());
        Verticle lastVerticle = verticleSequence.get(verticleSequence.size() - 1);
        verticleSequence.remove(verticleSequence.size() - 1);

        //jesli trzeba wygenerowac wiecej krawedzi niz jest pozycji w liscie - nie da sie wygenerowac tablicy
        if (lastVerticle.connectionCount > verticleSequence.size())
            return null;

        //odejmuje od ostatnich pozycji z listy wartosc 1 i dodaje znak w tablicy
        for (int i = 1; i <= lastVerticle.connectionCount; i++) {
            int actualNumber = verticleSequence.get(verticleSequence.size() - i).connectionCount - 1;
            verticleSequence.get(verticleSequence.size() - i).connectionCount = actualNumber;

            outputArray[lastVerticle.verticleNumber][verticleSequence.get(verticleSequence.size() - i).verticleNumber] = 1;
            outputArray[verticleSequence.get(verticleSequence.size() - i).verticleNumber][lastVerticle.verticleNumber] = 1;
        }

        int[][] recursiveArray = checkNumberSequence(verticleSequence);

        //jesli otrzymano tablice z funkcji rekurencyjnej - dodaj do biezacej tablicy i zwroc wynik
        if (recursiveArray != null)
            return HelperClass.addSquareMatrixes(outputArray, recursiveArray);
        else
            return null;
    }

    /**
     * funkcja ktora generuje tablice wezlow na podstawie macierzy
     */
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

    /**
     * funkcja ktora generuje tablice krawedzi na podstawie tablicy wierzcholkow
     */
    public void generateEdgeArray() {
        for (int i = 0; i < nodeGraph.size(); i++) {
            edgeGraph.add(new GraphEdge());
            edgeGraph.get(i).convertNodesToEdges(nodeGraph, i);
        }
//        //test krawedzi
//        for (int i = 0; i < edgeGraph.size(); i++) {
//            for(int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
//                System.out.printf("(%d, %d), ", edgeGraph.get(i).getConnectionEdgeList().get(j).getV().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getW().getId());
//            }
//            System.out.println(" ");
//        }
    }

    /**
     * sprawdza najwieksza skladowa grafu i zwraca tablice wezlow ktore do niej naleza
     */
    public ArrayList<GraphNode> checkBiggestConsistentComponent() {
        ArrayList<ArrayList<GraphNode>> consistentComponentsList = new ArrayList<>();
        ArrayList<GraphNode> tempNodeList = (ArrayList<GraphNode>) nodeGraph.clone();

        while (tempNodeList.size() != 0) {
            consistentComponentsList.add(new ArrayList<>());
            ArrayList<GraphNode> nodeQueue = new ArrayList<>();
            nodeQueue.add(tempNodeList.get(0));
            tempNodeList.remove(tempNodeList.get(0));

            while (nodeQueue.size() != 0) {
                int lastArrayIndex = consistentComponentsList.size() - 1;
                consistentComponentsList.get(lastArrayIndex).add(nodeQueue.get(0));

                for (GraphNode tempNode : nodeQueue.get(0).getConnectionList()) {
                    if (!consistentComponentsList.get(lastArrayIndex).contains(tempNode) && !nodeQueue.contains(tempNode)) {
                        nodeQueue.add(tempNode);
                        tempNodeList.remove(tempNode);
                    }
                }

                nodeQueue.remove(0);
            }
        }

        int biggestCountId = -1;
        int biggestCount = -1;

        for (int i = 0; i < consistentComponentsList.size(); i++) {
            if (consistentComponentsList.get(i).size() > biggestCount) {
                biggestCount = consistentComponentsList.get(i).size();
                biggestCountId = i;
            }
        }

        if (biggestCountId >= 0)
            return consistentComponentsList.get(biggestCountId);
        else
            return null;
    }

    /**
     * randomizuje graf
     */
    public void randomizeGraph() {
        for (int j = 0; j < 100; j++) {
            int randomIndexA = (int) ((nodeGraph.size() - 1) * Math.random());
            int randomIndexB = (int) ((nodeGraph.size() - 1) * Math.random());

            while (nodeGraph.get(randomIndexA).getConnectionList().size() == 0) {
                randomIndexA = (int) ((nodeGraph.size() - 1) * Math.random());
            }

            while (nodeGraph.get(randomIndexB).getConnectionList().size() == 0 || randomIndexB == randomIndexA) {
                randomIndexB = (int) ((nodeGraph.size() - 1) * Math.random());
            }

            GraphNode nodeA = nodeGraph.get(randomIndexA);
            GraphNode nodeB = nodeGraph.get(randomIndexB);

            int randomIndexC = (int) ((nodeA.getConnectionList().size() - 1) * Math.random());
            int randomIndexD = (int) ((nodeB.getConnectionList().size() - 1) * Math.random());

            boolean areNodesCBColliding = true;
            boolean areNodesDAColliding = true;


            for (int i = 0; i < nodeA.getConnectionList().size(); i++) {
                if (!nodeA.getConnectionList().get(i).getConnectionList().contains(nodeB) && nodeA.getConnectionList().get(i) != nodeB) {
                    randomIndexC = i;
                    areNodesCBColliding = false;
                    break;
                }
            }

            for (int i = 0; i < nodeB.getConnectionList().size(); i++) {
                if (!nodeB.getConnectionList().get(i).getConnectionList().contains(nodeA) && nodeB.getConnectionList().get(i) != nodeA) {
                    randomIndexD = i;
                    areNodesDAColliding = false;
                    break;
                }
            }

            if (areNodesCBColliding || areNodesDAColliding) {
                continue;
            }

            GraphNode nodeC = nodeA.getConnectionList().get(randomIndexC);
            GraphNode nodeD = nodeB.getConnectionList().get(randomIndexD);

            //odrywa A od C
            nodeA.removeConnection(nodeC);
            nodeC.removeConnection(nodeA);
            //odrywa B od D
            nodeB.removeConnection(nodeD);
            nodeD.removeConnection(nodeB);
            //przylacza A do D
            nodeA.addConnection(nodeD);
            nodeD.addConnection(nodeA);
            //przylacza B do C
            nodeB.addConnection(nodeC);
            nodeC.addConnection(nodeB);
        }
    }

    /**
     * sprawdza czy graf jest spojny przy uzyciu najwiekszej spojnej skladowej i randomizacji, a nastepnie, losuje wagi krawedzi
     */
    public void createConsistentGraphWithWeights() {
        ArrayList<GraphNode> nodes = checkBiggestConsistentComponent();
        int count = 0;

        while (count < 100 && (nodes.size()) != nodeGraph.size()) {
            randomizeGraph();
            nodes = checkBiggestConsistentComponent();
            count++;
        }
        if (nodes.size() != nodeGraph.size()) {
            System.out.println("Ggraf nie jest spójny po 100 probach randomizacji, wprowadz inna sekwencje.");
        } else {
            generateEdgeArray();
            Random r = new Random();
            for (int i = 0; i < edgeGraph.size(); i++) {
                for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                    //sprawdz czy jakas krawedz nie byla juz oznaczona
                    for (int k = 0; k < edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().size(); k++) {
                        if (edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().get(k).getFirst().getId() == edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()) {
                            int temp = r.nextInt(10) + 1;
                            edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().get(k).setWeight(temp);
                            edgeGraph.get(i).getConnectionEdgeList().get(j).setWeight(temp);
                            break;
                        }
                    }

                }
            }
            //test wag (krawedz) - waga
            for (int i = 0; i < edgeGraph.size(); i++) {
                for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                    System.out.printf("(%d, %d) - %d, ", edgeGraph.get(i).getConnectionEdgeList().get(j).getFirst().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getWeight());
                }
                System.out.println(" ");
            }
        }
    }


}
