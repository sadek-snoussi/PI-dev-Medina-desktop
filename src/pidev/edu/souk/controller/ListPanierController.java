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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.services.PanierService;
import pidev.edu.souk.services.panierCellListView;

/**
 * FXML Controller class
 *
 * @author Sofienne
 */
public class ListPanierController implements Initializable {

    @FXML
    private ListView<Panier> AffPanier;
    
    private ObservableList<Panier> data = FXCollections.observableArrayList();
    PanierService ps = new PanierService();
    Panier panier = new Panier();
    @FXML
    private Button passerCommande;
    @FXML
    private Button annuler;

    
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AffPanier.setVisible(true);
        loadData();

        
        
    } 
    
       
    private void loadData(){
        //System.out.println("apres click "+iduser.getText());
        //int x = Integer.valueOf(iduser.getText());
        data.addAll(ps.selectPanierById(SingninController.userIden,0));
        AffPanier.setItems(data);
        AffPanier.setCellFactory((ListView<Panier> param)-> new panierCellListView());
    }              
    
    public void refreshData(int x){
        //int x = Integer.valueOf(iduser.getText());
        data.addAll(ps.selectPanierById(x,0));
        AffPanier.setItems(data);
        AffPanier.refresh();
    }
    
    
    private void loadpageforId(){
        
       
        data.addAll(ps.selectPanierById(SingninController.userIden,0));
        AffPanier.setCellFactory(new Callback<ListView<Panier>, ListCell<Panier>>() {
            @Override
            public ListCell<Panier> call(ListView<Panier> param) {
                ListCell<Panier> cell = new ListCell<Panier>() {
                   Label panier = new Label("");
                   TextField nbrProduit = new TextField();
                
                   Button btnSupprimer = new Button("Supprimer");
                   HBox hbox = new HBox(panier,nbrProduit,btnSupprimer);
                   
                    @Override
                    protected void updateItem(Panier p, boolean btl) {
                        super.updateItem(p, btl);
                          setGraphic(null);
                        if (p != null) {
                           
                           ArrayList<Produit> list = ps.selectProduitById(p.getProduitId().getIdProduit());  
                            Produit produit =list.stream().findFirst().get();
                            p.setProduitId(produit);
                            panier.setText(p.toString());
                            setGraphic(hbox);

                        }
                    } 
                   
                };
                        return cell; 
            }
        });
        AffPanier.setItems(data);
    }

    private void tryload(ActionEvent event) {
             //loadpageforId();
             loadData();
    }

    @FXML
    private void passezCommande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelClient.fxml"));
            Parent root = loader.load();
            PannelClientController pannelUser = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ajouterCommande.fxml"));
            Parent root2 = loader2.load();
            pannelUser.getContainer_client().getChildren().setAll(root2);
            AffPanier.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void annulerRetour(ActionEvent event) {
    }
   
    
}
