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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pidev.edu.souk.entities.Stand;
import pidev.edu.souk.services.StandService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class GestionStand2FXMLController implements Initializable {
    
    @FXML
    private VBox vboximage;
    @FXML
    private HBox hboxbt;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btsupprimer;
    private Label labelmodifier;
    @FXML
    private Button enregistrer;
    @FXML
    private TableView<Stand> stands;
    @FXML
    private TableColumn<Stand, Double> superficie;
    @FXML
    private TableColumn<Stand, String> emplacement;
    @FXML
    private TableColumn<Stand, Boolean> etat;
    @FXML
    private TableColumn<Stand, Double> prix;
    @FXML
    private TableColumn<Stand, String> couleur;
    @FXML
    private Button ajout;
    @FXML
    private TextField superficie1;
    @FXML
    private Label lblsup;
    @FXML
    private TextField emplacement1;
    @FXML
    private Label lblEmp;
    @FXML
    private ColorPicker couleur1;
    @FXML
    private Label lblcouleur;
    @FXML
    private TextField pr1;
    @FXML
    private Label lblprix;
    @FXML
    private Button btajout;
    @FXML
    private AnchorPane idajout;
    @FXML
    private DialogPane idgest;
    @FXML
    private VBox idboxmodif;
    @FXML
    private Label lblmodif22;
    
    public TableColumn<Stand, Boolean> getEtat() {
        return etat;
    }
    
    public void setEtat(TableColumn<Stand, Boolean> etat) {
        this.etat = etat;
    }
    
    Boolean verifsuperficie = false;
    Boolean verifiEmp = false;
    Boolean verifCouleur = false;
    Boolean verifiPrix = false;
    
    StandService standservice = new StandService();
    Boolean verifemp = true;
    Boolean verifprix = true;
    @FXML
    private TextField emp;
    @FXML
    private TextField pr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStand();
        superficie.setCellValueFactory(new PropertyValueFactory<>("superficieStand"));
        emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacementStand"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat1"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //  labelmodifier.setVisible(false);
        emp.setVisible(false);
        pr.setVisible(false);
        idajout.setVisible(false);
        enregistrer.setVisible(false);
        lblmodif22.setVisible(false);
        
        // enregistrer.setVisible(false);

        // TODO
    }
    
    public void loadStand() {
        StandService s = new StandService();
        ArrayList<Stand> stand = s.listerStand();
        ObservableList observableList = FXCollections.observableArrayList(stand);
        stands.setItems(observableList);
        
    }
    
    @FXML
    private void modifierStand(ActionEvent event) {
        Stand s = new Stand();
        
        s = stands.getSelectionModel().getSelectedItem();
        
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un Stand");
            alert.show();
        } else {
            
            System.out.println(s);
            emp.setVisible(true);
            pr.setVisible(true);
//            labelmodifier.setVisible(true);
            enregistrer.setVisible(true);
            emp.setText(s.getEmplacementStand());
            pr.setText(s.getPrix().toString());
          //  idboxmodif.setVisible(true);
            
        }
        
    }
    
    @FXML
    private void supprimerStand(ActionEvent event) {
        Stand s = new Stand();
        s = stands.getSelectionModel().getSelectedItem();
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un stand");
            alert.show();
            
        } else {
            standservice.deleteStand(s);
            
             TrayNotification tray = new TrayNotification("Successfully", "Stand N°" + s.getNumStand() + " Supprimer avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
            
            loadStand();
            
        }
    }
    
    @FXML
    private void SaveModification(ActionEvent event) {
        Stand s = new Stand();
        s = stands.getSelectionModel().getSelectedItem();
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un stand à modifier");
            alert.show();
            
        } else {
            if (verifemp == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez corriger l'emplacement du stand");
                alert.show();
                
            } else if (verifprix == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez saisir le prix");
                alert.show();
            } else {
                StandService sd = new StandService();
                s.setEmplacementStand(emp.getText());
                s.setPrix(Double.parseDouble(pr.getText()));
                sd.UpdateStand(s, s.getNumStand());
                emp.setVisible(false);
                pr.setVisible(false);
                lblmodif22.setVisible(false);
                enregistrer.setVisible(false);
                stands.getItems().clear();
                stands.getItems().addAll(sd.listerStand());
                
                System.out.println("Stand modifier");
                
              
             TrayNotification tray = new TrayNotification("Successfully", "Stand N° " + s.getNumStand() + " Modification Effectuée avec Succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
            }
        }
        
    }
    
    @FXML
    private void ajouterStand(ActionEvent event) {
        vboximage.setVisible(false);
        stands.setVisible(false);
        idajout.setVisible(true);
        idgest.setVisible(false);
        
    }
    
    @FXML
    private void controlEmp(KeyEvent event) {
        
        if (emplacement.getText().trim().equals("")) {
            
            verifemp = false;
            
        } else {
            
            verifemp = true;
        }
    }
    
    @FXML
    private void controlPrix(KeyEvent event) {
        System.out.println(pr.getText().trim());
        if (pr.getText().trim().length() > 0) {
            int nbChar = 0;
            for (int i = 1; i < pr.getText().trim().length(); i++) {
                char ch = pr.getText().charAt(i);
                
                if (Character.isLetter(ch)) {
                    
                    nbChar++;
                    
                }
                System.out.println(nbChar);
            }
            
            if (nbChar == 0) {
                lblprix.setText("Prix valide");
                verifprix = true;
            } else {
                lblprix.setText("Prix invalide  "
                        + " Il exist des char ");
                verifprix = false;
                
            }
            
        } else {
            lblprix.setText("Il faut  au moin 1 chiffre");
            verifiPrix = false;
        }
        
    }
    
    @FXML
    private void controlsurperficie(KeyEvent event) {
        System.out.println(superficie1.getText().trim());
        if (superficie1.getText().trim().length() > 0) {
            int nbChar = 0;
            for (int i = 1; i < superficie1.getText().trim().length(); i++) {
                char ch = superficie1.getText().charAt(i);
                
                if (Character.isLetter(ch)) {
                    
                    nbChar++;
                    
                }
                System.out.println(nbChar);
            }
            
            if (nbChar == 0) {
                lblsup.setText("superficie valide");
                verifsuperficie = true;
            } else {
                lblsup.setText("invalide number "
                        + " Il exist des char");
                verifsuperficie = false;
                
            }
            
        } else {
            lblsup.setText("Il faut  au moin 2 chiffres");
            verifsuperficie = false;
        }
        
    }
    
    @FXML
    private void controlcouleur(ActionEvent event) {
        if (couleur1.getValue().toString().trim().equals("")) {
            
            verifCouleur = false;
            
        } else {
            
            verifCouleur = true;
        }
    }
    
    @FXML
    private void AjouterStand(ActionEvent event) {
        
        if (verifsuperficie == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez préciser superficie du stand");
            alert.show();
            
        } else if (verifiEmp == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez préciser l'emplacement du stand ");
            alert.show();
            
        } else if (verifCouleur == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez saisir la couleur du stand");
            alert.show();
            
        } else if (verifiPrix == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez préciser le prix du stand");
            alert.show();
            
        } else {
            
            try {
                Stand stand = new Stand();
                stand.setSuperficieStand(Double.parseDouble(superficie1.getText()));
                stand.setEmplacementStand(emplacement1.getText());
                ///stand.setCouleur(couleur.getValue().toString());
                stand.setCouleur(couleur1.getValue().toString());
                stand.setPrix(Double.parseDouble(pr1.getText()));
                stand.setEtat(false);
                
                StandService servicestand = new StandService();
                servicestand.addStand(stand);
                
                 TrayNotification tray = new TrayNotification("Successfully", "Stand Ajout Effectuée avec Succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
//                
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionStand2FXML.fxml"));
//                
//                Parent root = loader.load();
//                GestionStand2FXMLController addstand = loader.getController();
//                superficie1.getScene().setRoot(root);




                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));

            System.out.println(loader.getCharset());
            Parent root = loader.load();

            PannelAdminController infoPart = loader.getController();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionStand2FXML.fxml"));

            Parent root2 = loader2.load();
            GestionStand2FXMLController infoPart2 = loader2.getController();

            infoPart.getContainer_admin().getChildren().setAll(root2);

            lblEmp.getScene().setRoot(root);

            } catch (IOException ex) {
                
            }
        }
        
    }
    
    @FXML
    private void controlempla(KeyEvent event) {
        
        if (emplacement1.getText().trim().equals("")) {
            
            verifiEmp = false;
            
        } else {
            
            verifiEmp = true;
        }
    }
    
    @FXML
    private void controlprice(KeyEvent event) {
        
        System.out.println(pr1.getText().trim());
        if (pr1.getText().trim().length() > 0) {
            int nbChar = 0;
            for (int i = 1; i < pr1.getText().trim().length(); i++) {
                char ch = pr1.getText().charAt(i);
                
                if (Character.isLetter(ch)) {
                    
                    nbChar++;
                    
                }
                System.out.println(nbChar);
            }
            
            if (nbChar == 0) {
                lblprix.setText("Prix valide");
                verifiPrix = true;
            } else {
                lblprix.setText("Prix invalide  "
                        + " Il exist des char ");
                verifiPrix = false;
                
            }
            
        } else {
            lblprix.setText("Il faut  au moin 1 chiffre");
            verifiPrix = false;
        }
        
    }
}
