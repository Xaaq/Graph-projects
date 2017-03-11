package graph_package.graph_classes;

/**
 * Created by Xaaq333 on 2017-03-11.
 */
public class Graph {

    int[][] graphMatrix;

    public Graph() {

    }

    public void checkNumberSeqence(int[] numberSequence) {
        System.out.print(numberSequence[0]);
        numberSequence[0] -= 1;
        System.out.print(numberSequence[0]);
    }
}
