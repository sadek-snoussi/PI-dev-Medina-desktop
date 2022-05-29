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
import javafx.scene.layout.HBox;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.entities.Guide;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class CellGuideUserController {

    @FXML
    private HBox cellBonPlanUserGal;
    @FXML
    private Label NomGuideUser;
    @FXML
    private Label PrenomGuideUser;
    @FXML
    private Label GouvGuideUser;
    @FXML
    private Label numGuideUser;
    @FXML
    private Label mailGuideUser;
    @FXML
    private ImageView imgGuideUser;
    
    ObservableList<Guide> data;

    /**
     * Initializes the controller class.
     */

    
          public CellGuideUserController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CellGuideUser.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public HBox getCellPanier() {
        return cellBonPlanUserGal;
    }
      
        public void setInfo(Guide guide) {
        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGuide\\" + guide.getImgGuide());
        System.out.println("je suis dans set info");
        imgGuideUser.setImage(img);
        mailGuideUser.setText(guide.getMailguide());
        NomGuideUser.setText(guide.getNomGuide());
        PrenomGuideUser.setText(guide.getPrenomGuide());
        numGuideUser.setText(guide.getTelGuide());
        GouvGuideUser.setText(guide.getVilleGuide());
        
    }
}
