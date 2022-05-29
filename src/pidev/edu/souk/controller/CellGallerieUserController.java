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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pidev.edu.souk.entities.Gallerie;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class CellGallerieUserController  {
    
         ObservableList<Gallerie> data;

    @FXML
    private HBox cellBonPlanUserGal;
    @FXML
    private Label titreGallerieUser;
    @FXML
    private Label typeGallerieUser;
    @FXML
    private Label GouvGallerieUser;
    @FXML
    private TextArea DescriptionGallerieUser;
    @FXML
    private ImageView imageGallerie;

    /**
     * Initializes the controller class.
     */
  
    @FXML
    private void aggrandirImage(MouseEvent event) {
    }
    
         public CellGallerieUserController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/CellGallerieUser.fxml"));
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
      
        public void setInfo(Gallerie gallerie) {
        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie\\" + gallerie.getImgGallerie());
        System.out.println("je suis dans set info");
        imageGallerie.setImage(img);
        titreGallerieUser.setText(gallerie.getTitreGallerie());
        typeGallerieUser.setText(gallerie.getTypeGallerie());
        GouvGallerieUser.setText(gallerie.getLieuGallerie());
        DescriptionGallerieUser.setText(gallerie.getDescription());
        
    }
    
    
}
