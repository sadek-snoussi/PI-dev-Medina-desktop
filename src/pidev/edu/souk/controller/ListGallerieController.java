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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class ListGallerieController implements Initializable {

    @FXML
    private TableView<Gallerie> AfficheBonPlan;
    @FXML
    private TableColumn<Gallerie, Gallerie> TitreGallerie;
    @FXML
    private TableColumn<Gallerie, Gallerie> DescriptionGallerie;
    @FXML
    private TableColumn<Gallerie, Gallerie> typeGallerie;
    @FXML
    private TableColumn<Gallerie, Gallerie> GouverneratGallerie;
    @FXML
    private ImageView imgGallerie;
    @FXML
    private Button SupprimerGallerie;
    @FXML
    private Button modifierBonPlan;
    @FXML
    private Label titremodifier;
    @FXML
    private TextField TXtitreGallerie;
    @FXML
    private ComboBox<String> TXtypeGallerie;
    @FXML
    private ComboBox<String> TXGouvGallerie;
    @FXML
    private TextArea TXDescriptionGallerie;
    @FXML
    private Button TXimgGallerie;
    @FXML
    private Label PathFile;
    @FXML
    private Button BtnModifieraGuideUpdate;

    Gallerie g = new Gallerie();
    GallerieService GS = new GallerieService();

    String path;
    File selectedFile;

    private List<Gallerie> myList = new ArrayList<Gallerie>();
    private ObservableList<Gallerie> ObservableListGallerie;

    ObservableList<String> listTypeGallerie = FXCollections.observableArrayList("Textile", "Monument", "Statue", "Personnalité", "bibelot", "Autres creation");
    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");

    boolean verificationTitre = true;
    boolean verificationType = true;
    boolean verificationGouv = true;
    boolean verificationDescription = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TXDescriptionGallerie.setVisible(false);
        TXGouvGallerie.setVisible(false);
        TXimgGallerie.setVisible(false);
        TXtitreGallerie.setVisible(false);
        TXtypeGallerie.setVisible(false);
        titremodifier.setVisible(false);
        PathFile.setVisible(false);
        BtnModifieraGuideUpdate.setVisible(false);

        TitreGallerie.setCellValueFactory(new PropertyValueFactory<>("TitreGallerie"));
        DescriptionGallerie.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeGallerie.setCellValueFactory(new PropertyValueFactory<>("typeGallerie"));
        GouverneratGallerie.setCellValueFactory(new PropertyValueFactory<>("LieuGallerie"));
       

        AfficheBonPlan.setItems((ObservableList<Gallerie>) GS.afficherGallerieAdmin());

    }

    @FXML
    private void ssss(MouseEvent event) {
        Gallerie gallerie = AfficheBonPlan.getItems().get(AfficheBonPlan.getSelectionModel().getSelectedIndex());
        g = gallerie;
        System.out.println("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie\\" + g.getImgGallerie());
        Image img = new Image("file:" + "C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie\\" + g.getImgGallerie());
        imgGallerie.imageProperty().set(img);
    }

    public void ReloadTableViewBonPlan() {

        TitreGallerie.setCellValueFactory(new PropertyValueFactory<>("TitreGallerie"));
        DescriptionGallerie.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeGallerie.setCellValueFactory(new PropertyValueFactory<>("typeGallerie"));
        GouverneratGallerie.setCellValueFactory(new PropertyValueFactory<>("LieuGallerie"));

        myList = GS.afficherGallerieAdmin();
        ObservableListGallerie = FXCollections.observableArrayList(myList);
        AfficheBonPlan.setItems(ObservableListGallerie);

    }

    @FXML
    private void SupprimerGallerie(ActionEvent event) {

        g = AfficheBonPlan.getSelectionModel().getSelectedItem();
        if (g == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir une Gallerie");
            alert.show();

        } else {
            GS.supprimerGallerie(g.getIdGallerie());
            ReloadTableViewBonPlan();
            System.out.println("Gallerie Supprimer !!");

            TrayNotification tray = new TrayNotification("Successfully", "Bonplan " + g.getTitreGallerie() + " Supprimé avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));

        }
    }

    @FXML
    private void AffichermodifieGallerie(ActionEvent event) {

        g = AfficheBonPlan.getSelectionModel().getSelectedItem();

        if (g == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir une Gallerie");
            alert.show();

        } else {

            TXtitreGallerie.setText(g.getTitreGallerie());
            TXtypeGallerie.setValue(g.getTypeGallerie());
            TXGouvGallerie.setValue(g.getLieuGallerie());
            TXDescriptionGallerie.setText(g.getDescription());
            TXGouvGallerie.setItems(listGouv);
            TXtypeGallerie.setItems(listTypeGallerie);

            TXDescriptionGallerie.setVisible(true);
            TXGouvGallerie.setVisible(true);
            TXimgGallerie.setVisible(true);
            TXtitreGallerie.setVisible(true);
            TXtypeGallerie.setVisible(true);
            titremodifier.setVisible(true);
            PathFile.setVisible(true);
            BtnModifieraGuideUpdate.setVisible(true);

        }

    }

    @FXML
    private void controltitre(KeyEvent event) {
        if (TXtitreGallerie.getText().trim().equals("")) {

            verificationTitre = false;

        } else {

            verificationTitre = true;
        }
    }

    @FXML
    private void ControlTypeGallerie(ActionEvent event) {
        if (TXtypeGallerie.getValue() == null) {

            verificationType = false;

        } else {

            verificationType = true;
        }
    }

    @FXML
    private void ControlGouv(ActionEvent event) {
        if (TXGouvGallerie.getValue() == null) {

            verificationGouv = false;

        } else {

            verificationGouv = true;
        }
    }

    @FXML
    private void ControlDescription(MouseEvent event) {
        if (TXDescriptionGallerie.getText().trim().equals("")) {

            verificationDescription = false;

        } else {

            verificationDescription = true;
        }
    }

    @FXML
    private void ModifierimgGallerie(ActionEvent event) {

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
                File dest = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie\\");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ModifierGallerieUpdate(ActionEvent event) {

        g = AfficheBonPlan.getSelectionModel().getSelectedItem();

        if (g == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un bonplan pour a modifier");
            alert.show();

        }
        if (verificationTitre == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez ajouter un titre");
            alert.show();

        } else if (verificationType == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir Type");
            alert.show();
        } else if (verificationGouv == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le Gouvernerat");
            alert.show();
        } else if (verificationDescription == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez ajouter une Description");
            alert.show();
        } else {

            g.setTitreGallerie(TXtitreGallerie.getText());
            g.setTypeGallerie(TXtypeGallerie.getValue());
            g.setLieuGallerie(TXGouvGallerie.getValue());
            g.setDescription(TXDescriptionGallerie.getText());
            g.setImgGallerie(path);

            System.out.println(TXtitreGallerie.getText());
            GS.modifierGallerie(g.getIdGallerie(), g);

            TXDescriptionGallerie.setVisible(false);
            TXGouvGallerie.setVisible(false);
            TXimgGallerie.setVisible(false);
            TXtitreGallerie.setVisible(false);
            TXtypeGallerie.setVisible(false);
            titremodifier.setVisible(false);
            PathFile.setVisible(false);
            BtnModifieraGuideUpdate.setVisible(false);

            AfficheBonPlan.getItems().clear();
            AfficheBonPlan.getItems().addAll(GS.afficherGallerieAdmin());

            System.out.println("Gallerie Modifier !!");

            TrayNotification tray = new TrayNotification("Successfully", "Gallerie " + g.getTitreGallerie() + " Modifier avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));
        }
    }

}
