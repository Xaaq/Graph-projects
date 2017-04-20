package graph_classes;

import java.util.*;

/**
 * Created by Mateusz on 10.04.2017.
 */

public class Kosaraju {

    /**
     * konstruktor który tworzy obiekt do wykonania algorytmu Kosaraju dla danego digrafu
     */
    public Kosaraju(DiGraph diGraph) {
        graph = new List[diGraph.getGraphMatrix().length];
        ArrayList<GraphNode> graphNodes = diGraph.getNodeGraph();

        for (int i = 0; i < diGraph.getGraphMatrix().length; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (GraphNode eachNode : graphNodes) {
            for (GraphNode e : eachNode.getConnectionList()) {
                graph[eachNode.getId()].add(e.getId());
            }
        }
    }

    /**
     * lista sąsiednich wierzchołków
     */
    private List<Integer>[] graph;
    /**
     * przechowuje wszystkie silnie spójne składowe
     */
    private List<List<Integer>> kosarajuSCComponents;

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
     * Funkcja która zwraca wszystkie silnie spójne składowe grafu
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
        kosarajuSCComponents = new ArrayList<>();
        kosarajuSCComponents = SCComponents;

        printSCComponents(kosarajuSCComponents);
        return SCComponents;
    }

    private void printSCComponents(List<List<Integer>> SCComponents) {
        System.out.println("Silnie spójne składowe:");
        for (List<Integer> l : SCComponents) {
            System.out.print("[");
            for (int a : l) {
                // +1 bo zaczynamy od węzłą 1 a nie 0 !!! poprawić potem
                System.out.print(" " + (a));
            }
            System.out.print(" ]\n");

        }
    }

    /**
     * szukamy największej silnie spójnej składowej
     */
    public List<Integer> getTheBiggestSCComponent() {
        int size_max = 0;
        List<Integer> theBiggestSCComponent = new ArrayList<>();
        for (List<Integer> l : kosarajuSCComponents) {
            if (l.size() > size_max){
                size_max = l.size();
                theBiggestSCComponent = new ArrayList<>();
                theBiggestSCComponent = l;
            }
        }
        //System.out.println(size_max);
        //System.out.println(theBiggestSCComponent);
        return theBiggestSCComponent;
    }

}





















