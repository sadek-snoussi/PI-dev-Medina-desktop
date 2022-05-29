/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.RatingBonPlan;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.RatingBonplanService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class LocalisationPannelUserController implements Initializable, MapComponentInitializedListener {

    ListBonplanUserController LBUC = new ListBonplanUserController();

    GoogleMap g;
    private GeocodingService geocodingService;
    @FXML
    private Label GeoTitreBonplan;
    @FXML
    private Label GeoAdresseBonplan;
    @FXML
    private Label GeoLongitudeBonplan;
    @FXML
    private Label GeoLatitudeBonplan;
    @FXML
    private GoogleMapView map2;
    @FXML
    private AnchorPane anchorPaneMap;
    @FXML
    private ImageView imgLocaBonplan;
    @FXML
    private Rating Njoum;
    @FXML
    private Label NoteLabel;
    @FXML
    private Button BtnVoterRating;


    RatingBonPlan RBP = new RatingBonPlan();
    RatingBonplanService RateBPS = new RatingBonplanService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image img = new Image("file:" + "C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgBonPlan\\" + LBUC.selectionedBonplan.getImgBonplan());

        System.out.println(LBUC.selectionedBonplan.getImgBonplan());

        GeoTitreBonplan.setText(LBUC.selectionedBonplan.getNombonplan());
        GeoAdresseBonplan.setText(LBUC.selectionedBonplan.getAdresseBonplan());
        GeoLongitudeBonplan.setText(String.valueOf(LBUC.selectionedBonplan.getLongitude()));
        GeoLatitudeBonplan.setText(String.valueOf(LBUC.selectionedBonplan.getLatitude()));
        imgLocaBonplan.setImage(img);

        map2.addMapInializedListener(this);

        Njoum.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                NoteLabel.setText("" + newValue.toString());
            }

        });
// 

    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        //3maltha abstrait khater nheb nbadel kima nheb ok bb :) bye 
        MapOptions options = new MapOptions(); //bech n7adedou chniyaa fehaa ptption

        options.center(new LatLong(Double.valueOf(GeoLongitudeBonplan.getText()), Double.valueOf(GeoLatitudeBonplan.getText())))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapMaker(true)
                .zoom(25);
        g = map2.createMap(options);

        LatLong cord = new LatLong(Double.valueOf(GeoLongitudeBonplan.getText()), Double.valueOf(GeoLatitudeBonplan.getText()));
        MarkerOptions Options = new MarkerOptions();
        Options.position(cord)
                .visible(Boolean.TRUE)
                .title("My Marker")
                .animation(Animation.BOUNCE);
        Marker marker = new Marker(Options);
        g.addMarker(marker);
        g.setCenter(new LatLong(Double.valueOf(GeoLongitudeBonplan.getText()), Double.valueOf(GeoLatitudeBonplan.getText())));
        g.setZoom(15);

    }

    @FXML
    private void BtnVoterRating(ActionEvent event) {
        
     

        System.out.println("je suis dans NoSuchElement");
        Double ratingVal = Double.valueOf(NoteLabel.getText());
        User u = new User();
        //Videodiy vid = new Videodiy();
        Bonplan Bp = new Bonplan();
        u.setId(SingninController.userIden);
        Bp.setIdBonplan(LBUC.selectionedBonplan.getIdBonplan());
        System.out.println("l id du bonplan est "+Bp.getIdBonplan());
        RBP.setRating(ratingVal);
        RBP.setId_bonplan(Bp);
        RBP.setId_user(u);
        RateBPS.AjouterNote(RBP);
        RateBPS.UpdateMoyeneBonPlan(Bp.getIdBonplan());
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("INFORMATION");
            alert.setContentText("Votre vote a etait enregistrer avec succés ");
            alert.show();
        
      
    }

}
