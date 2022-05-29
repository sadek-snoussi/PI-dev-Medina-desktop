/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
public class ProfileClientController implements Initializable {

    @FXML
    private Label iduser;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField num;
    @FXML
    private Button modifier;
    @FXML
    private TextField username;
    @FXML
    private DatePicker dateNaiss;
    @FXML
    private Label labelemail;
    @FXML
    private Label labelusername;
    @FXML
    private Label labelnum;
    @FXML
    private Label labeldateNaiss;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
         this.iduser.setText(Integer.toString(iduser));
    }

    public TextField getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public TextField getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.setText(prenom);
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(String  email) {
        this.email.setText(email);
    }

    public TextField getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num.setText(num);
    }

    public DatePicker getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss.setValue(LocalDate.of(dateNaiss.getYear(),dateNaiss.getMonth(),dateNaiss.getDayOfMonth()));
    }

  

    public TextField getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       email.setVisible(true);
       iduser.setVisible(false);
       nom.setVisible(true);
       num.setVisible(true);
       prenom.setVisible(true);
       username.setVisible(true);
       
    }    

    @FXML
    private void modifierProfileClient(ActionEvent event) {
         java.sql.Date datep = java.sql.Date.valueOf(dateNaiss.getValue());
          PartenaireService ps =new PartenaireService();
        User user = new User();
        user.setNomUser(nom.getText());
        user.setPrenomUser(prenom.getText());
        user.setEmail(email.getText());
       user.setTelUser(num.getText());
       user.setUsername(username.getText());
       user.setDateNaissUser(datep);
        ps.modifierProfileClient(user, Integer.parseInt(iduser.getText()));
  TrayNotification tray = new TrayNotification("Successfully",
                        "Modification Effectuée avec Succés", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.SLIDE);
                tray.showAndDismiss(Duration.seconds(10));
        
    }

      Boolean verificationUserPhone=false;
     Boolean verificationUserEmail=false;
     Boolean verificationUserUsername=false;

    @FXML
    private void controlEmail(KeyEvent event) {
          PartenaireService ps= new PartenaireService();
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
            Matcher matcher = pattern.matcher(email.getText().subSequence(0, email.getText().length()));

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelemail.setVisible(true);
                labelemail.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelemail.setVisible(true);
                labelemail.setText("Email Format invalide !");
              // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }
    }

    @FXML
    private void controlTel(KeyEvent event) {
          System.out.println(num.getText().trim());
             if (num.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < num.getText().trim().length(); i++) {
                char ch = num.getText().charAt(i);

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
    private void controlUsername(KeyEvent event) {
        
          PartenaireService ps= new PartenaireService();
        if (ps.findUserByUsername(username.getText().trim()) == true) {
            labelusername.setText("Username Existe déja");
            verificationUserUsername = false;
        }
        else {verificationUserUsername=true;
        labelusername.setText("Username valide");
        }
    }
    
}
