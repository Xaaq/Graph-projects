package graphPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
