package graph_package.graph_classes;

import java.util.*;

/**
 * Created by Xaaq333 on 2017-03-11.
 */
//przechowuje graf w postaci macierzy i funkcje z nim zwiazane
public class Graph {

    //przechowuje graf w formie macierzy
    private int[][] graphMatrix;
    //przechowywuje graf w postaci wezlow
    private ArrayList<GraphNode> nodeGraph;

    //konstruktor
    public Graph() {

    }

    //Zwraca węzeł według id
    public GraphNode getGraphNode(int id) {
        for (GraphNode eachNode : nodeGraph) {
            if (eachNode.getId() == id) {
                return eachNode;
            }
        }
        return null;
    }

    // Zwraca liczbę wierzchołków grafu z postaci macierzowej
    public int getNumberOfVertices() {
        return graphMatrix.length;
    }

    //ustawia macierz tego grafu na podana
    public void setGraphMatrix(int[][] inputMatrix) {
        graphMatrix = inputMatrix.clone();
    }

    //zwraca graf w posatci macierzy
    public int[][] getGraphMatrix() {
        return graphMatrix.clone();
    }

    //ustawia graf w postaci wezlow na podany
    public void setNodeGraph(ArrayList<GraphNode> inputNodeGraph) {
        nodeGraph = inputNodeGraph;
    }

    //zwraca graf w postaci węzłów
    public ArrayList<GraphNode> getNodeGraph() {
        return nodeGraph;
    }

    //zwraca ilosc wezlow w grafie
    public int getNodeGraphLength() {
        return nodeGraph.size();
    }

    //sub-klasa ktora opakowywuje wierzcholek i liczbe polaczonych z nim krawedzi (potrzebna do tworzenia grafu)
    private class Verticle {
        public final int verticleNumber;
        public int connectionCount;

        Verticle(int verticleNumber, int connectionCount) {
            this.verticleNumber = verticleNumber;
            this.connectionCount = connectionCount;
        }
    }

    //sub-klasa ktora sluzy do porownania obiektow klasy Verticle
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

    //robi graf z podanej sekwencji liczb jesli sie da
    public int[][] checkNumberSequence(int[] inputNumberSequence) {
        ArrayList<Verticle> verticleSequece = new ArrayList<>();

        for (int i = 0; i < inputNumberSequence.length; i++) {
            verticleSequece.add(new Verticle(i, inputNumberSequence[i]));
        }

        return checkNumberSequence(verticleSequece);
    }

    //sprawdza czy mozna zrobic graf z podanej sekwencji liczb
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

    //funkcja ktora generuje tablice wezlow na podstawie macierzy
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

    //sprawdza najwieksza skladowa grafu i zwraca tablice wezlow ktore do niej naleza
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

    //Program do sprawdzania (dla małych grafów) czy graf jest hamiltonowski.

//    public boolean isHamiltonianGraph(){
//        //Wystarczy sprawdzić od wierzchołka 0. Jak jest hamiltonowski to nie ważne od którego wierzchołka zaczniemy
//        DFSPaths dfs = new DFSPaths(this, 0);
//
//        return dfs.isHamiltonianGraph();
//    }

    public boolean isHamiltonianGraph(){
        DFS dfs = new DFS();
        return dfs.isHamiltonianCycle(this.getGraphMatrix());
    }

    //randomizuje graf
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

    public String getIsEulerLabelTrue() {
        return isEulerLabelTrue;
    }

    public String getEulerianCycle() {
        return eulerianCycle;
    }

    public void setIsEulerLabelTrue(String isEulerLabelTrue) {
        this.isEulerLabelTrue = isEulerLabelTrue;
    }

    private String isEulerLabelTrue;
    private String eulerianCycle;

