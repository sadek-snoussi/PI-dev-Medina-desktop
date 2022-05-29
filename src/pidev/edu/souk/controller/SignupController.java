/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.services.Password;
import pidev.edu.souk.services.UpdatableBCrypt;
import pidev.edu.souk.services.serviceCryptage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class SignupController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField mdp;
    @FXML
    private TextField cmdp;
    @FXML
    private TextField num;

    @FXML
    private Button signup;
    @FXML
    private DatePicker date;
    @FXML
    private TextField address;
    @FXML
    private Label labelemail;
    @FXML
    private Label labelusername;
    @FXML
    private Label labelNum;

    Boolean verificationUserPhone = false;
    Boolean verificationUserNom = false;
    Boolean verificationUserPrenom = false;
    Boolean verificationUserEmail = false;
    Boolean verificationUserUsername = false;
    Boolean verificationUserMdp = false;
    Boolean verificationUserMdp2 = false;
    Boolean verificationDateNaiss = false;
    @FXML
    private Label labeldate;
    @FXML
    private ImageView emailTick;
    @FXML
    private ImageView usernameTick;
    @FXML
    private ImageView mdpTick;
    @FXML
    private ImageView mdp2Tick;
    @FXML
    private ImageView numeroTick;
    @FXML
    private ImageView dateTick;
    @FXML
    private Label labelcmdp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nom.setVisible(true);
        prenom.setVisible(true);
        email.setVisible(true);
        username.setVisible(true);
        mdp.setVisible(true);
        cmdp.setVisible(true);
        num.setVisible(true);
        date.setVisible(true);
        address.setVisible(true);
        dateTick.setVisible(false);
        emailTick.setVisible(false);
        usernameTick.setVisible(false);
        numeroTick.setVisible(false);
        mdpTick.setVisible(false);
        mdp2Tick.setVisible(false);

    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        /* Password md = new Password();
          String mdpCrypte1 = md.hashPassword(mdp.getText());
          String mdpCrypte2 = md.hashPassword(cmdp.getText());
          boolean check = md.checkPassword(mdpCrypte1, mdpCrypte2);
           System.out.println("***** check*****"+check);
           System.out.println("***** 1*****"+mdpCrypte1);
           System.out.println("***** 2*****"+mdpCrypte2);*/

        if (verificationUserPhone == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le telephone");
            alert.show();

        } else if (verificationUserNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le nom");
            alert.show();

        } else if (verificationUserPrenom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le prenom");
            alert.show();

        } else if (verificationUserEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();

        } else if (verificationUserUsername == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le nom de l'utilisateur");
            alert.show();

        } else if ((verificationUserMdp == false) && (verificationUserMdp2 == false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez resaissir votre Mot de Passe Correctement");
            alert.show();

        } else if (verificationDateNaiss == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez choisir votre date de naissance ");
            alert.show();

        } else {
            java.sql.Date datep = java.sql.Date.valueOf(date.getValue());

            Password md = new Password();
            String mdpCrypte1 = md.hashPassword(mdp.getText());
            User user = new User();
            user.setUsername(username.getText());
            user.setEmail(email.getText());
            user.setEmailCanonical(email.getText());
            user.setPassword(mdpCrypte1);
            user.setNomUser(nom.getText());
            user.setPrenomUser(prenom.getText());
            user.setTelUser(num.getText());
            user.setDateNaissUser(datep);
            user.setAdresse(address.getText());
            PartenaireService ps = new PartenaireService();
            ps.ajouterClient(user);
            TrayNotification tray = new TrayNotification("Successfully",
                    "Inscription Effectuée avec Succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
            Parent root = loader.load();
            SingninController signin = loader.getController();
            prenom.getScene().setRoot(root);

        }
    }

    @FXML
    private void precedent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
            Parent root = loader.load();
            SingninController signin = loader.getController();
            prenom.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void controlNom(KeyEvent event) {

        if (nom.getText().trim().equals("")) {

            verificationUserNom = false;

        } else {

            verificationUserNom = true;
        }
    }

    @FXML
    private void controlPrenom(KeyEvent event) {
        if (prenom.getText().trim().equals("")) {

            verificationUserPrenom = false;

        } else {

            verificationUserPrenom = true;
        }
    }

    @FXML
    private void controlEmail(KeyEvent event) {

        PartenaireService ps = new PartenaireService();
        if (ps.findUserByEmail(email.getText().trim()) == true) {
            labelemail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (ps.findUserByEmail(email.getText().trim()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelemail.setVisible(true);
                labelemail.setText("Email valide !");
                verificationUserEmail = true;
                emailTick.setVisible(true);
                labelemail.setText("");

            } else {
                labelemail.setVisible(true);
                labelemail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }
    }

    @FXML
    private void controlUsername(KeyEvent event) {

        PartenaireService ps = new PartenaireService();
        if (ps.findUserByUsername(username.getText().trim()) == true) {
            labelusername.setText("Username Existe déja");
            verificationUserUsername = false;
        } else {
            verificationUserUsername = true;
            labelusername.setText("");
            usernameTick.setVisible(true);
        }

    }

    @FXML
    private void controlNumero(KeyEvent event) {

        System.out.println(num.getText().trim());
        if (num.getText().trim().length() == 7) {
            int nbChar = 0;
            for (int i = 1; i < num.getText().trim().length(); i++) {
                char ch = num.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                labelNum.setText("");
                verificationUserPhone = true;
                numeroTick.setVisible(true);
            } else {
                labelNum.setText("numéro invalide  "
                        + " Il existe des caractères");
                verificationUserPhone = false;

            }

        } else {
            labelNum.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }

    }

    @FXML
    private void controlMDP(KeyEvent event) {

        if (mdp.getText().trim().equals("")) {

            verificationUserMdp = false;

        } else {

            verificationUserMdp = true;

        }
    }

    @FXML
    private void controlMDP2(KeyEvent event) {

        if (cmdp.getText().trim().equals("")) {

            verificationUserMdp2 = false;

        } else if (!cmdp.getText().equals(mdp.getText())) {
            verificationUserMdp2 = false;

            labelcmdp.setText("Resaisissez votre Mot de Passe Correctement");

        } else {

            verificationUserMdp2 = true;
            labelcmdp.setText("");
            mdpTick.setVisible(true);
            mdp2Tick.setVisible(true);
        }
    }

    @FXML
    private void controlDateNaiss(ActionEvent event) {

        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        java.sql.Date datep = java.sql.Date.valueOf(date.getValue());

        if (date.getValue().toString().trim().equals("")) {

            verificationDateNaiss = false;

        } else if (datep.after(date_sql) || (datep.compareTo(date_util) == 0) || (datep.getYear() > date_sql.getYear() - 18)) {
            labeldate.setText("Date de Naissance invalide ");
            verificationDateNaiss = false;

        } else {

            verificationDateNaiss = true;
            labeldate.setText("");
            dateTick.setVisible(true);
        }
    }

    @FXML
    private void controlDateNaissOnkeyReleased(KeyEvent event) {

        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        java.sql.Date datep = java.sql.Date.valueOf(date.getValue());

        if (date.getValue().toString().trim().equals("")) {

            verificationDateNaiss = false;

        } else if (datep.after(date_sql) || (datep.compareTo(date_util) == 0) || (datep.getYear() > date_sql.getYear() - 18)) {
            labeldate.setText("Date de Naissance invalide ");
            verificationDateNaiss = false;

        } else {

            verificationDateNaiss = true;
            labeldate.setText("");
            dateTick.setVisible(true);
        }
    }

}
