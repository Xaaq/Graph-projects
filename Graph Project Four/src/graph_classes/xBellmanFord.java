package graph_classes;

public class xBellmanFord {
    private int distances[];
    public int numberOfVertices;

    public static final int MAX_VALUE = 999;

    public xBellmanFord(int numberofvertices) {
        this.numberOfVertices = numberofvertices;
        distances = new int[numberofvertices + 1];
    }

    public int[] BellmanFordEvaluation(int source, int adjacencymatrix[][]) {

        for (int node = 1; node <= numberOfVertices; node++) {
            distances[node] = MAX_VALUE;
        }

        distances[source] = 0;
        for (int node = 1; node <= numberOfVertices-1; node++) {
            for (int sourcenode = 1; sourcenode <= numberOfVertices; sourcenode++) {
                for (int destinationnode = 1; destinationnode <= numberOfVertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                        if (destinationnode != source && distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode]) {
                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                        }
                    }
                }
            }
        }

        for (int sourcenode = 1; sourcenode <= numberOfVertices; sourcenode++) {
            for (int destinationnode = 1; destinationnode <= numberOfVertices; destinationnode++) {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                    if (distances[destinationnode] > distances[sourcenode]
                            + adjacencymatrix[sourcenode][destinationnode])
                        System.out.println("Graf zawiera cykl o łącznej ujemnej wadze osiągalny ze źródła!");
                }
            }
        }

    //printDistancesFromSource(source);
        return distances;

    }

    private void printDistancesFromSource(int source) {
        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            System.out.println("odległość od: " + source + " do: " + vertex + " wynosi: " + distances[vertex]);
        }
    }

    public int[] getDistances() {
        return distances;
    }
}
