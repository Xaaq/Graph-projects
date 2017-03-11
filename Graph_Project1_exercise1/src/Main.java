import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static ConnectionMatrix cMatrix;
    private static int nrOfVertices;

    private static void message(){
        System.out.println("Uzupełnij sąsiadów: wpisując 1, jeżeli dane wierzchołki sąsiadują ze sobą, oraz 0 jeżeli nie.");
        System.out.println("Zaczynamy od wierzchołka nr. 1. Każde cyfry oddziel spacją.");
        System.out.println("-1 kończy wpisywanie w danym wierzchołku.");
    }
    //Tworzymy macierz sąsiedztwa według rozmiaru podanego przez użytkownika i uzupełnia ją
    private static void createGraph(){
        StringTokenizer strToken;
        String input;
        int[] arrayWithNeighbors;
        // 2 Scannery - jak pracowałem na jednym wyskakiwał błąd(?)
        Scanner reader = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);

        System.out.println("Podaj liczbę wierzchołków: ");
        int numberOfVertices = reader.nextInt();
        nrOfVertices = numberOfVertices;
        cMatrix = new ConnectionMatrix(numberOfVertices);
        arrayWithNeighbors = new int[numberOfVertices];
        message();

        for(int i=0; i<numberOfVertices; ++i){
            System.out.println("Podaj sąsiadów wierzchołka " + (i+1) + ": ");
            input = reader2.nextLine();
            strToken = new StringTokenizer(input);

            for(int j =0; j<numberOfVertices; ++j){
                int toArray = Integer.parseInt((String)strToken.nextElement());

                if(toArray == -1)
                    break;
                arrayWithNeighbors[j] = toArray;
            }
            //Zapisujemy do naszej macierzy
            for(int wsp2: arrayWithNeighbors){
                System.out.println(wsp2-1);
                // wsp2 - 1, bo indeksujemy od 0 w tablicy. Warunek if, żeby nie wpisać -1 do macierzy
                if(wsp2 -1 >= 0)
                    cMatrix.setGraphMatrix(i,wsp2-1,1);
                //na przekątnych 0, bo nie można być sąsiadem samego siebie
                if(wsp2 - 1 == i)
                    cMatrix.setGraphMatrix(i,wsp2-1,0);
            }

            //Zerujemy pomocniczą tablicę po każdej pętli
            for(int x=0; x<numberOfVertices; ++x)
                arrayWithNeighbors[x] = 0;
        }

    }

    public static void main(String[] args) {
        createGraph();
        cMatrix.printMatrix();
        int nrOfEdges = cMatrix.getNumberOfEdges();
        IncidenceMatrix incMatrix = new IncidenceMatrix(cMatrix, nrOfVertices, nrOfEdges);
        incMatrix.printIncidenceMatrix();

    }
}
