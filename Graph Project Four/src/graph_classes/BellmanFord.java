package graph_classes;

/**
 * Created by Mateusz on 18.04.2017.
 */

public class BellmanFord {
    //macierz odległości
    private int distances[];
    private int numberOfVertices;
    // nasza wartośc nieskończoność
    public static final int infinity = 999;

    public BellmanFord(int numberOfVertices) {
        System.out.println("Number of Vertices: " + numberOfVertices);
        this.numberOfVertices = numberOfVertices;
        distances = new int[numberOfVertices + 1];
    }

    public void BellmanFordEvaluation(int source, int adjacencymatrix[][]) {
        for (int node = 1; node <= numberOfVertices; node++) {
            distances[node] = infinity;
        }

        distances[source] = 0;
                                // moze byc bez tego -1?
        for (int node = 1; node <= numberOfVertices - 1; node++) {
            for (int sourcenode = 1; sourcenode <= numberOfVertices; sourcenode++) {
                for (int destinationnode = 1; destinationnode <= numberOfVertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != infinity) {
                        if (distances[destinationnode] > distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode]) {
                            distances[destinationnode] = distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode];
                            distances[source] =0;
                            System.out.println(distances[source]);
                            System.out.println("sourcenode: " + sourcenode + " destnode: "+destinationnode+ "distance source: " + distances[source]);
                        }
                    }
                }
            }
        }


        // Wypisujemy odległości
        printDistancesFromSource(source);
    }

    private void printDistancesFromSource(int source) {
        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            System.out.println("odległość od: " + source + " do: " + vertex + " wynosi: " + distances[vertex]);
        }
    }

}