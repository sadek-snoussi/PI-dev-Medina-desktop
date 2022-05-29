/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import pidev.edu.souk.entities.Statistiques;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.GradeService;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.services.serviceCryptage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class AjouterDemandeProController implements Initializable {

    @FXML
    private TextField nomE;
    @FXML
    private TextField numE;
    @FXML
    private ComboBox<String> specialite;
    @FXML
    private ComboBox<String> grade;
    @FXML
    private Button demander;
    @FXML
    private Label iduser;
    @FXML
    private TextField adresse;
    @FXML
    private Button logo;
    @FXML
    private Label labellogo;

    File file;
    @FXML
    private Label labelnum;
    @FXML
    private ImageView numeroTick;
    @FXML
    private ImageView specialiteTick;
    @FXML
    private ImageView gradeTick;
    @FXML
    private ImageView logoTick;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iduser.setVisible(true);
        nomE.setVisible(true);
        numE.setVisible(true);
        specialite.setVisible(true);
        grade.setVisible(true);
        adresse.setVisible(true);
        iduser.setVisible(false);
        

        numeroTick.setVisible(false);
        specialiteTick.setVisible(false);
        gradeTick.setVisible(false);
        logoTick.setVisible(false);
        GradeService gs = new GradeService();
        ArrayList<Statistiques> gradesList = gs.listGrades();
        ArrayList<String> specialites = new ArrayList<>();
        ObservableList observableList = FXCollections.observableArrayList(gradesList);
        ObservableList observableListSpecialites = FXCollections.observableArrayList(specialites);
        for (Statistiques statistiques : gradesList) {
            grade.getItems().add(statistiques.getGrade());
        }
        specialite.getItems().add("Epicerie");
        specialite.getItems().add("Patisserie");
        specialite.getItems().add("Bijouterie");
        specialite.getItems().add("Textile");
    }

    @FXML
    private void demanderPartenariat(ActionEvent event) {
         

        if (verificationAdresse == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'adresse de votre Entreprise");
            alert.show();
        } else if (verificationGrade == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Choisir votre Grade souhaité");
            alert.show();
        } else if (verificationSpecialite == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Choisir votre Spécialité");
            alert.show();
        } else if (verificationUserPhone == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Saisir le Numéro de votre Entreprise");
            alert.show();
        } else if (verificationLogo == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Entrez le Logo de votre Entreprise");
            alert.show();
        } else {

            PartenaireService ps = new PartenaireService();
            User user = new User();
            user.setNomEntreprisePro(nomE.getText());
            user.setTelBureauPro(numE.getText());
            user.setSpecialitePart(specialite.getValue());
            user.setGradePro(grade.getValue());
            user.setAdresse(adresse.getText());

            String imgName = serviceCryptage.cryptWithMD5(labellogo.getText());
            String imgFullName = imgName + labellogo.getText().substring(labellogo.getText().length() - 4, labellogo.getText().length());
            Random rdm = new Random();

            String finalImgName = rdm.nextInt(999999999) + imgFullName;

            user.setUrlLogoPro(finalImgName);
            PartenaireService prodServ = new PartenaireService();

            prodServ.saveFile(file, finalImgName);
            ps.ajoutDemandePartenariatPro(user, SingninController.userIden);
            TrayNotification tray = new TrayNotification("Successfully",
                    "Demande de Partenariat envoyée avec succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

        }
    }

    @FXML
    private void chooseFile(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open Resource File");
        Window stage = null;
        file = filechooser.showOpenDialog(stage);

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        labellogo.setText(file.getName());
        labellogo.setVisible(true);
         if (labellogo.getText().trim().equals("")) {

            verificationLogo = false;
        } else {
            verificationLogo = true;
            logoTick.setVisible(true);

        }

    }

    Boolean verificationUserPhone = false;
    Boolean verificationNomEntreprise = false;
    Boolean verificationAdresse = false;
    Boolean verificationLogo = false;
    Boolean verificationSpecialite = false;
    Boolean verificationGrade = false;

    @FXML
    private void controlNum(KeyEvent event) {
        System.out.println(numE.getText().trim());
        if (numE.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < numE.getText().trim().length(); i++) {
                char ch = numE.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                labelnum.setText("");
                verificationUserPhone = true;
                numeroTick.setVisible(true);
                
            } else {
                labelnum.setText("numero invalide "
                        + " Il existe des caractéres");
                verificationUserPhone = false;

            }

        } else {
            labelnum.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }
    }

    @FXML
    private void ControlNomEntreprise(KeyEvent event) {
        if (nomE.getText().trim().equals("")) {

            verificationNomEntreprise = false;

        } else {

            verificationNomEntreprise = true;
            
        }
    }

    @FXML
    private void controlAdresse(KeyEvent event) {
        if (adresse.getText().trim().equals("")) {

            verificationAdresse = false;

        } else {

            verificationAdresse = true;
        }
    }

  

    

   

    @FXML
    private void ControlGrade(ActionEvent event) {
         if (grade.getValue().toString().trim().equals("")) {

            verificationGrade = false;

        } else {

            verificationGrade = true;
            gradeTick.setVisible(true);
        }
    }

    @FXML
    private void ControlSpecialite(ActionEvent event) {
         if (specialite.getValue().toString().trim().equals("")) {

            verificationSpecialite = false;

        } else {

            verificationSpecialite = true;
            specialiteTick.setVisible(true);
        }
    }

}
