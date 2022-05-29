/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.services.CategoryService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField nomCategorie;
    @FXML
    private TextField typeCategorie;
    @FXML
    private Button submitCategorie;
    @FXML
    private Hyperlink back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomCategorie.setVisible(true);
        typeCategorie.setVisible(true);
    }    

    @FXML
    private void AddCatgory(ActionEvent event) {
        
        Categorie c = new Categorie(nomCategorie.getText(), typeCategorie.getText());
        CategoryService serv =new CategoryService();
        serv.add(c);
        
        //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("la Catégorie "+c.getNomCategorie()+" a été Ajoutée avec succés");
            alert.show();
            //***********************************
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListCategory.fxml"));
            Parent root = loader.load();
            ListCategoryController listcatCtrl = loader.getController();
            nomCategorie.getScene().setRoot(root);
    }


}
