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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.services.GallerieCellView;
import pidev.edu.souk.services.GallerieService;
import pidev.edu.souk.services.GuideCellView;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ListGallerieUserController implements Initializable {
    

    @FXML
    private ListView<Gallerie> affBonPlanUser;
    @FXML
    private TextField TXrecherche;
    @FXML
    private ImageView loupBonplan;

    static Gallerie selectionedGallerie;

    ObservableList<String> listTypeGallerie = FXCollections.observableArrayList("Textile", "Monument", "Statue", "Personnalité", "bibelot", "Autres creation");
    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");

    private ObservableList<Gallerie> data = FXCollections.observableArrayList();
    GallerieService GS = new GallerieService();
    Gallerie gallerie = new Gallerie();
    @FXML
    private Label RechTagLabel;
    @FXML
    private Label RechTagCount;
    @FXML
    private Label RechTagCount1;
    @FXML
    private ComboBox<String> RechGouvBox;
    @FXML
    private Label RechTagCount11;
    @FXML
    private Label RechTypeLabel;
    @FXML
    private Label RechGouvLabel;
    @FXML
    private Label RechGouvCount2;
    @FXML
    private ComboBox<String> RechTypeBox;
    @FXML
    private Label RechTypeCount;
    @FXML
    private ImageView Trieup;
    @FXML
    private ImageView Triedown;
    @FXML
    private Button rechercheAll;
    @FXML
    private Label RechAll;
    @FXML
    private Label RechallNum;
    @FXML
    private AnchorPane AnchorPaneBackground;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);
        RechallNum.setVisible(false);
        RechAll.setVisible(false);

        RechGouvBox.setItems(listGouv);
        RechTypeBox.setItems(listTypeGallerie);

        affBonPlanUser.setVisible(true);
        loadData();

        affBonPlanUser.setVisible(true);

        affBonPlanUser.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (affBonPlanUser.getSelectionModel().getSelectedItem() != null) {
                    selectionedGallerie = affBonPlanUser.getSelectionModel().getSelectedItem();
                    Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/AgrandirImgGallerie.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ListBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
    }

    private void loadData() {
        data.addAll(GS.afficherGallerieClient());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        // affBonPlanUser.setCellFactory((ListView<Panier> param)-> new bonPlanCellView());
    }

    @FXML
    private void rechecherBonplan(ActionEvent event) {
    }

    @FXML
    private void ValidRechercheBonplan(MouseEvent event) {
        data.clear();

        data.addAll(GS.chercherGallerieParTag(TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

        RechTagLabel.setVisible(true);
        RechTagCount.setVisible(true);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);

        int x = GS.chercherGallerieParTagCount(TXrecherche.getText());

        RechTagCount.setText(String.valueOf(x));

        refrechList();

    }

    private void refrechList() {
        data.clear();
        data.addAll(GS.chercherGallerieParTag(TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    @FXML
    private void RechGouvBox(ActionEvent event) {
        data.clear();

        data.addAll(GS.chercherGallerieParGouvernerat(RechGouvBox.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        System.out.println(RechGouvBox.getValue());

        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(true);
        RechGouvCount2.setVisible(true);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);

        int x = GS.chercherGallerieParGouvCount(RechGouvBox.getValue());

        RechGouvCount2.setText(String.valueOf(x));

        refrechListGouv();
    }

    private void refrechListGouv() {
        data.clear();
        data.addAll(GS.chercherGallerieParGouvernerat(RechGouvBox.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    @FXML
    private void RechTypeBox(ActionEvent event) {

        data.clear();

        data.addAll(GS.chercherGallerieParType(RechTypeBox.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        System.out.println(RechTypeBox.getValue());

        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(true);
        RechTypeCount.setVisible(true);

        int x = GS.chercherGallerieParTypeCount(RechTypeBox.getValue());

        RechTypeCount.setText(String.valueOf(x));

        refrechListType();
    }

    private void refrechListType() {
        data.clear();
        data.addAll(GS.chercherGallerieParType(RechTypeBox.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    @FXML
    private void Trieup(MouseEvent event) {

        data.clear();

        data.addAll(GS.trieup());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        System.out.println(RechTypeBox.getValue());

        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);

        refrechListup();
    }

    private void refrechListup() {
        data.clear();
        data.addAll(GS.trieup());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    private void refrechListdown() {
        data.clear();
        data.addAll(GS.triedown());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    @FXML
    private void rechercheAll(ActionEvent event) {
        data.clear();

        data.addAll(GS.chercherGallerieALL(RechTypeBox.getValue(), RechGouvBox.getValue(), TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        System.out.println(RechGouvBox.getValue());

        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);
        RechallNum.setVisible(true);
        RechAll.setVisible(true);

        int x = GS.chercherGallerieALLCount(RechTypeBox.getValue(), RechGouvBox.getValue(), TXrecherche.getText());
        RechallNum.setText(String.valueOf(x));

        refrechListAll();

    }

    private void refrechListAll() {
        data.clear();
        data.addAll(GS.chercherGallerieALL(RechGouvBox.getValue(), RechTypeBox.getValue(), TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());

    }

    @FXML
    private void Triedown(MouseEvent event) {

        data.clear();

        data.addAll(GS.triedown());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Gallerie> param) -> new GallerieCellView());
        System.out.println(RechTypeBox.getValue());

        RechTagLabel.setVisible(false);
        RechTagCount.setVisible(false);
        RechGouvLabel.setVisible(false);
        RechGouvCount2.setVisible(false);
        RechTypeLabel.setVisible(false);
        RechTypeCount.setVisible(false);

        refrechListdown();

    }

}
