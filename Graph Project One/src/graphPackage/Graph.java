package graphPackage;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph {

	//przechowuje graf w postaci macierzy
	private int[][] graphMatrix = null;
	//odnosnik do panelu w ktorym bedzie wypisywac liczbowa reprezentacje grafu
	private JPanel writePanel;

	//konstruktor przyjmujacy jako argument JFrame do ktorego bedzie wpisywana macierzowa reprezentacja grafu
	public Graph(JPanel writePanel) {
		this.writePanel = writePanel;
	}

	//generuje graf w postaci macierzy z zadanym prawdopodobienstwem wstapienia krawedzi
	public void generateProbabilityMatrix(int size, double probability) {
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
	public void generateNumberMatrix(int size, double numberOfLines) {
		int number = (int) numberOfLines;
		graphMatrix = new int[size][size];

		ArrayList<Point> pointList = new ArrayList<Point>();
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
		//dklsjhfsd
	}

	//funkcja robiaca grid w JFramie (podanym w konstruktorze) i wpisujaca do niego macierz
	public void writeMatrix() {
		if (graphMatrix == null)
			return;

		writePanel.removeAll();
		writePanel.setLayout(new GridLayout(graphMatrix.length, graphMatrix[0].length, 10, 10));

		for (int i = 0; i < graphMatrix.length; i++) {
			for (int j = 0; j < graphMatrix[i].length; j++) {
				JLabel label = new JLabel(Integer.toString(graphMatrix[i][j]));
				GridBagConstraints gridInfo = new GridBagConstraints();
				gridInfo.gridx = i;
				gridInfo.gridy = j;
				writePanel.add(label, gridInfo);
			}
		}
	}

}
