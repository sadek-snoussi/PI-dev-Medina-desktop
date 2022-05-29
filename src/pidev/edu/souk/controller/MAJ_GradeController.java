package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pidev.edu.souk.entities.Statistiques;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.AdminService;
import pidev.edu.souk.services.GradeService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class MAJ_GradeController implements Initializable {

    @FXML
    private ComboBox<String> grades;
    @FXML
    private TextField produits;
    @FXML
    private TextField videos;
    @FXML
    private Button modifer;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         GradeService gs = new GradeService();
        ArrayList<Statistiques>gradesList=gs.listGrades();
         ObservableList observableList = FXCollections.observableArrayList(gradesList);
         for (Statistiques statistiques : gradesList) {
            grades.getItems().add(statistiques.getGrade());
        }
         
    }    

    @FXML
    private void grades(ActionEvent event) {
        String grade=grades.getSelectionModel().getSelectedItem();
        GradeService gs=new GradeService();
        Statistiques detailsGrades=gs.findByNomGrade(grade);
        produits.setText(Integer.toString(detailsGrades.getNbproduitsVendu()));
        videos.setText(Integer.toString(detailsGrades.getNbvideoPost()));
    }

    @FXML
    private void MAJGrade(ActionEvent event) {
        Statistiques grade =new Statistiques();
        grade.setGrade(grades.getValue());
        grade.setNbproduitsVendu(Integer.parseInt(produits.getText()));
        grade.setNbvideoPost(Integer.parseInt(videos.getText()));
        GradeService gs=new GradeService();
        gs.MAJGrade(grade, grade.getGrade());
    }
    
}
