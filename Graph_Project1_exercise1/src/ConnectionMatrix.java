import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 11.03.2017.
 */
public class ConnectionMatrix {
    // liczba krawędzi
    private int numberOfEdges;
    // liczba wierzchołków
    private int numberOfVertices;
    //przechowuje graf w postaci macierzy
    public int[][] graphMatrix = null;


    public ConnectionMatrix(int size) {
        graphMatrix = new int[size][size];
    }

    // z listy sąsiadów na macierz połączeń
    public ConnectionMatrix(ListOfNeighbors list, int nrOfVertices){
        numberOfVertices = nrOfVertices;
        graphMatrix = new int[numberOfVertices][numberOfVertices];
        List<List<Integer>> neighborsList = list.getNeighborsList();

        for(int i = 0; i< numberOfVertices; ++i ){
            for(int j=0; j< numberOfVertices; ++j){
                int neighbor = neighborsList.get(i).get(j);
                if(neighbor > -1){
                    graphMatrix[i][neighbor] = 1;
                }
            }
        }
    }
    // z macierzy incydencji na macierz połączeń
    public ConnectionMatrix(IncidenceMatrix iMatrix,int nrOfVertices, int nrOfEdges) {
        numberOfVertices = nrOfVertices;
        numberOfEdges = nrOfEdges;
        graphMatrix = new int[numberOfVertices][numberOfVertices];
        int[][] edgesTab = iMatrix.getEdges();

        int firstElementInEdge = -1;
        int secondElementInEdge;

        for (int i = 0; i < numberOfEdges; ++i) {
            for (int j = 0; j < numberOfVertices; ++j) {
                if (edgesTab[i][j] == 1) {
                    if (firstElementInEdge > -1) {
                        secondElementInEdge = j;
                        graphMatrix[firstElementInEdge][secondElementInEdge] = 1;
                        graphMatrix[secondElementInEdge][firstElementInEdge] = 1;
                    }
                    else
                        firstElementInEdge = j;
                }
            }
            firstElementInEdge = -1;
        }
    }

    public void setGraphMatrix(int wsp1, int wsp2, int neighbor){
        graphMatrix[wsp1][wsp2] = neighbor;
    }
    public void printMatrix(){
        System.out.println("Printing Connections Matrix");
        for(int i=0;i<graphMatrix.length;++i){
            System.out.print("|");
            for(int j=0;j<graphMatrix.length;++j){
                System.out.print(" ");
                System.out.print(graphMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("");
    }
    public int getNumberOfEdges(){
        int sum = 0;
        for(int i=0;i< graphMatrix.length; ++i)
            for (int j=0;j< graphMatrix.length; ++j)
                sum += graphMatrix[i][j];
        return sum/2;
    }

}
