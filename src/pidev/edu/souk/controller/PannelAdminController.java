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
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class PannelAdminController implements Initializable {

    @FXML
    private TitledPane GestionPart;
    @FXML
    private Button ListClient;
    @FXML
    private TitledPane GestionEvent;
    @FXML
    private Button btnStand;
    @FXML
    private Button btnEvent;
    @FXML
    private Button ListeInscrit;
    @FXML
    private TitledPane GestionGallerie;
    @FXML
    private Button AjoutGallerie;
    @FXML
    private Button ConsulterGallerie;
    @FXML
    private TitledPane GestionGuide;
    @FXML
    private Button AjoutGuide;
    @FXML
    private TitledPane GestionBonplan;
    @FXML
    private Button AjoutBonplan;
    @FXML
    private Button ConsulterBonplan;
    @FXML
    private TitledPane GestionCategorie;
    @FXML
    private Button ListCategorie;
    @FXML
    private TitledPane Validation;
    @FXML
    private Button ValdPartenariat;
    @FXML
    private Button ValCommande;
    @FXML
    private Button ValVideos;
    @FXML
    private Button btn_validerProduits;
    @FXML
    private AnchorPane container_admin;
    @FXML
    private Button ListePartner;
    @FXML
    private Button ListeGuide;

    public AnchorPane getContainer_admin() {
        return container_admin;
    }

    public void setContainer_admin(AnchorPane container_admin) {
        this.container_admin = container_admin;
    }

    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        GestionPart.setVisible(true);
        
        GestionEvent.setVisible(true);
     
        GestionGallerie.setVisible(true);

        GestionGuide.setVisible(true);
    
        GestionBonplan.setVisible(true);
       
        GestionCategorie.setVisible(true);
     
        Validation.setVisible(true);
        
        container_admin.setVisible(true);
      
    }    

    //-----------------------------------------------------------------------------------
    //------------------------------------Validation-------------------------------------
    //-----------------------------------------------------------------------------------
    
    
    @FXML
    private void ValiderProduits(ActionEvent event) throws IOException {
        
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ValiderProduit.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ValiderPartenariat(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/validationPartenariat.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ValiderCommandes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ValiderCommande.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ValiderVideos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/afficheVideoNonValid.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions Categories----------------------------
    //-----------------------------------------------------------------------------------
    
    
    @FXML
    private void Liste_categories(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListCategory.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }
    
    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions Partenaires---------------------------
    //-----------------------------------------------------------------------------------

    @FXML
    private void ListePartner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/listPartenairesAdmin.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ListeClient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/listClientsAdmin.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    
    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions de Galleries--------------------------
    //-----------------------------------------------------------------------------------
    
    @FXML
    private void AjoutGallerie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddGallerie.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ConsulterGallerie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListGallerie.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    
    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions des Guides----------------------------
    //-----------------------------------------------------------------------------------
    
    
    
    @FXML
    private void AjoutGuide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CrudGuide.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ListeGuide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AfficheGuideAdmin.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    
    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions BonPlans------------------------------
    //-----------------------------------------------------------------------------------
    
    
    
    @FXML
    private void AjoutBonplan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddBonPlan.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void ConsulterBonplan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListBonPlan.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    
    //-----------------------------------------------------------------------------------
    //------------------------------------Gestions Events--------------------------------
    //-----------------------------------------------------------------------------------

    @FXML
    private void gest_stand(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionStand2FXML.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void gest_event(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionEvent2FXML.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void gest_inscription(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListInscription.fxml"));
            Parent root = loader.load();       
            container_admin.getChildren().setAll(root);
    }

    @FXML
    private void accueilAdmin(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AccueilAdmin.fxml"));       
            Parent root = loader.load();
            container_admin.getChildren().setAll(root);
        } catch (IOException ex) {
          
        }
        
    }

    @FXML
    private void Logout(ActionEvent event) {
        
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
            Parent root = loader.load();
            SingninController sc = loader.getController();

            GestionGuide.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
