/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.RatingService;
import pidev.edu.souk.services.VideoDIYService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class RecommandedVideoController {

    @FXML
    private BorderPane borderpaneVideo;
    @FXML
    private StackPane stackPane;
    @FXML
    private MediaView vdeoView;
    @FXML
    private Slider sliderVideo;
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
    private Label idVideo;
    @FXML
    private Rating starRating;

    private MediaPlayer videoPlayer;

    RatingService ratingService = new RatingService();
    VideoDIYService videoService = new VideoDIYService();

    public RecommandedVideoController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/recommandedVideo.fxml"));

        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            

        }
    }

    public void setInfo(Videodiy video) {

        Media videoMedia = new Media("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/" + video.getVideo());

        videoPlayer = new MediaPlayer(videoMedia);

        String idVid = "" + video.getIdVideo() + "";

        vdeoView.setMediaPlayer(videoPlayer);
        idVideo.setText(idVid);

        titre.setText(video.getTitre());
        starRating.setDisable(true);

        final Rating rating = new Rating();
        rating.setPartialRating(true);

        video.setAvgRating(ratingService.compterNote(video));
        videoService.modifierAvgRating(video, video.getIdVideo());

        starRating.setRating(ratingService.compterNote(video));

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

    public BorderPane getBorderpaneVideo() {
        return borderpaneVideo;
    }

    @FXML
    private void playButton(ActionEvent event) {
        videoPlayer.play();
        videoPlayer.setRate(1);
    }

    @FXML
    private void pauseButton(ActionEvent event) {
        videoPlayer.pause();

    }

    @FXML
    private void slowButton(ActionEvent event) {
        videoPlayer.setRate(.5);

    }

    @FXML
    private void stopButton(ActionEvent event) {
        videoPlayer.stop();
    }

    @FXML
    private void fastButton(ActionEvent event) {
        videoPlayer.setRate(1.5);

    }

}
