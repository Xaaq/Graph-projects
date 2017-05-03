package graph_package.graph_classes;

/**
 * Created by Mateusz on 03.05.2017.
 */

import java.util.Arrays;


public class DFS {
    private int numbersOfV, pathCount;
    private int[] path;
    private int[][] graphMatrix;


    public boolean isHamiltonianCycle(int[][] g) {
        numbersOfV = g.length;
        path = new int[numbersOfV];

        Arrays.fill(path, -1);
        graphMatrix = g;
        try {
            path[0] = 0;
            pathCount = 1;
            dfs(0);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * funkcja rekurencyjna do znajdowania scieżki
     **/
    public void dfs(int vertex) throws Exception {

        if (graphMatrix[vertex][0] == 1 && pathCount == numbersOfV)
            throw new Exception("Znaleziono rozwiązanie");
        // wszystkie wierzchołki wybrane ale ostatni nie połączony z 0
        if (pathCount ==numbersOfV )
            return;

        for (int i = 0; i < numbersOfV; i++) {
            // jeżeli połączone
            if (graphMatrix[vertex][i] == 1) {
                // dodaj do ścieżki
                path[pathCount++] = i;
                // usuń połączenie
                graphMatrix[vertex][i] = 0;
                graphMatrix[i][vertex] = 0;

                // jeżeli wierzchołek nie wybrany - rozwiąż rekurencyjnie
                if (!isPresent(i))
                    dfs(i);


                graphMatrix[vertex][i] = 1;
                graphMatrix[i][vertex] = 1;
                // usuń ścieżkę
                path[--pathCount] = -1;
            }
        }
    }

    /**
     * Funkcja która sprawdza czy ścieżka jest wybrana
     **/
    public boolean isPresent(int v) {
        for (int i = 0; i < pathCount - 1; i++)
            if (path[i] == v)
                return true;
        return false;
    }

}