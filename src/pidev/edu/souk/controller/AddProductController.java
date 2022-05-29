/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;

import java.io.IOException;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Random;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.Statistiques;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.AdminService;
import pidev.edu.souk.services.GradeService;
import pidev.edu.souk.services.ProductService;
import pidev.edu.souk.services.serviceCryptage;
import pidev.edu.souk.utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AddProductController implements Initializable {

    @FXML
    private ComboBox<String> categ_prod;
    @FXML
    private TextField nom_prod;
    @FXML
    private TextField ref_prod;
    @FXML
    private TextField matiere_prod;
    @FXML
    private TextField prixBase_prod;
    @FXML
    private TextField prixVente_prod;
    @FXML
    private DatePicker DateExpiration;
    @FXML
    private TextField Qte_dispo_prod;
    @FXML
    private Label filenale;
    @FXML
    private Button add_prod_btn;

    File file;
    @FXML
    private Label lab_ref;
    @FXML
    private Label lab_prixBase;
    @FXML
    private Label lab_prixVente;
    @FXML
    private Label lab_date;
    @FXML
    private Label lab_Qte;
    @FXML
    private Label lab_cat;

    java.util.Date date_util = new java.util.Date();
    java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
    @FXML
    private Label Nullable_warn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String req = "SELECT * FROM CATEGORIE";
        Statement st;
        try {

            st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            ObservableList<String> comboCat = FXCollections.observableArrayList();

            while (rs.next()) {
                comboCat.add(rs.getString("nomcategorie"));
            }

            categ_prod.setItems(comboCat);

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        




    }

//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
    @FXML
    private void chooseFile(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open Resource File");
        Window stage = null;
        file = filechooser.showOpenDialog(stage);

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        if(file!=null){
        filenale.setText(file.getName());
        filenale.setVisible(true);
        }
        else{
        filenale.setText("Il Faut Choisir Une Photo Du Produit.");
        filenale.setVisible(true);
        }

        
        

    }

//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
//        String imgName = serviceCryptage.cryptWithMD5(filenale.getText());
//        String imgFullName=imgName+"."+filenale.getText().substring(filenale.getText().length()-4, filenale.getText().length());
    //System.out.println(imgName);
    @FXML
    private void add_product(ActionEvent event) throws SQLException, IOException {

        String requete = "SELECT * FROM CATEGORIE WHERE NOMCATEGORIE='"
                + categ_prod.getValue() + "'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet result = st.executeQuery(requete);
        Categorie cat = new Categorie();

        while (result.next()) {
            cat.setIdCategorie(result.getInt(1));
            cat.setNomCategorie(result.getString(2));
            cat.setTypeCategorie(result.getString(3));

        }

        if (controle_AntiNull()==true 
                && Controle_reference() == true 
                && Controle_Cat() == true 
                && Controle_prixBase() == true
                && Controle_prixVente() == true 
                && Controle_quantite() == true 
                && Controle_Date() == true 
                ) {

            java.sql.Date date = java.sql.Date.valueOf(DateExpiration.getValue());
            Produit p = new Produit();

            p.setNomProduit(nom_prod.getText());

            p.setMatiereProduit(matiere_prod.getText());

            p.setPrixBaseProduit(Double.parseDouble(prixBase_prod.getText()));

            p.setPrixVenteProduit(Double.parseDouble(prixVente_prod.getText()));

            p.setDateExpirationProduit(date);

            p.setReferenceProd(ref_prod.getText());

            p.setQteDispoProduit(Integer.parseInt(Qte_dispo_prod.getText()));
            
            User u =new User();
            u.setId(SingninController.userIden);
            p.setIdUser(u);
            p.setIdCategorie(cat);

            String imgName = serviceCryptage.cryptWithMD5(filenale.getText());
            String imgFullName = imgName + filenale.getText().substring(filenale.getText().length() - 4, filenale.getText().length());
            Random rdm = new Random();

            String finalImgName = rdm.nextInt(999999999) + imgFullName;

            p.setUrlImgProduit(finalImgName);

            ProductService prodServ = new ProductService();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            prodServ.saveFile(file, finalImgName);
            System.out.println("******************************************");
            prodServ.add(p);
            
            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getNomProduit()+" a été Ajouté avec succés");
            alert.show();
            //***********************************

        //***********************************Gestion Grade***********************************///
            AdminService gs = new AdminService();
            GradeService gs1 = new GradeService();
            User user = gs.finUserById(SingninController.userIden);

            ArrayList<Produit> produits = gs.listProduitsByUser(user);
            ArrayList<Videodiy> videos = gs.listVideosByUser(user);
            ArrayList<Statistiques> stats = gs1.listGrades();

            int totalqteProdVendu = 0;
            int totalvid = 0;

            for (Produit prod : produits) {
                totalqteProdVendu += prod.getQteVenduProduit();

            }
            for (Videodiy vid : videos) {
                totalvid += 1;
            }
            System.out.println("grade : " + user.getGradePro());
            System.out.println("gradeStat" + stats.get(0).getGrade());
            System.out.println("nbrePStat" + stats.get(1).getNbproduitsVendu());
            System.out.println("nbreVStat" + stats.get(1).getNbvideoPost());
            System.out.println("total prod" + totalqteProdVendu);
            System.out.println("total video" + totalvid);
            System.out.println("compraison totprodvendu and stat plafond " + (totalqteProdVendu >= stats.get(1).getNbproduitsVendu()));
            System.out.println("compraison totvid and stat plafond " + (totalvid >= stats.get(1).getNbvideoPost()));
            if ((stats.get(0).getGrade().equals(user.getGradePro())) && (totalqteProdVendu >= stats.get(1).getNbproduitsVendu()) && (totalvid >= stats.get(1).getNbvideoPost())) {

                System.out.println("silverrrrrrrrrrrrrrrrrrrrrr");
                TrayNotification tray = new TrayNotification("Successfully",
                        "Vous Pouvez Maintenant migrer vers  le Grade Gold", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.SLIDE);
                tray.showAndDismiss(Duration.seconds(10));

                Image img;
                img = new Image("file:C:\\Users\\amalb\\Desktop\\amal_ouma\\Medina\\src\\pidev\\edu\\souk\\Images\\souk.png");
                tray.setTrayIcon(img);

            } else if ((stats.get(1).getGrade().equals(user.getGradePro())) && (totalqteProdVendu <= stats.get(2).getNbproduitsVendu()) && (totalvid <= stats.get(2).getNbvideoPost())) {

                System.out.println("goldddddddddddddddddd");
                TrayNotification tray = new TrayNotification("Successfully",
                        "Vous Pouvez Maintenant migrer vers  le Grade Platinuim ", NotificationType.SUCCESS);
                tray.setAnimationType(AnimationType.SLIDE);
                tray.showAndDismiss(Duration.seconds(10));

            }

            System.out.println("platinuimmmmmmmmmmmm");
        } else {

            Nullable_warn.setVisible(true);
        }

    }

    @FXML
    private boolean Controle_reference() {
        Boolean bool = false;

        if (ref_prod.getText().trim().length() > 1) {

            System.out.println(ref_prod.getText().substring(0, 1) + "0000000000" + ref_prod.getText().substring(1, 2));

            if (ref_prod.getText().substring(0, 1).equals("R") && ref_prod.getText().substring(1, 2).equals("F")) {

                lab_ref.setVisible(false);
                return true;
            } else {
                lab_ref.setVisible(true);
                lab_ref.setText("la Référence doit Commencer par 'RF'.");
                return false;
            }
        }
        return bool;
    }

    private boolean Controle_Cat() {

        if (categ_prod.getValue() != null) {
            lab_cat.setVisible(false);
            return true;
        } else {
            lab_cat.setText("Il faut Choisir une Catégorie.");
            lab_cat.setVisible(true);
            return false;
        }
    }

    @FXML
    private boolean Controle_prixBase() {

        lab_prixBase.setVisible(false);

        for (int i = 0; i < prixBase_prod.getText().length(); i++) {
            if (!Character.isDigit(prixBase_prod.getText().charAt(i))) {
                lab_prixBase.setText("Il faut Préciser une Valeur Numérique.");
                lab_prixBase.setVisible(true);
                return false;
            }
        }
        return true;
    }

    @FXML
    private boolean Controle_prixVente() {

        lab_prixVente.setVisible(false);

        for (int i = 0; i < prixVente_prod.getText().length(); i++) {
            if (!Character.isDigit(prixVente_prod.getText().charAt(i))) {
                lab_prixVente.setText("Il faut Préciser une Valeur Numérique.");
                lab_prixVente.setVisible(true);
                return false;
            }
        }
        return true;

    }

    @FXML
    private boolean Controle_quantite() {

        lab_Qte.setVisible(false);

        for (int i = 0; i < Qte_dispo_prod.getText().length(); i++) {
            if (!Character.isDigit(Qte_dispo_prod.getText().charAt(i))) {
                lab_Qte.setText("Il faut Préciser une Valeur Numérique.");
                lab_Qte.setVisible(true);
                return false;
            }
        }

        return true;
    }

    @FXML
    private Boolean Controle_Date() {

        lab_date.setVisible(false);
        //System.out.println(d.toString()+"*********"+date_sql.toString());
        if (DateExpiration.getValue() == null ||
            java.sql.Date.valueOf(DateExpiration.getValue()).before(date_sql)) {

            lab_date.setVisible(true);
            lab_date.setText("Il faut Préciser une Date Supérieur.");
            return false;
        }

        return true;

    }

    //*************************************************************************
    //************************************************************************
    

    private Boolean controle_AntiNull(){
        
                if(DateExpiration.getValue() != null
                && prixVente_prod.getText()!=null
                && prixBase_prod.getText()!=null
                && Qte_dispo_prod.getText()!=null
                && categ_prod.getValue()!=null
                && matiere_prod.getText()!=null
                &&nom_prod.getText()!=null
                && ref_prod.getText()!=null
                && filenale.getText()!=null){
            
            
            return true;
        }
        
        
        return false;
    }

}
