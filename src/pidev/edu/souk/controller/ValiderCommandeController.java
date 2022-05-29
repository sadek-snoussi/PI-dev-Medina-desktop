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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev.edu.souk.entities.Commande;
import pidev.edu.souk.services.CommandeService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ValiderCommandeController implements Initializable {

    
    @FXML
    private TableView<Commande> TableView;
    @FXML
    private TableColumn<?, ?> TbNom;
    @FXML
    private TableColumn<?, ?> TbPrenom;
    @FXML
    private TableColumn<?, ?> TbEtatCommande;
    @FXML
    private TableColumn<?, ?> TbRefCommande;
    @FXML
    private TableColumn<?, ?> TbAction;
    @FXML
    private Button BValiser;
    @FXML
    private Button BNValide;
    @FXML
    private TableColumn<?, ?> TbIdCommande;
    
    CommandeService cd = new CommandeService();
    Alert alert = new Alert(AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableView.setVisible(true);
        CommandeService crudCommande = new CommandeService();
        ArrayList arrayList = (ArrayList) crudCommande.selectAllCommande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableView.setItems(observableList);
        TbRefCommande.setCellValueFactory(new PropertyValueFactory<>("Referance"));
        TbNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        TbPrenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        TbEtatCommande.setCellValueFactory(new PropertyValueFactory<>("etatCommande"));
        TbIdCommande.setVisible(false);
        TbIdCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        
        TbAction.setCellValueFactory(new PropertyValueFactory<>("button"));
       /*Commande c = new Commande();
        Button b = new Button("v");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                crudCommande.validerCommande(6);
            }
        });
        c.setButton(b);*/
    }    

    @FXML
    private void validerCommandes(ActionEvent event) {
        Commande c = TableView.getSelectionModel().getSelectedItem();
    
        cd.validerCommande(c.getIdCommande());
        //TableView.refresh();
        cd.selectAllCommande();
        alert.setHeaderText("Commande validée");
        alert.setContentText(null);
        alert.showAndWait();
    }

    @FXML
    private void nePasvaliderCommandes(ActionEvent event) {
        cd.selectAllCommande();
    }


   

 
    
}
