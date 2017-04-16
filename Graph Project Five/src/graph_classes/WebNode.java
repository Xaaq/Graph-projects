package graph_classes;

import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-16.
 */

/**
 * Pojedyńczy węzeł sieci.
 */
public class WebNode {

    /**
     * Numer identyfikacyjny węzła.
     */
    private int id;

    /**
     * Tablica sąsiadów - lista węzłów, z którymi jest połączony.
     */
    private ArrayList<WebNode> connectionList = new ArrayList<>();

    /**
     * Tworzy węzeł z podanym id.
     *
     * @param id numer identyfikacyjny węzła
     */
    public WebNode(int id) {
        this.id = id;
    }

    /**
     * Dodaje połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void addConnection(WebNode connectionNode) {
        connectionList.add(connectionNode);
    }

    /**
     * Usuwa połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void removeConnection(WebNode connectionNode) {
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
    public ArrayList<WebNode> getConnectionList() {
        return connectionList;
    }
}