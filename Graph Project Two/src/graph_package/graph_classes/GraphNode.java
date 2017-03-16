package graph_package.graph_classes;

import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-16.
 */

//opakowywuje pojedynczy wezel grafu
public class GraphNode {

    //id tego wezla
    private int id;
    //lista id innych wezlow z ktorymi jest polaczony
    private ArrayList<Integer> connectionList = new ArrayList<>();

    GraphNode(int id) {
        this.id = id;
    }

    public void addConnection(int connectionId) {
        connectionList.add(connectionId);
    }

    public ArrayList<Integer> getConnectionList() {
        return (ArrayList<Integer>) connectionList.clone();
    }
}