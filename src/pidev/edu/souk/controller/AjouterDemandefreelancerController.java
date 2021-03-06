/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PartenaireService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class AjouterDemandefreelancerController implements Initializable {

    @FXML
    private TextField numE;
    @FXML
    private ComboBox<String> specialite;
    @FXML
    private Button demander;
    @FXML
    private Label iduser;
    @FXML
    private TextField adresse;
    @FXML
    private Button photo;
    @FXML
    private Label labelphoto;

    File file;
    @FXML
    private Label labelnum;
    @FXML
    private ImageView numeroTick;
    @FXML
    private ImageView photoTick;
    @FXML
    private ImageView specialiteTick;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    
    Boolean verificationUserPhone = false;
    Boolean verificationNomEntreprise = false;
    Boolean verificationAdresse = false;
    Boolean verificationLogo = false;
    Boolean verificationSpecialite = false;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iduser.setVisible(false);
        numeroTick.setVisible(false);
        specialiteTick.setVisible(false);
        photoTick.setVisible(false);

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
        } 
         else if (verificationSpecialite == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Choisir votre Sp??cialit??");
            alert.show();
        } 
          else if (verificationUserPhone == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Saisir le Num??ro de votre Entreprise");
            alert.show();
        } 
           else if (verificationLogo == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Entrez le Logo de votre Entreprise");
            alert.show();
        } 
         
           else{
        PartenaireService ps = new PartenaireService();
        User user = new User();
        user.setTelBureauPro(numE.getText());
        user.setSpecialitePart(specialite.getValue().toString());
        user.setAdresse(adresse.getText());
        ps.ajoutDemandePartenariatFree(user, Integer.parseInt(iduser.getText()));
        
         TrayNotification tray = new TrayNotification("Successfully",
                    "Demande de Partenariat envoy??e avec succ??s", NotificationType.SUCCESS);
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

        labelphoto.setText(file.getName());
        labelphoto.setVisible(true);

        if (labelphoto.getText().trim().equals("")) {

            verificationLogo = false;
        } else {
            verificationLogo = true;
            photoTick.setVisible(true);

        }
    }

   

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
                labelnum.setText("number valide");
                verificationUserPhone = true;
            } else {
                labelnum.setText("invalide number "
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {
            labelnum.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
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
    private void ControlSpecialite(ActionEvent event) {
        
          if (specialite.getValue().toString().trim().equals("")) {

            verificationSpecialite = false;

        } else {

            verificationSpecialite = true;
            specialiteTick.setVisible(true);
        }
    }

}
