package graph_classes;

/**
 * Created by Paweł on 08.04.2017.
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa zawierająca liste sąsiadujących krawędzi.
 */
public class GraphEdge {
    private GraphNode first;
    private GraphNode second;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight = -1;

    /**
     * Tablica sąsiadów - lista krawedzi, z którymi jest połączony.
     */
    public GraphEdge() {
    }

    /**
     * gettter listy sasiedztwa
     *
     * @return lista sasiedztwa wierzcholka
     */
    public ArrayList<GraphEdge> getConnectionEdgeList() {
        return connectionEdgeList;
    }

    private ArrayList<GraphEdge> connectionEdgeList = new ArrayList<>();

    public GraphNode getFirst() {

        return first;
    }

    public GraphNode getSecond() {
        return second;
    }

    public GraphEdge(GraphNode v, GraphNode w) {
        first = v;
        second = w;
    }


    /**
     * konwertuje pary wierzcholkow z listy siasiednich wierzcholkow sasiednich krawedzi
     *
     * @param nodesCopy
     * @return
     */
    void convertNodesToEdges(ArrayList<GraphNode> nodesCopy, int index) {
        for (int j = 0; j < nodesCopy.get(index).getConnectionList().size(); j++) {
            //dodaj krawedz (index, j)
            connectionEdgeList.add(new GraphEdge(nodesCopy.get(index), nodesCopy.get(index).getConnectionList().get(j)));
        }
    }
}
