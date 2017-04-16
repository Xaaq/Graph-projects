package graph_classes;

import java.util.ArrayList;

/**
 * Pojedyńczy węzeł sieci.
 */
public class WebNode {

    /**
     * Licznik id.
     */
    private static int idCounter = 0;

    /**
     * Numer identyfikacyjny węzła.
     */
    private int id;

    /**
     * Lista krawędzi wychodzących.
     */
    private ArrayList<WebEdge> connectionList = new ArrayList<>();

    /**
     * Tworzy węzeł z podanym id.
     */
    WebNode() {
        this.id = idCounter++;
    }

    /**
     * Dodaje połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void addConnection(WebNode connectionNode) {
        //TODO: poprawic zeby funkcja dodawała krawędź
//        connectionList.add(connectionNode);
    }

    /**
     * Usuwa połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void removeConnection(WebNode connectionNode) {
        //TODO: poprawić tą funkcję zeby wyszukiwała edge a nie node (ewentualnie dopisac drugą ktora przyjume edge)
//        connectionList.remove(connectionNode);
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
    public ArrayList<WebEdge> getConnectionList() {
        return connectionList;
    }
}