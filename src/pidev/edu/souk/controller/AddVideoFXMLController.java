/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import static jdk.nashorn.tools.Shell.SUCCESS;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.VideoDIYService;
import pidev.edu.souk.services.serviceCryptage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AddVideoFXMLController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private TextField titre;
    @FXML
    private TextField tags;
    @FXML
    private Button upload;
    @FXML
    private Label labelVideo;
    @FXML
    private Button addButton;
    @FXML
    private MediaView videoView;

    private Media video;
    private MediaPlayer videoPlayer;
    @FXML
    private Label videoURI;

    File f;
    @FXML
    private Label idUser;
    @FXML
    private AnchorPane addPane;
    @FXML
    private ImageView uploadIcone;
    @FXML
    private Label lbltitre;
    @FXML
    private Label lbldesc;
    @FXML
    private Label lbltags;
    @FXML
    private Label lblvideo;
    Boolean verifTitre = false;
    Boolean verifdesc = false;
    Boolean verifvideo = false;
    Boolean veriftag = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setVisible(true);
        description.setVisible(true);
        tags.setVisible(true);
        videoURI.setVisible(false);
        idUser.setVisible(false);

    }

    @FXML
    private void ajouterVideo(ActionEvent event) throws Exception {

        if (verifTitre == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le titre");
            alert.show();

        } else if (verifdesc == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir la description");
            alert.show();

        }  else if (verifvideo == true) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("veuillez ajouter votre video !!! ");

            alert.show();

        }
        
        
        
        else {

            Videodiy v = new Videodiy();
            User user = new User();

            String videoName = serviceCryptage.cryptWithMD5(labelVideo.getText());
            String videoFullName = videoName + labelVideo.getText().substring(labelVideo.getText().length() - 4, labelVideo.getText().length());
            Random rdm = new Random();

        String finalVideoName = rdm.nextInt(999999999) + videoFullName;

        v.setTitre(titre.getText());
        v.setDescriptionVideo(description.getText());
        v.setTags(tags.getText());
        user.setId(PannelUserController.iduser2);
        v.setIdUser(user);
        v.setVideo(finalVideoName);
        

        VideoDIYService service = new VideoDIYService();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        
        service.saveFile(f, finalVideoName);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

        service.ajouterVideoDIY(v);
                   setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml")));

            TrayNotification tray = new TrayNotification("Successfully", "Video Ajouté " + titre.getText() + "", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

        }

    }

//    private void uploadVideo(ActionEvent event) throws MalformedURLException {
//
//        FileChooser fc = new FileChooser();
//
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tous les fichiers", "*.mp4"));
//        f = fc.showOpenDialog(null);
//
//        if (f != null) {
//            labelVideo.setText(f.getName());
//
//            videoURI.setText(f.getName());
//            video = new Media(f.toURI().toString());
//            videoPlayer = new MediaPlayer(video);
//            videoView.setMediaPlayer(videoPlayer);
//
//            //--------------------------------------------Adjustement--------------
//            videoPlayer.setAutoPlay(true);
//            DoubleProperty width = videoView.fitWidthProperty();
//            DoubleProperty height = videoView.fitHeightProperty();
//          //  width.bind(Bindings.selectDouble(videoView.scaleXProperty(), "width"));
//
//            //--------------------------------------------Adjustement--------------
//        }
//
//    }
    public Label getIdUser() {
        return idUser;
    }

    public void setIdUser(int iduser) {
        this.idUser.setText(Integer.toString(iduser));
    }
    
    
     private void setNode(Node node) throws IOException {
        addPane.getChildren().clear();
       addPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml"));

        Parent root = loader.load();
        // root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml"));
        Scene scene = new Scene(root);
        // DetailVideoController detailVideo = loader.getController();
        // detailVideo.setIdUser(Integer.valueOf(idUser.getText()));

        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void uploadVideos(MouseEvent event) {

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tous les fichiers", "*.mp4"));
        f = fc.showOpenDialog(null);

        if (f != null) {
            labelVideo.setText(f.getName());

            videoURI.setText(f.getName());
            video = new Media(f.toURI().toString());
            videoPlayer = new MediaPlayer(video);
            videoView.setMediaPlayer(videoPlayer);

            //--------------------------------------------Adjustement--------------
            videoPlayer.setAutoPlay(true);
            DoubleProperty width = videoView.fitWidthProperty();
            DoubleProperty height = videoView.fitHeightProperty();
        }
    }

//    private void controlTitre(KeyEvent event) {
//        if (titre.getText().trim().equals("")) {
//            verifTitre = false;
//            lbltitre.setText("veuillez remplir le champs titre");
//        } else {
//            verifTitre = true;
//        }
//
//    }
    @FXML
    private void controlDes(KeyEvent event) {
        if (description.getText().trim().equals("")) {
            verifdesc = false;
            lbldesc.setText("veuillez remplir le champs description");
        } else {
            verifdesc = true;
        }
    }

    @FXML
    private void controlTags(KeyEvent event) {
        if (tags.getText().trim().length() > 0) {
            int nbchar = 0;
            for (int i = 1; i < tags.getText().trim().length(); i++) {
                char ch = tags.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbchar++;
                }
            }

            if (nbchar == 0) {
                lbltags.setText("veuillez remplir ce champ qu'avec des lettres");
                lbltags.setStyle("  -fx-text-fill:#b50707 ");
                veriftag = false;

            } else {

                lbltags.setText("Tags valides");
                                lbltags.setStyle("  -fx-text-fill:#0c6004 ");
                veriftag = true;

            }

        }
    }

    @FXML
    private void controlVdeo(KeyEvent event) {
//        if (labelVideo.getText().trim().equals("")) {
//            verifvideo = false;
//
//        }
//        int length = labelVideo.getText().length();
//        String ch = labelVideo.getText().substring(length - 3);
//        System.out.println("**********" + ch);
//
//        if (ch.equals("mp4")) {
//            verifvideo = true;
//
//        } else {
//            verifvideo = false;
//        }
        if (labelVideo.getText().trim().equals("")) {
            verifvideo = false;
        } else {
            verifvideo = true;
        }

    }

    @FXML
    private void controlTitre(KeyEvent event) {
        if (titre.getText().trim().equals("")) {
            verifTitre = false;
            lbltitre.setText("veuillez remplir le champs titre");
        } else {
            verifTitre = true;
        }
    }

}
