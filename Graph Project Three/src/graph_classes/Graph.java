package graph_classes;

import java.io.*;
import java.util.*;

/**
 * Created by Xaaq333 on 2017-03-11.
 */

/**
 * przechowuje graf w postaci macierzy i funkcje z nim zwiazane
 */

public class Graph {

    /**
     * Przechowuje graf w formie wierzchołków.
     */
    private ArrayList<GraphNode> nodeGraph;


    public ArrayList<GraphEdge> getEdgeGraph() {
        return edgeGraph;
    }

    /**
     * Przechowuje graf w formie krawedzi sasiadujacych z wierzcholkiem.
     */
    private ArrayList<GraphEdge> edgeGraph = new ArrayList<>();
    /**
     * przechowuje graf w formie macierzy
     */

    private int[][] graphMatrix;

    /**
     * Zwraca listę wierzchołków grafu.
     *
     * @return Lista wierzchołków grafu
     */
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }

    /**
     * ustawia macierz tego grafu na podana
     */
    public void setGraphMatrix(int[][] inputMatrix) {
        graphMatrix = inputMatrix.clone();
    }

    /**
     * zwraca graf w posatci macierzy
     */
    public int[][] getGraphMatrix() {
        return graphMatrix.clone();
    }

    /**
     * ustawia graf w postaci wezlow na podany
     */
    public void setNodeGraph(ArrayList<GraphNode> inputNodeGraph) {
        nodeGraph = inputNodeGraph;
    }

    /**
     * sub-klasa ktora opakowywuje wierzcholek i liczbe polaczonych z nim krawedzi (potrzebna do tworzenia grafu)
     */

    private class Verticle {
        public final int verticleNumber;
        public int connectionCount;

        Verticle(int verticleNumber, int connectionCount) {
            this.verticleNumber = verticleNumber;
            this.connectionCount = connectionCount;
        }
    }

    /**
     * sub-klasa ktora sluzy do porownania obiektow klasy Verticle
     */

    private class VerticleComparator implements Comparator<Verticle> {
        @Override
        public int compare(Verticle verticle1, Verticle verticle2) {
            if (verticle1.connectionCount > verticle2.connectionCount)
                return 1;
            if (verticle1.connectionCount < verticle2.connectionCount)
                return -1;
            return 0;
        }
    }

    /**
     * robi graf z podanej sekwencji liczb jesli sie da
     */

    public int[][] checkNumberSequence(int[] inputNumberSequence) {
        ArrayList<Verticle> verticleSequece = new ArrayList<>();

        for (int i = 0; i < inputNumberSequence.length; i++) {
            verticleSequece.add(new Verticle(i, inputNumberSequence[i]));
        }

        return checkNumberSequence(verticleSequece);
    }

    /**
     * sprawdza czy mozna zrobic graf z podanej sekwencji liczb
     */
    private int[][] checkNumberSequence(ArrayList<Verticle> inputVerticleSequence) {
        ArrayList<Verticle> verticleSequence = (ArrayList<Verticle>) inputVerticleSequence.clone();
        int highestVerticleNumber = 0;

        //sprawdza jaki jest najwieksze id wierzcholka (zeby wiedziec jak duza tablice zrobic)
        for (Verticle verticle : verticleSequence) {
            if (verticle.verticleNumber > highestVerticleNumber)
                highestVerticleNumber = verticle.verticleNumber;
        }

        highestVerticleNumber++;
        int[][] outputArray = new int[highestVerticleNumber][highestVerticleNumber];

        //wypelnia tablice zerami
        for (int i = 0; i < highestVerticleNumber; i++) {
            for (int j = 0; j < highestVerticleNumber; j++) {
                outputArray[i][j] = 0;
            }
        }
        //usuwa wsyzstkie zerowe pozycje z listy
        for (int i = verticleSequence.size() - 1; i >= 0; i--) {
            if (verticleSequence.get(i).connectionCount <= 0)
                verticleSequence.remove(i);
        }

        //jesli lista jest pusta zwraca pusta tablice
        if (verticleSequence.isEmpty())
            return outputArray;

        verticleSequence.sort(new VerticleComparator());
        Verticle lastVerticle = verticleSequence.get(verticleSequence.size() - 1);
        verticleSequence.remove(verticleSequence.size() - 1);

        //jesli trzeba wygenerowac wiecej krawedzi niz jest pozycji w liscie - nie da sie wygenerowac tablicy
        if (lastVerticle.connectionCount > verticleSequence.size())
            return null;

        //odejmuje od ostatnich pozycji z listy wartosc 1 i dodaje znak w tablicy
        for (int i = 1; i <= lastVerticle.connectionCount; i++) {
            int actualNumber = verticleSequence.get(verticleSequence.size() - i).connectionCount - 1;
            verticleSequence.get(verticleSequence.size() - i).connectionCount = actualNumber;

            outputArray[lastVerticle.verticleNumber][verticleSequence.get(verticleSequence.size() - i).verticleNumber] = 1;
            outputArray[verticleSequence.get(verticleSequence.size() - i).verticleNumber][lastVerticle.verticleNumber] = 1;
        }

        int[][] recursiveArray = checkNumberSequence(verticleSequence);

        //jesli otrzymano tablice z funkcji rekurencyjnej - dodaj do biezacej tablicy i zwroc wynik
        if (recursiveArray != null)
            return HelperClass.addSquareMatrixes(outputArray, recursiveArray);
        else
            return null;
    }

    /**
     * funkcja ktora generuje tablice wezlow na podstawie macierzy
     */
    public ArrayList<GraphNode> generateNodeArray() {
        ArrayList<GraphNode> nodeArray = new ArrayList<>();

        for (int i = 0; i < graphMatrix.length; i++) {
            nodeArray.add(new GraphNode(i));
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == 1)
                    nodeArray.get(i).addConnection(nodeArray.get(j));
            }
        }

        return nodeArray;
    }

    /**
     * funkcja ktora generuje tablice krawedzi na podstawie tablicy wierzcholkow
     */
    public void generateEdgeArray() {
        for (int i = 0; i < nodeGraph.size(); i++) {
            edgeGraph.add(new GraphEdge());
            edgeGraph.get(i).convertNodesToEdges(nodeGraph, i);
        }
//        //test krawedzi
//        for (int i = 0; i < edgeGraph.size(); i++) {
//            for(int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
//                System.out.printf("(%d, %d), ", edgeGraph.get(i).getConnectionEdgeList().get(j).getV().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getW().getId());
//            }
//            System.out.println(" ");
//        }
    }

    /**
     * sprawdza najwieksza skladowa grafu i zwraca tablice wezlow ktore do niej naleza
     */
    public ArrayList<GraphNode> checkBiggestConsistentComponent() {
        ArrayList<ArrayList<GraphNode>> consistentComponentsList = new ArrayList<>();
        ArrayList<GraphNode> tempNodeList = (ArrayList<GraphNode>) nodeGraph.clone();

        while (tempNodeList.size() != 0) {
            consistentComponentsList.add(new ArrayList<>());
            ArrayList<GraphNode> nodeQueue = new ArrayList<>();
            nodeQueue.add(tempNodeList.get(0));
            tempNodeList.remove(tempNodeList.get(0));

            while (nodeQueue.size() != 0) {
                int lastArrayIndex = consistentComponentsList.size() - 1;
                consistentComponentsList.get(lastArrayIndex).add(nodeQueue.get(0));

                for (GraphNode tempNode : nodeQueue.get(0).getConnectionList()) {
                    if (!consistentComponentsList.get(lastArrayIndex).contains(tempNode) && !nodeQueue.contains(tempNode)) {
                        nodeQueue.add(tempNode);
                        tempNodeList.remove(tempNode);
                    }
                }

                nodeQueue.remove(0);
            }
        }

        int biggestCountId = -1;
        int biggestCount = -1;

        for (int i = 0; i < consistentComponentsList.size(); i++) {
            if (consistentComponentsList.get(i).size() > biggestCount) {
                biggestCount = consistentComponentsList.get(i).size();
                biggestCountId = i;
            }
        }

        if (biggestCountId >= 0)
            return consistentComponentsList.get(biggestCountId);
        else
            return null;
    }

    /**
     * randomizuje graf
     */
    public void randomizeGraph() {
        for (int j = 0; j < 100; j++) {
            int randomIndexA = (int) ((nodeGraph.size() - 1) * Math.random());
            int randomIndexB = (int) ((nodeGraph.size() - 1) * Math.random());

            while (nodeGraph.get(randomIndexA).getConnectionList().size() == 0) {
                randomIndexA = (int) ((nodeGraph.size() - 1) * Math.random());
            }

            while (nodeGraph.get(randomIndexB).getConnectionList().size() == 0 || randomIndexB == randomIndexA) {
                randomIndexB = (int) ((nodeGraph.size() - 1) * Math.random());
            }

            GraphNode nodeA = nodeGraph.get(randomIndexA);
            GraphNode nodeB = nodeGraph.get(randomIndexB);

            int randomIndexC = (int) ((nodeA.getConnectionList().size() - 1) * Math.random());
            int randomIndexD = (int) ((nodeB.getConnectionList().size() - 1) * Math.random());

            boolean areNodesCBColliding = true;
            boolean areNodesDAColliding = true;


            for (int i = 0; i < nodeA.getConnectionList().size(); i++) {
                if (!nodeA.getConnectionList().get(i).getConnectionList().contains(nodeB) && nodeA.getConnectionList().get(i) != nodeB) {
                    randomIndexC = i;
                    areNodesCBColliding = false;
                    break;
                }
            }

            for (int i = 0; i < nodeB.getConnectionList().size(); i++) {
                if (!nodeB.getConnectionList().get(i).getConnectionList().contains(nodeA) && nodeB.getConnectionList().get(i) != nodeA) {
                    randomIndexD = i;
                    areNodesDAColliding = false;
                    break;
                }
            }

            if (areNodesCBColliding || areNodesDAColliding) {
                continue;
            }

            GraphNode nodeC = nodeA.getConnectionList().get(randomIndexC);
            GraphNode nodeD = nodeB.getConnectionList().get(randomIndexD);

            //odrywa A od C
            nodeA.removeConnection(nodeC);
            nodeC.removeConnection(nodeA);
            //odrywa B od D
            nodeB.removeConnection(nodeD);
            nodeD.removeConnection(nodeB);
            //przylacza A do D
            nodeA.addConnection(nodeD);
            nodeD.addConnection(nodeA);
            //przylacza B do C
            nodeB.addConnection(nodeC);
            nodeC.addConnection(nodeB);
        }
    }

    /**
     * sprawdza czy graf jest spojny przy uzyciu najwiekszej spojnej skladowej i randomizacji, a nastepnie, losuje wagi krawedzi
     */
    public void createConsistentGraphWithWeights() {
        ArrayList<GraphNode> nodes = checkBiggestConsistentComponent();
        int count = 0;

        while (count < 100 && (nodes.size()) != nodeGraph.size()) {
            randomizeGraph();
            nodes = checkBiggestConsistentComponent();
            count++;
        }
        if (nodes.size() != nodeGraph.size()) {
            System.out.println("Ggraf nie jest spójny po 100 probach randomizacji, wprowadz inna sekwencje.");
        } else {
            generateEdgeArray();
            Random r = new Random();
            for (int i = 0; i < edgeGraph.size(); i++) {
                for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                    //sprawdz czy jakas krawedz nie byla juz oznaczona
                    for (int k = 0; k < edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().size(); k++) {
                        if (edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().get(k).equals(edgeGraph.get(i).getConnectionEdgeList().get(j))) {
                            int temp = r.nextInt(10) + 1;
                            edgeGraph.get(edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId()).getConnectionEdgeList().get(k).setWeight(temp);
                            edgeGraph.get(i).getConnectionEdgeList().get(j).setWeight(temp);
                            break;
                        }
                    }

                }
            }
            //test wag (krawedz) - waga
            for (int i = 0; i < edgeGraph.size(); i++) {
                for (int j = 0; j < edgeGraph.get(i).getConnectionEdgeList().size(); j++) {
                    System.out.printf("(%d, %d) - %d, ", edgeGraph.get(i).getConnectionEdgeList().get(j).getFirst().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getSecond().getId(), edgeGraph.get(i).getConnectionEdgeList().get(j).getWeight());
                }
                System.out.println(" ");
            }
            for (int i = 0; i < nodeGraph.size(); i++) {
                System.out.printf("%d -> ", nodeGraph.get(i).getId());
                for (int j = 0; j < nodeGraph.get(i).getConnectionList().size(); j++) {
                    System.out.printf("%d ", nodeGraph.get(i).getConnectionList().get(j).getId());
                }
                System.out.println(" ");
            }
        }
    }

    private HashMap<Integer, Integer> distances = new HashMap<>();

    /**
     * Implementacja dijkstry plus zapis do pliku Dijkstra najkrotszej sciezki plus odlegloscm iedzy wierzcholkami
     *
     * @param beginIndex poczatkowy indeks
     */
    public void dijkstra(int beginIndex) {
        int initialNodeIndex = nodeGraph.get(beginIndex).getId();
        HashMap<Integer, Integer> predecessors = new HashMap<>();
        PriorityQueue<GraphNode> availableNodes = new PriorityQueue(nodeGraph.size(), new Comparator<GraphNode>() {

            public int compare(GraphNode one, GraphNode two) {
                int weightOne = distances.get(one.getId());
                int weightTwo = distances.get(two.getId());
                return weightOne - weightTwo;
            }
        });
        HashSet<GraphNode> visitedNodes = new HashSet<>();
        //oznacz wszystkich poprzednikow wartoscia null, a wszystkich sasaidow inf (MAX_VALUE)
        for (GraphNode key : nodeGraph) {
            predecessors.put(key.getId(), null);
            distances.put(key.getId(), Integer.MAX_VALUE);
        }
        //odleglosc do siebie =  0
        distances.put(initialNodeIndex, 0);

        //reszta inicjalizacji - ustaw poprzednikow wierzcholkow sasiadujacych, odleglosci do nich i dodaj do mozliwych wierzcholkow
        GraphNode initialNode = nodeGraph.get(beginIndex);
        ArrayList<GraphEdge> initialNodeNeighbors = edgeGraph.get(beginIndex).getConnectionEdgeList();
        for (GraphEdge e : initialNodeNeighbors) {
            GraphNode other = e.getOther(initialNode.getId());
            predecessors.put(other.getId(), initialNodeIndex);
            distances.put(other.getId(), e.getWeight());
            availableNodes.add(other);
        }
        //oznacz wierzcholek(beginIndex) jako odwiedzony
        visitedNodes.add(initialNode);

        //gdy sa jeszcze mozliwe wierzcholki, sciagnij ten o najmniejszej wadze i konynuuj dijkstre dla nieodwiedzonych wierzcholkow
        while (availableNodes.size() > 0) {
            GraphNode next = availableNodes.poll();
            int distanceToNext = distances.get(next.getId());
            List<GraphEdge> nextNeighbors = edgeGraph.get(next.getId()).getConnectionEdgeList();
            for (GraphEdge e : nextNeighbors) {
                GraphNode other = e.getSecond();
                if (visitedNodes.contains(other)) {
                    continue;
                }
                //proces relaksacji
                int currentWeight = distances.get(other.getId());
                int newWeight = distanceToNext + e.getWeight();
                if (newWeight < currentWeight) {
                    predecessors.put(other.getId(), next.getId());
                    distances.put(other.getId(), newWeight);
                    availableNodes.remove(other);
                    availableNodes.add(other);
                }
            }
            visitedNodes.add(next);
        }
        StringBuilder s = new StringBuilder();
        //drukuj najkrotsze odleglosci od wierzcholek(beginIndex)
        for (int i = 0; i < nodeGraph.size(); i++) {
            int destination = nodeGraph.get(i).getId();
            LinkedList<GraphNode> path = new LinkedList<>();
            path.add(nodeGraph.get(destination));

            while (destination != initialNodeIndex) {
                GraphNode predecessor = nodeGraph.get((predecessors.get(destination)));
                destination = predecessor.getId();
                path.add(0, predecessor);
            }

            path.forEach(g -> {
                s.append(g.getId());
                s.append("->");
            });
            s.append(" | Odleglosc: ");
            s.append(distances.get(i));
            s.append("\n");
        }

        try (FileWriter fw = new FileWriter("Dijkstra.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] distanceMatrix;

    /**
     * tworzy macierz odleglosci i zapisuje ja do pliku distanceMatrix
     */
    public void createDistanceMatrix() {
        distanceMatrix = new int[nodeGraph.size()][nodeGraph.size()];

        for (int i = 0; i < distanceMatrix.length; i++) {
            dijkstra(i);
            for (int j = 0; j < distanceMatrix[i].length; j++) {
                distanceMatrix[i][j] = distances.get(j);
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < distanceMatrix.length; i++) {
//            System.out.printf("[");
            s.append("[");
            for (int j = 0; j < distanceMatrix[i].length; j++) {
//                System.out.printf("%d, ", distanceMatrix[i][j]);
                s.append(distanceMatrix[i][j]);
                if (j < distanceMatrix[i].length - 1) s.append(", ");
            }
//            System.out.printf("]\n");
            s.append("]\n");
        }

        try (FileWriter fw = new FileWriter("DistanceMatrix.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * znajduje centra odleglosci i je dopisuje do distanceMatrix
     */
    public void findDistanceCentre() {
        //sumuj odleglosci wierszami
        int distanceSum[] = new int[distanceMatrix.length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceSum[i] = distanceMatrix[i][0];
            for (int j = 1; j < distanceMatrix[i].length; j++) {
                distanceSum[i] += distanceMatrix[i][j];
            }
        }

        //znajdz najmniejsza sume - centrum
        int min = 0;
        int minTemp;
        for (int i = 1; i < distanceSum.length; i++) {
            minTemp = (distanceSum[i - 1] < distanceSum[i]) ? i - 1 : i;
            min = (distanceSum[min] < distanceSum[minTemp]) ? min : minTemp;
        }

        //jezeli jest wiele center to dodaj
        ArrayList<Integer> centres = new ArrayList<>(1);
        centres.add(min);
        for (int i = 0; i < distanceSum.length; i++) {
            if (i != min && distanceSum[i] == distanceSum[min]) {
                centres.add(i);
            }
        }
        StringBuilder s = new StringBuilder();
        s.append("\n");

        centres.forEach(centre -> {
            s.append("Centrum grafu: ");
            s.append(centre);
            s.append(" - ");
            s.append(distanceSum[centre]);
        });

        try (FileWriter fw = new FileWriter("DistanceMatrix.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Znajduje centra Min-Max i je dopisuje do distanceMatrix
     */
    public void findMinMaxCentre() {
        //znajdz max w wierszu
        // mapa -> klucz : index wierszu, wartosc: indeks kolumny
        HashMap<Integer, Integer> max = new HashMap<>(distanceMatrix.length);

        //dodaj najwieksze elementy w wierszu
        int maxTemp;
        for (int i = 0; i < distanceMatrix.length; i++) {
            max.put(i, 0);
            for (int j = 1; j < distanceMatrix[i].length; j++) {
                maxTemp = (distanceMatrix[i][j] > distanceMatrix[i][j - 1]) ? j : j - 1;
                max.replace(i, max.get(i), (distanceMatrix[i][max.get(i)] > distanceMatrix[i][maxTemp]) ? max.get(i) : maxTemp);
            }
        }

////        znajdz min wsrod maksimum i sprawdz czy nie ma ich wiecej
        int min = 0;
        int minTemp;
        for (int i = 1; i < max.size(); i++) {
            minTemp = (distanceMatrix[i - 1][max.get(i - 1)] < distanceMatrix[i][max.get(i)]) ? i - 1 : i;
            min = (distanceMatrix[min][max.get(min)] < distanceMatrix[minTemp][max.get(minTemp)]) ? min : minTemp;
        }

        //jezeli jest wiele center minamx to dodaj
        HashMap<Integer, Integer> minMap = new HashMap<>();
        minMap.put(min, max.get(min));
        for (int i = 0; i < max.size(); i++) {
            if (i != min && distanceMatrix[i][max.get(i)] == distanceMatrix[min][max.get(min)]) {
                minMap.put(i, max.get(i));
            }
        }
        StringBuilder s = new StringBuilder();
        s.append("\n");
        //iteruj przez kazde centrum min-max i wypisz
        for (Map.Entry<Integer, Integer> entry : minMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            s.append("Centrum MIN-MAX: ");
            s.append(key);
            s.append(" - ");
            s.append(distanceMatrix[key][value]);
            s.append("\n");
        }

        try (FileWriter fw = new FileWriter("DistanceMatrix.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * implementacja algorytmu prima - znajdowanie najmniejszych drzew rozpinajacych, zapisuje krawedzie do pliku MST
     *
     * @param beginIndex poczatkowy index szukanego MST
     */
    public void prim(int beginIndex) {
        int initialNodeIndex = nodeGraph.get(beginIndex).getId();
        //kolejka priorytetowa krawedzi ktore mozemy wybrac z danego wierzhcolka
        PriorityQueue<GraphEdge> avaliableEdges = new PriorityQueue<>(new Comparator<GraphEdge>() {
            @Override
            public int compare(GraphEdge o1, GraphEdge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        //odwiedzone wiezcholki
        HashSet<GraphNode> visitedNodes = new HashSet<>();

        for (GraphEdge edge : edgeGraph.get(beginIndex).getConnectionEdgeList()) {
            avaliableEdges.add(edge);
        }

        //oznacz wierzcholek(beginIndex) jako odwiedzony
        GraphNode initialNode = nodeGraph.get(beginIndex);
        visitedNodes.add(initialNode);

        LinkedList<GraphEdge> mst = new LinkedList<>();
        int overallWeight = 0;
        //poki sa wolne krawedzie wykonuj prima
        while (!avaliableEdges.isEmpty()) {
            GraphEdge edge = avaliableEdges.poll();
            GraphNode first = edge.getFirst();
            GraphNode second = edge.getSecond();
            if (!visitedNodes.contains(first) || !visitedNodes.contains(second)) {
                GraphNode node = (visitedNodes.contains(second)) ? first : second;
                visitedNodes.add(node);
                overallWeight += edge.getWeight();
                mst.add(edge);
                for (GraphEdge e : edgeGraph.get(node.getId()).getConnectionEdgeList()) {
                    if (visitedNodes.contains(e.getSecond())) {
                        continue;
                    }
                    avaliableEdges.add(e);
                }
            }
        }

        StringBuilder s = new StringBuilder();
        s.append("\nKrawedzie MST\n");
        mst.forEach(name -> {
            s.append(name.toString());
            s.append(" -");
        });
        s.append("\n\nWagi krawędzi MST:\n");
        mst.forEach(name -> {
            s.append(name.getWeight());
            s.append(" -");
        });
        s.append("\nWaga drzewa MST: ");
        s.append(overallWeight);
        s.append("\n");

        try (FileWriter fw = new FileWriter("MST.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

