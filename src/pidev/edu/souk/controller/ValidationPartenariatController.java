/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.AdminService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ValidationPartenariatController implements Initializable {

    @FXML
    private TableView<User> validpart;
    @FXML
    private MenuItem valider;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> specialite;
    @FXML
    private MenuItem supprimer;
    @FXML
    private Button majGrade;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         nom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
         prenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
         tel.setCellValueFactory(new PropertyValueFactory<>("telBureauPro"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
         specialite.setCellValueFactory(new PropertyValueFactory<>("specialitePart"));
         role.setCellValueFactory(new PropertyValueFactory<>("typeUser"));
        // action.setCellFactory(cellFactory);
        validpart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          loadUsers();
    }  
       private  void loadUsers()
    {
         AdminService as = new AdminService();
        ArrayList<User>PartenairesNonValides=as.listPartenaireNonValidesAdmin();
         ObservableList observableList = FXCollections.observableArrayList(PartenairesNonValides);
         validpart.setItems(observableList);
    }


    @FXML
    private void validerPartenariat(ActionEvent event) {
        List <User>PartenairesNonValides = validpart.getSelectionModel().getSelectedItems();
        //System.out.println(PartenairesNonValides);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation Dialog");
        alert.setHeaderText(null);
        
        alert.setContentText("etes vous sure de valider cette demande de partenariat? ");
        Optional<ButtonType> action2 =alert.showAndWait();
        AdminService as=new AdminService();
        if(action2.get()==ButtonType.OK)
        {
            as.validerDemandePartenariat(PartenairesNonValides);
            
        }
        loadUsers();
        validpart.refresh();
        
    }

    @FXML
    private void supprimerDemandePartenariat(ActionEvent event) {
        
         List <User>users = validpart.getSelectionModel().getSelectedItems();
        System.out.println(users);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation Dialog");
        alert.setHeaderText(null);
        
        alert.setContentText("etes vous sure de supprimer cette demande de partenariat? ");
        Optional<ButtonType> action2 =alert.showAndWait();
        AdminService as=new AdminService();
        if(action2.get()==ButtonType.OK)
        {
            as.supprimerDemandePartenariat(users);
            
        }
        loadUsers();
         validpart.refresh();
        
    }

    @FXML
    private void MAJGrades(ActionEvent event) 
    {
        try {
         
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));
            Parent root = loader.load();
            PannelAdminController pannelAdmin = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/MAJ_Grade.fxml"));
            Parent root2 = loader2.load();
            pannelAdmin.getContainer_admin().getChildren().setAll(root2);
            label.getScene().setRoot(root);
        } catch (IOException ex) {
          
        }
    }
    
}
