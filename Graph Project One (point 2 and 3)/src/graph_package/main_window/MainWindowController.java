package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphCanvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainWindowController {

    public Button saveButton;
    public Button generateMatrixButton;
    public TextField probabilityInput;
    public TextField sizeInput;
    public ComboBox comboBox;
    public GraphCanvas canvas;

    private Graph graph = new Graph();

    //fucnkaj wywolujaca sie gdy cale okno zostalo zaladowane
    public void initialize() {
    }

    //obsluguje przycisniecie przycisku generowania grafu
    public void generateMatrixButtonClick() {
        try {
            //graf
            int size = Integer.parseInt(sizeInput.getText());
            double probability = Double.parseDouble(probabilityInput.getText());
            int choose = comboBox.getSelectionModel().getSelectedIndex();

            //generacja
            if (choose == 0)
                graph.generateNumberMatrix(size, probability);
            else if (choose == 1)
                graph.generateProbabilityMatrix(size, probability);

            canvas.drawGraph(graph);
           /* subPan1.remove(circleGraph1);
            circleGraph1 = new CircleGraph(graph);
            subPan1.add(circleGraph1);

            SwingUtilities.updateComponentTreeUI(mainPanel);
            frame.pack();*/
        } catch (NumberFormatException exception) {
        }
    }

    //obsluguje przycisniecie przycisku zapisywania grafu
    public void saveButtonClick() {
        //pola tekstowe -- bedzie do zapisywania pliku
        StringBuilder s = new StringBuilder();

        for (int[] x : graph.getGraphMatrix()) {
            s.append("|");

            for (int y : x) {
                s.append(" ");
                s.append(y);
                s.append(" ");
            }

            s.append("|\n");
        }

        try (PrintWriter out = new PrintWriter("graf.txt")) {
            out.println(s.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
