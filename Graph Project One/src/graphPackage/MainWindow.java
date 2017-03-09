package graphPackage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Created by pawel on 07.03.17.
 */
@SuppressWarnings("DefaultFileTemplate")
class MainWindow {
    private static JFrame frame;
    private JComboBox comboBox2;
    private JButton zapiszDoPlikuButton;
    private JButton generujButton;
    private CircleGraph circleGraph1;
    private JPanel mainPanel;
    private JPanel subPan1;
    private JPanel subPan2;
    private JPanel subPan21;
    private JTextField wielkoscTextField;
    private JTextField prawdopodobienstwoTextField;
    private final Graph graph = new Graph();

    private MainWindow() {
        generujButton.addActionListener( actionEvent -> {
            try {
                //graf
                int size = Integer.parseInt(wielkoscTextField.getText());
                double probability = Double.parseDouble(prawdopodobienstwoTextField.getText());
                String choose = String.valueOf(comboBox2.getSelectedItem());
                //generacja
                if (choose.equals( "Ilosc krawedzi" )) {
                    graph.generateNumberMatrix( size, probability );
                } else {
                    graph.generateProbabilityMatrix( size, probability );
                }
                subPan1.remove(circleGraph1);
                circleGraph1 = new CircleGraph(graph);
                subPan1.add(circleGraph1);

                SwingUtilities.updateComponentTreeUI(mainPanel);
                frame.pack();
            } catch (NumberFormatException exception) {
                System.out.println(exception.getMessage());
            }
        } );
        zapiszDoPlikuButton.addActionListener( actionEvent -> {
//                pola tekstowe -- bedzie do zapisywania pliku
            StringBuilder s = new StringBuilder();
            for (int[] x : graph.getGraphMatrix()) {
                s.append( "|" );
                for (int y : x) {
                    s.append( " " );
                    s.append( y );
                    s.append( " " );
                }
                s.append( "|\n" );
            }
            try(  PrintWriter out = new PrintWriter( "graf.txt" )  ){
                out.println(s.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } );
    }

    public static void main(String[] args) {
        frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        circleGraph1 = new CircleGraph(0); // potrzebny do wygenerowania programu - graf o 0 wierzcholkach
    }
}
