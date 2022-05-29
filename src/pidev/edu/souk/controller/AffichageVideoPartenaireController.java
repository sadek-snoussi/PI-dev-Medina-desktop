/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static pidev.edu.souk.controller.AfficheVideoClientFXMLController.selectedVideo;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.VideoDIYService;
import pidev.edu.souk.services.videoCellListViewPartenaire;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AffichageVideoPartenaireController implements Initializable {

    @FXML
    private ListView<Videodiy> videoList;

    private ObservableList<Videodiy> data = FXCollections.observableArrayList();
    Videodiy video = new Videodiy();

    VideoDIYService videoService = new VideoDIYService();
    @FXML
    private AnchorPane paneVideoList;
    
     static Videodiy selectedVideo;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
          videoList.setOnMouseClicked((MouseEvent event2) -> {
            if (event2.getClickCount() >= 2) {
                if (videoList.getSelectionModel().getSelectedItems() != null) {
                    selectedVideo = videoList.getSelectionModel().getSelectedItem();

                    Stage stage = new Stage();
                    //Parent root;
                    try {
                        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml"));
                        setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/editVideo.fxml")));
//                         Parent root = loader.load();
//                       // root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml"));
//                        Scene scene = new Scene(root); 
//                        DetailVideoController detailVideo = loader.getController();
//                       detailVideo.setIdUser(Integer.valueOf(idUser.getText()));
//                        stage.setScene(scene);
//                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(DetailVideoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        );
        

        // TODO
    }

    public void loadData() {

        int idUser= SingninController.userIden;
        try{
        data.addAll(videoService.afficherVideoPartenaire(idUser));
        
        videoList.setItems(data);
        videoList.setCellFactory((ListView<Videodiy> param) -> new videoCellListViewPartenaire());
        System.out.println(videoList);
        }catch(Exception r){
            System.err.println(r.getMessage());
        }

    }

    public void refresh() {
        
        

        data.removeAll(data);
       

    }
    
     private void setNode(Node node) throws IOException {
        paneVideoList.getChildren().clear();
        paneVideoList.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/editVideo.fxml"));

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
