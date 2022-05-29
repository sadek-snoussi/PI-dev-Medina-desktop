/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.edu.souk.entities.Videodiy;
//import pidev.edu.souk.services.AlertDialog;
import pidev.edu.souk.utils.MyConnection;

import pidev.edu.souk.services.VideoDIYService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CelluleVideoPartenaireController {

    @FXML
    private HBox cellList;
    @FXML
    private Label titre;
    @FXML
    private Label date;
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
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Label idVideo;
   @FXML
   private  Label etatVideo;

    private MediaPlayer videoPlayer;

  

    //  ObservableList<Videodiy> data;
    VideoDIYService videoService = new VideoDIYService();

    AffichageVideoPartenaireController data = new AffichageVideoPartenaireController();

    //private AffichageVideoPartenaireController data;

    public CelluleVideoPartenaireController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CelluleVideoPartenaire.fxml"));
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
        
       
        date.setText( video.getDateExpoVideo().toString());

        titre.setText(video.getTitre());
        description.setText(video.getDescriptionVideo());
        idVideo.setText(Integer.toString(video.getIdVideo()));
        
       
        if(video.getValid()== 0){
            etatVideo.setText("En cours");
            etatVideo.setStyle("-fx-background-color:#e27e09;  -fx-text-fill:#ffffff ");
            
           
            
        }else if (video.getValid()==1){
            etatVideo.setText("Validé");
            etatVideo.setStyle("-fx-background-color:#00902c; -fx-text-fill:#ffffff");
           // etatVideo.setFill(Color.rgb(0, 140, 44));
            
        }else{
            etatVideo.setText("Rejeté");
             etatVideo.setStyle("-fx-background-color:#ce0000;-fx-text-fill:#ffffff ");
            
           // etatVideo.setFill(Color.rgb(208, 0, 0));
        }
            

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

    @FXML
    private void modifier(ActionEvent event) throws IOException {

        //selectedIdVideo = idVideo.getText();
      //  data.setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/editVideo.fxml")));

    }

    @FXML
    private void supprimer(ActionEvent event) {

        String requete2 = "DELETE FROM videodiy  WHERE idVideo=?";

        String id = idVideo.getText();

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);

            pst.setString(1, id);

            int i = pst.executeUpdate();
            cellList.setVisible(false);
            cellList.setPrefHeight(-5000);
            cellList.setPrefWidth(-5000);
            if (i == 1) {
                //AlertDialog.display("Message de Retour", "");

                //  data.refresh();
//                try {
//                    
//                    Stage stage = new Stage();
//                    Parent root;
//                    root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml"));
//                    Scene scene = new Scene(root);
//                    stage.setScene(scene);
//                    stage.show();    
//                   
//                    
//                } catch (IOException ex) {
//                    Logger.getLogger(CelluleVideoPartenaireController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                ;
                // data.loadData();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
