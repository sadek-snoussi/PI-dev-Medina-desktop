/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.mail.MessagingException;
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.entities.Stand;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.StandService;
import pidev.edu.souk.utils.Mail;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class ListStandDispoFXMLController implements Initializable {

    @FXML
    private TableView<Stand> standDispo;
    @FXML
    private TableColumn<Stand, Double> superficie;
    @FXML
    private TableColumn<Stand, String> emp;
    @FXML
    private TableColumn<Stand, String> couleur;
    @FXML
    private TableColumn<Stand, Double> prix;
    @FXML
    private Button reserver;
    @FXML
    private Label iduser;
    @FXML
    private Label idevent;
    @FXML
    private Label nbstand;
    @FXML
    private Label info;
    @FXML
    private Button conf;
    @FXML
    private TextField rechercheS;
    @FXML
    private Button btfind;

    private ObservableList<Stand> data = FXCollections.observableArrayList();
    @FXML
    private TextField Email;
   
    
    @FXML
    private RadioButton btsuper;
    @FXML
    private RadioButton btprix;
    @FXML
    private Label sdispo;
    @FXML
    private Label lbfeliii;
    @FXML
    private AnchorPane idan;
    @FXML
    private AnchorPane idan2;

    public Label getNbstand() {
        return nbstand;
    }

    public void setNbstand(Integer nbstand) {
        this.nbstand.setText(Integer.toString(nbstand));
    }

    public TextField getRechercheS() {
        return rechercheS;
    }

    public void setRechercheS(TextField rechercheS) {
        this.rechercheS = rechercheS;
    }

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    public Label getIdevent() {
        return idevent;
    }

    public void setIdevent(Integer idevent) {
        this.idevent.setText(Integer.toString(idevent));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStanddispo();
        superficie.setCellValueFactory(new PropertyValueFactory<>("superficieStand"));
        emp.setCellValueFactory(new PropertyValueFactory<>("emplacementStand"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
      idan.setVisible(false);
        btsuper.setSelected(false);
       
        nbstand.setVisible(false);
        
        btprix.setSelected(false);
        Button b1 = new Button("reserver");

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ReserverStandFXML.fxml"));
                    Parent root = loader.load();
                    ReserverStandFXMLController reserver = loader.getController();

                    b1.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListStandDispoFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        

     
    }

    public void loadStanddispo() {

        StandService s = new StandService();
        ArrayList<Stand> stand = s.findStandBydispo();

        ObservableList observableList = FXCollections.observableArrayList(stand);
        System.out.println(stand);
        standDispo.setItems(observableList);
        System.out.println(" hello");
    }

    @FXML
    private void reserverStand(ActionEvent event) {

        Stand stand = new Stand();
        StandService ser = new StandService();

        Stand s = standDispo.getSelectionModel().getSelectedItem();

        if (s == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un stand");
            alert.show();
        } else {

            User u = new User();
            Event e = new Event();
            u.setId(SingninController.userIden);
            EventStandListFXMLController ev = new EventStandListFXMLController();

            idevent.setText(ev.selection.getIdEvent().toString());
            nbstand.setText(ev.selection.getNbreStand().toString());

            String g = nbstand.getText();
            int n = Integer.valueOf(g) - 1;

            e.setIdEvent(Integer.parseInt(idevent.getText()));

            System.out.println("    --------------------------- ");
            e.setNbreStand(n);

            System.out.println(n);

            iduser.setText(String.valueOf(SingninController.userIden));

            System.out.println("55555555555");
            System.out.println(iduser);
            System.out.println(idevent);

            String b = idevent.getText();
            int x = Integer.valueOf(b);

            e.setNbreStand(n);

            s.setEventstand(e);

            s.setUserId(u);
            System.out.println(e);

            ser.reservation(s, s.getNumStand());
            ser.downNbstand(e, s.getEventstand().getIdEvent());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Succès");
            alert.setContentText("Reservation Effectuée avec succès");
            alert.show();

            standDispo.setVisible(false);
            btfind.setVisible(false);

            reserver.setVisible(false);

            conf.setVisible(true);
            lbfeliii.setVisible(true);
            idevent.setVisible(false);
            iduser.setVisible(false);
            info.setVisible(true);
            Email.setVisible(true);
            btprix.setVisible(false);
            btsuper.setVisible(false);
            rechercheS.setVisible(false);
            idan.setVisible(true);
       
            sdispo.setVisible(false);

        }

    }

    @FXML
    private void findbysuperficie(ActionEvent event) {
        System.out.println("" + rechercheS.getText());

        StandService ss = new StandService();

        if (btsuper.isSelected() == true) {

            //ss.findStandBySuperficie(Double.parseDouble(rechercheS.getText()));
            standDispo.getItems().clear();

            standDispo.getItems().addAll(ss.findStandBySuperficie(Double.parseDouble(rechercheS.getText())));
            btsuper.setSelected(false);
            btsuper.setSelected(false);
            btprix.setDisable(false);
        } else if (btprix.isSelected() == true) {

            //ss.findStandByPrice(Double.parseDouble(rechercheS.getText()));
            standDispo.getItems().clear();
            standDispo.refresh();
            standDispo.getItems().addAll(ss.findStandByPrice(Double.parseDouble(rechercheS.getText())));
             btprix.setSelected(false);
             btprix.setSelected(false);
            btsuper.setDisable(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez Choisir un Critére pour Votre Recherche");
            alert.show();
        }

    }

    @FXML
    private void envoyerMailReservation(ActionEvent event) {
        try {
            Stand s = standDispo.getSelectionModel().getSelectedItem();

            String contenu = " Votre Reçu \n SOUK MEDINA Vous Souhaite le Bienvenue  dans Notre Evenement \n"
                    + "REF Stand:   " + s.getNumStand() + "\n" + "Superficie:    " + s.getSuperficieStand() + "\n" + "Couleur:      "
                    + s.getCouleur() + "\n" + "Emplacement:   " + s.getEmplacementStand() + "\n" + " La Presence du Reçu est impérative pour prendre votre Stand le jour-ji. ";
            System.out.println(contenu);
            Mail.sendMail(Email.getText(), "SOUK MEDINA", contenu);
            System.out.println("bravoooo");
            idan2.setVisible(false);
            
        } catch (MessagingException ex) {

        }

    }

    @FXML
    private void verifsuper(ActionEvent event) {

        if (btprix.isSelected() == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un seul critére SVP");
            alert.show();
           
           
              }
       btprix.setSelected(false);
    }

    @FXML
    private void verifprix(ActionEvent event) {

        if (btsuper.isSelected() == true) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un seul critére SVP");
            alert.show();
          
           
              }
        btsuper.setSelected(false);
    }

}
