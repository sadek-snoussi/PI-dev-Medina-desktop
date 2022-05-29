/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import pidev.edu.souk.entities.Commande;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.CommandeService;
import pidev.edu.souk.services.ConcatPDFFiles;
import pidev.edu.souk.services.PanierService;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.services.Pdf;
import pidev.edu.souk.services.commandeCellListView;
import pidev.edu.souk.services.panierCellListView;

/**
 * FXML Controller class
 *
 * @author Sofienne
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField nomCommande;
    @FXML
    private TextField prenomCommande;
    @FXML
    private Button ajouterCommande;
    @FXML
    private ListView<Panier> lisPanier;
    @FXML
    private Label prixTotale;
    @FXML
    private Label promo;
    @FXML
    private Label prixFinale;
    
    
    PanierService ps = new PanierService();
    CommandeService cmds = new CommandeService();
    private ObservableList<Panier> data = FXCollections.observableArrayList();
    @FXML
    private TextField emailCommande;
    @FXML
    private TextField paysCommande;
    @FXML
    private TextField telephoneCommande;
    @FXML
    private TextField gouvernoratCommande;
    @FXML
    private TextField villeCommande;
    @FXML
    private TextField adresseCommande;
    @FXML
    private TextField codePostaleCommande;
    @FXML
    private Button annulerCommande;
    @FXML
    private Label idPromoLabel;
    @FXML
    private ImageView tickNom;
    @FXML
    private ImageView tickTelephone;
    @FXML
    private ImageView tickVille;
    @FXML
    private ImageView tickAddresse;
    @FXML
    private ImageView tickPays;
    @FXML
    private ImageView tickPrenom;
    @FXML
    private ImageView tickEmail;
    @FXML
    private ImageView tickGouvernorat;
    @FXML
    private ImageView tickCodePostale;
    Boolean vmail = false;
    static double prixFinal=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        nomCommande.setVisible(true);
        prenomCommande.setVisible(true);
        loadData();
        double prixTotales=cmds.calculerTotalCommande(SingninController.userIden);
        
          PartenaireService ps1 =new PartenaireService();
       
          int promotion=ps1.gestionPromotionCommande(SingninController.userIden);
        
        promo.setText(String.valueOf(promotion));
        prixFinal=prixTotales-((prixTotales*promotion)/100);
        
        prixFinale.setText(String.valueOf(prixFinal));
        
        System.out.println(prixTotales);
       prixTotale.setText(String.valueOf(prixTotales));
       prixTotale.setVisible(true);
    }    
    
     private void loadData(){
          tickEmail.setVisible(false);
        //System.out.println("apres click "+iduser.getText());
        //int x = Integer.valueOf(iduser.getText());        
        data.addAll(ps.selectPanierById(SingninController.userIden,0));
        lisPanier.setItems(data);
        lisPanier.setCellFactory((ListView<Panier> param)-> new commandeCellListView());
    }  

    @FXML
    private void ajouterCommande(ActionEvent event) {
        CommandeService crud = new CommandeService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Commande");
        alert.setHeaderText("Commande ajouter avec succes");
        alert.showAndWait();
        System.out.println("le prix par la methode "+cmds.calculerTotalCommande(SingninController.userIden));
        double prixTotales= cmds.calculerTotalCommande(SingninController.userIden);
        
        Pdf.TotaleCommande=prixFinal;
        Pdf pdf = new Pdf();
        pdf.createpdf();
        crud.setFlag(SingninController.userIden);
        crud.ModifStock(SingninController.userIden);
        ArrayList<Panier> list = new ArrayList<Panier>();
        list=crud.selectPanierWithFlag(SingninController.userIden);
        for (int i = 0 ; i < list.size(); i++){
            java.util.Date date = new java.util.Date();
            java.sql.Date dateSql = new java.sql.Date(date.getTime());
                Commande commande = new Commande();
                User u = new User();
                Panier panier = new Panier();
                u.setId(SingninController.userIden);
                panier=list.get(i);
                System.out.println(panier.getId());
                commande.setUserId(u);
                commande.setPanierId(panier);
                commande.setDateCommande(dateSql);
                commande.setTotalPrixCommande(prixTotales);
                commande.setNom(nomCommande.getText());
                commande.setPrenom(prenomCommande.getText());
                commande.setEmail(emailCommande.getText());
                commande.setTel(telephoneCommande.getText());
                commande.setPays(paysCommande.getText());
                commande.setGouvernorat(gouvernoratCommande.getText());
                commande.setVille(villeCommande.getText());
                commande.setAdresse(adresseCommande.getText());
                commande.setCodepostale(codePostaleCommande.getText());
                crud.ajouterCommande(commande);
        }
        
        
    }

    @FXML
    private void annulerCommande(ActionEvent event) {
        ConcatPDFFiles concatPdf = new ConcatPDFFiles();
        concatPdf.concat();
    }

    @FXML
    private void nomVerif(ActionEvent event) {
    }

    @FXML
    private void prenomVerif(ActionEvent event) {
    }

    

    @FXML
    private void paysVerif(ActionEvent event) {
    }

    @FXML
    private void telephoneVerif(ActionEvent event) {
    }

    @FXML
    private void gouvernoratVerif(ActionEvent event) {
    }

    @FXML
    private void villeVerif(ActionEvent event) {
    }

    @FXML
    private void addresseVerif(ActionEvent event) {
    }

    @FXML
    private void codePostaleVerif(ActionEvent event) {
    }

    @FXML
    private void emailVerif(KeyEvent event) {
        
//        if (emailCommande.getText().trim().equals("")) {
//
//            vmail = false;
//        } else {
//
//            //{ici longeur  }
//            //debut ^
//            //fin $
//            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//            Pattern pattern = Pattern.compile(email_pattern);
//            Matcher matcher = pattern.matcher(emailCommande.getText());
//
//            if (matcher.matches()) {       //if   matcher ne contient pas la format   
//                tickEmail.setVisible(true);
//                vmail = true;
//
//            } else {
//                tickEmail.setVisible(true);
//                // JOptionPane.showMessageDialog(null, "Email Format invalide");
//                vmail = false;
//
//            }
//        }
    }
    
}
