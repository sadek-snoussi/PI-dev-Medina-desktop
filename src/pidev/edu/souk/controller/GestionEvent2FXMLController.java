/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.text.DateFormatter;

import pidev.edu.souk.entities.Event;
import pidev.edu.souk.gui.InfoPartController;
import pidev.edu.souk.services.EvenementService;
import pidev.edu.souk.services.serviceCryptage;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class GestionEvent2FXMLController implements Initializable {

    @FXML
    private TableView<Event> events;
    @FXML
    private TableColumn<Event, String> nomevent;
    @FXML
    private TableColumn<Event, Date> datevent;
    @FXML
    private TableColumn<Event, String> lieux;
    @FXML
    private TableColumn<Event, Integer> nbplaces;
    @FXML
    private TableColumn<Event, Integer> nbstand;
    @FXML
    private TableColumn<Event, String> description;

    private VBox VboxEdit;

    @FXML
    private ImageView imgEvent;
    @FXML
    private Button btmodifier;
    @FXML
    private Button btsupprimer;

    Event E = new Event();

    EvenementService EventService = new EvenementService();
    @FXML
    private VBox vboximage;
    @FXML
    private HBox hboxbt;
    @FXML
    private TextField desc;
    @FXML
    private Button enregistrer;
    @FXML
    private TextField nom;
    @FXML
    private Label labelmodifier;
    @FXML
    private Button ajout;

    Boolean verifnom = true;
    Boolean verifdesc = true;
    Boolean verifdate = true;
    @FXML
    private DatePicker datemodif;

    java.util.Date date_util = new java.util.Date();
    java.sql.Date datesql = new java.sql.Date(date_util.getTime());
    private AnchorPane idpane;
    @FXML
    private DialogPane idialog;
    @FXML
    private TextField nomE;
    @FXML
    private Label verifevent;
    @FXML
    private TextField objectifE;
    @FXML
    private Label verifob;
    @FXML
    private TextField descriptionE;
    @FXML
    private Label verifds;
    @FXML
    private TextField nbplaces1;
    @FXML
    private Label verifnbp;
    @FXML
    private TextField nbstand1;
    @FXML
    private Label verifnbs;
    @FXML
    private DatePicker datevent1;
    @FXML
    private Label verifdt;
    @FXML
    private ComboBox<String> lieuxE;
    @FXML
    private Label verifL;
    @FXML
    private Label filenale;
    @FXML
    private Button image;
    @FXML
    private Label veriffile;
    @FXML
    private Button btajout;
    @FXML
    private Button btannuler;
    @FXML
    private AnchorPane idanchor;

    File file;

    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");

    Boolean verifinom = false;
    Boolean verifobjectif = false;
    Boolean verifidesc = false;
    Boolean verifnbplace = false;
    Boolean verifnbstand = false;
    Boolean verifnblieu = false;
    Boolean verifidate = false;
    Boolean verifimage = false;

    java.util.Date date_util2 = new java.util.Date();
    java.sql.Date datesql2 = new java.sql.Date(date_util.getTime());
    @FXML
    private Label idlist;
    @FXML
    private AnchorPane idajouter;

    public AnchorPane getIdpane() {
        return idpane;
    }

    public void setIdpane(AnchorPane idpane) {
        this.idpane = idpane;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setVisible(false);
        desc.setVisible(false);
        labelmodifier.setVisible(false);
        enregistrer.setVisible(false);
        datemodif.setVisible(false);

        nomevent.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        datevent.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        lieux.setCellValueFactory(new PropertyValueFactory<>("lieux"));

        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbrePlace"));
        nbstand.setCellValueFactory(new PropertyValueFactory<>("nbreStand"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptionevent"));

///        idialog.setVisible(false);
        idajouter.setVisible(false);
        loadEvent();

    }

    public void loadEvent() {

        ArrayList<Event> Events = EventService.listerEvenement();

        ObservableList observableList = FXCollections.observableArrayList(Events);
        events.setItems(observableList);

    }

    @FXML
    private void ssss(MouseEvent event) {

        Event SG = events.getItems().get(events.getSelectionModel().getSelectedIndex());
        E = SG;

        System.out.println(E.getUrlafficheevent());

        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\img\\" + E.getUrlafficheevent());

        imgEvent.setImage(img);

        imgEvent.setVisible(true);
        // DoubleProperty height = imgEvent.fitHeightProperty();
        // DoubleProperty width = imgEvent.fitWidthProperty();

        imgEvent.setFitWidth(300);
        imgEvent.setFitHeight(400);
        imgEvent.setPreserveRatio(true);
        imgEvent.setSmooth(true);
        imgEvent.setCache(true);

        VboxEdit.getChildren().add(imgEvent);

    }

    @FXML
    private void modifierE(ActionEvent event) {

        Event e = new Event();

        e = events.getSelectionModel().getSelectedItem();

        if (e == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un evenement");
            alert.show();

        } else {

            nom.setText(E.getNomEvent());
            desc.setText(E.getDescriptionevent());

            nom.setVisible(true);
            desc.setVisible(true);
            datemodif.setVisible(true);

            labelmodifier.setVisible(true);
            enregistrer.setVisible(true);
        }
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {

        Event e = new Event();

        e = events.getSelectionModel().getSelectedItem();
        if (e == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un evenement");
            alert.show();

        } else {
            EventService.deleteEvenement(e);
            loadEvent();
            System.out.println("evenement Supprimer !!");
            TrayNotification tray = new TrayNotification("Successfully", "Evenement " + e.getNomEvent() + " Modification Effectuée avec Succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
        }
    }

    @FXML
    private void SaveModification(ActionEvent event) {

        Event e = new Event();
        e = events.getSelectionModel().getSelectedItem();

        if (e == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un evenement à modifier");
            alert.show();

        } else {
            if (verifnom == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir le nom");
                alert.show();

            } else if (verifdesc == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez saisir la description");
                alert.show();

            } else if (verifdate == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez modifier la date");
                alert.show();

            } else {
                System.out.println("******"+verifdate);
                EvenementService es = new EvenementService();
                e.setNomEvent(nom.getText());
                e.setDescriptionevent(desc.getText());
                java.sql.Date date3 = java.sql.Date.valueOf(datemodif.getValue());
                e.setDateEvent(date3);

                es.UpdateEvenement(e, e.getIdEvent());
                nom.setVisible(false);
                desc.setVisible(false);
                labelmodifier.setVisible(false);
                datemodif.setVisible(false);
                enregistrer.setVisible(false);

                events.getItems().clear();
                events.getItems().addAll(es.listerEvenement());
                System.out.println("Evenement modifier");

                TrayNotification tray = new TrayNotification("Successfully", "Evenement  " + e.getNomEvent() + " Modification Effectuée avec Succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(10));
            }

        }

    }

    ////////////////////////////////// Ajouter Event//////////////////////////////////
    @FXML
    private void Ajouter(ActionEvent event) {
        
        idanchor.setVisible(false);
        lieuxE.setItems(listGouv);

        idlist.setVisible(false);
        idajouter.setVisible(true);
        
        
        
        
        
                

    }

    @FXML
    private void controldate(ActionEvent event) {

        java.sql.Date date = java.sql.Date.valueOf(datemodif.getValue());
        if (datemodif.getValue().toString().trim().equals("") || (date.compareTo(datesql) == -1)) {

            verifdate = false;

        } else {

            verifdate = true;
        }
        System.out.println("*****verifdatecontrol***"+verifdate);
    }

    @FXML
    private void controlNom(KeyEvent event) {
        if (nom.getText().trim().equals("")) {

            verifnom = false;

        } else {

            verifnom = true;
        }

    }

    @FXML
    private void controlDesc(KeyEvent event) {
        if (desc.getText().trim().equals("")) {

            verifdesc = false;

        } else {

            verifdesc = true;
        }
    }

    @FXML
    private void controleObjectif(KeyEvent event) {
        if (objectifE.getText().trim().equals("")) {

            verifobjectif = false;

        } else {

            verifobjectif = true;
        }

    }

    @FXML
    private void verifLieux(ActionEvent event) {
        if (lieuxE.getValue().trim().equals("")) {

            verifnblieu = false;

        } else {

            verifnblieu = true;
        }
    }

    @FXML
    private void ADDEvenement(ActionEvent event) {

        try {

            Event E = new Event();

            if (verifinom == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir le nom de l'evenement");
                alert.show();

            } else if (verifobjectif == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez saisir l'objectif de l'evenement");
                alert.show();

            } else if (verifidesc == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez saisir la description de l'evenement");
                alert.show();

            } else if (verifnbplace == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez préciser le nombre des places de l'evenement");
                alert.show();

            } else if (verifnbstand == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez préciser le nombre des stands de l'evenement");
                alert.show();

            } else if (verifnblieu == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez  préciser le lieux");
                alert.show();

            } else if (verifidate == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez  préciser la Date de l'evenement");
                alert.show();

            } else if (verifimage == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez  choisir une image de l'evenement");
                alert.show();

            } else {
                java.sql.Date date2 = java.sql.Date.valueOf(datevent1.getValue());
                E.setNomEvent(nomE.getText());
                E.setObjectifEvent(objectifE.getText());
                E.setDescriptionevent(descriptionE.getText());
                E.setNbrePlace(Integer.parseInt(nbplaces1.getText()));
                E.setNbreStand(Integer.parseInt(nbstand1.getText()));
                E.setLieux(lieuxE.getValue());
                E.setDateEvent(date2);

                String imgName = serviceCryptage.cryptWithMD5(filenale.getText());
                String imgFullName = imgName + filenale.getText().substring(filenale.getText().length() - 4, filenale.getText().length());
                Random rdm = new Random();

                String finalImgName = rdm.nextInt(999999999) + imgFullName;

                E.setUrlafficheevent(finalImgName);

                EvenementService serviceEvent = new EvenementService();
                serviceEvent.saveFile(file, finalImgName);
                serviceEvent.addEvenement(E);
                TrayNotification tray = new TrayNotification("Successfully", "Evenement Ajout Effectuée avec Succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(10));
//
//                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionEvent2FXML.fxml"));
//                Parent root2 = loader2.load();
//                GestionEvent2FXMLController addEvent = loader2.getController();
//                
//                
            
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));

            System.out.println(loader.getCharset());
            Parent root = loader.load();

            PannelAdminController infoPart = loader.getController();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionEvent2FXML.fxml"));

            Parent root2 = loader2.load();
            GestionEvent2FXMLController infoPart2 = loader2.getController();

            infoPart.getContainer_admin().getChildren().setAll(root2);

            labelmodifier.getScene().setRoot(root);

                objectifE.getScene().setRoot(root2);
            }
        } catch (IOException ex) {

        }

    }

    @FXML
    private void Reset(ActionEvent event) {
        nomE.clear();
        objectifE.clear();
        descriptionE.clear();
        nbplaces1.clear();
        nbstand1.clear();

    }

    @FXML
    private void choosefile(ActionEvent event) {

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

        filenale.setText(file.getName());
        filenale.setVisible(true);
        if (filenale.getText().trim().equals("")) {
            verifimage = false;
        } else {
            verifimage = true;
        }
    }

    @FXML
    private void choidDate(ActionEvent event) {

        java.sql.Date date = java.sql.Date.valueOf(datevent1.getValue());
        if (datevent1.getValue().toString().trim().equals("") || (date.compareTo(datesql) == -1)) {

            verifidate = false;

        } else {

            verifidate = true;
        }

    }

    @FXML
    private void controlnom2(KeyEvent event) {
        if (nomE.getText().trim().equals("")) {

            verifinom = false;

        } else {

            verifinom = true;
        }
    }

    @FXML
    private void controlDesc11(KeyEvent event) {
        if (descriptionE.getText().trim().equals("")) {

            verifidesc = false;

        } else {

            verifidesc = true;
        }

    }

    @FXML
    private void controlenbplace2(KeyEvent event) {

        System.out.println(nbplaces1.getText().trim());
        if (nbplaces1.getText().trim().length() > 0) {
            int nbChar = 0;
            for (int i = 1; i < nbplaces1.getText().trim().length(); i++) {
                char ch = nbplaces1.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                verifnbp.setText("number valide");
                verifnbplace = true;
            } else {
                verifnbp.setText("invalide number "
                        + " Il exist des char");
                verifnbplace = false;

            }

        } else {
            verifnbp.setText("Il faut  au moin 1 chiffres");
            verifnbplace = false;
        }

    }

    @FXML
    private void controlnbstand(KeyEvent event) {

        System.out.println(nbstand1.getText().trim());
        if (nbstand1.getText().trim().length() > 0) {
            int nbChar = 0;
            for (int i = 1; i < nbstand1.getText().trim().length(); i++) {
                char ch = nbstand1.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                verifnbs.setText("number valide");
                verifnbstand = true;
            } else {
                verifnbs.setText("invalide number "
                        + " Il exist des char");
                verifnbstand = false;

            }

        } else {
            verifnbs.setText("Il faut  au moin 1 chiffre");
            verifnbstand = false;
        }

    }

}
