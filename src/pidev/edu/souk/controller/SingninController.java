/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pidev.edu.souk.services.UserService;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.Password;
import pidev.edu.souk.services.UpdatableBCrypt;
import pidev.edu.souk.services.serviceCryptage;
import pidev.edu.souk.test.Main;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class SingninController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField mdp;
    @FXML
    private Button connecter;
    @FXML
    private Hyperlink inscri;

    public static int userIden = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setVisible(true);
        mdp.setVisible(true);

    }

    @FXML
    private void connecter(ActionEvent event) throws IOException {

        try {

            UserService us = new UserService();
            User user = new User();
            user = us.connecter(username.getText());
            System.out.println("**ali***");
            System.out.println(mdp.getText());
            System.out.println(user.getRoles());

            Password md = new Password();
            Boolean mdpCrypte = md.checkPassword(mdp.getText(), user.getPassword());

            System.out.println(mdpCrypte);

            userIden = user.getId();
            System.out.println("amouuuuuuuuuuuuulaaaaaaaaaaaaaaaaaa" + userIden);
            if (mdpCrypte == true) {

                System.out.println("authentification reussite");

                if (user.getRoles().equals("a:2:{i:0;s:11:\"ROLE_CLIENT\";i:1;s:10:\"ROLE_ADMIN\";}")) {

                    //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));

                    Parent root = loader.load();

                    PannelAdminController pannelAdmin = loader.getController();
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AccueilAdmin.fxml"));

                    Parent root2 = loader2.load();
                    ScrollPane sp = new ScrollPane();
//                    sp.setContent(root2);
//                    sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//                    sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//                    root.resize(1080, 720);
                    AccueilAdminController ad = loader2.getController();

                    pannelAdmin.getContainer_admin().getChildren().setAll(root2);

                    mdp.getScene().setRoot(root);

                } else if ((user.getRoles().equals("a:1:{i:0;s:15:\"ROLE_FREELANCER\";}") || (user.getRoles().equals("a:1:{i:0;s:8:\"ROLE_PRO\";}")))) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelUser.fxml"));
                    Parent root = loader.load();
                    PannelUserController pannelUser = loader.getController();

                    pannelUser.setIduser(user.getId());

                    //primaryStage.setMaximized(true);
                    mdp.getScene().setRoot(root);

                } else if (user.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelClient.fxml"));
                    Parent root = loader.load();
                    PannelClientController pannelClient = loader.getController();

                    //primaryStage.setMaximized(true);
                    mdp.getScene().setRoot(root);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("veuillez verifier votre login ou mot de passe ..");
                alert.show();
                System.out.println("veuillez verifier votre login ou mot de passe ..");
            }

        } catch (SQLException ex) {

        }

    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/signup.fxml"));
            Parent root = loader.load();
            SignupController signup = loader.getController();
            username.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

}
