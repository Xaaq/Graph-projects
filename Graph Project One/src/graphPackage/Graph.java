package graphPackage;

import java.awt.*;
import java.util.ArrayList;

class Graph {

    int[][] getGraphMatrix() {
        return graphMatrix;
    }

    //przechowuje graf w postaci macierzy
    private int[][] graphMatrix = null;

    Graph() {}

    //generuje graf w postaci macierzy z zadanym prawdopodobienstwem wstapienia krawedzi

        void generateProbabilityMatrix(int size, double probability) {
        graphMatrix = new int[size][size];

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = i; j < graphMatrix[i].length; j++) {
                if (i == j) {
                    graphMatrix[i][j] = 0;
                    continue;
                }

                if (Math.random() <= probability)
                    graphMatrix[i][j] = 1;
                else
                    graphMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < i; j++) {
                graphMatrix[i][j] = graphMatrix[j][i];
            }
        }
    }

    //generuje graf w postaci macierzy z podana liczba krawedzi
    void generateNumberMatrix(int size, double numberOfLines) {
        int number = (int) numberOfLines;
        graphMatrix = new int[size][size];

        ArrayList<Point> pointList = new ArrayList<>();
        int lineCount = 0;

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = i + 1; j < graphMatrix[i].length; j++) {
                pointList.add(new Point(i, j));
            }
        }

        while (!pointList.isEmpty() && lineCount < number) {
            int randomIndex = (int) (Math.random() * (pointList.size()));
            int x = (int) pointList.get(randomIndex).getX();
            int y = (int) pointList.get(randomIndex).getY();
            pointList.remove(randomIndex);

            graphMatrix[x][y] = 1;
            lineCount++;
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < i; j++) {
                graphMatrix[i][j] = graphMatrix[j][i];
            }
        }
    }
}
// commit Mateusz testowy
// 2dew