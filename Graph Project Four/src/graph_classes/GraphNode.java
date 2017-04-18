package graph_classes;

import java.util.ArrayList;

/**
 * Created by Mateusz on 10.04.2017.
 */

//opakowywuje pojedynczy wezel grafu
public class GraphNode {

    /**
     * Numer identyfikacyjny węzła.
     */
    private int id;
    /**
     * Zmienna mówiąca o odwiedzeniu wierzcholka.
     */
    private boolean isVisited = false;

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

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

    /**
     * tworzy głeboką kopie arraylist sasiednich wierzcholkow
     *
     * @param from lista sasiednich wierzcholkow
     * @return gleboko sklonowana lsita sasiednich wierzcholkow
     */
    public ArrayList<GraphNode> deepNodeArrayListCopy(ArrayList<GraphNode> from) {
        ArrayList<GraphNode> to = new ArrayList<>();
        for (int i = 0; i < from.size(); i++) {
            to.add(new GraphNode(from.get(i).getId()));
        }
        for (int i = 0; i < to.size(); i++) {
            for (int j = 0; j < to.size(); j++) {
                for (int k = 0; k < from.get(i).getConnectionList().size(); k++) {
                    if (to.get(j).getId() == from.get(i).getConnectionList().get(k).getId())
                        to.get(i).addConnection(to.get(j));
                }
            }
        }
        return to;
    }
}