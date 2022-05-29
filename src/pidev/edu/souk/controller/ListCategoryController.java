/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.services.CategoryService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ListCategoryController implements Initializable {


    @FXML
    private TableView<Categorie> table_Cat;
    @FXML
    private Button add_cat;
    @FXML
    private TableColumn<Categorie,String> ref_cat;
    @FXML
    private TableColumn<Categorie,String> nom_cat;
    @FXML
    private Button edit_btn;
    @FXML
    private Button remove_btn;
    
    
    //note : l'idex pour l'objet à effacer
    private IntegerProperty index=new SimpleIntegerProperty();
    Categorie c=new Categorie();
    CategoryService catServ = new CategoryService();   
    final ObservableList categories=FXCollections.observableArrayList(catServ.list());    
    private AnchorPane editerCategorieBox;
    @FXML
    private Label editPane_title;
    @FXML
    private Label editPane_lab1;
    @FXML
    private Label editPane_lab2;
    @FXML
    private Label editPane_lab3;
    @FXML
    private AnchorPane SuppCategorieBox;
    @FXML
    private Button SuppPane_noBtn;
    @FXML
    private Button suppPane_yesBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        table_Cat.setItems(categories);       
        ref_cat.setCellValueFactory(new PropertyValueFactory<Categorie,String>("typeCategorie"));
        nom_cat.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nomCategorie"));


        //recupérer l'index du row selectionné dans le tableView
        table_Cat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
            index.set(categories.indexOf(newValue));           
            System.out.println(categories.indexOf(newValue));
            
            c=(Categorie) categories.get(index.get());
            
           System.out.println(c);

            
            }
        });
        
        
        
        
    }    

    @FXML
    private void redirect_to_add(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddCategory.fxml"));
            Parent root = loader.load();
            AddCategoryController addcatCtrl = loader.getController();
            table_Cat.getScene().setRoot(root);
        
    }

    @FXML
    private void editerCategorie(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/Editcategory.fxml"));
        Parent root = loader.load();
        EditcategoryController editcatCtrl = loader.getController();
        
        editcatCtrl.getNomCategorie().setText(c.getNomCategorie());
        editcatCtrl.getTypeCategorie().setText(c.getTypeCategorie());
        editcatCtrl.getId_cat().setText(c.getIdCategorie().toString());

        
        
        
        
        table_Cat.getScene().setRoot(root);
               
    }

    @FXML
    private void removeCategory(ActionEvent event) {
        
        CategoryService catserv=new CategoryService();
        int i = index.get();
        if(i>-1){
            
            catserv.delete(c.getIdCategorie());
            categories.remove(i);
            table_Cat.getSelectionModel().clearSelection();
            
            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("la Categorie "+c.getNomCategorie()+" a été supprimé avec succés");
            alert.show();
            //***********************************
         }
        
    }

    public Categorie getC() {
        return c;
    }


    @FXML
    private void closeEditPane(ActionEvent event) {
    
        SuppCategorieBox.setVisible(false);
        table_Cat.setDisable(false);

        
    }


    @FXML
    private void show_SuppPane(ActionEvent event) {
        
        SuppCategorieBox.setVisible(true);
        table_Cat.setDisable(true);
          
        
        if(c.getNomCategorie()!=null){
            
        editPane_title.setText("Warning.");
        editPane_lab1.setText("Vous etes sur le point de supprimer la Categorie");
        editPane_lab1.setVisible(true);
        editPane_lab2.setText(c.getNomCategorie()+" .Ceci est Définitif.");
        editPane_lab2.setVisible(true);
        editPane_lab3.setText("Voulez-vous vraiment continuer ?");
        editPane_lab3.setVisible(true);
        suppPane_yesBtn.setVisible(true);
        SuppPane_noBtn.setText("Non.");
        
        
        }else{
        
        editPane_title.setText("Error.");
        editPane_lab1.setVisible(false);
        editPane_lab2.setText("Veuillez Selectionner la Ligne à Supprimer !");
        editPane_lab3.setVisible(false);
        suppPane_yesBtn.setVisible(false);
        SuppPane_noBtn.setText("Fermer");
        }
    }


    
    
    
    
}
