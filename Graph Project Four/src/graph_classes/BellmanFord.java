package graph_classes;

/**
 * Created by Mateusz on 18.04.2017.
 */

public class BellmanFord {
    //macierz odległości
    private int distances[];
    public int numberOfVertices;
    // nasza wartośc nieskończoność
    public static final int infinity = 999;

    public BellmanFord(int numberOfVertices) {
        System.out.println("Number of Vertices: " + numberOfVertices);
        this.numberOfVertices = numberOfVertices;
        distances = new int[numberOfVertices + 1];
    }

    public int[] BellmanFordEvaluation(int source, int adjacencymatrix[][]) {
        // dołożone
        source = source + 1;
        //

        for (int node = 1; node <= numberOfVertices; node++) {
            distances[node] = infinity;
        }

        distances[source] = 0;
        // moze byc bez tego -1?
        // dla -2 dziala(?)
        for (int node = 1; node <= numberOfVertices - 1; node++) {
            for (int sourcenode = 1; sourcenode <= numberOfVertices; sourcenode++) {
                for (int destinationnode = 1; destinationnode <= numberOfVertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != infinity) {
                        // destinationnode różne od source, żeby odległość do samego siebie się nie zmieniała
                        if (destinationnode != source &&
                                distances[destinationnode] > distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode]) {
                            distances[destinationnode] = distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode];
//                            System.out.println(distances[source]);
//                            System.out.println("sourcenode: " + sourcenode + " destnode: " + destinationnode + "distance source: " + distances[source]);
                        }
                    }
                }
            }
        }
        // Wypisujemy odległości
        printDistancesFromSource(source);
        return distances;
    }

    private void printDistancesFromSource(int source) {
        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            System.out.println("odległość od: " + (source-1) + " do: " + (vertex - 1) + " wynosi: " + distances[vertex]);
        }
    }

}