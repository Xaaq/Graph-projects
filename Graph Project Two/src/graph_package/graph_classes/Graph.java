package graph_package.graph_classes;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Xaaq333 on 2017-03-11.
 */
//przechowuje graf w postaci macierzy i funkcje z nim zwiazane
public class Graph {

    //przechowuje graf w formie macierzy
    private int[][] graphMatrix;
    //przechowywuje graf w postaci wezlow
    private ArrayList<GraphNode> nodeGraph;

    //konstruktor
    public Graph() {

    }
    //Zwraca węzeł według id
    public GraphNode getGraphNode(int id){
        for(GraphNode eachNode: nodeGraph){
            if(eachNode.getId() == id){
                return eachNode;
            }
        }
        return null;
    }

    //ustawia macierz tego grafu na podana
    public void setGraphMatrix(int[][] inputMatrix) {
        graphMatrix = inputMatrix.clone();
    }

    //zwraca graf w posatci macierzy
    public int[][] getGraphMatrix() {
        return graphMatrix.clone();
    }

    //ustawia graf w postaci wezlow na podany
    public void setNodeGraph(ArrayList<GraphNode> inputNodeGraph) {
        nodeGraph = inputNodeGraph;
    }

    //zwraca graf w postaci węzłów
    public ArrayList<GraphNode> getNodeGraph(){return (ArrayList<GraphNode>)nodeGraph; }


    public int getNodeGraphLength() {
        return nodeGraph.size();
    }

    //sub-klasa ktora opakowywuje wierzcholek i liczbe polaczonych z nim krawedzi (potrzebna do tworzenia grafu)
    private class Verticle {
        public final int verticleNumber;
        public int connectionCount;

        Verticle(int verticleNumber, int connectionCount) {
            this.verticleNumber = verticleNumber;
            this.connectionCount = connectionCount;
        }
    }

    //sub-klasa ktora sluzy do porownania obiektow klasy Verticle
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

    //robi graf z podanej sekwencji liczb jesli sie da
    public int[][] checkNumberSequence(int[] inputNumberSequence) {
        ArrayList<Verticle> verticleSequece = new ArrayList<>();

        for (int i = 0; i < inputNumberSequence.length; i++) {
            verticleSequece.add(new Verticle(i, inputNumberSequence[i]));
        }

        return checkNumberSequence(verticleSequece);
    }

    //sprawdza czy mozna zrobic graf z podanej sekwencji liczb
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

    //funkcja ktora generuje tablice wezlow na podstawie macierzy
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

    //sprawdza najwieksza skladowa grafu i zwraca tablice wezlow ktore do niej naleza
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

// Program do sprawdzania (dla małych grafów) czy graf jest hamiltonowski.

public boolean isHamiltonianGraph(Graph graph){
    //Wystarczy sprawdzić od wierzchołka 0. Jak jest hamiltonowski to nie ważne od którego wierzchołka zaczniemy
    DFSPaths dfs = new DFSPaths(graph,0);

    return dfs.isHamiltonianGraph();
}
}











