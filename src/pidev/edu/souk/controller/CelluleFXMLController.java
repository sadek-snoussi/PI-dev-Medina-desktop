/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.edu.souk.controller.ActualiteEvent2FXMLController;
import pidev.edu.souk.controller.InscriptionEventFXMLController;
import pidev.edu.souk.entities.Event;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class CelluleFXMLController  {

    @FXML
    private VBox listEvent;
    @FXML
    private HBox idbox;
    @FXML
    private Label idevent;
    @FXML
    private Label iduser;
    @FXML
    private HBox imagebox;
    @FXML
    private ImageView imageEvent;
    @FXML
    private Label im;
    @FXML
    private HBox nomevent;
    @FXML
    private Label nome;
    @FXML
    private HBox date;
    @FXML
    private Label dateE;
    @FXML
    private HBox lieux;
    @FXML
    private Label lieuE;
    @FXML
    private Label nbplace;
    @FXML
    private Label nbstand;

    /**
     * Initializes the controller class.
     */
   
    public Label getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent.setText(Integer.toString(idevent));
    }

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    public VBox getListEvent() {
        return listEvent;
    }

    public void setListEvent(VBox listEvent) {
        this.listEvent = listEvent;
    }

    public CelluleFXMLController() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/celluleFXML.fxml"));
        loader.setController(this);
        try {
//            binscrire.setVisible(false);
            loader.load();

        } catch (IOException e) {

        }

    }

    /**
     * Initializes the controller class.
     */
    public void affiche(Event e) {
        //try {
        Image img1;
        img1 = new Image("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/img/" + e.getUrlafficheevent());
        System.out.println(e.getUrlafficheevent());

        imageEvent.setImage(img1);
        imageEvent.setVisible(true);
        imageEvent.setFitWidth(500);
        imageEvent.setFitHeight(250);
        imageEvent.setPreserveRatio(true);
        imageEvent.setSmooth(true);
        imageEvent.setCache(true);

        nome.setText(e.getNomEvent());
        idevent.setText(Integer.toString(e.getIdEvent()));
        dateE.setText(e.getDateEvent().toString());
        nbplace.setText(e.getNbrePlace().toString());
        nbstand.setText(e.getNbreStand().toString());
        System.out.println(idevent);
        idevent.setVisible(false);
        lieuE.setText(e.getLieux());

    }

    private void inscription(ActionEvent event) {

        try {
            Event e = new Event();

//            System.out.println(Integer.parseInt(iduser.getText()));
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ActualiteEvent2FXML.fxml"));
            ActualiteEvent2FXMLController aa = loader1.getController();
            int y = aa.sentid();
            System.out.println(y);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/InscriptionEventFXML.fxml"));
            Parent root = loader.load();
            InscriptionEventFXMLController inscrievent = loader.getController();

            inscrievent.setId(Integer.parseInt(idevent.getText()));
            // inscrievent.setIduser(iduser);
            // System.out.println(iduser.getText());
            inscrievent.setIduser(Integer.valueOf(iduser.getText()));
            inscrievent.setNameEvent(e.getNomEvent());
            System.out.println(inscrievent);
            im.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(pidev.edu.souk.controller.CelluleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
    
}
