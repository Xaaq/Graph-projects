package main_window;

import graph_classes.Web;
import graph_classes.WebNode;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Canvas canvas;

    private Web web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Wyrysowwuje graf na canvasie.
     *
     * @param web graf do wyrysowania
     */
    public void drawGraph(Web web) {
//        double canvasWidth = canvas.getWidth();
//        double canvasHeight = canvas.getHeight();
//        double graphSize = (canvasWidth < canvasHeight) ? canvasWidth : canvasHeight;
//        int dotSize = 15;
//        ArrayList<WebNode> nodeGraph = web.getNodeGraph();
//        int dotCount = nodeGraph.size();
//        GraphicsContext context = canvas.getGraphicsContext2D();
//
//        context.clearRect(0, 0, canvasWidth, canvasHeight);
//        context.setFill(Color.web("#673ab7"));
//        context.setStroke(Color.web("#673ab7"));
//        context.setLineWidth(3);
//
//        //rysuje kolka
//        for (int i = 0; i < dotCount; i++) {
//            double angle = i * 360 / dotCount * Math.PI / 180;
//            double x = canvasWidth / 2 + Math.sin(angle) * graphSize * 2 / 5 - dotSize / 2;
//            double y = canvasHeight / 2 + Math.cos(angle) * graphSize * 2 / 5 - dotSize / 2;
//            context.fillText(Integer.toString(web.getNodeGraph().get(i).getId()), x + dotSize, y + dotSize);
//            context.fillOval(x, y, dotSize, dotSize);
//        }
//
//        //rysuje linie
//        for (int i = 0; i < dotCount; i++) {
//            for (WebNode node : nodeGraph.get(i).getConnectionList()) {
//                double angle1 = i * 360 / dotCount * Math.PI / 180;
//                double x1 = canvasWidth / 2 + Math.sin(angle1) * graphSize * 2 / 5;
//                double y1 = canvasHeight / 2 + Math.cos(angle1) * graphSize * 2 / 5;
//
//                double angle2 = nodeGraph.indexOf(node) * 360 / dotCount * Math.PI / 180;
//                double x2 = canvasWidth / 2 + Math.sin(angle2) * graphSize * 2 / 5;
//                double y2 = canvasHeight / 2 + Math.cos(angle2) * graphSize * 2 / 5;
//
//                context.strokeLine(x1, y1, x2, y2);
//            }
//        }
    }
}
