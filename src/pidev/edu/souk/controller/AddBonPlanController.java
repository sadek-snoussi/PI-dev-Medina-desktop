/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;



import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.management.Notification;
import netscape.javascript.JSObject;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.BonplanService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AddBonPlanController implements Initializable, MapComponentInitializedListener {
    
    private Notification Notifications;
    String path;
    File selectedFile;
    GoogleMap g;
    
    private GeocodingService geocodingService;
    
    private StringProperty address = new SimpleStringProperty();
    
    Boolean verificationUserNom = false;
    Boolean verificationAdresse = false;
    Boolean verificationLongitude = true;
    Boolean verificationLatitude = true;
    Boolean verificationTypeBonplan = false;
    Boolean verificationAvis = false;
    
    @FXML
    private AnchorPane LayoutGuide1;
    @FXML
    private TextField nombonplan;
    
    @FXML
    private TextField Longitude;
    @FXML
    private TextField Latitude;
    @FXML
    private ComboBox<String> typeBonplan;
    private ComboBox<Integer> avisBonplan;
    @FXML
    private Button imgBonplan;
    @FXML
    private GoogleMapView map;
   
    @FXML
    private TextField addressTextField;
    
    ObservableList<String> listTypeBonPlan = FXCollections.observableArrayList("Restaurant", "Salon de thé", "Musée", "Bien être", "Site naturelle", "Cinéma");
    //ObservableList<Integer> listAvieBonPlan = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    
    BonplanService bonplanService = new BonplanService();
    @FXML
    private Button BtnAjouterBonPlan;
    @FXML
    private ImageView TickBonplan1;
    @FXML
    private ImageView TickBonplan11;
    @FXML
    private ImageView TickBonplan111;
    @FXML
    private ImageView TickBonplan112;
    @FXML
    private ImageView TickBonplan1121;
    private ImageView TickBonplan1122;
    @FXML
    private ImageView TickBonplan1123;
    @FXML
    private Button BtnAjouterBonPlan1;
    @FXML
    private TextField AdressePick;

    private TextField testnote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombonplan.setVisible(true);
        addressTextField.setVisible(false);
        Longitude.setVisible(true);
        Latitude.setVisible(true);
        map.setVisible(false);
      
        
        typeBonplan.setItems(listTypeBonPlan);
//        avisBonplan.setItems(listAvieBonPlan);
        
        TickBonplan1.setVisible(false);
        TickBonplan11.setVisible(false);
        TickBonplan111.setVisible(false);
        TickBonplan112.setVisible(false);
        TickBonplan1121.setVisible(false);
//        TickBonplan1122.setVisible(false);
        TickBonplan1123.setVisible(false);
        BtnAjouterBonPlan1.setVisible(false);
        
        map.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
    }
    
    @FXML
    private void ajouterBonPlan(ActionEvent event) {
        
        Bonplan Bn = new Bonplan();
        
        if (verificationUserNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le nom");
            alert.show();
            
        } else if (verificationAdresse == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir l'addresse");
            alert.show();
        } else if (verificationLongitude == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir la Longitude");
            alert.show();
        } else if (verificationLatitude == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir la Latitude");
            alert.show();
            
        } else if (verificationTypeBonplan == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez choisir le type du Bonplan");
            alert.show();
        }  else {
            
            Bn.setNombonplan(nombonplan.getText());
            Bn.setAdresseBonplan(AdressePick.getText());
            Bn.setTypeBonplan(typeBonplan.getValue());
            //Bn.setAvisBonplan(avisBonplan.getValue());
            Bn.setImgBonplan(path);
            Bn.setLongitude(Double.parseDouble(Longitude.getText()));
            Bn.setLatitude(Double.parseDouble(Latitude.getText()));
            User u = new User();
            u.setId(SingninController.userIden);
            Bn.setId_user(u);
            
            TickBonplan1.setVisible(true);
            TickBonplan11.setVisible(true);
            TickBonplan111.setVisible(true);
            TickBonplan112.setVisible(true);
            TickBonplan1121.setVisible(true);
//            TickBonplan1122.setVisible(true);
            TickBonplan1123.setVisible(true);
            BtnAjouterBonPlan1.setVisible(true);
            
            System.out.println(Bn.toString());
            
            BonplanService bs = new BonplanService();
            bs.addBonplan(Bn);
            
            TrayNotification tray = new TrayNotification("Successfully", "bonplan " + Bn.getNombonplan() + " " + Bn.getAdresseBonplan() + " Ajoutée avec succés !", SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
        }
        
    }
    
    @FXML
    private void controlNom(KeyEvent event) {
        
        if (nombonplan.getText().trim().equals("")) {
            
            verificationUserNom = false;
            TickBonplan1.setVisible(false);
            
        } else {
            
            verificationUserNom = true;
            TickBonplan1.setVisible(true);
        }
    }
    
    @FXML
    private void AjouterimgBonplan(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        // selectedfile de type file
        selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile != null) {
            
            path = selectedFile.getName();
//          path = selectedFile.toURI().toURL().toExternalForm();
            imgBonplan.setText(path);
            
        };
        
        if (selectedFile != null) {
            try {
                //System.out.println(selectedFile.toString());
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\imgBonplan\\");
                
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void controlAdresse(KeyEvent event) {
        
        if (AdressePick.getText().trim().equals("")) {
            
            verificationAdresse = false;
            TickBonplan11.setVisible(false);
            
        } else {
            
            verificationAdresse = true;
            TickBonplan11.setVisible(true);
        }
    }
    
    @FXML
    private void controlLongitude(KeyEvent event) {
        if (Longitude.getText().trim().equals("")) {
            
            verificationLongitude = false;
            TickBonplan111.setVisible(false);
            
        } else {
            
            verificationLongitude = true;
            TickBonplan111.setVisible(true);
        }
    }
    
    @FXML
    private void controlLatitude(KeyEvent event) {
        if (Latitude.getText().trim().equals("")) {
            
            verificationLatitude = false;
            TickBonplan112.setVisible(false);
            
        } else {
            
            verificationLatitude = true;
            TickBonplan112.setVisible(true);
        }
        
    }
    
    @FXML
    private void ControlTypeBonplan(ActionEvent event) {
        if (typeBonplan.getValue() == null) {
            
            verificationTypeBonplan = false;
            TickBonplan1121.setVisible(false);
            
        } else {
            
            verificationTypeBonplan = true;
            TickBonplan1121.setVisible(true);
        }
    }
    
    private Marker marker;
    
        
    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        //3maltha abstrait khater nheb nbadel kima nheb ok bb :) bye 
        MapOptions options = new MapOptions(); //bech n7adedou chniyaa fehaa ptption
        
        options.center(new LatLong(36.8189700, 10.1657900))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .mapMaker(true)
                .zoom(25);
        g = map.createMap(options);
        
        LatLong cord = new LatLong(36.8189700, 10.1657900);
        MarkerOptions Options = new MarkerOptions();
        Options.position(cord)
                .visible(Boolean.TRUE)
                .title("My Marker")
                .animation(Animation.BOUNCE);
        marker = new Marker(Options);
        g.addMarker(marker);
        g.setCenter(new LatLong(36.8189700, 10.1657900));
        g.setZoom(15);
        
        g.addUIEventHandler(com.lynden.gmapsfx.javascript.event.UIEventType.click, (event) -> {
        
        g.removeMarker(marker);
        LatLong latLong = new LatLong((JSObject)event.getMember("latLng"));
        
        
        
            try {
                URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                        + latLong.getLatitude() + "," + latLong.getLongitude() + "&sensor=true");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                String formattedAddress = "";
                
                try {
                    InputStream in = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String result, line = reader.readLine();
                    result = line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                    
                    JSONParser parser = new JSONParser();
                    JSONObject rsp = (JSONObject) parser.parse(result);
                    
                    if (rsp.containsKey("results")) {
                        JSONArray matches = (JSONArray) rsp.get("results");
                        JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                        formattedAddress = (String) data.get("formatted_address");
                    }
                    AdressePick.setText(formattedAddress);
                    System.out.println(formattedAddress);
                    
                } catch (ParseException ex) {
                Logger.getLogger(AddBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException iOException) {
            }
        
        
        
        
        
            
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLong).visible(true);
        marker =new Marker(markerOptions);
        g.addMarker(marker);
        Latitude.setText(Double.toString(latLong.getLatitude()));
        Longitude.setText(Double.toString(latLong.getLongitude()));
        
        
        });
        
        
    }
    

    
    @FXML
    private void AfficherMap(MouseEvent event) {
        map.setVisible(true);
      
        addressTextField.setVisible(true);
    }
    
    @FXML
    private void addressTextFieldAction(ActionEvent event) {
        geocodingService.geocode(address.get(), (com.lynden.gmapsfx.service.geocoding.GeocodingResult[] results, com.lynden.gmapsfx.service.geocoding.GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if (status == com.lynden.gmapsfx.service.geocoding.GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            g.setCenter(latLong);
            
        });
    }
    
    @FXML
    private void ajouterBonPlan1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddBonPlan.fxml"));
        Parent root = loader.load();
        LayoutGuide1.getChildren().setAll(root);
    }
    
    @FXML
    private void mapAddress(ActionEvent event) {
        
    }
    
    
}