    //funkcja ktora tworzy graf z cyklem eulera, podmienia starą liste sąsiadow z nową zawierającą ten cykl i znajduje go.
    public void createAndFindEulerGraph(int beginEulerNode) {
        isEulerLabelTrue = "";
        eulerianCycle = "";
        ArrayList<GraphNode> nodes = checkBiggestConsistentComponent();
        int count = 0;

        while (count < 100 && (nodes.size()) != nodeGraph.size()) {
            randomizeGraph();
            nodes = checkBiggestConsistentComponent();
            count++;
        }
        if (nodes.size() != nodeGraph.size()) {
            isEulerLabelTrue = "Nie można utworzyć grafu z cyklem Eulera, graf nie jest spójny.";
        } else {
//            System.out.println("Graf spójny");
            Random rand = new Random();
            int randNodeIndex;
            //szuka wierzcholkow nie parzystych
            for (int i = 0; i < nodes.size() - 1; i++) {
                // jezeli znajdzie jeden to szuka kolejnego nieparzystego
                if (nodes.get(i).getConnectionList().size() % 2 != 0) {
                    for (int j = i + 1; j < nodes.size(); j++) {
                        if (nodes.get(j).getConnectionList().size() % 2 != 0) {
                            //jezeli nie sa polaczone ta sama krawedzia
                            if (!(nodes.get(i).getConnectionList().contains(nodes.get(j)) && nodes.get(j).getConnectionList().contains(nodes.get(i)))) {
                                // polacz wiezcholki nie parzyste nie bedace sasiadami
                                nodes.get(i).addConnection(nodes.get(j));
                                nodes.get(j).addConnection(nodes.get(i));
                            }
                        }
                    }
                }
            }
            //wszystkie nie parzyste wierzcholki nie sasiadujace polaczone
            //szukaj nieparzystych i sasiadujacych wierzcholkow
            for (int i = 0; i < nodes.size() - 1; i++) {
                //jezeli znajdziesz jeden to szukaj kolejnego nieparzystego
                if (nodes.get(i).getConnectionList().size() % 2 != 0) {
                    for (int j = i + 1; j < nodes.size(); j++) {
                        if (nodes.get(i).getConnectionList().size() % 2 != 0) {
                            //jezeli sa polaczone ta sama krawedzia
                            if (nodes.get(i).getConnectionList().contains(nodes.get(j)) && nodes.get(j).getConnectionList().contains(nodes.get(i))) {
                                int icount = 0;
                                int jcount = 100;
                                randNodeIndex = rand.nextInt(nodes.size());
                                while (icount < 100 && (nodes.get(randNodeIndex).getConnectionList().size() % 2 != 0 || nodes.get(i).getConnectionList().contains(nodes.get(randNodeIndex))) || nodes.get(j).getConnectionList().contains(nodes.get(randNodeIndex))) {
                                    randNodeIndex = rand.nextInt(nodes.size());
                                    icount++;
                                }
                                if (icount == 100) {
                                    jcount = 0;
                                }
                                while (jcount < 100 && (nodes.get(randNodeIndex).getConnectionList().size() % 2 != 0 || nodes.get(j).getConnectionList().contains(nodes.get(randNodeIndex)) || nodes.get(i).getConnectionList().contains(nodes.get(randNodeIndex)))) {
                                    randNodeIndex = rand.nextInt(nodes.size());
                                    jcount++;
                                }
                                if (icount < 100) {
                                    nodes.get(i).addConnection(nodes.get(randNodeIndex));
                                    nodes.get(randNodeIndex).addConnection(nodes.get(i));
                                    //polacz drugi stary z nowym
                                    nodes.get(j).addConnection(nodes.get(randNodeIndex));
                                    nodes.get(randNodeIndex).addConnection(nodes.get(j));
                                } else if (jcount < 100) {
                                    nodes.get(j).addConnection(nodes.get(randNodeIndex));
                                    nodes.get(randNodeIndex).addConnection(nodes.get(j));
                                    //polacz drugi stary z nowym
                                    nodes.get(i).addConnection(nodes.get(randNodeIndex));
                                    nodes.get(randNodeIndex).addConnection(nodes.get(i));
                                }
                            }
                        }
                    }
                }
            }
            if (nodes.get(nodes.size() - 1).getConnectionList().size() % 2 != 0) {
                isEulerLabelTrue = "Nie można utworzyć grafu z cyklem Eulera, zostały 2 wierzchołki nieparzyste połączone tą samą krawędzią.";
            } else {
                isEulerLabelTrue = "Utworzono graf z cyklem Eulera";
                Collections.sort(nodes, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
                nodeGraph.clear();
                nodeGraph.addAll(nodes);
//            // losuj cykl eulera dla kazdego wierzcholka
//            for (GraphNode n : nodeGraph) {
//                EulerianCycle(nodeGraph,n.getId());
//            }
//                eulerianCycle += "\n";

//                //losuj cykl eulera dla losowego wierzcholka
                EulerianCycle(nodeGraph, beginEulerNode);
            }
        }
    }

    // klasa krawedz skladajaca sie z wierzcholkow i flagi isUsed mowiaca czy krawedz juz byla uzyta
    private static class Edge {
        private GraphNode v;
        private GraphNode w;
        private boolean isUsed;

        public void setUsed(boolean used) {
            isUsed = used;
        }

        public GraphNode getV() {

            return v;
        }

        public GraphNode getW() {
            return w;
        }

        public boolean isUsed() {
            return isUsed;
        }

        public Edge(GraphNode v, GraphNode w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        // zwraca drugi wierzcholek
        public int other(int node) {
            if (node == v.getId())
                return w.getId();
            else
                return v.getId();
        }
    }

    //tworzy głeboką kopie arraylist sasiednich wierzcholkow
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

    //konwertuje pary wierzcholkow z listy siasiedztwa na krawedzie
    public ArrayList<Edge> convertNodesToEdges(ArrayList<GraphNode> nodesCopy) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < nodesCopy.size(); i++) {
            for (int j = 0; j < nodesCopy.get(i).getConnectionList().size(); j++) {
                //dodaj krawedz i, j
                edges.add(new Edge(nodesCopy.get(i), nodesCopy.get(i).getConnectionList().get(j)));
            }
        }
        nodesCopy.clear();
        return edges;
    }

