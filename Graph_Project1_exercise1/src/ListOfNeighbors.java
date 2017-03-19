import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 11.03.2017.
 */
public class ListOfNeighbors {
    // Liczba wierzchołków
    private int numberOfVertices;
    // Lista sąsiadów
    private List<List<Integer>> neighborsList;

    // z macierzy połączeń na listę sąsiadów
    public ListOfNeighbors(ConnectionMatrix cMatrix,int nrOfVertices) {
        numberOfVertices = nrOfVertices;
        neighborsList = new ArrayList<List<Integer>>();

        for (int i = 0; i < numberOfVertices; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < numberOfVertices; j++) {
                if(cMatrix.graphMatrix[i][j] == 1)
                    row.add(j);
                else
                    row.add(-1);
            }
            neighborsList.add(row);
        }
    }
    public List<List<Integer>> getNeighborsList(){
        return neighborsList;
    }

    public void printListOfNeighbors(){
        System.out.println("Printing List Of Neighbors");
        for(int i = 0; i< numberOfVertices; ++i ){
            System.out.print((i+1)+": ");
            for(int j=0; j< numberOfVertices; ++j){
                int neighbor = neighborsList.get(i).get(j);
                if(neighbor > -1){
                    // +1 bo indeksujemy w tablicy od 0, a wyświetlamy od 1
                    System.out.print(neighbor + 1);
                    System.out.print("-->");
                }
            if(j + 1 == numberOfVertices)
                System.out.print("NULL");
            }
            System.out.print("\n");
    }
        System.out.println("");
    }
}

