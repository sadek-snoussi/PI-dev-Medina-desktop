/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import static pidev.edu.souk.controller.ActualiteEvent2FXMLController.userid;

import pidev.edu.souk.entities.Event;
import pidev.edu.souk.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class ActualiteEvent2FXMLController implements Initializable {

    @FXML
    private ListView<Event> listEvent;

    private Label iduser;
    @FXML
    private Label idevent;

    public static int userid = 0;
    @FXML
    private Label iduserEvent;
    @FXML
    private Button inscriEvent;

    public static Event selection;
    @FXML
    private VBox boxlist;
    private DatePicker dateEvent;
    @FXML
    private Button rech;
    @FXML
    private ComboBox<String> comboxLieux;
    EvenementService es = new EvenementService();

    ObservableList<String> listGouv = FXCollections.observableArrayList("Tunis", "Ben Arous", "Ariana", "Manouba", "Beja", "Kef", "Jandouba", "Sfax", "Sousse", "Gabes", "Nabeul", "Monastir", "Kairaoun", "Gafsa", "Kasserine", "Kebili", "Médenine", "Mahdia", "Sidi Bouzid", "Tataouine", "Zaghouan", "Bizerte", "Tozeur");
    @FXML
    private TextArea idt;

    public Label getIduserEvent() {
        return iduserEvent;
    }

    public void setIduserEvent(int iduserEvent) {
        this.iduserEvent.setText(Integer.toString(iduserEvent));
    }

    public Label getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {

        this.idevent.setText(Integer.toString(idevent));
    }

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    private ObservableList<Event> data = FXCollections.observableArrayList();

    Event event = new Event();
    EvenementService eventservice = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(eventservice.listerEvenement());
        listEvent.setItems(data);
        // System.out.println(data);
        listEvent.setCellFactory((ListView<Event> param) -> new EventList());
        //uderId = Integer.parseInt(iduser.getText());
        idevent.setVisible(false);
        iduserEvent.setVisible(false);
        idt.isDisable();
        //comboxLieux.setItems(listGouv);

        //  iduser.setVisible(false);
    }

    public int sentid() {
        userid = Integer.valueOf(iduserEvent.getText());
        return userid;
    }

    @FXML
    private void inscriEvent(ActionEvent event) {
        // s=listEvent.setOnMouseClicked(((Mouseevent event2) -> 
        // {if(event2.getClickCount()>=1)

        if (listEvent.getSelectionModel().getSelectedItem() != null) {
            selection = listEvent.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            Parent root;
            try {

                if (selection.getNbrePlace() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Warning");
                    alert.setContentText("Stock  des Pass Epuisé !!");
                    alert.show();
                } else {

                    root = FXMLLoader.load(getClass().getResource("/pidev/edu/souk/gui/InscriptionEventFXML.fxml"));
                    //userid=(Integer.parseInt(iduserEvent.getText()));

                    System.out.println(SingninController.userIden);

                    // selection.getNomEvent()
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {

            }

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez sélectionné un Evenement  !!");
            alert.show();

        }
    }

    /*  private void rechercherDate(ActionEvent event) {

        java.sql.Date date = java.sql.Date.valueOf(dateEvent.getValue());
        EvenementService es = new EvenementService();
        data.clear();

        data.addAll(es.findByDate(Date.valueOf("2018-03-16")));
        listEvent.setItems(data);
        listEvent.setCellFactory((ListView<Event> param) -> new EventList());
        refreshList();
    }
     */
    private void rechDate(ActionEvent event) {

        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(dateEvent.getValue());
        EvenementService vs = new EvenementService();

        if ((dateEvent.getValue() != null)) {

            //data.clear();
            System.out.println("aaaaaaaaaaaaaaaaaaaaa" + gettedDatePickerDate.toString());

            //data.addAll(es.findByDate(Date.valueOf("2018-03-16")));
            listEvent.setItems(data);
            listEvent.setCellFactory((ListView<Event> param) -> new EventList());

            System.out.println(dateEvent.getValue());
            refreshList();

        }

    }
    ///****************************************** Rechercher PAr date ********************************//////////////

    private void refreshList() {
        EvenementService es = new EvenementService();
        java.sql.Date date = java.sql.Date.valueOf(dateEvent.getValue().toString());
        //  System.out.println(date);
        data.clear();

        data.addAll(es.findByDate(date.toString()));
        // System.out.println("hhhhhhhhhhhh" + date);
        listEvent.setItems(data);
        System.out.println(data);
        listEvent.setCellFactory((ListView<Event> param) -> new EventList());

    }

//    @FXML
//    private void findByDate(ActionEvent event) {
//
//        java.sql.Date date = java.sql.Date.valueOf(dateEvent.getValue().toString());
//        EvenementService es = new EvenementService();
//        data.clear();
////
//        data.addAll(es.findByDate(date.toString()));
////
//        listEvent.setItems(data);
//
//        listEvent.setCellFactory((ListView<Event> param) -> new EventList());
//        System.out.println("auuuuuuuuuuuuuuuuu");
//
//        // refreshList();
//    }

    //////*********************************Rechercher Par Lieux ************************************************///
    @FXML
    private void rechLieus(ActionEvent event) {

        data.clear();
        //data.addAll(es.findByLieu(comboxLieux.getValue()));
        data.addAll(es.findByLieu(comboxLieux.getValue()));
        System.out.println(data);
        listEvent.setItems(data);
        listEvent.setCellFactory((ListView<Event> param) -> new EventList());
        System.out.println(listEvent+"oooooo");
       

    }

    private void refreshLieu() {
        data.clear();
        data.addAll(es.findByLieu(comboxLieux.getValue()));
        listEvent.setItems(data);
        listEvent.setCellFactory((ListView<Event> param) -> new EventList());

    }


}
