package graph_classes;

/**
 * Created by Xaaq333 on 2017-03-12.
 */

/**
 * klasa majace metody do wykorzystania w innych miejscach
 */
public class HelperClass {

    /**
     * dodaje dwie macierze kwadratowe (jesli sa roznych rozmiarow wyrownuje je do lewego gornego rogu i dodaje)
     */
    public static int[][] addSquareMatrixes(final int[][] inputFirstMatrix, final int[][] inputSecondMatrix) {
        int[][] firstMatrix = inputFirstMatrix.clone();
        int[][] secondMatrix = inputSecondMatrix.clone();
        int size = (firstMatrix.length > secondMatrix.length) ? firstMatrix.length : secondMatrix.length;
        int[][] outputMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                outputMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                outputMatrix[i][j] += firstMatrix[i][j];
            }
        }

        for (int i = 0; i < secondMatrix.length; i++) {
            for (int j = 0; j < secondMatrix.length; j++) {
                outputMatrix[i][j] += secondMatrix[i][j];
            }
        }

        return outputMatrix;
    }
}