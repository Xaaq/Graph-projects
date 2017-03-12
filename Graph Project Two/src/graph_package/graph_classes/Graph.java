package graph_package.graph_classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Xaaq333 on 2017-03-11.
 */
//przechowuje graf w postaci macierzy i funkcje z nim zwiazane
public class Graph {

    private int[][] graphMatrix;

    public Graph() {

    }

    //sprawdza czy mozna zrobic graf z podanej sekwencji liczb (przyjmuje tablice intow)
    public boolean checkNumberSeqence(int[] inputNumberSequence) {
        ArrayList<Integer> numberSequece = new ArrayList<>();

        for (int inputNumber : inputNumberSequence) {
            numberSequece.add(inputNumber);
        }

        return checkNumberSeqence(numberSequece);
    }

    //sprawdza czy mozna zrobic graf z podanej sekwencji liczb (przyjmuje arraylist intow)
    public boolean checkNumberSeqence(ArrayList<Integer> inputNumberSequence) {
        //TODO: zrobic zeby funkcja robila od razu macierz grafowa
        if (inputNumberSequence.isEmpty())
            return true;

        ArrayList<Integer> numberSequence = (ArrayList<Integer>) inputNumberSequence.clone();
        Collections.sort(numberSequence);
        int lastNumber = numberSequence.get(numberSequence.size() - 1);
        numberSequence.remove(numberSequence.size() - 1);

        for (int i = numberSequence.size() - 1; i >= 0; i--) {
            if (numberSequence.get(i) <= 0)
                numberSequence.remove(i);
        }

        if (lastNumber > numberSequence.size())
            return false;

        for (int i = 1; i <= lastNumber; i++) {
            int actualNumber = numberSequence.get(numberSequence.size() - i) - 1;
            numberSequence.set(numberSequence.size() - i, actualNumber);
        }

        for (int i = numberSequence.size() - 1; i >= 0; i--) {
            if (numberSequence.get(i) <= 0)
                numberSequence.remove(i);
        }

        return checkNumberSeqence(numberSequence);
    }
}
