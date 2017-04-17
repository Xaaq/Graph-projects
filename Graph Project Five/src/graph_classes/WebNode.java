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
    private ArrayList<WebEdge> outputConnectionList;

    /**
     * Lista krawędzi wchodzących.
     */
    private ArrayList<WebEdge> inputConnectionList;

    /**
     * Tworzy węzeł z podanym id.
     */
    WebNode() {
        id = idCounter++;
        outputConnectionList = new ArrayList<>();
        inputConnectionList = new ArrayList<>();
    }

    /**
     * Dodaje połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     * @param value          waga krawędzi
     */
    public void addConnection(WebNode connectionNode, int value) {
        WebEdge webEdge = new WebEdge(value, this, connectionNode);
        outputConnectionList.add(webEdge);
        connectionNode.addInputConnection(webEdge);
    }

    private void addInputConnection(WebEdge edgeToAdd) {
        inputConnectionList.add(edgeToAdd);
    }

    /**
     * Usuwa połączenie do danego węzła.
     *
     * @param connectionNode referencja do danego węzła
     */
    public void removeConnection(WebNode connectionNode) {
        for (WebEdge webEdge : outputConnectionList) {
            if (webEdge.getOutputNode() == connectionNode) {
                outputConnectionList.remove(webEdge);
                webEdge.getOutputNode().inputConnectionList.remove(webEdge);
                break;
            }
        }
    }

    /**
     * Przeszukuje liste sąsiadów w poszukiwaniu podanego węzła.
     *
     * @param connectionNode węzeł do zanalezienia
     * @return czy znalazło węzeł
     */
    public boolean isThereConnection(WebNode connectionNode) {
        for (WebEdge webEdge : outputConnectionList) {
            if (webEdge.getOutputNode() == connectionNode) {
                return true;
            }
        }

        return false;
    }

    /**
     * Szuka krawędzi, która prowadzi do podanego węzła.
     *
     * @param connectionNode węzeł, do którego prowadzi krawędź
     * @return krawędź, która prowadzi do danego węzła
     */
    public WebEdge getEdgeLeadingToNode(WebNode connectionNode) {
        for (WebEdge singleEdge : outputConnectionList) {
            if (singleEdge.getOutputNode() == connectionNode)
                return singleEdge;
        }

        return null;
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
     * Zwraca listę połączeń wychodzących z tego wierzchołka.
     *
     * @return lista połączeń wychodzących z tego wierzchołka
     */
    public ArrayList<WebEdge> getOutputConnectionList() {
        return outputConnectionList;
    }

    /**
     * Zwraca listę połączeń wchodzących do tego wierzchołka.
     *
     * @return lista połączeń wchodzących do tego wierzchołka
     */
    public ArrayList<WebEdge> getInputConnectionList() {
        return inputConnectionList;
    }
}