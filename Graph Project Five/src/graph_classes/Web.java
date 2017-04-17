package graph_classes;

import java.util.ArrayList;

/**
 * przechowuje graf w postaci macierzy i funkcje z nim zwiazane
 */
public class Web {

    /**
     * Przechowuje sieć w formie warstw wierzchołków.
     */
    private ArrayList<ArrayList<WebNode>> layerWeb;

    /**
     * Tworzy obiekt sieci.
     *
     * @param numberOfLayers liczba warstw w sieci
     */
    public Web(int numberOfLayers) {
        layerWeb = new ArrayList<>();

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
     * Wyszukuje warstwę, w której znajduje się dany węzeł w sieci.
     *
     * @param nodeToFind węzeł do znalezienia
     * @return numer warstwy, w której znajduje się węzeł
     */
    public int getLayerOfNode(WebNode nodeToFind) {
        for (int i = 0; i < layerWeb.size(); i++) {
            ArrayList<WebNode> nodeArray = layerWeb.get(i);

            for (WebNode singleNode : nodeArray) {
                if (singleNode == nodeToFind)
                    return i;
            }
        }

        return -1;
    }

    /**
     * Inicjalizuje elementy sieci (tablice, zmienne).
     *
     * @param numberOfLayers liczba warstw w sieci
     */
    private void initializeWeb(int numberOfLayers) {
        //tworzy puste warstwy
        for (int i = 0; i <= numberOfLayers; i++) {
            layerWeb.add(new ArrayList<>());
        }

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

//        for (int i = 0; i < layerWeb.size() - 1; i++) {
//            ArrayList<WebNode> firstLayerNodeArray = layerWeb.get(i);
//            ArrayList<WebNode> secondLayerNodeArray = layerWeb.get(i + 1);
//
//            //dodaje po jednym połączeniu z każdego węzła warstwy wcześniejszej do losowych w warstwie następnej
//            for (int j = 0; j < firstLayerNodeArray.size(); j++) {
//                int secondLayerNodeIndex = (int) Math.round((secondLayerNodeArray.size() - 1) * Math.random());
//                WebNode firstLayerNode = firstLayerNodeArray.get(j);
//                WebNode secondLayerNode = secondLayerNodeArray.get(secondLayerNodeIndex);
//
//                int randomValue = (int) Math.round(Math.random() * 9 + 1);
//                firstLayerNode.addConnection(secondLayerNode, randomValue);
//            }
//
//            //sprawdza czy w warstwie późniejszej każdy węzeł ma połączenie wchodzące, a jak nie to dodaje
//            for (WebNode secondLayerNode : secondLayerNodeArray)
//                if (secondLayerNode.getInputConnectionList().size() == 0) {
//                    int firstLayerNodeIndex = (int) Math.round((firstLayerNodeArray.size() - 1) * Math.random());
//                    WebNode firstLayerNode = firstLayerNodeArray.get(firstLayerNodeIndex);
//
//                    while (firstLayerNode.isThereConnection(secondLayerNode)) {
//                        firstLayerNodeIndex = (int) Math.round((firstLayerNodeArray.size() - 1) * Math.random());
//                        firstLayerNode = firstLayerNodeArray.get(firstLayerNodeIndex);
//                    }
//
//                    int randomValue = (int) Math.round(Math.random() * 9 + 1);
//                    firstLayerNode.addConnection(secondLayerNode, randomValue);
//                }
//        }

        //dodaje 2 * N krawędzi
        for (int i = 0; i < numberOfLayers * 2; i++) {
            int firstLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
            int secondLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
            int firstNodeIndex = (int) Math.round((layerWeb.get(firstLayerIndex).size() - 1) * Math.random());
            int secondNodeIndex = (int) Math.round((layerWeb.get(secondLayerIndex).size() - 1) * Math.random());
            WebNode firstLayerNode = layerWeb.get(firstLayerIndex).get(firstNodeIndex);
            WebNode secondLayerNode = layerWeb.get(secondLayerIndex).get(secondNodeIndex);

            while (firstNodeIndex == secondNodeIndex || firstLayerIndex == layerWeb.size() - 1 || secondLayerIndex == 0
                    || firstLayerNode.isThereConnection(secondLayerNode)) {
                firstLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
                secondLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
                firstNodeIndex = (int) Math.round((layerWeb.get(firstLayerIndex).size() - 1) * Math.random());
                secondNodeIndex = (int) Math.round((layerWeb.get(secondLayerIndex).size() - 1) * Math.random());
            }

            System.out.println(firstLayerNode.getId() + " " + secondLayerNode.getId());

            int randomValue = (int) Math.round(Math.random() * 9 + 1);
            firstLayerNode.addConnection(secondLayerNode, randomValue);
        }
    }
}

