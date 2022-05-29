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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.management.Notification;
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
public class CrudGuideController implements Initializable {

    private Notification Notifications;
    String path;
    File selectedFile;
    Boolean verificationUserPhone = false;
    Boolean verificationUserNom = false;
    Boolean verificationUserPrenom = false;
    Boolean verificationUserEmail = false;

    @FXML
    private TextField NomGuide;
    @FXML
    private TextField PrenomGuide;
    @FXML
    private Button BtnAjouterGuide;
    @FXML
    private TextField telGuide;
    @FXML
    private TextField mailGuide;
    @FXML
    private AnchorPane LayoutGuide1;
    @FXML
    private ComboBox<String> ComboBox;
    @FXML
    private Button ajouterimg;

    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelEmail;

    GuideService guideService = new GuideService();
    @FXML
    private ImageView TickGuide1;
    @FXML
    private ImageView TickGuide11;
    @FXML
    private ImageView TickGuide111;
    @FXML
    private ImageView TickGuide112;
    @FXML
    private Button BtnAjouterGuide1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NomGuide.setVisible(true);
        PrenomGuide.setVisible(true);
        telGuide.setVisible(true);
        mailGuide.setVisible(true);
        ComboBox.setItems(listGouv);
        TickGuide1.setVisible(false);
        TickGuide11.setVisible(false);
        TickGuide111.setVisible(false);
        TickGuide112.setVisible(false);
        BtnAjouterGuide1.setVisible(false);

    }

    @FXML
    private void ajouterGuide(ActionEvent event) {
        Guide g = new Guide();

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

        } 
             else if(verificationUserPhone == false ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le telephone");
            alert.show();
                
            }
              else if (verificationUserEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir l'email");
            alert.show();

        } else {

            g.setNomGuide(NomGuide.getText());
            g.setPrenomGuide(PrenomGuide.getText());
            g.setTelGuide(telGuide.getText());
            g.setMailguide(mailGuide.getText());
            g.setImgGuide(path);
            g.setVilleGuide(ComboBox.getValue());
            GuideService gs = new GuideService();
            gs.addGuide(g);
            
             TickGuide1.setVisible(true);
             TickGuide11.setVisible(true);
       
            BtnAjouterGuide1.setVisible(true);

            TrayNotification tray = new TrayNotification("Successfully", "Guide " + g.getNomGuide() + " " + g.getPrenomGuide() + " Ajoutée avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.seconds(10));

        }
    }

    @FXML
    @SuppressWarnings("empty-statement")
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
            ajouterimg.setText(path);

        };

        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGuide");

                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                labelPhone.setText("Numero valide !");
                TickGuide111.setVisible(true);
                labelPhone.setTextFill(Color.web("#01af24"));
                verificationUserPhone = true;
                
            } else {
                
                labelPhone.setText("numero non valide "
                        + " Il exist des caractére");
                labelPhone.setTextFill(Color.web("#f70025"));
                TickGuide111.setVisible(false);
                verificationUserPhone = false;

            }

        } else {
            labelPhone.setText("Il faut 8 chiffres");
            labelPhone.setTextFill(Color.web("#ffc005"));
            TickGuide111.setVisible(false);
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

        if (guideService.ChercheGuideparEmail(mailGuide.getText().trim()) == true) {
            labelEmail.setText("Email Existe déja");
            labelEmail.setTextFill(Color.web("#ffc005"));
             TickGuide112.setVisible(false);
            verificationUserEmail = false;
        }
        if (guideService.ChercheGuideparEmail(mailGuide.getText().trim()) == false) {//alphanumerique@alphanumerique.com
           // {ici longeur  }
           // debut ^
           // fin $
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(mailGuide.getText());

            if (matcher.matches()) {       //if   matcher ne contient pas la format   
                labelEmail.setVisible(true);
                labelEmail.setText("Email valide !");
                labelEmail.setTextFill(Color.web("#01af24"));
                TickGuide112.setVisible(true);
                verificationUserEmail = true;

            } else {
                labelEmail.setVisible(true);
                labelEmail.setText("Email Format invalide !");
                 labelEmail.setTextFill(Color.web("#f70025"));
                 TickGuide112.setVisible(false);
                // JOptionPane.showMessageDialog(null, "Email Format invalide");
                verificationUserEmail = false;

            }
        }

    }

    @FXML
    private void ajouterNouveauGuide(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CrudGuide.fxml"));
            Parent root = loader.load();       
            LayoutGuide1.getChildren().setAll(root);
    }

}
