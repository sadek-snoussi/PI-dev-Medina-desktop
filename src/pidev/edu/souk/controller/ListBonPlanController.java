/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.services.BonplanService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ListBonPlanController implements Initializable {

    @FXML
    private TableView<Bonplan> AfficheBonPlan;
    @FXML
    private TableColumn<Bonplan, Bonplan> nombonplan;
    @FXML
    private TableColumn<Bonplan, Bonplan> Longitude;
    @FXML
    private TableColumn<Bonplan, Bonplan> Latitude;
    @FXML
    private TableColumn<Bonplan, Bonplan> typeBonplan;
   // private TableColumn<Bonplan, Bonplan> avisBonplan;
    @FXML
    private ImageView imgGuide;
    @FXML
    private Button SupprimerBonPlan;
    @FXML
    private Button modifierBonPlan;
    @FXML
    private Label titremodifier;
    @FXML
    private Label PathFile;
    @FXML
    private Button BtnModifieraGuideUpdate;
    
    @FXML
    private TextField TXnombonplan;
    @FXML
    private TextField TXAdresseBonplan;
    @FXML
    private TextField TXLongitude;
    @FXML
    private TextField TXLatitude;
    @FXML
    private ComboBox<String> TXtypeBonplan;
    private ComboBox<Integer> TXavisBonplan;
    @FXML
    private Button TXimgBonplan;
    @FXML
    private TableColumn<Bonplan, Bonplan> AdresseBonplan;

    Boolean verificationUserNom = true;
    Boolean verificationAdresse = true;
    Boolean verificationLongitude = true;
    Boolean verificationLatitude = true;
    Boolean verificationTypeBonplan = false;
    Boolean verificationAvis = false;

    Bonplan b = new Bonplan();
    private List<Bonplan> myList = new ArrayList<Bonplan>();
    private ObservableList<Bonplan> ObservableListBonplan;

    ObservableList<String> listTypeBonPlan = FXCollections.observableArrayList("Restaurant", "Salon de thé", "Musée", "Bien être", "Site naturelle", "Cinéma");
 //   ObservableList<Integer> listAvieBonPlan = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    BonplanService bonplanService = new BonplanService();
    String path;
    File selectedFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TXnombonplan.setVisible(false);
        TXAdresseBonplan.setVisible(false);
        TXLongitude.setVisible(false);
        TXLatitude.setVisible(false);
        TXtypeBonplan.setVisible(false);
        //TXavisBonplan.setVisible(false);
        titremodifier.setVisible(false);
        PathFile.setVisible(false);
        BtnModifieraGuideUpdate.setVisible(false);
        TXimgBonplan.setVisible(false);
        PathFile.setVisible(false);

        nombonplan.setCellValueFactory(new PropertyValueFactory<>("nombonplan"));
        AdresseBonplan.setCellValueFactory(new PropertyValueFactory<>("AdresseBonplan"));
        Longitude.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        Latitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        typeBonplan.setCellValueFactory(new PropertyValueFactory<>("typeBonplan"));
//        avisBonplan.setCellValueFactory(new PropertyValueFactory<>("avisBonplan"));

        AfficheBonPlan.setItems((ObservableList<Bonplan>) bonplanService.afficherBonplanAdmin());

        // TODO
    }

    public void ReloadTableViewBonPlan() {

        nombonplan.setCellValueFactory(new PropertyValueFactory<>("nombonplan"));
        AdresseBonplan.setCellValueFactory(new PropertyValueFactory<>("AdresseBonplan"));
        Longitude.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        Latitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        typeBonplan.setCellValueFactory(new PropertyValueFactory<>("typeBonplan"));
       //avisBonplan.setCellValueFactory(new PropertyValueFactory<>("AvgRating"));

        myList = bonplanService.afficherBonplanAdmin();
        ObservableListBonplan = FXCollections.observableArrayList(myList);
        AfficheBonPlan.setItems(ObservableListBonplan);

    }

    public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ssss(MouseEvent event) {

        Bonplan bonplan = AfficheBonPlan.getItems().get(AfficheBonPlan.getSelectionModel().getSelectedIndex());
        b = bonplan;
        System.out.println("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgBonPlan\\" + b.getImgBonplan());
        Image img = new Image("file:" + "C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgBonPlan\\" + b.getImgBonplan());
        imgGuide.imageProperty().set(img);

    }

    @FXML
    private void SupprimerBonPlan(ActionEvent event) {

        Bonplan bonplan = new Bonplan();

        bonplan = AfficheBonPlan.getSelectionModel().getSelectedItem();
        if (bonplan == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un bon plan");
            alert.show();

        } else {
            bonplanService.DeleteBonplan(bonplan.getIdBonplan());
            ReloadTableViewBonPlan();
            System.out.println("bonplan Supprimer !!");

            TrayNotification tray = new TrayNotification("Successfully", "Bonplan " + bonplan.getNombonplan() + " " + bonplan.getAdresseBonplan() + " Supprimé avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));

        }

    }

    @FXML
    private void AffichermodifieBonPlan(ActionEvent event) {

        Bonplan bonplan = new Bonplan();

        bonplan = AfficheBonPlan.getSelectionModel().getSelectedItem();

        if (bonplan == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un bonplan");
            alert.show();

        } else {

            TXnombonplan.setText(b.getNombonplan());
            TXAdresseBonplan.setText(b.getAdresseBonplan());
            TXLongitude.setText(String.valueOf(b.getLongitude()));
            TXLatitude.setText(String.valueOf(b.getLatitude()));
            TXtypeBonplan.setItems(listTypeBonPlan);
            //TXavisBonplan.setItems(listAvieBonPlan);

            TXnombonplan.setVisible(true);
            TXAdresseBonplan.setVisible(true);
            TXLongitude.setVisible(true);
            TXLatitude.setVisible(true);
            TXtypeBonplan.setVisible(true);
           // TXavisBonplan.setVisible(true);
            titremodifier.setVisible(true);
            PathFile.setVisible(true);
            BtnModifieraGuideUpdate.setVisible(true);
            TXimgBonplan.setVisible(true);
            PathFile.setVisible(false);

        }

        /**
         * *********************
         */
    }

    @FXML
    private void ModifierimgBonplan(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            PathFile.setText(path);

        };

        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\Medina\\web\\uploads\\imgBonPlan\\");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ModifierBonPlanUpdate(ActionEvent event) {
        Bonplan bonplan = new Bonplan();

        bonplan = AfficheBonPlan.getSelectionModel().getSelectedItem();

        if (bonplan == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un bonplan pour a modifier");
            alert.show();

        }
        if (verificationUserNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le nom");
            alert.show();

        } else if (verificationAdresse == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir l'addresse");
            alert.show();
        } else if (verificationLongitude == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir la Longitude");
            alert.show();
        } else if (verificationLatitude == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir la Latitude");
            alert.show();
            
        } else if (verificationTypeBonplan == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le type du Bonplan");
            alert.show();
        }  else {

            bonplan.setNombonplan(TXnombonplan.getText());
            bonplan.setAdresseBonplan(TXAdresseBonplan.getText());
            bonplan.setTypeBonplan(TXtypeBonplan.getValue());
            bonplan.setLongitude(Double.parseDouble(TXLongitude.getText()));
            bonplan.setLatitude(Double.parseDouble(TXLatitude.getText()));

            bonplan.setImgBonplan(path);
            System.out.println(TXnombonplan.getText());
            bonplanService.updateBonplan(bonplan.getIdBonplan(), bonplan);

            TXnombonplan.setVisible(false);
            TXAdresseBonplan.setVisible(false);
            TXLongitude.setVisible(false);
            TXLatitude.setVisible(false);
            TXtypeBonplan.setVisible(false);
            titremodifier.setVisible(false);
            PathFile.setVisible(false);
            BtnModifieraGuideUpdate.setVisible(false);
            TXimgBonplan.setVisible(false);
            PathFile.setVisible(false);

            AfficheBonPlan.getItems().clear();

            AfficheBonPlan.getItems().addAll(bonplanService.afficherBonplanAdmin());

            System.out.println("Guide Modifier !!");

            TrayNotification tray = new TrayNotification("Successfully", "bonplan " + bonplan.getNombonplan() + " " + bonplan.getAdresseBonplan() + " Modifier avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
        }

    }

    @FXML
    private void controlAdresse(KeyEvent event) {
          if (TXAdresseBonplan.getText().trim().equals("")) {

            verificationAdresse = false;

        } else {

            verificationAdresse = true;
        }
    }

    @FXML
    private void controlLongitude(KeyEvent event) {
        
            if (TXLongitude.getText().trim().equals("")) {

            verificationLongitude = false;

        } else {

            verificationLongitude = true;
        }
    }

    @FXML
    private void controlLatitude(KeyEvent event) {
        
           if (TXLatitude.getText().trim().equals("")) {

            verificationLatitude = false;

        } else {

            verificationLatitude = true;
        }

    }

    @FXML
    private void ControlTypeBonplan(ActionEvent event) {
        
           if (TXtypeBonplan.getValue() == null) {

            verificationTypeBonplan = false;

        } else {

            verificationTypeBonplan = true;
        }
    }

   
    
     @FXML
    private void controlNom(KeyEvent event) {
         if (TXnombonplan.getText().trim().equals("")) {

            verificationUserNom = false;

        } else {

            verificationUserNom = true;
        }
    }

}
