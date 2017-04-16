package graph_classes;

import java.util.ArrayList;

/**
 * przechowuje graf w postaci macierzy i funkcje z nim zwiazane
 */
public class Web {

    /**
     * Przechowuje sieć w formie warstw wierzchołków.
     */
    private ArrayList<ArrayList<WebNode>> layerWeb = new ArrayList<>();

    /**
     * Tworzy obiekt sieci.
     *
     * @param numberOfLayers liczba warstw w sieci
     */
    public Web(int numberOfLayers) {
        initializeWeb(numberOfLayers);
    }

    /**
     * Zwraca listę z warstwami sieci.
     *
     * @return lista warstw sieci
     */
    public ArrayList<ArrayList<WebNode>> getLayerList() {
        return layerWeb;
    }

    /**
     * Inicjalizuje elementy sieci (tablice, zmienne).
     *
     * @param numberOfLayers liczba warstw w sieci
     */
    private void initializeWeb(int numberOfLayers) {
        //tworzy puste warstwy
        for (int i = 0; i < numberOfLayers; i++) {
            layerWeb.add(new ArrayList<>());
        }

        layerWeb.add(new ArrayList<>());

        //dodanie węzłów początkowego i końcowego
        layerWeb.get(0).add(new WebNode());
        layerWeb.get(numberOfLayers).add(new WebNode());

        //wylosowanie liczby węzłów w każdej warstwie
        for (int i = 1; i < numberOfLayers; i++) {
            int numberOfNodesInLayer = (int) Math.round(Math.random() * (numberOfLayers - 2)) + 2;

            for (int j = 0; j < numberOfNodesInLayer; j++) {
                layerWeb.get(i).add(new WebNode());
            }
        }
    }
}

