package main_window;

import graph_classes.DiGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        /**
         * testy
         */

        DiGraph diGraph = new DiGraph();
        diGraph.printMatrix();
        diGraph.generateNodeArray();
        diGraph.printNodeArray();
        for(int i=0;i<10;++i){
            System.out.println(i + ": ------------------");
            diGraph.generateProbabilityMatrix(4,0.5);
        }
    }


    public static void main(String[] args) {launch(args);}

}
