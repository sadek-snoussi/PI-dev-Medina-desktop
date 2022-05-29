/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import javafx.stage.Stage;


/**
 *
 * @author amalb
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/PannelUser.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AccueilAdmin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/listPartenairesAdmin.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/signup.fxml"));
          
     
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
       
     
        //  primaryStage.setResizable(false);
        //primaryStage.setMaximized(true);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