    public void EulerianCycle(ArrayList<GraphNode> nodes, int startVertice) {
        //zrob gleboka kopie wierzcholkow
        ArrayList<GraphNode> nodesCopy = deepNodeArrayListCopy(nodes);
//        stworz tablice krawedzi
        ArrayList<Edge> edges = new ArrayList<>();
        edges = convertNodesToEdges(nodesCopy);
//        stworz liste sasiadujacych krawedzi
        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[nodes.size()];
        for (int v = 0; v < nodes.size(); v++)
            adj[v] = new LinkedList<>();
//        dodaj sasiadujace krawedzie dla kazdego wierzcholka
        for (int v = 0; v < nodes.size(); v++) {
            for (Edge e : edges) {
                if (nodes.get(v).getId() == e.getV().getId()) {
                    adj[v].add(e);
                }
            }
        }

        //dodaje stos wierzholkow - punkty z ktorych mozna isc
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(startVertice);

//        //stos cyklu
        Stack<Integer> cycle = new Stack<Integer>();
        //algorytm hierholzera - DFS
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].poll();
                if (edge.isUsed()) continue;
                for (Edge e : edges) {
                    if ((edge.getV() == e.getV() && edge.getW() == e.getW()) || (edge.getV() == e.getW() && edge.getW() == e.getV())) {
                        e.setUsed(true);
                    }
                }
                stack.push(v);
                v = edge.other(v);
            }
            // wrzuc wierzcholek na stos cyklu
            cycle.push(v);
        }
        //drukuj cykl

        while (!cycle.isEmpty()) {
            eulerianCycle += Integer.toString(cycle.pop());
            eulerianCycle += "->";
        }
        eulerianCycle = eulerianCycle.substring(0,eulerianCycle.length()-2);
    }

    // Generowanie losowych grafów k-reguralnych

    public void kReguralGraphs(int numberOfVertices, int k) {
        if (numberOfVertices * k % 2 != 0) {
            System.out.println("Błąd! Liczba wierzchołków * k musi być liczbą parzysta!");
            return;
        }
        // nasza sekwencja
        int[] numberSequence = new int[numberOfVertices];
        for (int element = 0; element < numberSequence.length; ++element) {
            numberSequence[element] = k;
        }
        // z tej sekwencji tworzymy macierz
        int[][] tempMatrix = checkNumberSequence(numberSequence);
        if (tempMatrix == null) {
            System.out.println("Błąd! Z podanej sekwencji nie możemy stworzyć grafu k-reguralnego!");
            return;
        }
        setGraphMatrix(tempMatrix);
        ArrayList<GraphNode> tempGraph = generateNodeArray();
        setNodeGraph(tempGraph);
        //randomizujemy
        randomizeGraph();
    }


}
