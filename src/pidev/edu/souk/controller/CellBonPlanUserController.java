/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.RatingBonPlan;
import pidev.edu.souk.services.RatingBonplanService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class CellBonPlanUserController {

    @FXML
    private HBox cellBonPlanUser;
    @FXML
    private Label titreBonPlanUser;
    @FXML
    private Label typeBonPlanUser;
    @FXML
    private ImageView picker;
    @FXML
    private ImageView imageBonPlan;
    @FXML
    private Rating NjoumAffichage;
  
    

    /**
     * Initializes the controller class.
     */
    ObservableList<Bonplan> data;

    public CellBonPlanUserController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CellBonPlanUser.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public HBox getCellPanier() {
        return cellBonPlanUser;
    }

    @FXML
    private void localisationUser(MouseEvent event) {

    }

    @FXML
    private void aggrandirImage(MouseEvent event) {
    }

    public void setInfo(Bonplan bonPlan) {
        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgBonplan\\" + bonPlan.getImgBonplan());
        System.out.println("je suis dans set info");
        imageBonPlan.setImage(img);
        titreBonPlanUser.setText(bonPlan.getNombonplan());
        typeBonPlanUser.setText(bonPlan.getTypeBonplan());
        NjoumAffichage.setRating(bonPlan.getAvgRating());
        NjoumAffichage.setDisable(true);

    }

}
