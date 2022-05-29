/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.services.GallerieCellView;
import pidev.edu.souk.services.GallerieService;
import pidev.edu.souk.services.GuideCellView;
import pidev.edu.souk.services.GuideService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ListGuideUserController implements Initializable {

    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");

    @FXML
    private ListView<Guide> affBonPlanUser;

    private ObservableList<Guide> data = FXCollections.observableArrayList();
    GuideService GS = new GuideService();
    Guide guide = new Guide();
    @FXML
    private ComboBox<String> RechGouvBox;
    @FXML
    private Label CountGouv;
    @FXML
    private Label CountLabel;
    @FXML
    private Label Countnumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.clear();
        affBonPlanUser.setVisible(true);
        loadData();
        RechGouvBox.setItems(listGouv);
        CountLabel.setVisible(false);
        Countnumber.setVisible(false);

    }

    private void loadData() {
        data.clear();
        data.addAll(GS.afficherGuideClient());
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Guide> param) -> new GuideCellView());
        // affBonPlanUser.setCellFactory((ListView<Panier> param)-> new bonPlanCellView());
    }


    @FXML
    private void RechGouvBox(ActionEvent event) {
            

        data.clear();
        data.addAll(GS.chercherGuideParGouvernerat(RechGouvBox.getValue()));
        affBonPlanUser.setItems(data);
        affBonPlanUser.setCellFactory((ListView<Guide> param) -> new GuideCellView());
        System.out.println(RechGouvBox.getValue());

          CountLabel.setVisible(true);
          Countnumber.setVisible(true);
                    int x = GS.chercherGuideParGouverneratcount(RechGouvBox.getValue());

          Countnumber.setText(String.valueOf(x));
          
    }

}
