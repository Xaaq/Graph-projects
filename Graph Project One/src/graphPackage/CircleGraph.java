package graphPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pawel on 07.03.17.
 */
@SuppressWarnings("DefaultFileTemplate")
class CircleGraph extends JPanel {
    private static final int SIZE = 256;

    private final int n;
    private int[][] graphMatrix;
    private final int[] tabX;
    private final int[] tabY;

    // konstruktor przyjmujacy macierz wieczholkow
    CircleGraph(Graph graph) {
        this( graph.getGraphMatrix()[0].length);
        this.graphMatrix = graph.getGraphMatrix();
    }
    // przyjmujacy ilosc wierzcholkow
    CircleGraph(int n) {
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n =n;
        tabX = new int[n];
        tabY = new int[n];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        int a = getWidth() / 2;
        int b = getHeight() / 2;
        int m = Math.min(a, b);
        int r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 3;
        super.setBackground(Color.WHITE);
        g2d.setColor(Color.BLUE.darker());
        int num = 1;
        //generacja x,y wraz z zapisaniem do tablicy
        for (int i = 0; i < n; i++) {
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            tabX[i] = x;
            tabY[i] = y;
            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
            g2d.drawString( Integer.toString( num++ ), x + 2* r2, y + 2 * r2);
        }
        //macierz symetryczna wiec liczy górny trojkat
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (graphMatrix[i][j] == 1)
                    g2d.drawLine( tabX[i], tabY[i], tabX[j], tabY[j] );
            }
        }
    }
}

