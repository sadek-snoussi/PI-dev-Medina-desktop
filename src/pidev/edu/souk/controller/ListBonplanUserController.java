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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.services.BonplanService;
import pidev.edu.souk.services.bonPlanCellView;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ListBonplanUserController implements Initializable {

    @FXML
    private ListView<Bonplan> affBonPlanUser;

    private ObservableList<Bonplan> data = FXCollections.observableArrayList();
    BonplanService bonplanservice = new BonplanService();
    Bonplan bonplan = new Bonplan();
    @FXML
    private TextField TXrecherche;
    @FXML
    private ImageView loupBonplan;

    static Bonplan selectionedBonplan;
    @FXML
    private ImageView trieup;
    @FXML
    private ImageView ratedown;
    @FXML
    private CheckBox etoileun;
    @FXML
    private CheckBox etoiledeux;
    @FXML
    private CheckBox etoiletroix;
    @FXML
    private CheckBox etoilequatre;
    @FXML
    private CheckBox etoilecinq;
    @FXML
    private Button etoilecherche;
    @FXML
    private ComboBox<String> cherchtypebonplan;
    
        ObservableList<String> listTypeBonPlan = FXCollections.observableArrayList("Restaurant", "Salon de thé", "Musée", "Bien être", "Site naturelle", "Cinéma");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cherchtypebonplan.setItems(listTypeBonPlan);

        affBonPlanUser.setVisible(true);
        loadData();
        affBonPlanUser.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (affBonPlanUser.getSelectionModel().getSelectedItem() != null) {
                    selectionedBonplan = affBonPlanUser.getSelectionModel().getSelectedItem();
                    Stage stage = new Stage();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/LocalisationPannelUser.fxml"));
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
        data.addAll(bonplanservice.afficherBonplanClient());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());
        // affBonPlanUser.setCellFactory((ListView<Panier> param)-> new bonPlanCellView());
    }

    @FXML
    private void rechecherBonplan(ActionEvent event) {
    }

    @FXML
    private void ValidRechercheBonplan(MouseEvent event) {

        data.clear();

        data.addAll(bonplanservice.chercherBonplanParGouvernerat(TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        refrechList();
    }

    private void refrechList() {
        data.clear();
        data.addAll(bonplanservice.chercherBonplanParGouvernerat(TXrecherche.getText()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

    }

    @FXML
    private void ratetrieup(MouseEvent event) {
        data.clear();

        data.addAll(bonplanservice.OrderByNoteUp());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

    }

    @FXML
    private void ratetriedown(MouseEvent event) {
        data.clear();

        data.addAll(bonplanservice.OrderByNoteDown());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

    }

    @FXML
    private void etoilecherche(ActionEvent event) {
        if (etoileun.isSelected()) {
            data.clear();
            System.out.println(" 1 selected ");
            data.addAll(bonplanservice.chercherBonplanParRating(1.0));

            affBonPlanUser.setItems(data);
            System.out.println(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        } else if (etoiledeux.isSelected()) {

            data.clear();
            System.out.println(" 2 selected ");
            data.addAll(bonplanservice.chercherBonplanParRating(2.0));
            affBonPlanUser.setItems(data);
            System.out.println(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        } else if (etoiletroix.isSelected()) {
            data.clear();
            System.out.println(" 3 selected ");
            System.out.println(data);
            data.addAll(bonplanservice.chercherBonplanParRating(3.0));
            affBonPlanUser.setItems(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        } else if (etoilequatre.isSelected()) {

            data.clear();
            System.out.println(" 4 selected ");

            data.addAll(bonplanservice.chercherBonplanParRating(4.0));
            System.out.println(data);
            affBonPlanUser.setItems(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        } else if (etoilecinq.isSelected()) {

            data.clear();
            System.out.println(" 5 selected ");

            data.addAll(bonplanservice.chercherBonplanParRating(5.0));
            System.out.println(data);
            affBonPlanUser.setItems(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

        }
        /*else {
            data.clear();
            System.out.println(" 7atchay selected ");
            data.addAll(bonplanservice.afficherBonplanClient());
            affBonPlanUser.setItems(data);
            affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());

            refrechList();
        }*/
    }

    @FXML
    private void cherchtypebonplan(ActionEvent event) {
        
          data.clear();

        data.addAll(bonplanservice.chercherBonplanParType(cherchtypebonplan.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Bonplan> param) -> new bonPlanCellView());
      
        
    }

    @FXML
    private void NejmaUn(ActionEvent event) {
        
         if (etoileun.isSelected()) {

            etoiledeux.setSelected(false);
            etoiletroix.setSelected(false);
            etoilequatre.setSelected(false);
            etoilecinq.setSelected(false);

        }
    }

    @FXML
    private void NejmaDeux(ActionEvent event) {
        
        if (etoiledeux.isSelected()) {

            etoileun.setSelected(false);
            etoiletroix.setSelected(false);
            etoilequatre.setSelected(false);
            etoilecinq.setSelected(false);

        }
        
    }

    @FXML
    private void NejmaTroix(ActionEvent event) {
        
         if (etoiletroix.isSelected()) {

            etoileun.setSelected(false);
            etoiledeux.setSelected(false);
            etoilequatre.setSelected(false);
            etoilecinq.setSelected(false);

        }
    }

    @FXML
    private void NejmaQuatre(ActionEvent event) {
        
        
              if (etoilequatre.isSelected()) {

            etoileun.setSelected(false);
            etoiledeux.setSelected(false);
            etoiletroix.setSelected(false);
            etoilecinq.setSelected(false);

        }
    }

    @FXML
    private void NejmaCinq(ActionEvent event) {
          if (etoilecinq.isSelected()) {

            etoileun.setSelected(false);
            etoiledeux.setSelected(false);
            etoiletroix.setSelected(false);
            etoilequatre.setSelected(false);

        }
    }

}
