/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import pidev.edu.souk.entities.Commentaire;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.PartageFB;
import pidev.edu.souk.services.RatingService;
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DetailRatedVideoController implements Initializable {

  @FXML
    private BorderPane borderpaneVideo;
    @FXML
    private StackPane stackPane;
    @FXML
    private MediaView videoView;
    @FXML
    private Slider sliderVideo;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button slowButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button fastButton;
    @FXML
    private Slider slider;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private TextArea tfComment;
    @FXML
    private Button btnPost;
    @FXML
    private ListView<Commentaire> commentList;

    @FXML
    private Label idUser;
    @FXML
    private Label idVideo;
    @FXML
    private Label ratingValue;
    @FXML
    private org.controlsfx.control.Rating rating;
    @FXML
    private Label idUserCnct;

    pidev.edu.souk.entities.Rating note = new pidev.edu.souk.entities.Rating();
    RatingService ratingService = new RatingService();

    private MediaPlayer videoPlayer;

   // AfficheVideoClientFXMLController videoList = new AfficheVideoClientFXMLController();
    AccueilPanelClientController videoList = new AccueilPanelClientController();

    private ObservableList<Commentaire> data = FXCollections.observableArrayList();

    String idVid = "" + videoList.selectedVideo.getIdVideo() + "";

    int video = videoList.selectedVideo.getIdVideo();
    int user = SingninController.userIden;
    @FXML
    private Button fbShare;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * ********************************
         */

        idUser.setVisible(false);
        idUserCnct.setVisible(false);
        idVideo.setVisible(false);
        loadData();
        retrieveRating();
        idUserCnct.setText(String.valueOf(PannelClientController.idusers));

        //user=Integer.valueOf(idUserCnct.getText());
        //  System.out.println("je suis a " + a);
        /**
         * *******rating*****
         */
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ratingValue.setText("" + newValue.toString());
            }
        });

        idUserCnct.setText(String.valueOf(PannelClientController.idusers));

        /**
         * ************
         */
        System.out.println(idUser.getText());

        //commentList.setVisible(false);
        Media videoMedia = new Media("file:///C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/" + videoList.selectedVideo.getVideo());

        videoPlayer = new MediaPlayer(videoMedia);

        videoView.setMediaPlayer(videoPlayer);

        idVideo.setText(idVid);

        User user = new User();

        user = getIdUser(idVid);

        idUser.setText(String.valueOf(user.getId()));

        System.out.println("ZZZZZZZZZZZZZZZZZ" + idUser.getText().trim());
        System.out.println("test " + PannelClientController.idusers);

        //titre.setText (videoList.selectedVideo.getTitre());
        description.setText(videoList.selectedVideo.getDescriptionVideo());

        slider.setValue(videoPlayer.getVolume() * 100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable
            ) {
                videoPlayer.setVolume(slider.getValue() / 100);
            }
        }
        );

        videoPlayer.currentTimeProperty()
                .addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
                            Duration newValue
                    ) {
                        sliderVideo.setValue(newValue.toSeconds());
                    }

                }
                );
        sliderVideo.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event
            ) {
                videoPlayer.seek(Duration.seconds(sliderVideo.getValue()));
            }

        }
        );

        // TODO
    }

    private void rateVideo() {
        
//        Double x = Double.valueOf(ratingValue.getText());
//        System.out.println("je suis x " + x);
//        if (x == 0.0){
//            System.out.println("je suis dans if");
//            Double ratingVal = Double.valueOf(ratingValue.getText());
//            User u = new User();
//            Videodiy vid = new Videodiy();
//            u.setId(user);
//            vid.setIdVideo(video);
//            note.setRating(ratingVal);
//            note.setUserId(u);
//            note.setVideoId(vid);
//            ratingService.ajouterNote(note);
//
//        } else {
//            
//            System.out.println("je suis dans else");
//            Double ratingVal = Double.valueOf(ratingValue.getText());
//            ratingService.modifierNote(user, video, ratingVal);
//        }
        
        //******************************************* new try
        
            try {
            note = ratingService.suspendre(user, video);
            System.out.println("je suis dans try");
            Double ratingVal = Double.valueOf(ratingValue.getText());
            ratingService.modifierNote(user, video, ratingVal);

        } catch (NoSuchElementException ex) {
            System.out.println("je suis dans NoSuchElement");
            Double ratingVal = Double.valueOf(ratingValue.getText());
            User u = new User();
            Videodiy vid = new Videodiy();
            u.setId(user);
            vid.setIdVideo(video);
            note.setRating(ratingVal);
            note.setUserId(u);
            note.setVideoId(vid);
            ratingService.ajouterNote(note);
        }

    }

    private void retrieveRating() {

        try {
            note = ratingService.suspendre(user, video);
            rating.setRating(note.getRating());
            System.out.println("entrer dans boucle if");

        } catch (NoSuchElementException ex) {
            System.out.println("je suis dans la merde");
            rating.setRating(0);
            ratingValue.setText("0.0");
        }

    }

    private void loadData() {
        data.addAll(afficherCommentaires(idVid));
        commentList.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
            @Override
            public ListCell<Commentaire> call(ListView<Commentaire> param) {
                ListCell<Commentaire> cell = new ListCell<Commentaire>() {
                    Label comment = new Label("");

                    @Override
                    protected void updateItem(Commentaire c, boolean btl) {
                        super.updateItem(c, btl);
                        setGraphic(null);
                        if (c != null) {

                            comment.setText(c.toString());
                            //  System.out.println(v.getIdVideo());
                            setGraphic(comment);
                        }
                    }

                };
                return cell;
            }
        });

        commentList.setItems(data);
    }

    @FXML
    private void playVideo(ActionEvent event) {
        videoPlayer.play();
        videoPlayer.setRate(1);
    }

    @FXML
    private void pauseVideo(ActionEvent event) {
        videoPlayer.pause();
    }

    @FXML
    private void slowVideo(ActionEvent event) {
        videoPlayer.setRate(.5);

    }

    @FXML
    private void stopVideo(ActionEvent event) {
        videoPlayer.stop();
    }

    @FXML
    private void fastVideo(ActionEvent event) {
        videoPlayer.setRate(1.5);

    }

    @FXML
    private void postComment(ActionEvent event) {

        String requete = "INSERT INTO commentaire (contenuCommentaire,	id_user, id_video)"
                + " VALUES (?,?,?)";

        PreparedStatement prst;
        try {
            prst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            prst.setString(1, tfComment.getText());
            prst.setInt(2, SingninController.userIden);
            prst.setString(3, idVideo.getText());

            prst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DetailVideoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        data.clear();

        loadData();
    }

    private User getUserNameFromId(String userID) {

        User user = new User();
        try {
            String requete3 = "select * from user where id=" + userID;

            // String id = idUser.getText();
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement

            while (rs.next()) {

                user.setNomUser(rs.getString(2));
                System.out.println(rs.getString(2));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return user;
    }

    private User getIdUser(String videoID) {
        Videodiy video = new Videodiy();
        User user = new User();
        Statement st3;
        try {
            String requete = "select id_user from videodiy where idVideo=" + videoID;
            st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete);
            while (rs.next()) {

                user.setId(rs.getInt(1));
                System.out.println(rs.getInt(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DetailVideoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    private ObservableList<Commentaire> afficherCommentaires(String videoID) {
        ObservableList<Commentaire> listCommentaire = FXCollections.observableArrayList();

        try {
            String requete3 = "select * from commentaire where id_video=" + videoID;
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);//contenaire n7ot fih eli yji mel base les enregistrement
            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                commentaire.setContenuCommentaire(rs.getString(4));
                commentaire.setIdUser(getUserNameFromId(rs.getString(2)));
                //User u = new User() ;
                //u.setNomUser("who cares -_- ");
                //commentaire.setIdUser(u);
                listCommentaire.add(commentaire);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return listCommentaire;
    }

    public Label getIdUser() {
        return idUser;
    }

    public void setIdUser(int iduser) {
        this.idUser.setText(Integer.toString(iduser));
    }

    public Label getIdUserCnct() {
        return idUserCnct;
    }

    public void setIdUserCnct(int idUserCnct) {
        this.idUserCnct.setText(Integer.toString(idUserCnct));
    }

    @FXML
    private void fbShare(ActionEvent event) throws FileNotFoundException {
        
        String titre = videoList.selectedVideo.getTitre();
        String descreption = videoList.selectedVideo.getDescriptionVideo();
        String video = "C:/xampp/htdocs/Medina_VersionFinale/web/uploads/videos/" + videoList.selectedVideo.getVideo();
        //9adech tayo howa video deja howa video ya5o wa9t bech yaploadi
        //700ko uiui 7kaya far8a eyy mafhemtech aaleh ( ahayka taswira l aal bbureau ok 
        
        PartageFB partagefb= new PartageFB();
        System.out.println("video "+video);
        partagefb.partager(titre   , descreption, video);
        
        
    }

    @FXML
    private void Noter(ActionEvent event) {
        
           try {
            note = ratingService.suspendre(user, video);
            System.out.println("je suis dans try");
            Double ratingVal = Double.valueOf(ratingValue.getText());
            ratingService.modifierNote(user, video, ratingVal);

        } catch (NoSuchElementException ex) {
            System.out.println("je suis dans NoSuchElement");
            Double ratingVal = Double.valueOf(ratingValue.getText());
            User u = new User();
            Videodiy vid = new Videodiy();
            u.setId(user);
            vid.setIdVideo(video);
            note.setRating(ratingVal);
            note.setUserId(u);
            note.setVideoId(vid);
            ratingService.ajouterNote(note);
        }
    }
}
