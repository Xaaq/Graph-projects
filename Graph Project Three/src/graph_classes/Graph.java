package graph_classes;

import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-11.
 */

//przechowuje graf w postaci macierzy i funkcje z nim zwiazane
public class Graph {

    /**
     * Przechowuje graf w formie wierzchołków.
     */
    private ArrayList<GraphNode> nodeGraph;

    /**
     * Zwraca listę wierzchołków grafu.
     *
     * @return Lista wierzchołków grafu
     */
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }
}
