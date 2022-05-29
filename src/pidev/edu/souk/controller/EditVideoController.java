/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
//import pidev.edu.souk.services.AlertDialog;
import pidev.edu.souk.services.VideoDIYService;
import pidev.edu.souk.utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EditVideoController implements Initializable {

    @FXML
    private BorderPane borderpaneVideo;
    @FXML
    private StackPane stackPane;
    @FXML
    private MediaView videoView;
    @FXML
    private Slider sliderVideo;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button slowButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button fastButton;
    @FXML
    private Slider slider;
    @FXML
    private Label titre;
    @FXML
    private Label date;
    @FXML
    private Label idVideo;
    @FXML
    private Label description;
    @FXML
    private TextField modifTitre;
    @FXML
    private TextArea modifDescrip;
    @FXML
    private TextField modifTags;

    private MediaPlayer videoPlayer;

    AffichageVideoPartenaireController videoFromList = new AffichageVideoPartenaireController();
    String idVid = "" + videoFromList.selectedVideo.getIdVideo() + "";

    VideoDIYService videoService = new VideoDIYService();
    @FXML
    private AnchorPane editPane;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /**
         * *************** Set Video**************
         */
        idVideo.setVisible(false);
        Media videoMedia = new Media("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/" + videoFromList.selectedVideo.getVideo());
        videoPlayer = new MediaPlayer(videoMedia);
        videoView.setMediaPlayer(videoPlayer);

        titre.setText(videoFromList.selectedVideo.getTitre());
        description.setText(videoFromList.selectedVideo.getDescriptionVideo());

        modifTitre.setText(videoFromList.selectedVideo.getTitre());
        modifDescrip.setText(videoFromList.selectedVideo.getDescriptionVideo());
        modifTags.setText(getVideoFromId(idVid).getTags());
        date.setText(String.valueOf(getVideoFromId(idVid).getDateExpoVideo()));

        // date.setText(videoFromList.selectedVideo.getDateExpoVideo());
        /**
         * *******************************************
         */
        slider.setValue(videoPlayer.getVolume() * 100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable
            ) {
                videoPlayer.setVolume(slider.getValue() / 100);
            }
        }
        );

        videoPlayer.currentTimeProperty()
                .addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
                            Duration newValue
                    ) {
                        sliderVideo.setValue(newValue.toSeconds());
                    }

                }
                );
        sliderVideo.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event
            ) {
                videoPlayer.seek(Duration.seconds(sliderVideo.getValue()));
            }

        }
        );

    }

    private User getIdUser(String videoID) {
        Videodiy video = new Videodiy();
        User user = new User();
        Statement st3;
        try {
            String requete = "select id_user from videodiy where idVideo=" + videoID;
            st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete);
            while (rs.next()) {

                user.setId(rs.getInt(1));
                System.out.println(rs.getInt(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailVideoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    private Videodiy getVideoFromId(String videoID) {
        Videodiy video = new Videodiy();
        Statement st3;
        try {

            String requete = "select * from videodiy where idVideo=" + videoID;
            st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete);
            while (rs.next()) {
                video.setTitre(rs.getString(8));
                video.setDescriptionVideo(rs.getString(3));
                video.setVideo(rs.getString(9));
                video.setTags(rs.getString(7));
                video.setDateExpoVideo(rs.getDate(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EditVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return video;
    }

    @FXML
    private void playVideo(ActionEvent event) {
        videoPlayer.play();
        videoPlayer.setRate(1);
    }

    @FXML
    private void pauseVideo(ActionEvent event) {
        videoPlayer.pause();
    }

    @FXML
    private void slowVideo(ActionEvent event) {
        videoPlayer.setRate(.5);
    }

    @FXML
    private void stopVideo(ActionEvent event) {
        videoPlayer.stop();
    }

    @FXML
    private void fastVideo(ActionEvent event) {
        videoPlayer.setRate(1.5);
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            String titre = modifTitre.getText();
            String descrip = modifDescrip.getText();
            String tags = modifTags.getText();
            int id = videoFromList.selectedVideo.getIdVideo();
            String requete2 = "UPDATE videodiy SET titre=?,descriptionVideo=?,tags=?, valid=0 WHERE idVideo=?";

            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);
                pst.setString(1, titre);
                pst.setString(2, descrip);
                pst.setString(3, tags);
                pst.setInt(4, id);
                
                
                pst.executeUpdate();
                
                
                
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                
            }
              TrayNotification tray = new TrayNotification("Successfully", "Video modifi�e", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

            setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml")));
            
            
        } catch (IOException ex) {
            Logger.getLogger(EditVideoController.class.getName()).log(Level.SEVERE, null, ex);

        }


    }
    

    @FXML
    private void supprimer(ActionEvent event) {

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation Dialog");
            alert.setHeaderText(null);

            alert.setContentText("are you sure to delete selected video? ");
            Optional<ButtonType> action2 = alert.showAndWait();
            if (action2.get() == ButtonType.OK) {

                videoService.supprimerVideoDIY(videoFromList.selectedVideo.getIdVideo());
                
                 TrayNotification tray = new TrayNotification("Successfully", "Video supprimée ", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
            }
            setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(EditVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setNode(Node node) throws IOException {
        editPane.getChildren().clear();
        editPane.getChildren().add((Node) node);
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
}
