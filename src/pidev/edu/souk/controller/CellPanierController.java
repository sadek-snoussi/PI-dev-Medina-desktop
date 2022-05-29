/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.services.PanierService;

/**
 * FXML Controller class
 *
 * @author Sofienne
 */
public class CellPanierController {

    @FXML
    private HBox cellPanier;
    @FXML
    private ImageView imageId;
    @FXML
    private Label nomProduit;
    @FXML
    private Label prixPoduit;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private Label quantiteProduitLabel;
    @FXML
    private ImageView upQuantite;
    @FXML
    private ImageView downQuantite;
    @FXML
    private Label quantiteDispo;
    @FXML
    private Button supprimerDuPanier;
    @FXML
    private Label Idpanier;
    @FXML
    private Label Iduser;
    @FXML
    private Label Idproduit;
    @FXML
    private Label testQuantite;
    boolean verifprod = false;

    PanierService ps = new PanierService();

    ObservableList<Panier> data;
    @FXML
    private ImageView upQuantite1;
    @FXML
    private ImageView downQuantite1;

    public CellPanierController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CellPanier.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public HBox getCellPanier() {
        return cellPanier;
    }

    public void setInfo(Panier panier) {
        testQuantite.setVisible(false);
        Idpanier.setVisible(false);
        Iduser.setVisible(false);
        Idproduit.setVisible(false);
        quantiteProduitLabel.setVisible(false);
        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + panier.getProduitId().getUrlImgProduit());       //****************
        imageId.setImage(img);
        nomProduit.setText(panier.getProduitId().getNomProduit());
        prixPoduit.setText(String.valueOf(panier.getProduitId().getPrixVenteProduit())+" TND");
        quantiteDispo.setText(String.valueOf(panier.getProduitId().getQteDispoProduit()));
        Idpanier.setText(String.valueOf(panier.getId()));
        quantiteProduit.setText(String.valueOf(panier.getQuantiteProduit()));
        Iduser.setText(String.valueOf(panier.getUserId().getId()));
        Idproduit.setText(String.valueOf(panier.getProduitId().getIdProduit()));
        
        if (panier.getProduitId().getQteDispoProduit() < 1 ){
            testQuantite.setText("Indisponible");
            testQuantite.setStyle("-fx-background-color:#ef0000;");
            testQuantite.setVisible(true);
        }
        else if (panier.getProduitId().getQteDispoProduit() < 10 && panier.getProduitId().getQteDispoProduit() >= 1 ){
            quantiteDispo.setStyle("#fa9b02;");
            testQuantite.setText("Seuls "+panier.getProduitId().getQteDispoProduit()+" restants");
            testQuantite.setStyle("-fx-background-color:#fa9b02;");
            testQuantite.setVisible(true);
        }
    }

    @FXML
    private void alertQuantitieProduit(KeyEvent event) {
        try {
            if (quantiteProduit.getText().trim().length() >= 1) {
                int nbChar = 0;
                for (int i = 0; i < quantiteProduit.getText().trim().length(); i++) {
                    char ch = quantiteProduit.getText().charAt(i);

                    if (Character.isLetter(ch)) {

                        nbChar++;

                    }
                    System.out.println(nbChar);
                }

                if (nbChar == 0) {
                    quantiteProduitLabel.setText("jawek behi");
                    verifprod = true;
                     quantiteProduitLabel.setVisible(true);
                    

                } else {
                    quantiteProduitLabel.setText("numéro invalide  "
                            + " Il existe des caractères");
                    verifprod = false;
                    quantiteProduitLabel.setVisible(true);

                }

            }
            else{
                quantiteProduitLabel.setText("Num insuffisant");
                quantiteProduitLabel.setVisible(true);
                verifprod = false;
            }
            int quantiteProduits = Integer.valueOf(quantiteProduit.getText());
            int quantiteDisponible = Integer.valueOf(quantiteDispo.getText());
            if (quantiteProduits > quantiteDisponible) {
                quantiteProduitLabel.setText("Stock insuffisant !!");
                quantiteProduitLabel.setVisible(true);
            } else {
                quantiteProduitLabel.setVisible(false);
            }
        } catch (NumberFormatException ex) {

        }
        int idPanier = Integer.valueOf(Idpanier.getText());
        int quantite = Integer.valueOf(quantiteProduit.getText());
        ps.quantiteProduitInPanier(quantite, idPanier);

    }

    @FXML
    private void supprimerDuPanier(ActionEvent event) {
        PanierService panierService = new PanierService();
        Panier panier = new Panier();
        int x = Integer.valueOf(Idpanier.getText());
        panierService.supprimerPduPanier(x);
        cellPanier.setVisible(false);
        cellPanier.setPrefHeight(-2000);
        cellPanier.setPrefWidth(-2000);
        /**
         * //********* RELAOD ********************* FXMLLoader loader = new
         * FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListPanier.fxml"));
         * try { Parent root = loader.load(); ListPanierController
         * listPanierController = loader.getController(); //
         * listPanierController.refreshData(90); } catch (IOException ex) {
         * System.out.println(ex.getMessage());
        }*
         */
    }

    @FXML
    private void upQuantite1(MouseEvent event) {
        int x = Integer.valueOf(quantiteProduit.getText()) + 1;
        quantiteProduit.setText(String.valueOf(x));
        int quantiteProduits = Integer.valueOf(quantiteProduit.getText());
        int quantiteDisponible = Integer.valueOf(quantiteDispo.getText());
        int idPanier = Integer.valueOf(Idpanier.getText());
        int quantite = Integer.valueOf(quantiteProduit.getText());
        ps.quantiteProduitInPanier(quantite, idPanier);
        if (quantiteProduits > quantiteDisponible) {
            quantiteProduitLabel.setText("Stock insuffisant !!");
            quantiteProduitLabel.setVisible(true);
        } else {
            quantiteProduitLabel.setVisible(false);
        }

    }

    @FXML
    private void downQuantite2(MouseEvent event) {
        int x = Integer.valueOf(quantiteProduit.getText()) - 1;
        quantiteProduit.setText(String.valueOf(x));
        int quantiteProduits = Integer.valueOf(quantiteProduit.getText());
        int quantiteDisponible = Integer.valueOf(quantiteDispo.getText());
        int idPanier = Integer.valueOf(Idpanier.getText());
        int quantite = Integer.valueOf(quantiteProduit.getText());
        ps.quantiteProduitInPanier(quantite, idPanier);
        if (quantiteProduits > quantiteDisponible) {
            quantiteProduitLabel.setText("Stock insuffisant !!");
            quantiteProduitLabel.setVisible(true);
        } else {
            quantiteProduitLabel.setVisible(false);
        }

    }

}
