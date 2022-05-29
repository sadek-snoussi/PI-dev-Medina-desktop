/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.VideoDIYService;
//import pidev.edu.souk.services.AlertDialog;
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficheVideoPartenaireFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> titleColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> descriptionColumn;
    @FXML
    private TableColumn<?, ?> videoColumn;
    //private TableColumn<?, ?> stateColumn;
    //private TableColumn<?, ?> updateColumn;
    @FXML
    private TableView<Videodiy> videoTable;
    ObservableList<Videodiy> data;
    //private TableColumn<?, ?> deleteColumn;
    @FXML
    private TextField tf_titre;
    @FXML
    private TextArea ta_description;
    @FXML
    private Button btn_ajouterVideo;
    @FXML
    private Button btn_suupVideo;
    @FXML
    private Button btn_modifierVideo;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TextField tf_id;
    VideoDIYService videoService = new VideoDIYService();
    @FXML
    private TableColumn<?, ?> modifColumn;

    //private Button suppButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tf_titre.setVisible(true);
        ta_description.setVisible(true);
        tf_id.setVisible(false);
        setCellTable();
        setCellValueFromTableToTextField();
        loadData();

    }

    private void loadData() {
         int idUser= SingninController.userIden;
        data = FXCollections.observableArrayList();
        data.addAll(videoService.afficherVideoPartenaire(idUser));
        videoTable.setItems(data);
    }

    private void setCellTable() {

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionVideo"));
        videoColumn.setCellValueFactory(new PropertyValueFactory<>("video"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idVideo"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateExpoVideo"));
        // deleteColumn.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
         modifColumn.setCellValueFactory(new PropertyValueFactory<>("modifier"));

    }

    private void setCellValueFromTableToTextField() {

        videoTable.setOnMouseClicked(e -> {
            Videodiy v = videoTable.getItems().get(videoTable.getSelectionModel().getSelectedIndex());
            String id = "" + v.getIdVideo() + "";
            tf_titre.setText(v.getTitre());
            ta_description.setText(v.getDescriptionVideo());
            tf_id.setText(id);

        }
        );
    }

    @FXML
    private void ajouterVideo(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/addVideoFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void supprimerVideo(ActionEvent event) {
        String requete2 = "DELETE FROM videodiy  WHERE idVideo=?";

        String id = tf_id.getText();

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);

            pst.setString(1, id);

            int i = pst.executeUpdate();

            if (i == 1) {
               //AlertDialog.display("Message de Retour", "");
                loadData();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private void refreshTable(){
        data.clear();
        loadData();
    }

    @FXML
    public void modifierVideo(ActionEvent event) {
        String titre = tf_titre.getText();
        String descrip = ta_description.getText();
        String id = tf_id.getText();
        String requete2 = "UPDATE videodiy SET titre=?,descriptionVideo=? WHERE idVideo=?";
        

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst.setString(1, titre);
            pst.setString(2, descrip);
            pst.setString(3, id);

            int i = pst.executeUpdate();

            if (i == 1) {
//                AlertDialog.display("Message de Confirmation", "");
                refreshTable();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    

}
