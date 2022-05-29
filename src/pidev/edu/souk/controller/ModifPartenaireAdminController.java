/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PartenaireService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ModifPartenaireAdminController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField numBureau;
    @FXML
    private ComboBox<String> specialite;
    @FXML
    private TextField nomE;
    @FXML
    private TextField grade;
    @FXML
    private Button modifier;
    @FXML
    private Label iduser;

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

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public TextField getNumBureau() {
        return numBureau;
    }

    public void setNumBureau(String numBureau) {
        this.numBureau.setText(numBureau);
    }

    public ComboBox<String> getSpecialite() {
        return specialite;
    }

    public void setSpecialite(ComboBox<String> specialite) {
        this.specialite = specialite;
    }

    public TextField getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE.setText(nomE);
    }

    public TextField getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.setText(grade);
    }

    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(iduser.toString());
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         specialite.getItems().add("Epicerie");
        specialite.getItems().add("Patisserie");
        specialite.getItems().add("Bijouterie");
        specialite.getItems().add("Textile");
    }    

    @FXML
    private void modiferProfilePro(ActionEvent event) {
           PartenaireService ps =new PartenaireService();
        User user = new User();
        user.setNomUser(nom.getText());
        user.setPrenomUser(prenom.getText());
        user.setEmail(email.getText());
        user.setTelBureauPro(numBureau.getText());
        user.setSpecialitePart(specialite.getValue());
        user.setNomEntreprisePro(nomE.getText());
        user.setGradePro(grade.getText());
        ps.modifierProfilePro(user, Integer.parseInt(iduser.getText()));
    }

    @FXML
    private void ControlSpecialite(ActionEvent event) {
        
    }
    
}
