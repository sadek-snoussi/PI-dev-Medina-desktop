/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.edu.souk.entities.Tags;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.TagsService;
import pidev.edu.souk.services.VideoDIYService;
import pidev.edu.souk.services.videoCellListView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficheVideoClientFXMLController implements Initializable {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<Videodiy> videoList;
    private ObservableList<Videodiy> data = FXCollections.observableArrayList();
    Videodiy video = new Videodiy();

    static Videodiy selectedVideo;

    VideoDIYService videoService = new VideoDIYService();
    @FXML
    private Label idUser;
    @FXML
    private AnchorPane AnchorePaneAfficheVideoClient;

    int user = SingninController.userIden;

    TagsService tagService = new TagsService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        idUser.setVisible(false);

        videoList.setOrientation(Orientation.HORIZONTAL);
        data.addAll(videoService.afficherVideoClient());
        videoList.setItems(data);

        videoList.setCellFactory((ListView<Videodiy> param) -> new videoCellListView());

        videoList.setOnMouseClicked((MouseEvent event2) -> {
            if (event2.getClickCount() >= 2) {
                if (videoList.getSelectionModel().getSelectedItems() != null) {
                    selectedVideo = videoList.getSelectionModel().getSelectedItem();

                    Stage stage = new Stage();
                    //Parent root;
                    try {
                        //   FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml"));
                        setNode(FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml")));
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
    }

    private void setNode(Node node) throws IOException {
        AnchorePaneAfficheVideoClient.getChildren().clear();
        AnchorePaneAfficheVideoClient.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.seconds(1));//dure de la translation

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/detailVideo.fxml"));
        // DetailVideoController detailVideo = loader.getController();
        //  detailVideo.setIdUserCnct(Integer.valueOf(idUser.getText()));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        //  detailVideo.setIdUser(Integer.valueOf(idUser.getText()));
        ft.setNode(node);
        ft.setFromValue(0.10);//dispartion 
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    public Label getIdUser() {
        return idUser;
    }

    public void setIdUser(int iduser) {
        this.idUser.setText(Integer.toString(iduser));
    }

    @FXML
    private void rechercherVideo(ActionEvent event) {

        data.clear();

        data.addAll(videoService.chercherVideo(searchTextField.getText()));
        videoList.setItems(data);
        videoList.setCellFactory((ListView<Videodiy> param) -> new videoCellListView());
        refreshVideoList();
        Tags tag = new Tags();
        tag.setTag(searchTextField.getText());
        User user1 = new User();
        user1.setId(user);
        tag.setUserId(user1);
        tagService.ajouterTag(tag);

    }

    private void refreshVideoList() {
        data.clear();
        data.addAll(videoService.chercherVideo(searchTextField.getText()));
        videoList.setItems(data);
        videoList.setCellFactory((ListView<Videodiy> param) -> new videoCellListView());

    }

}
