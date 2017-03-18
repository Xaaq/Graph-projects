package graph_package.graph_classes;

import java.util.ArrayList;

/**
 * Created by Mateusz on 18.03.2017.
 */
public class DFSPaths {
    private int[] edgeTo;
    // tablica wierzchołków już odwiedzonych
    private boolean[] marked;
    // wierzchołek startowy
    private int startedVertex;
    // czy jest to cykl Hamiltona
    private boolean isHamilton;
    private int flaga;
    // wierzchołek na którym się skończyło
    private int endVertex;
    public DFSPaths(Graph graph, int startedVertex){
        isHamilton = false;
        flaga = 0;
        this.startedVertex = startedVertex;
        edgeTo = new int[graph.getNodeGraphLength()];
        marked = new boolean[graph.getNodeGraphLength()];
        dfs_recursive(graph, startedVertex);
    }

    private void dfs_recursive(Graph graph, int vertex){
        // sprawdzamy czy udało się wrócić do punktu startowego
        // jeśli tak to jest to cykl Hamiltona
        // Musi minąć przynajmniej jedna iteracja
        System.out.print(flaga);
        System.out.print(" :");
        System.out.println(vertex);

        // oznaczamy wierzchołek, jako oznaczony
        marked[vertex] = true;
        GraphNode node = graph.getGraphNode(vertex);
        // odwiedzamy każdy sąsiedni nieodwiedzony wierzchołek i zapisujemy trase
        for (GraphNode eachVertex: node.getConnectionList()){
            ++flaga;
            //jeżeli nieodwiedzony
            if(eachVertex.getId() == startedVertex && isAllMarked())
                isHamilton = true;
            if(!marked[eachVertex.getId()]){
                edgeTo[eachVertex.getId()] = vertex;
                dfs_recursive(graph, eachVertex.getId());
            }
        }
    }
    private boolean isAllMarked(){
        for(boolean check: marked){
            if(!check)
                return false;
        }
        return true;
    }

    public boolean isHamiltonianGraph(){return isHamilton;}

}
