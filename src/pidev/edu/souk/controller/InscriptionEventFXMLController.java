/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.mail.MessagingException;
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.UserEvent;
import pidev.edu.souk.services.EvenementService;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.utils.Mail;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class InscriptionEventFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;

    @FXML
    private Button valider;
    @FXML
    private Label id;
    @FXML
    private Label iduser;
    @FXML
    private Label nameEvent;
    @FXML
    private Label lblmail;
    Boolean vmail = false;
    Boolean vnom = false;
    Boolean vprenom = false;
    @FXML
    private Label nbplace;
    @FXML
    private Label nom1;
    @FXML
    private Label prenom2;
    @FXML
    private Label mail2;
    @FXML
    private Label info;
    @FXML
    private Label conf;
   

    public Label getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent.setText(nameEvent);
    }

    public Label getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id.setText(Integer.toString(id));

    }

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameEvent.setVisible(true);
        id.setVisible(true);
        nom.setVisible(true);
        prenom.setVisible(true);
        email.setVisible(true);
        iduser.setVisible(true);
        nom1.setVisible(true);
        prenom2.setVisible(true);
        mail2.setVisible(true);
        info.setVisible(true);
        nameEvent.setVisible(true);
        valider.setVisible(true);
        conf.setVisible(false);
      
      
       
        

        //iduser.setText(String.valueOf(ActualiteEvent2FXMLController.uderId));
    }

    @FXML
    private void inscription(ActionEvent event) {

        if (vnom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText(" Veuillez saisir Votre Nom");
            alert.show();
        } else if (vprenom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText(" Veuillez saisir Votre Prenom");
            alert.show();

        } else if (vmail == false) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Format E-mail invalide");
            alert.show();
        } else {
            UserEvent inscri = new UserEvent();
            EvenementService es = new EvenementService();
            // String a =String.valueOf(ActualiteEvent2FXMLController.uderId);
            //int x = Integer.valueOf(a);
            User u = new User();
            u.setId(SingninController.userIden);
            ActualiteEvent2FXMLController event1 = new ActualiteEvent2FXMLController();
            Event e = new Event();
            nameEvent.setText(event1.selection.getNomEvent());
            id.setText(event1.selection.getIdEvent().toString());
            iduser.setText(String.valueOf(SingninController.userIden));
            nbplace.setText(event1.selection.getNbrePlace().toString());

            String g = nbplace.getText();
            int n = Integer.valueOf(g) - 1;
            e.setNbrePlace(n);

            System.out.println("je suis dans inscri " + SingninController.userIden);
            // int a=event1.userid;
            // iduser.setText(a);

            //System.out.println(a);
            System.out.println(nameEvent);

            String b = id.getText();

            int y = Integer.valueOf(b);

            e.setIdEvent(y);
            inscri.setUserId(u);
            System.out.println("    --------------------------");
            System.out.println(u);
            inscri.setNom(nom.getText());
            inscri.setPrenom(prenom.getText());
            inscri.setAdressemail(email.getText());

            inscri.setEventId(e);
            es.inscription(inscri);
            es.downNbplace(e, y);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Inscription Effectuée avec succès");
              alert.show();

            try {
                String contenu = " Votre Pass \n SOUK MEDINA Vous Souhaite le Bienvenue  dans Notre Evenement \n"
                        + nameEvent.getText().toString() + "\n" + "Nom :    " + nom.getText() + "\n" + "Prenom:      "
                        + prenom.getText().toString() + "\n" + " La Presence du Pass est impérative pour assister a l'Evenement. \n SVP Imprimer Votre Pass Avant d'arriver a l'Evenement. ";
                System.out.println(contenu);
                Mail.sendMail(email.getText(), "SOUK MEDINA", contenu);
                System.out.println("bravoooo");
            } catch (MessagingException ex) {

            }

          
            nom.setVisible(false);
            id.setVisible(false);
            iduser.setVisible(false);
            prenom.setVisible(false);
            email.setVisible(false);
            lblmail.setVisible(false);
            nom1.setVisible(false);
            prenom2.setVisible(false);
            mail2.setVisible(false);
            nameEvent.setVisible(false);
            info.setVisible(false);
            valider.setVisible(false);
            nbplace.setVisible(false);
            conf.setVisible(true);
       
            iduser.setVisible(false);
            id.setVisible(false);

        }
    }

    @FXML
    private void verifmail(KeyEvent event) {

        if (email.getText().trim().equals("")) {

            vmail = false;
        } else {

            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                lblmail.setVisible(true);
                lblmail.setText("Email valide !");
                vmail = true;

            } else {
                lblmail.setVisible(true);
                lblmail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                vmail = false;

            }
        }

    }

    @FXML
    private void vnom(KeyEvent event) {
        if (nom.getText().trim().equals("")) {

            vnom = false;

        } else {

            vnom = true;
        }
    }

    @FXML
    private void vprenom(KeyEvent event) {
        if (prenom.getText().trim().equals("")) {

            vprenom = false;

        } else {

            vprenom = true;
        }
    }

    /* private void envoiMail(ActionEvent event) {

        try {
            String contenu = " Votre Pass \n SOUK MEDINA Vous Souhaite le Bienvenue  dans Notre Evenement \n"
                    + nameEvent.getText().toString() + "\n" + "Nom :    " + nom.getText() + "\n" + "Prenom:      "
                    + prenom.getText().toString() + "\n" + " La Presence du Pass est impérative pour assister a l'Evenement. \n SVP Imprimer Votre Pass Avant d'arriver a l'Evenement. ";
            System.out.println(contenu);
            Mail.sendMail(email.getText(), "SOUK MEDINA", contenu);
            System.out.println("bravoooo");
        } catch (MessagingException ex) {

        }

    }*/
}
