package graph_classes;

/**
 * Created by Paweł on 08.04.2017.
 */

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa zawierająca liste sąsiadujących krawędzi.
 */
public class GraphEdge {
    private GraphNode first;
    private GraphNode second;
    private int weight = -1;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

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

    @Override
    public boolean equals(Object edge) {
        GraphEdge edge2 = (GraphEdge) edge;
        if (first.getId() == edge2.first.getId() && second.getId() == edge2.second.getId() || first.getId() == edge2.second.getId() && second.getId() == edge2.first.getId())
            return true;
        else
            return false;
    }

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

    @Override
    public String toString() {
        return "(" + getFirst().getId() + "," + getSecond().getId() + ")";
    }

    /**
     * zwraca drugi wierzcholek
     *
     * @param node pierwszy wierzcholek
     * @return drugi wierzcholek
     */
    public GraphNode getOther(int node) {
        if (node == first.getId())
            return second;
        else
            return first;
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
