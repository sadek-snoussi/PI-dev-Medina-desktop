/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import static pidev.edu.souk.controller.ActualiteEvent2FXMLController.selection;
import static pidev.edu.souk.controller.ActualiteEvent2FXMLController.userid;
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class EventStandListFXMLController implements Initializable {

    @FXML
    private ListView<Event> listevent;
    @FXML
    private Button btreserver;
    @FXML
    private Label iduser;
    public static int userid = 0;
    public static Event selection;
    @FXML
    private TextArea idtext;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    private ObservableList<Event> data = FXCollections.observableArrayList();
    Event event = new Event();
    EvenementService eventservice = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(eventservice.listerEvenement());
        listevent.setItems(data);
        // System.out.println(data);
        listevent.setCellFactory((ListView<Event> param) -> new EventList());
        iduser.setVisible(false);
        idtext.setDisable(false);

    }

    public int sentid() {
        userid = Integer.valueOf(iduser.getText());
        return userid;
    }

    @FXML
    private void loadStandDispo(ActionEvent event) {
        if (listevent.getSelectionModel().getSelectedItem() != null) {
            selection = listevent.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            Parent root;
            try {

                root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/ListStandDispoFXML.fxml"));
                //userid=(Integer.parseInt(iduserEvent.getText()));
               

                if (selection.getNbrePlace() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning");
                    alert.setContentText("Stock des Stand Epuisé !!");
                    alert.show();
                } else {

                    System.out.println(SingninController.userIden);

                    // selection.getNomEvent()
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {

            }

        }
        else {
             
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning");
                    alert.setContentText("Veuillez sélectionné un Evenement  !!");
                    alert.show();

                }
        
    }

}
