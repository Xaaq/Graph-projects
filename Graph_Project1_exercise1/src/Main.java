import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static ConnectionMatrix cMatrix;


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
        Scanner reader = new Scanner(System.in);
        Scanner reader2 = new Scanner(System.in);

        System.out.println("Podaj liczbę wierzchołków: ");
        int numberOfVertices = reader.nextInt();

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
            for(int y: arrayWithNeighbors)
                System.out.println(y + " ");

            //Wyzerować po każdej pętli
            for(int x=0; x<numberOfVertices; ++x)
                arrayWithNeighbors[x] = 0;
        }

    }

    public static void main(String[] args) {
        createGraph();
    }
}
