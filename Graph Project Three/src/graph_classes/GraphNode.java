package graph_classes;

import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-16.
 */

//opakowywuje pojedynczy wezel grafu
public class GraphNode {

    /**
     * Numer identyfikacyjny węzła.
     */
    private int id;

    /**
     * Tablica sąsiadów - lista węzłów, z którymi jest połączony.
     */
    private ArrayList<GraphNode> connectionList = new ArrayList<>();

    /**
     * Tworzy węzeł z podanym id.
     *
     * @param id numer identyfikacyjny węzła
     */
    public GraphNode(int id) {
        this.id = id;
    }

    /**
     * Dodaje połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void addConnection(GraphNode connectionNode) {
        connectionList.add(connectionNode);
    }

    /**
     * Usuwa połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void removeConnection(GraphNode connectionNode) {
        connectionList.remove(connectionNode);
    }

    /**
     * Zwraca id węzła.
     *
     * @return id węzła
     */
    public int getId() {
        return id;
    }

    /**
     * Zwraca listę połączeń tego wierzchołka.
     *
     * @return lista połączeń wierzchołka
     */
    public ArrayList<GraphNode> getConnectionList() {
        return connectionList;
    }
}