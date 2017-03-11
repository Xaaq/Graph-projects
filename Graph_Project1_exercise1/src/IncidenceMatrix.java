/**
 * Created by Mateusz on 11.03.2017.
 */
public class IncidenceMatrix {
    // Wierzchołki
    private int[] vertices;
    // Krawędzie
    private int[] edges;

    public IncidenceMatrix(ConnectionMatrix cMatrix, int size, int numberOfEdges) {
        vertices = new int[size];
        int counter = 1;
        //Wierzchołki od 1 do size
        for (int i = 0; i < size; ++i) {
            vertices[i] = counter++;
        }
        System.out.println(numberOfEdges);


    }
}