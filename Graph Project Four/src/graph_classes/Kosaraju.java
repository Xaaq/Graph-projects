package graph_classes;

import java.util.*;

/**
 * Created by Mateusz on 10.04.2017.
 */

public class Kosaraju {

    /**
     * konstruktor który tworzy obiekt do wykonania algorytmu Kosaraju dla danego digrafu
     */
    public Kosaraju(DiGraph diGraph){
        graph = new List[diGraph.getGraphMatrix().length];
        ArrayList<GraphNode> graphNodes = diGraph.getNodeGraph();

        for (int i = 0; i < diGraph.getGraphMatrix().length; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for(GraphNode eachNode: graphNodes){
            for (GraphNode e : eachNode.getConnectionList()) {
                graph[eachNode.getId()].add(e.getId());
            }
        }
    }

    private List<Integer>[] graph;

    /**
     * DFS - przeszukiwanie w głąb
     **/
    public void DFS(List<Integer>[] graph, int vertex, boolean[] visited, List<Integer> tmpSSCList) {
        // zaznaczamy wierzchołek jako odwiedzony
        visited[vertex] = true;
        for (int i = 0; i < graph[vertex].size(); i++)
            if (!visited[graph[vertex].get(i)])
                DFS(graph, graph[vertex].get(i), visited, tmpSSCList);
        tmpSSCList.add(vertex);
    }

    /**
     * transpozycja grafu
     **/
    public List<Integer>[] getTranspose(List<Integer>[] graph) {
        int size = graph.length;
        List<Integer>[] g = new List[size];
        for (int i = 0; i < size; i++)
            g[i] = new ArrayList<Integer>();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < graph[i].size(); j++)
                g[graph[i].get(j)].add(i);
        return g;
    }

    /**
     * funkcja która zwraca kolejność odwiedzenia wierzchołków podczas DFS
     **/
    public List<Integer> findDFSOrder(List<Integer>[] graph, boolean[] visited) {
        int V = graph.length;
        List<Integer> order = new ArrayList<Integer>();

        for (int i = 0; i < V; i++)
            if (!visited[i])
                DFS(graph, i, visited, order);
        return order;
    }

    /**
     * Funkcja która zwraca wszystki silnie spójne składowe grafu
     **/
    public List<List<Integer>> getSCComponents() {
        int size = graph.length;
        boolean[] visited = new boolean[size];
        List<Integer> orderList = findDFSOrder(graph, visited);
        // transpozycja grafu
        List<Integer>[] transposedGraphList = getTranspose(graph);

        // odwracamy kolejność, żeby mieć taką jaką chcemy
        Collections.reverse(orderList);
        // czyścimy tablicę odwiedzonych wierzchołków
        visited = new boolean[size];

        List<List<Integer>> SCComponents = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            // pobieramy wierzchołek z listy kolejności
            int vertex = orderList.get(i);
            if (!visited[vertex]) {
                //lista w której przechowujemy poszczególne silnie spójne skladowe
                List<Integer> tmpSSCList = new ArrayList<>();
                DFS(transposedGraphList, vertex, visited, tmpSSCList);
                SCComponents.add(tmpSSCList);
            }
        }
        return SCComponents;
    }

}




















