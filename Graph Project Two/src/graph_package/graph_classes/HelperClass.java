package graph_package.graph_classes;

/**
 * Created by Xaaq333 on 2017-03-12.
 */
//klasa majace metody do wykorzystania w innych miejscach
public class HelperClass {

    //dodaje dwie macierze (jesli sa roznych rozmiarow wyrownuje je do lewego gornego rogu i dodaje)
    public static int[][] addMatrixes(final int[][] inputFirstMatrix, final int[][] inputSecondMatrix) {
        int[][] firstMatrix = inputFirstMatrix.clone();
        int[][] secondMatrix = inputSecondMatrix.clone();
        int width = (firstMatrix.length > secondMatrix.length) ? firstMatrix.length : secondMatrix.length;
        int height = (firstMatrix[0].length > secondMatrix[0].length) ? firstMatrix[0].length : secondMatrix[0].length;
        int[][] outputMatrix = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                outputMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                outputMatrix[i][j] += firstMatrix[i][j];
            }
        }

        for (int i = 0; i < secondMatrix.length; i++) {
            for (int j = 0; j < secondMatrix[0].length; j++) {
                outputMatrix[i][j] += secondMatrix[i][j];
            }
        }

        return outputMatrix;
    }
}
