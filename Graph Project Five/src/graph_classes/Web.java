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
     * Szuka minimalnej ścieżki (przechodzącej przez najmniejszą ilość węzłów) od początku do końca sieci.
     *
     * @return lista węzłów uczestniczących w minimalnej ścieżce
     */
    public ArrayList<WebNode> searchMinimalPathFromStartToEnd() {
        WebNode finalNode = layerWeb.get(layerWeb.size() - 1).get(0);
        ArrayList<WebNode> minimalPath = new ArrayList<>();
        ArrayList<ArrayList<WebNode>> arrayOfPaths = new ArrayList<>();
        arrayOfPaths.add((ArrayList<WebNode>) layerWeb.get(0).clone());

        while (!arrayOfPaths.isEmpty()) {
            ArrayList<WebNode> actualPath = arrayOfPaths.get(0);
            arrayOfPaths.remove(0);
            WebNode actualPathLastNode = actualPath.get(actualPath.size() - 1);

            for (WebEdge actualEdge: actualPathLastNode.getOutputConnectionList()) {
                if (actualEdge.getValue() <= 0)
                    continue;

                WebNode nodeToAddToPath = actualEdge.getOutputNode();
                ArrayList<WebNode> newPath = (ArrayList<WebNode>) actualPath.clone();
                newPath.add(nodeToAddToPath);
                arrayOfPaths.add(newPath);

                if (nodeToAddToPath == finalNode)
                    return newPath;
            }
        }

        return null;
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

        for (int i = 0; i < layerWeb.size() - 1; i++) {
            ArrayList<WebNode> firstLayerNodeArray = layerWeb.get(i);
            ArrayList<WebNode> secondLayerNodeArray = layerWeb.get(i + 1);

            //dodaje po jednym połączeniu z każdego węzła warstwy wcześniejszej do losowych w warstwie następnej
            for (int j = 0; j < firstLayerNodeArray.size(); j++) {
                int secondLayerNodeIndex = (int) Math.round((secondLayerNodeArray.size() - 1) * Math.random());
                WebNode firstLayerNode = firstLayerNodeArray.get(j);
                WebNode secondLayerNode = secondLayerNodeArray.get(secondLayerNodeIndex);

                int randomValue = (int) Math.round(Math.random() * 9 + 1);
                firstLayerNode.addConnection(secondLayerNode, randomValue);
            }

            //sprawdza czy w warstwie późniejszej każdy węzeł ma połączenie wchodzące, a jak nie to dodaje
            for (WebNode secondLayerNode : secondLayerNodeArray)
                if (secondLayerNode.getInputConnectionList().size() == 0) {
                    int firstLayerNodeIndex = (int) Math.round((firstLayerNodeArray.size() - 1) * Math.random());
                    WebNode firstLayerNode = firstLayerNodeArray.get(firstLayerNodeIndex);

                    while (firstLayerNode.isThereConnection(secondLayerNode)) {
                        firstLayerNodeIndex = (int) Math.round((firstLayerNodeArray.size() - 1) * Math.random());
                        firstLayerNode = firstLayerNodeArray.get(firstLayerNodeIndex);
                    }

                    int randomValue = (int) Math.round(Math.random() * 9 + 1);
                    firstLayerNode.addConnection(secondLayerNode, randomValue);
                }
        }

        //licznik zeby sie nie zacięło na generowaniu 2 * N krawędzi
        int counter = numberOfLayers * 100;

        //dodaje 2 * N krawędzi
        for (int i = 0; i < numberOfLayers * 2; i++) {
            int firstLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
            int secondLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
            int firstNodeIndex = (int) Math.round((layerWeb.get(firstLayerIndex).size() - 1) * Math.random());
            int secondNodeIndex = (int) Math.round((layerWeb.get(secondLayerIndex).size() - 1) * Math.random());
            WebNode firstLayerNode = layerWeb.get(firstLayerIndex).get(firstNodeIndex);
            WebNode secondLayerNode = layerWeb.get(secondLayerIndex).get(secondNodeIndex);

            //można usunąć ostatni warunek aby pozwolić żeby generowało też ścieżki na któych można iść w obu kierunkach
            while (firstLayerNode == secondLayerNode || firstLayerIndex == layerWeb.size() - 1 || secondLayerIndex == 0
                    || firstLayerNode.isThereConnection(secondLayerNode)
                    || secondLayerNode.isThereConnection(firstLayerNode)) {
                firstLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
                secondLayerIndex = (int) Math.round((layerWeb.size() - 1) * Math.random());
                firstNodeIndex = (int) Math.round((layerWeb.get(firstLayerIndex).size() - 1) * Math.random());
                secondNodeIndex = (int) Math.round((layerWeb.get(secondLayerIndex).size() - 1) * Math.random());
                firstLayerNode = layerWeb.get(firstLayerIndex).get(firstNodeIndex);
                secondLayerNode = layerWeb.get(secondLayerIndex).get(secondNodeIndex);

                //warunek przerwania gdyby za długo szukało
                if (counter-- < 0)
                    break;
            }

            //warunek przerwania gdyby za długo szukało
            if (counter < 0)
                break;

            int randomValue = (int) Math.round(Math.random() * 9 + 1);
            firstLayerNode.addConnection(secondLayerNode, randomValue);
        }
    }
}

