/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CelluleValidVideoController {

    @FXML
    private HBox cellList;
    @FXML
    private Label userName;
    @FXML
    private Label titre;
    @FXML
    private Label description;
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
    private Label idVideo;
    
     private MediaPlayer videoPlayer;
     int user = SingninController.userIden;

   
    public CelluleValidVideoController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/celluleValidVideo.fxml"));
        //System.out.println("heeeer");
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {

        }

    }
    
     public void setInfo(Videodiy video) {
         idVideo.setVisible(false);

        Media videoMedia = new Media("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/" + video.getVideo());

        videoPlayer = new MediaPlayer(videoMedia);

        videoView.setMediaPlayer(videoPlayer);

        userName.setText(getUserNameFromId(user).getNomUser());
        
        titre.setText(video.getTitre());
        description.setText(video.getDescriptionVideo());
        idVideo.setText(Integer.toString(video.getIdVideo()));

        slider.setValue(videoPlayer.getVolume() * 100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                videoPlayer.setVolume(slider.getValue() / 100);
            }
        });

        videoPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                sliderVideo.setValue(newValue.toSeconds());
            }

        });
        sliderVideo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                videoPlayer.seek(Duration.seconds(sliderVideo.getValue()));
            }

        });

    }
     
      public HBox getCellList() {
        return cellList;
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
         videoPlayer.setRate(2);
    }
    
     private User getUserNameFromId(int userID) {

        User user = new User();
        try {
            String requete3 = "select * from user where id=" + userID;

            // String id = idUser.getText();
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement

            while (rs.next()) {

                user.setNomUser(rs.getString(2));
                System.out.println(rs.getString(2));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return user;
    }
    
}
