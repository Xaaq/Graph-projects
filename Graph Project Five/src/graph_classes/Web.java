package graph_classes;

import java.util.ArrayList;

/**
 * Created by Xaaq333 on 2017-03-11.
 */

/**
 * przechowuje graf w postaci macierzy i funkcje z nim zwiazane
 */
public class Web {

    /**
     * Przechowuje sieć w formie warstw wierzchołków.
     */
    private ArrayList<ArrayList<WebNode>> layerWeb;

    /**
     * Zwraca tablicę z warstwami sieci.
     *
     * @return Lista warstw sieci
     */
    public ArrayList<ArrayList<WebNode>> getLayerWeb() {
        return layerWeb;
    }
}

