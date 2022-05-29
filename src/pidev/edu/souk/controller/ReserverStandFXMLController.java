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

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class ReserverStandFXMLController implements Initializable {

    @FXML
    private Label idstand;

    public Label getIdstand() {
        return idstand;
    }

    public void setIdstand(Integer idstand) {
       this.idstand.setText(Integer.toString(idstand));
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idstand.setVisible(true);
    }    
    
}
