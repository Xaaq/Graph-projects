package main_window;

import graph_classes.Web;
import graph_classes.WebEdge;
import graph_classes.WebNode;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Canvas canvas;
    public Label nodeArray;
    public Label maximumFlow;
    public TextField webSize;

    private Web web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void countFlowButtonClick() {
        maximumFlow.setText("Przepływ: " + String.valueOf(web.countMaximumFlow()));
        printWebArray(web);
        drawGraph(web);
    }

    public void generateWebButtonClick() {
        web = new Web(Integer.parseInt(webSize.getText()));
        printWebArray(web);
        drawGraph(web);
    }

    /**
     * Wypisuje tablice wszystkich węzłów sieci.
     *
     * @param web sieć do wypiasnia
     */
    private void printWebArray(Web web) {
        ArrayList<ArrayList<WebNode>> webLayerList = web.getLayerList();
        String textToPrint = "Lista połączeń węzłów (w nawiasach są wagi połączeń):\n";

        for (ArrayList<WebNode> nodeArray : webLayerList) {
            for (WebNode singleNode : nodeArray) {
                textToPrint += "Węzeł nr " + singleNode.getId() + ": ";

                for (WebEdge singleEdge : singleNode.getOutputConnectionList()) {
                    textToPrint += singleEdge.getOutputNode().getId() + "(" + singleEdge.getCurrentValue() + "/" + singleEdge.getMaxValue() + ") ";
                }

                textToPrint += "\n";
            }
        }

        nodeArray.setText(textToPrint);
    }

    /**
     * Wyrysowywuje graf na canvasie.
     *
     * @param web sieć do wyrysowania
     */
    private void drawGraph(Web web) {
        int dotSize = 15;
        double canvasWidth = canvas.getWidth() - dotSize * 5;
        double canvasHeight = canvas.getHeight() - dotSize * 5;
        ArrayList<ArrayList<WebNode>> webLayerList = web.getLayerList();
        int layerCount = webLayerList.size();
        GraphicsContext context = canvas.getGraphicsContext2D();
        int biggestLayerSize = 0;

        context.clearRect(0, 0, canvasWidth * 2, canvasHeight * 2);
        context.setLineWidth(3);

        for (ArrayList<WebNode> nodeList : webLayerList) {
            if (nodeList.size() > biggestLayerSize)
                biggestLayerSize = nodeList.size();
        }

        for (int i = 0; i < webLayerList.size(); i++)
            for (int j = 0; j < webLayerList.get(i).size(); j++) {
                //rysuje kółka
                context.setFill(Color.web("#90caf9"));
                context.setStroke(Color.web("#90caf9"));

                double x = i * canvasWidth / (layerCount - 1) + dotSize * 2;
                double y = j * canvasHeight / (biggestLayerSize - 1) + dotSize * 2;
                context.fillOval(x, y, dotSize, dotSize);

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

                    double x2 = numberOfLayerOfNodeToConnectTo * canvasWidth / (layerCount - 1) + dotSize / 2 + dotSize * 2;
                    double y2 = indexOfNodeInLayer * canvasWidth / (biggestLayerSize - 1) + dotSize / 2 + dotSize * 2;

                    double lineAngle = Math.atan2(y2 - y1, x2 - x1);
                    double textX = (x1 + x2) / 2 - Math.cos(lineAngle + Math.PI / 2) * 20;
                    double textY = (y1 + y2) / 2 - Math.sin(lineAngle + Math.PI / 2) * 20;

                    //rysuje linie
                    context.setFill(Color.web("#90caf9"));
                    context.setStroke(Color.web("#90caf9"));

                    context.strokeLine(x1, y1, x2, y2);

                    //rysuje grot strzałki
                    double angleDifference = Math.PI * 30 / 180;
                    context.strokeLine(x2, y2, x2 - Math.cos(lineAngle + angleDifference) * 15, y2 - Math.sin(lineAngle + angleDifference) * 15);
                    context.strokeLine(x2, y2, x2 - Math.cos(lineAngle - angleDifference) * 15, y2 - Math.sin(lineAngle - angleDifference) * 15);

                    //wypisuje wagę połączenia
                    context.setFill(Color.web("#000000"));
                    context.setStroke(Color.web("#000000"));

                    Font font = new Font(16);
                    context.setFont(font);
                    context.fillText(String.valueOf(singleEdge.getCurrentValue() + "/" + singleEdge.getMaxValue()), textX, textY);
                }

                //wypisuje id węzła
                context.setFill(Color.web("#000000"));
                context.setStroke(Color.web("#000000"));

                Font font = new Font(20);
                context.setFont(font);
                context.fillText(String.valueOf(webLayerList.get(i).get(j).getId()), x + dotSize, y + dotSize * 2);
            }
    }
}
