/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import static pidev.edu.souk.controller.AffichageVideoPartenaireController.selectedVideo;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.VideoAdminService;
import pidev.edu.souk.services.videoCellListViewAdmin;
import pidev.edu.souk.utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficheVideoNonValidController implements Initializable {

    @FXML
    private ListView<Videodiy> invalidListVideo;
    private ObservableList<Videodiy> data = FXCollections.observableArrayList();
    Videodiy video = new Videodiy();
    VideoAdminService videoService = new VideoAdminService();
    VideoAdminService adminService = new VideoAdminService();
    static Videodiy selectedVideo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        invalidListVideo.setOnMouseClicked((MouseEvent event2) -> {
            if (event2.getClickCount() >= 1) {
                if (invalidListVideo.getSelectionModel().getSelectedItems() != null) {
                    selectedVideo = invalidListVideo.getSelectionModel().getSelectedItem();

                }
            }
        });
    }

    public void loadData() {

        try {
            data.addAll(videoService.afficherVideoNonValide());

            invalidListVideo.setItems(data);
            invalidListVideo.setCellFactory((ListView<Videodiy> param) -> new videoCellListViewAdmin());
            // System.out.println(videoList);
        } catch (Exception r) {
            System.err.println(r.getMessage());
        }

    }

    @FXML
    private void validerVideo(ActionEvent event) {
        int id = selectedVideo.getIdVideo();

        adminService.validerVideoDIY(id);
          TrayNotification tray = new TrayNotification("Successfully", "Vidée validée ", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
        data.clear();
        loadData();

    }

    @FXML
    private void rejeterVideo(ActionEvent event) {
        int id = selectedVideo.getIdVideo();

        adminService.rejeterVideoDIY(id);
          TrayNotification tray = new TrayNotification("Successfully", "Video rejetée", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
        data.clear();
        loadData();
    }

}
