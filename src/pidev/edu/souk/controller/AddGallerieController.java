/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.services.GallerieService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AddGallerieController implements Initializable {

    @FXML
    private AnchorPane LayoutGuide1;
    @FXML
    private TextField titreGallerie;
    @FXML
    private ComboBox<String> typeGallerie;
    @FXML
    private ComboBox<String> Gouv;
    @FXML
    private TextArea DescriptionGallerie;
    @FXML
    private Button imgGallerie;
    @FXML
    private Button BtnAjouterGallerie;

    boolean verificationTitre = false;
    boolean verificationType = false;
    boolean verificationGouv = false;
    boolean verificationDescription = false;

    String path;
    File selectedFile;

    ObservableList<String> listTypeGallerie = FXCollections.observableArrayList("Textile", "Monument", "Statue", "Personnalité", "bibelot", "Autres creation");
    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");
    @FXML
    private ImageView TickTitre;
    @FXML
    private ImageView TickTitre1;
    @FXML
    private ImageView TickTitre11;
    @FXML
    private ImageView TickTitre111;
    @FXML
    private Button BtnAjouterGallerie1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titreGallerie.setVisible(true);
        DescriptionGallerie.setVisible(true);
         TickTitre.setVisible(false);
         TickTitre1.setVisible(false);
         TickTitre11.setVisible(false);
         TickTitre111.setVisible(false);
         BtnAjouterGallerie1.setVisible(false);

        typeGallerie.setItems(listTypeGallerie);
        Gouv.setItems(listGouv);
    }

    @FXML
    private void controlTitre(KeyEvent event) {
        if (titreGallerie.getText().trim().equals("")) {

            verificationTitre = false;
            TickTitre.setVisible(false);

        } else {

            verificationTitre = true;
          
        }
    }

    @FXML
    private void ControlTypeGallerie(ActionEvent event) {
        if (typeGallerie.getValue() == null) {

            verificationType = false;
            TickTitre1.setVisible(false);

        } else {

            verificationType = true;
        }

    }

    @FXML
    private void ControlGouv(ActionEvent event) {

        if (Gouv.getValue() == null) {

            verificationGouv = false;
            TickTitre11.setVisible(false);

        } else {

            verificationGouv = true;
        }
    }

    @FXML
    private void DescriptionGallerie(KeyEvent event) {
        if (DescriptionGallerie.getText().trim().equals("")) {

            verificationDescription = false;
            TickTitre111.setVisible(false);

        } else {

            verificationDescription = true;
        }

    }

    @FXML
    private void AjouterimgGallerie(ActionEvent event) {
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
            imgGallerie.setText(path);

        };

        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ajouterGallerie(ActionEvent event) {

        Gallerie g = new Gallerie();
        
        if (verificationTitre == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez ajouter un titre");
            alert.show();
            

        }
        else if (verificationGouv == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le gouvernerat");
            alert.show();

        }
        else if (verificationType == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le type");
            alert.show();

        }
        else if (verificationDescription == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez ajouter une description");
            alert.show();

        }
        else {
          TickTitre.setVisible(true);
          TickTitre1.setVisible(true);
          TickTitre11.setVisible(true);
          TickTitre111.setVisible(true);
          BtnAjouterGallerie1.setVisible(true);
          
          
        g.setTitreGallerie(titreGallerie.getText());
        g.setDescription(DescriptionGallerie.getText());
        g.setLieuGallerie(Gouv.getValue());
        g.setTypeGallerie(typeGallerie.getValue());
        g.setImgGallerie(path);

        GallerieService gs = new GallerieService();
        gs.ajouterGallerie(g);

        TrayNotification tray = new TrayNotification("Successfully", "la Gallerie " + g.getTitreGallerie() + " Ajoutée avec succés !", SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(10));
        }
    }

    @FXML
    private void ajouterNewGallerie(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddGallerie.fxml"));
            Parent root = loader.load();       
            LayoutGuide1.getChildren().setAll(root);
        
    }

}
