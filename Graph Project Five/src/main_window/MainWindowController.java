package main_window;

import graph_classes.Web;
import graph_classes.WebEdge;
import graph_classes.WebNode;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Web web = new Web(3);
        drawGraph(web);
    }

    /**
     * Wyrysowywuje graf na canvasie.
     *
     * @param web graf do wyrysowania
     */
    private void drawGraph(Web web) {
        int dotSize = 15;
        double canvasWidth = canvas.getWidth() - dotSize;
        double canvasHeight = canvas.getHeight() - dotSize;
        ArrayList<ArrayList<WebNode>> webLayerList = web.getLayerList();
        int layerCount = webLayerList.size();
        GraphicsContext context = canvas.getGraphicsContext2D();
        int biggestLayerSize = 0;

        Font font = new Font(18);
        context.setFont(font);
        context.clearRect(0, 0, canvasWidth, canvasHeight);
        context.setLineWidth(3);

        for (ArrayList<WebNode> nodeList : webLayerList) {
            if (nodeList.size() > biggestLayerSize)
                biggestLayerSize = nodeList.size();
        }

        for (int i = 0; i < webLayerList.size(); i++)
            for (int j = 0; j < webLayerList.get(i).size(); j++) {
                //rysuje kółka
                context.setFill(Color.web("#673ab7"));
                context.setStroke(Color.web("#673ab7"));

                double x = i * canvasWidth / (layerCount - 1);
                double y = j * canvasHeight / (biggestLayerSize - 1);
                context.fillOval(x, y, dotSize, dotSize);

                //wypisuje id węzła
                context.setFill(Color.web("#000000"));
                context.setStroke(Color.web("#000000"));

                context.fillText(String.valueOf(webLayerList.get(i).get(j).getId()), x+dotSize, y + dotSize);

                //oblica i rysuje linie
                ArrayList<WebEdge> edgeList = webLayerList.get(i).get(j).getOutputConnectionList();
                double x1 = x + dotSize / 2;
                double y1 = y + dotSize / 2;

                for (WebEdge singleEdge : edgeList) {
                    WebNode nodeToConnectTo = singleEdge.getOutputNode();
                    int numberOfLayerOfNodeToConnectTo = web.getLayerOfNode(nodeToConnectTo);
                    int indexOfNodeInLayer = -1;

                    for (int k = 0; k < webLayerList.get(numberOfLayerOfNodeToConnectTo).size(); k++) {
                        if (nodeToConnectTo == webLayerList.get(numberOfLayerOfNodeToConnectTo).get(k)) {
                            indexOfNodeInLayer = k;
                            break;
                        }
                    }

                    double x2 = numberOfLayerOfNodeToConnectTo * canvasWidth / (layerCount - 1) + dotSize / 2;
                    double y2 = indexOfNodeInLayer * canvasWidth / (biggestLayerSize - 1) + dotSize / 2;

                    double textX = (x1 + x2) / 2;
                    double textY = (y1 + y2) / 2;

                    //rysuje linie
                    context.setFill(Color.web("#673ab7"));
                    context.setStroke(Color.web("#673ab7"));

                    context.strokeLine(x1, y1, x2, y2);

                    //rysuje grot strzałki
                    double angle = Math.atan2(y2 - y1, x2 - x1);
                    double angleDifference = Math.PI * 30 / 180;

                    context.strokeLine(x2, y2, x2 - Math.cos(angle + angleDifference) * 15, y2 - Math.sin(angle + angleDifference) * 15);
                    context.strokeLine(x2, y2, x2 - Math.cos(angle - angleDifference) * 15, y2 - Math.sin(angle - angleDifference) * 15);

                    //wypisuje wagę połączenia
                    context.setFill(Color.web("#000000"));
                    context.setStroke(Color.web("#000000"));

                    context.fillText(String.valueOf(singleEdge.getValue()), textX, textY);
                }
            }
    }
}
