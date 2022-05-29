/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class AgrandirImgGallerieController implements Initializable {

    ListGallerieUserController LGUC = new ListGallerieUserController();
    @FXML
    private Label DescriptionGallerieAnexe;
    @FXML
    private ImageView imgGallerieAnexe;
    @FXML
    private Label LabelGallerieTitre;
    @FXML
    private Label TitreGallerieAnexe;
    @FXML
    private ImageView imgBackground;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    Image img = new Image("file:" + "C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgGallerie\\" + LGUC.selectionedGallerie.getImgGallerie());
         
         System.out.println( LGUC.selectionedGallerie.getImgGallerie());
         
         DescriptionGallerieAnexe.setText(LGUC.selectionedGallerie.getDescription());
         TitreGallerieAnexe.setText(LGUC.selectionedGallerie.getTitreGallerie());
         imgBackground.setImage(img);

      
    }    
    
}
