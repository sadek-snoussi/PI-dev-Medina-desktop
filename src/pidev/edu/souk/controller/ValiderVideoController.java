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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.AdminService;
import pidev.edu.souk.services.GestionVideoService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ValiderVideoController implements Initializable {

    @FXML
    private ListView<Videodiy> videoList;
    private ObservableList<Videodiy> data = FXCollections.observableArrayList();
    GestionVideoService adminService = new GestionVideoService();
    
    Videodiy video = new Videodiy();

    //VideoDIYService videoService = new VideoDIYService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         data.addAll(adminService.afficherVideoNonValide());
        videoList.setCellFactory(new Callback<ListView<Videodiy>, ListCell<Videodiy>>() {

            @Override
            public ListCell<Videodiy> call(ListView<Videodiy> param) {
                ListCell<Videodiy> cell = new ListCell<Videodiy>() {
                    Button btn = new Button("Valider");
                    Button btn1 = new Button("Rejeter");
                    Label diy = new Label("");
                    HBox hbox = new HBox(diy);
                    HBox hbox1 = new HBox(btn, btn1);

                    VBox vbox = new VBox(hbox, hbox1);

                    @Override
                    protected void updateItem(Videodiy v, boolean btl) {
                        super.updateItem(v, btl);
                        setGraphic(null);
                        if (v != null) {

                            diy.setText(v.toString());
                            System.out.println(v.getIdVideo());
                            setGraphic(vbox);

                            // setText(v.getDescriptionVideo());
                            //  setText(v.getVideo());
                        }
                    }
                };
                return cell;
            }

        });
        videoList.setItems(data);
    
        
    }    
    
}
