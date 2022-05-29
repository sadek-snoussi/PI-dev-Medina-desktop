/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import static javax.swing.Spring.width;
import pidev.edu.souk.entities.Videodiy;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class VideoCellFXMLController {

    @FXML
    private VBox videoCell;
    @FXML
    private MediaView videoView;
    @FXML
    private Label titre;
    @FXML
    private Label description;

    public VideoCellFXMLController() {
        //System.err.println(getClass().getResource("/pidev/edu/souk/gui/videoCellFXML.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/VideoCellFXML.fxml"));
        //System.out.println("heeeer");
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {

        }

    }

    public void setInfo(Videodiy video) {
        
        

        String time= ""+50+"";
       Media videoMedia = new Media("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/"+video.getVideo());
       
       MediaPlayer videoPlayer = new MediaPlayer(videoMedia);
     
      
              videoView.setMediaPlayer(videoPlayer);
        

        titre.setText(video.getTitre());
        description.setText(video.getDescriptionVideo());
        
      

    }

    public VBox getVideoCell() {
        return videoCell;
    }

}
