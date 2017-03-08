package graphPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by pawel on 07.03.17.
 */
public class MainWin {
    private JTabbedPane tabbedPane1;
    private JTextArea wpiszReprezentacjeGrafuTextArea;
    private JComboBox comboBox2;
    private JButton zapiszDoPlikuButton;
    private JButton generujButton;
    private CircleGraph circleGraph1;
    private JPanel mainPanel;
    private JPanel subPan1;
    private JPanel subPan2;
    private JPanel subPan21;
    private JPanel subPan22;
    private JTextArea wpiszReprezentacjeGrafuTextArea2;
    private JTextArea wpiszReprezentacjeGrafuTextArea1;
    private JTextField wielkoscTextField;
    private JTextField prawdopodobienstwoTextField;
    private JButton wczytajZPlikuButton;
    private Graph graph = new Graph();

    public MainWin() {
        generujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    int size = Integer.parseInt(wielkoscTextField.getText());
                    double probability = Double.parseDouble(prawdopodobienstwoTextField.getText());
//
//
//					trzeba tu zrobic cos zeby mozna bylo wybrac
//					czy chce sie zeby generowalo graf na podstawie prawdopodobienstwa
//					czy sztwnej liczby wystapien
//
//
//
                    graph.generateNumberMatrix(size, probability);
                    subPan1.remove(circleGraph1);
                    circleGraph1 = new CircleGraph(graph);
                    subPan1.add(circleGraph1);
                    SwingUtilities.updateComponentTreeUI(mainPanel);
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWin");
        frame.setContentPane(new MainWin().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        circleGraph1 = new CircleGraph(0); // napisany na chwile, potrzebny do wygenerowania
    }
}
