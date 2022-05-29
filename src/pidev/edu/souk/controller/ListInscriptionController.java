/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.entities.UserEvent;
import pidev.edu.souk.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class ListInscriptionController implements Initializable {

    @FXML
    private TableView<UserEvent> listinscri;
    @FXML
    private TableColumn<UserEvent, Integer> numinscri;
    @FXML
    private TableColumn<UserEvent, String> nom;
    @FXML
    private TableColumn<UserEvent, String> prenom;
    @FXML
    private TableColumn<UserEvent, String> mail;
    @FXML
    private TableColumn<UserEvent,String> dateins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numinscri.setCellValueFactory(new PropertyValueFactory<>("idInscri"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("adressemail"));
        dateins.setCellValueFactory(new PropertyValueFactory<>("dateInscri"));
        loadInscri();
    }

    public void loadInscri() {
        EvenementService es = new EvenementService();
        
        ArrayList<UserEvent> inscris = es.listerInscri();

        ObservableList observableList = FXCollections.observableArrayList(inscris);
        listinscri.setItems(observableList);
        
               
      
    }

}
