/**
 * Created by Mateusz on 11.03.2017.
 */
public class ConnectionMatrix {
    // liczba krawędzi
    private int numberOfEdges;
    //przechowuje graf w postaci macierzy
    public int[][] graphMatrix = null;


    public ConnectionMatrix(int size) {
        graphMatrix = new int[size][size];
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
        for(int i=0;i<graphMatrix.length; ++i)
            for (int j=0;j<graphMatrix.length; ++j)
                sum += graphMatrix[i][j];
        return sum/2;
    }

}
