package graphPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pawel on 07.03.17.
 */
public class CircleGraph extends JPanel {
    private static final int SIZE = 256;
    private int n;
    private int[][] graphMatrix;

    // konstruktor przyjmujacy macierz wieczholkow
    public CircleGraph(int[][] graphMatrix) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.graphMatrix = graphMatrix;
        this.n = graphMatrix[0].length;
    }
    // przyjmujacy ilosc wierzcholkow
    public CircleGraph(int n) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n =n;
    }

//rysowanie wierzcholkow na okregu - dorobic rysowanie krawedzi po przyjeciu tablicy
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
        g2d.setColor(Color.BLUE);
        for (int i = 0; i < n; i++) {
            double t = 2 * Math.PI * i / n;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
        }
    }

}
