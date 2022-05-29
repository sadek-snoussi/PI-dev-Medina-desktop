/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import com.sun.javaws.exceptions.ExitException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static pidev.edu.souk.controller.AffichageVideoPartenaireController.selectedVideo;
import static pidev.edu.souk.controller.AfficheVideoClientFXMLController.selectedVideo;
import pidev.edu.souk.entities.Tags;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.TagsService;
import pidev.edu.souk.services.VideoDIYService;
import pidev.edu.souk.services.topRatedVideoCellListView;
import pidev.edu.souk.services.videoCellListView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AccueilPanelClientController implements Initializable {

    @FXML
    private ListView<Videodiy> topRatedVideoList;
    @FXML
    private ListView<Videodiy> reccomandationList;
    @FXML
    private AnchorPane anchorPaneAccueil;

    private ObservableList<Videodiy> data = FXCollections.observableArrayList();
    private ObservableList<Tags> dataTags = FXCollections.observableArrayList();
    private ObservableList<Videodiy> dataVideoRecc = FXCollections.observableArrayList();

    Videodiy video = new Videodiy();

    static Videodiy selectedVideo;

    VideoDIYService videoService = new VideoDIYService();

    TagsService tagsService = new TagsService();

    int user = PannelClientController.idusers;

    private String searchTag1;
    private String searchTag2;
    private String searchTag3;
    @FXML
    private Label labelListe1;
    @FXML
    private Label labelListe2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        topRatedVideoList.setVisible(false);
//        reccomandationList.setVisible(false);
//        labelListe1.setVisible(false);
//        labelListe2.setVisible(false);
        topRatedVideoList.setOrientation(Orientation.HORIZONTAL);
        reccomandationList.setOrientation(Orientation.HORIZONTAL);
       
        
        topRatedVideo();
        reccomandedVideo();

        topRatedVideoList.setOnMouseClicked((MouseEvent event2) -> {
            if (event2.getClickCount() >= 2) {
                if (topRatedVideoList.getSelectionModel().getSelectedItems() != null) {
                    selectedVideo = topRatedVideoList.getSelectionModel().getSelectedItem();
                    Stage stage = new Stage();
                    try {
                        setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/DetailRatedVideo.fxml")));

                    } catch (IOException ex) {
                        Logger.getLogger(DetailVideoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        );
        
         reccomandationList.setOnMouseClicked((MouseEvent event2) -> {
            if (event2.getClickCount() >= 2) {
                if (reccomandationList.getSelectionModel().getSelectedItems() != null) {
                    selectedVideo = reccomandationList.getSelectionModel().getSelectedItem();
                    System.out.println("aaaaaaaaaaaaaaaa"+ selectedVideo.getIdVideo());
                   
                    
                            
                    Stage stage = new Stage();
                    try {
                        setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/DetailRatedVideo.fxml")));

                    } catch (IOException ex) {
                        Logger.getLogger(DetailVideoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        );

    }

    private void topRatedVideo() {
        
//        topRatedVideoList.setVisible(true);
//        labelListe1.setVisible(true);
        data.addAll(videoService.topRatedVideo());
        ObservableList<Videodiy> listVid = FXCollections.observableArrayList();

        if (data.size() == 1) {
            listVid.add(data.get(0));
        } else if (data.size() == 2) {
            listVid.add(data.get(0));
            listVid.add(data.get(1));
        } else if (data.size() >= 3) {
            listVid.add(data.get(0));
            listVid.add(data.get(1));
            listVid.add(data.get(2));

        }

        System.out.println(listVid.size());

        topRatedVideoList.setItems(listVid);

        topRatedVideoList.setCellFactory(
                (ListView<Videodiy> param) -> new topRatedVideoCellListView());

    }

    private void reccomandedVideo() {
        
//         reccomandationList.setVisible(true);
//        labelListe2.setVisible(true);
        
        dataTags.addAll(tagsService.listeTags(SingninController.userIden));
        ObservableList<Videodiy> listTags = FXCollections.observableArrayList();

        if (dataTags.size() == 1) {
            Tags tag1 = dataTags.get(0);
            searchTag1 = tag1.getTag();
            System.out.println("" + searchTag1);
        } else if (dataTags.size() == 2) {
            Tags tag1 = dataTags.get(0);
            searchTag1 = tag1.getTag();
            Tags tag2 = dataTags.get(1);
            searchTag2 = tag2.getTag();
            System.out.println("" + searchTag1);
            System.out.println("" + searchTag2);

        } else if (dataTags.size() >= 3) {
            Tags tag1 = dataTags.get(0);
            searchTag1 = tag1.getTag();
            Tags tag2 = dataTags.get(1);
            searchTag2 = tag2.getTag();
            Tags tag3 = dataTags.get(2);
            searchTag3 = tag3.getTag();
            System.out.println("" + searchTag1);
            System.out.println("" + searchTag2);
            System.out.println("" + searchTag3);
        }

        System.out.println("tag1" + searchTag1);
        System.out.println("tag2" + searchTag2);
        System.out.println("tag3" + searchTag3);

        dataVideoRecc.addAll(videoService.afficherVideoRecc(searchTag1, searchTag2, searchTag3));
      
        ObservableList<Videodiy> listVidRecc = FXCollections.observableArrayList();

        if (dataVideoRecc.size() == 1) {
            listVidRecc.add(dataVideoRecc.get(0));
            System.out.println("aaaaaaaa");
        } else if (dataVideoRecc.size() == 2) {
            listVidRecc.add(dataVideoRecc.get(0));
            listVidRecc.add(dataVideoRecc.get(1));
                        System.out.println("bbbbbbbbbbbbb");

        } else if (dataVideoRecc.size() >= 3) {
            listVidRecc.add(dataVideoRecc.get(0));
            listVidRecc.add(dataVideoRecc.get(1));
            listVidRecc.add(dataVideoRecc.get(2));
                        System.out.println("cccccccccccccc");


        }

        reccomandationList.setItems(listVidRecc);
        reccomandationList.setCellFactory(
                (ListView<Videodiy> param) -> new topRatedVideoCellListView());
        /**
         * **************************************************************
         */
        
        
        
    }
    
     private void setNode(Node node) throws IOException {
        anchorPaneAccueil.getChildren().clear();
        anchorPaneAccueil.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/DetailRatedVideo.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
   

        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

}
