/**
 * Created by Mateusz on 11.03.2017.
 */
public class IncidenceMatrix {
    // Liczba wierzchołków
    private int numberOfVertices;
    //Liczba krawędzi
    private int numberOfEdges;
    // Krawędzie
    private int[][] edges;

    public IncidenceMatrix(ConnectionMatrix cMatrix, int nrOfVertices, int nrOfEdges) {
        numberOfVertices = nrOfVertices;
        numberOfEdges = nrOfEdges;
        edges = new int[numberOfEdges][numberOfVertices];

        int counter = 0;
        for(int i=0; i<numberOfEdges; ++i)
            for(int j=0; j<numberOfVertices; ++j){
            // j>i bo bierzemy elementy nad przekątną(macierz jest symetryczna)
                if(cMatrix.graphMatrix[i][j] == 1 && j>i){
                    edges[counter][i] = 1;
                    edges[counter][j] = 1;
                    ++counter;
                }
            }
    }
    public void printIncidenceMatrix(){
        System.out.println("Printing Incidence Matrix");
        System.out.print("  | ");
        for(int i=0; i<numberOfVertices; ++i){
            System.out.print((i+1) + "  ");
        }
        System.out.println("| <-- Vertices");
        for(int i=0;i<numberOfEdges;++i){
            System.out.print("E"+(i+1)+"|");
            for(int j=0;j<numberOfVertices;++j){
                System.out.print(" ");
                System.out.print(edges[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");
    }

}