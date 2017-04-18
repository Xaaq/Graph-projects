package graph_classes;

/**
 * Created by Mateusz on 18.04.2017.
 */
public class BellmanFord {
    // macierz odległości
    private int distances[];
    // liczba wierzchołków
    private int numberofvertices;
    public static final int MAX_VALUE = 999;

    public BellmanFord(int numberofvertices) {
        this.numberofvertices = numberofvertices;
        distances = new int[numberofvertices ];
    }

    public void BellmanFordEvaluation(int sourceVertex, int adjacencymatrix[][]) {
        // inicjalizujemy na początku macierz odległości wartościami maksymalnymi
        for (int i = 0; i < numberofvertices; ++i) {
            distances[i] = MAX_VALUE;
        }

        distances[sourceVertex] = 0;

        for (int node = 0; node < numberofvertices -1; node++) {
            for (int sourcenode = 0; sourcenode < numberofvertices; sourcenode++) {
                for (int destinationnode = 0; destinationnode < numberofvertices; destinationnode++) {

                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                        if (distances[destinationnode] > distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode])
                            distances[destinationnode] = distances[sourcenode] + adjacencymatrix[sourcenode][destinationnode];
                    }
                }
            }
        }

//        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++) {
//            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++) {
//                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
//                    if (distances[destinationnode] > distances[sourcenode]
//                            + adjacencymatrix[sourcenode][destinationnode])
//                        System.out.println("The Graph contains negative egde cycle");
//                }
//            }
//        }

        for (int vertex = 0; vertex <= numberofvertices; vertex++) {
            System.out.println("distance of source  " + sourceVertex + " to "
                                + vertex + " is " + distances[vertex]);
        }
    }
}
