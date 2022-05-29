/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.services.GuideService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AfficheGuideAdminController implements Initializable {

    @FXML
    private TableView<Guide> AfficheGuide;
    @FXML
    private TableColumn<Guide, String> Nom;
    @FXML
    private TableColumn<Guide, String> Prenom;
    @FXML
    private TableColumn<Guide, String> tel;
    @FXML
    private TableColumn<Guide, String> Mail;
    @FXML
    private TableColumn<Guide, Guide> Ville;
    @FXML
    private ImageView imgGuide;


    /**
     * *************************************Declaration List
     * Gouvernerat*******************
     */
    private List<Guide> myList = new ArrayList<Guide>();
    private ObservableList<Guide> ObservableListGuideAdmin;

    /**
     * *************************************Instantiation**********************************
     */
    Guide G = new Guide();
    GuideService guideService = new GuideService();

    /**
     * *************************************Declation des
     * Variables************************
     */
    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");
    String path;
    File selectedFile;
    Boolean verificationUserPhone = true;
    Boolean verificationUserNom = true;
    Boolean verificationUserPrenom = true;
    Boolean verificationUserEmail = true;
    private VBox VboxEdit;
    @FXML
    private TextField NomGuide;
    @FXML
    private TextField PrenomGuide;
    @FXML
    private TextField telGuide;
    @FXML
    private ComboBox<String> ComboBox;
    @FXML
    private TextField mailGuide;
    @FXML
    private Button Modifierimg;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelEmail;

    @FXML
    private Label titremodifier;
    @FXML
    private Label PathFile;
    @FXML
    private Button BtnModifieraGuideUpdate;
    @FXML
    private Button SupprimerGuide;
    @FXML
    private Button modifierGuide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NomGuide.setVisible(false);
        PrenomGuide.setVisible(false);
        telGuide.setVisible(false);
        ComboBox.setVisible(false);
        mailGuide.setVisible(false);
        labelPhone.setVisible(false);
        labelEmail.setVisible(false);
        titremodifier.setVisible(false);
        BtnModifieraGuideUpdate.setVisible(false);
        Modifierimg.setVisible(false);
        PathFile.setVisible(false);

        Nom.setCellValueFactory(new PropertyValueFactory<>("nomGuide"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenomGuide"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telGuide"));
        Mail.setCellValueFactory(new PropertyValueFactory<>("mailguide"));
        Ville.setCellValueFactory(new PropertyValueFactory<>("villeGuide"));

        AfficheGuide.setItems(guideService.afficherGuideAdmin());

    }

    /**
     * *************************************Recharge TableView avec les
     * nouvelle donnée aprés Suppression ************************
     */
    public void ReloadTableViewGuide() {

        Nom.setCellValueFactory(new PropertyValueFactory<>("nomGuide"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenomGuide"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telGuide"));
        Mail.setCellValueFactory(new PropertyValueFactory<>("mailguide"));
        Ville.setCellValueFactory(new PropertyValueFactory<>("villeGuide"));

        myList = guideService.afficherGuideAdmin();
        ObservableListGuideAdmin = FXCollections.observableArrayList(myList);
        AfficheGuide.setItems(ObservableListGuideAdmin);

    }

    @FXML
    public void SupprimerGuide() {
        Guide guide = new Guide();

        guide = AfficheGuide.getSelectionModel().getSelectedItem();
        if (guide == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un guide");
            alert.show();

        } else {
            guideService.DeleteGuide(guide.getIdGuide());
            ReloadTableViewGuide();
            System.out.println("Guide Supprimer !!");

            TrayNotification tray = new TrayNotification("Successfully", "Guide " + guide.getNomGuide() + " " + guide.getPrenomGuide() + " Supprimé avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));

        }

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

    /**
     * *************************************Affichage Taswira quand Click 3al
     * tableView ************************
     */
    @FXML
    private void ssss(MouseEvent event) {
        Guide SG = AfficheGuide.getItems().get(AfficheGuide.getSelectionModel().getSelectedIndex());
        G = SG;
        System.out.println("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGuide\\" + G.getImgGuide());
        Image img = new Image("file:" + "C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGuide\\" + G.getImgGuide());
        imgGuide.imageProperty().set(img);

    }

    @FXML
    private void controlphone(KeyEvent event) {

        System.out.println(telGuide.getText().trim());
        if (telGuide.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < telGuide.getText().trim().length(); i++) {
                char ch = telGuide.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                System.out.println(nbChar);
            }

            if (nbChar == 0) {
                labelPhone.setText("number valide");
                verificationUserPhone = true;
            } else {
                labelPhone.setText("invalide number "
                        + " Il exist des char");
                verificationUserPhone = false;

            }

        } else {
            labelPhone.setText("Il faut 8 chiffres");
            verificationUserPhone = false;
        }

    }

    @FXML
    private void controlNom(KeyEvent event) {

        if (NomGuide.getText().trim().equals("")) {

            verificationUserNom = false;

        } else {

            verificationUserNom = true;
        }

    }

    @FXML
    private void controlPrenom(KeyEvent event) {

        if (PrenomGuide.getText().trim().equals("")) {

            verificationUserPrenom = false;

        } else {

            verificationUserPrenom = true;
        }

    }

    @FXML
    private void verifEmail(KeyEvent event) {

        /*if (guideService.ChercheGuideparEmail(mailGuide.getText().trim()) == true) {
            labelEmail.setText("Email Existe déja");
            verificationUserEmail = false;
        }
        if (guideService.ChercheGuideparEmail(mailGuide.getText().trim()) == false) {//alphanumerique@alphanumerique.com
            //{ici longeur  }
            //debut ^
            //fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(mailGuide.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(true);
                labelEmail.setText("Email valide !");
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                labelEmail.setText("Email Format invalide !");
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }*/

    }

    @FXML
    private void ModifierGuideUpdate(ActionEvent event) {

        Guide guide = new Guide();

        guide = AfficheGuide.getSelectionModel().getSelectedItem();

        if (guide == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un guide pour a modifier");
            alert.show();

        } else {

            /**
             * *************************************
             */
            System.out.println(guide.toString());

            if (verificationUserNom == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir le nom");
                alert.show();

            } else if (verificationUserPrenom == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir le prenom");
                alert.show();

            } else if (verificationUserPhone == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir le telephone");
                alert.show();

            } else if (verificationUserEmail == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("Veillez remplir l'email");
                alert.show();

            } else {

                
                
                guide.setNomGuide(NomGuide.getText());
                 
                guide.setPrenomGuide(PrenomGuide.getText());
                guide.setTelGuide(telGuide.getText());
                guide.setMailguide(mailGuide.getText());
                guide.setVilleGuide(ComboBox.getValue());
                guide.setImgGuide(path);
                
                
                
              
                guideService.update(guide.getIdGuide(), guide);
        NomGuide.setVisible(false);
        PrenomGuide.setVisible(false);
        telGuide.setVisible(false);
        ComboBox.setVisible(false);
        mailGuide.setVisible(false);
        labelPhone.setVisible(false);
        labelEmail.setVisible(false);
        titremodifier.setVisible(false);
        BtnModifieraGuideUpdate.setVisible(false);
        Modifierimg.setVisible(false);
        PathFile.setVisible(false);
             

        AfficheGuide.getItems().clear();
             
        AfficheGuide.getItems().addAll(guideService.afficherGuideAdmin());
        
            System.out.println("Guide Modifier !!");

                TrayNotification tray = new TrayNotification("Successfully", "Guide " + guide.getNomGuide() + " " + guide.getPrenomGuide() + " Modifier avec succés !", SUCCESS);
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.seconds(10));

            }

            /**
             * *********************
             */
        }

    }

    @FXML
    private void AffichermodifierGuide(ActionEvent event) {

        Guide guide = new Guide();

        guide = AfficheGuide.getSelectionModel().getSelectedItem();

        if (guide == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez Choisir un guide");
            alert.show();

        } else {

            NomGuide.setText(G.getNomGuide());
            PrenomGuide.setText(G.getPrenomGuide());
            telGuide.setText(G.getTelGuide());
            mailGuide.setText(G.getMailguide());
            ComboBox.setItems(listGouv);

            NomGuide.setVisible(true);
            PrenomGuide.setVisible(true);
            telGuide.setVisible(true);
            ComboBox.setVisible(true);
            mailGuide.setVisible(true);
            labelPhone.setVisible(true);
            labelEmail.setVisible(true);
            titremodifier.setVisible(true);
            BtnModifieraGuideUpdate.setVisible(true);
            Modifierimg.setVisible(true);
            PathFile.setVisible(true);

        }

        /**
         * *********************
         */
    }

    @FXML
    private void imageGuide(ActionEvent event) throws MalformedURLException {
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
                File dest = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGuide\\");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
