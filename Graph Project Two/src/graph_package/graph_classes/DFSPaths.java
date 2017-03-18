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


    public DFSPaths(Graph graph, int startedVertex){
        isHamilton = false;
        this.startedVertex = startedVertex;
        edgeTo = new int[graph.getNodeGraphLength()];
        marked = new boolean[graph.getNodeGraphLength()];
        dfs_recursive(graph, startedVertex);
    }

    private void dfs_recursive(Graph graph, int vertex){
        // oznaczamy wierzchołek, jako oznaczony
        marked[vertex] = true;
        //Pobieramy wierzchołek
        GraphNode node = graph.getGraphNode(vertex);
        // odwiedzamy każdy sąsiedni nieodwiedzony wierzchołek i zapisujemy trase
        for (GraphNode eachVertex: node.getConnectionList()){
        // sprawdzamy czy sąsiadem naszego wierzchołka jest punkt startowy,
            // oraz czy wszystkie wierzchołki są już odwiedzone
            if(eachVertex.getId() == startedVertex && isAllMarked())
                isHamilton = true;
            //jeżeli nieodwiedzony
            if(!marked[eachVertex.getId()]){
                edgeTo[eachVertex.getId()] = vertex;
                dfs_recursive(graph, eachVertex.getId());
            }
        }
    }
    // Sprawdza, czy wszystkie węzły zostały odwiedzone
    private boolean isAllMarked(){
        for(boolean check: marked){
            if(!check)
                return false;
        }
        return true;
    }

    public boolean isHamiltonianGraph(){return isHamilton;}

}
