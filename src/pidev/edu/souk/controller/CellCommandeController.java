/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.services.PanierService;

/**
 * FXML Controller class
 *
 * @author Sofienne
 */
public class CellCommandeController  {

    @FXML
    private HBox cellCommande;
    @FXML
    private ImageView imageProduit;
    @FXML
    private Label nomProduit;
    @FXML
    private Label prixProduit;
    @FXML
    private Label quantiteProduit;

    PanierService ps = new PanierService();

    ObservableList<Panier> data;
    
     public CellCommandeController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CellCommande.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public HBox getCellCommande() {
        return cellCommande;
    }
    
      public void setInfo(Panier panier) {
        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + panier.getProduitId().getUrlImgProduit());       //****************
        imageProduit.setImage(img);
        nomProduit.setText(panier.getProduitId().getNomProduit());
        prixProduit.setText(String.valueOf(panier.getProduitId().getPrixVenteProduit()));
        quantiteProduit.setText(String.valueOf(panier.getQuantiteProduit()));
    }

    
}
