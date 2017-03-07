package graphPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private Graph graph;
	private JTextField probabilityTextField;
	private JTextField graphSizeTextField;

	//odpalenie aplikacji
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//stworzenie okna
	public MainWindow() {
		//tworzy glowne okno
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 617);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//tworzy pudelko w ktorym bedzie mozna wygenerowac macierz
		JPanel matrixPanel = new JPanel();
		matrixPanel.setBounds(1, 1, 552, 448);
		contentPane.add(matrixPanel);

		graph = new Graph(matrixPanel);

		//tworzy paski do scrollowania pudelka w ktorym bedzie wygenerowana macierz grafu
		JScrollPane matrixScrollPane = new JScrollPane(matrixPanel);
		matrixScrollPane.setLocation(0, 0);
		matrixScrollPane.setSize(619, 502);
		contentPane.add(matrixScrollPane);

		//tworzy label do textfielda wielkosci grafu
		JLabel graphSizeLabel = new JLabel("wielkosc grafu");
		graphSizeLabel.setBounds(627, 48, 96, 14);
		contentPane.add(graphSizeLabel);

		//tworzy textfield do wpisywania wielkosci grafu
		graphSizeTextField = new JTextField();
		graphSizeTextField.setBounds(627, 73, 186, 40);
		contentPane.add(graphSizeTextField);
		graphSizeTextField.setColumns(10);

		//tworzy label do textfielda prawdopodobienstwa wystapienia / liczby krawedzi
		JLabel probabilityLabel = new JLabel("prawdopodobienstwo (0 - 1)");
		probabilityLabel.setBounds(640, 136, 173, 14);
		contentPane.add(probabilityLabel);
		
		//tworzy textfield do wpisywania prawdopodobienstwa wystapienia / liczby krawedzi
		probabilityTextField = new JTextField();
		probabilityTextField.setBounds(640, 161, 192, 47);
		contentPane.add(probabilityTextField);
		probabilityTextField.setColumns(10);

		//tworzy przycisk do generowania grafu
		JButton generateGraphButton = new JButton("Wygeneruj graf");
		generateGraphButton.setBackground(Color.WHITE);
		generateGraphButton.setBounds(684, 222, 109, 72);
		contentPane.add(generateGraphButton);

		//przypisuje akcje nacisniecia przycisku
		generateGraphButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int size = Integer.parseInt(graphSizeTextField.getText());
					double probability = Double.parseDouble(probabilityTextField.getText());
//
//					
//					trzeba tu zrobic cos zeby mozna bylo wybrac
//					czy chce sie zeby generowalo graf na podstawie prawdopodobienstwa
//					czy sztwnej liczby wystapien
//					
//					
					graph.generateNumberMatrix(size, probability);
					graph.writeMatrix();
					
					SwingUtilities.updateComponentTreeUI(matrixPanel);
				} catch (NumberFormatException exception) {
					System.out.println(exception.getMessage());
				}

			}
		});
	}
	//komentarz z brancza MOJEGO KURWA
	//brancz z M
}
