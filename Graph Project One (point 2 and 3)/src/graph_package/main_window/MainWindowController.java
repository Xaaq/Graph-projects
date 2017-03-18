package graph_package.main_window;

import graph_package.graph_classes.Graph;
import graph_package.graph_classes.GraphCanvas;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Button saveButton;
    public Button generateMatrixButton;
    public TextField probabilityInput;
    public TextField sizeInput;
    public ComboBox comboBox;
    public GraphCanvas canvas;

    private Graph graph = new Graph();

    //funkcja wywolujaca sie gdy cale okno zostalo zaladowane
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
