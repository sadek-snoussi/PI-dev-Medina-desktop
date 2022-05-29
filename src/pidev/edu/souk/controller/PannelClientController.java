/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import static pidev.edu.souk.controller.PannelUserController.iduser2;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class PannelClientController implements Initializable {

    public static int idusers = 0;
    @FXML
    private HBox hboxmenu;
    @FXML
    private Button accueil;
    @FXML
    private MenuButton menudemande;
    @FXML
    private MenuItem btDemande;
    @FXML
    private MenuItem btDemande2;
    @FXML

    private Button forum;
    @FXML
    private HBox hboximage;
    @FXML
    private ImageView souk;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView login;
    @FXML
    private ImageView LogoutBox;
    @FXML
    private AnchorPane container_client;
    @FXML
    private Label iduser;
    @FXML
    private MenuItem btactual;

    public AnchorPane getContainer_client() {
        return container_client;
    }

    public void setContainer_client(AnchorPane container_client) {
        this.container_client = container_client;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idusers = SingninController.userIden;
        login.setVisible(true);
        LogoutBox.setVisible(true);
        panier.setVisible(true);
        //hboxout.setVisible(true);
        souk.setVisible(true);
    }

    @FXML
    private void ListProduit(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ProductForClients.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            ProductForClientsController sprods = loader.getController();
            //showVideo.setIdUser(Integer.valueOf(iduser.getText()));
            //System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void demanderPartenariatPro(ActionEvent event) {

        PartenaireService ps = new PartenaireService();

        User user = ps.finUserById(idusers);
        if ((user.getTypeUser().equals("pro")) || (user.getTypeUser().equals("freelancer"))) {
            TrayNotification tray = new TrayNotification("Rejected",
                    "Vous avez déjà passer une demande de partenariat \n Veuillez Attendre la confirmation de l'Administrateur ", NotificationType.ERROR);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

        } else {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ajouterDemandePro.fxml"));
                Parent root = loader.load();
                container_client.getChildren().setAll(root);
                AjouterDemandeProController ajoutDemandePro = loader.getController();
                ajoutDemandePro.setIduser(SingninController.userIden);

                // menudemande.setVisible(false);
            } catch (IOException ex) {

            }
        }
    }

    @FXML
    private void demanderPartenariatFree(ActionEvent event) {

        PartenaireService ps = new PartenaireService();

        User user = ps.finUserById(idusers);
        if ((user.getTypeUser().equals("pro")) || (user.getTypeUser().equals("freelancer"))) {
            TrayNotification tray = new TrayNotification("Successfully",
                    "Vous avez déjà passer une demande de partenariat \n Veuillez Attendre la confirmation de l'Administrateur ", NotificationType.ERROR);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

        } else {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ajouterDemandefreelancer.fxml"));
                Parent root = loader.load();
                container_client.getChildren().setAll(root);
                AjouterDemandefreelancerController ajoutDemandeFree = loader.getController();

                ajoutDemandeFree.setIduser(SingninController.userIden);

                //menudemande.setVisible(false);
                //iduser.getScene().setRoot(root);
            } catch (IOException ex) {

            }
        }
    }

    @FXML
    private void forum(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/afficheVideoClientFXML.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            AfficheVideoClientFXMLController showVideo = loader.getController();
            showVideo.setIdUser(SingninController.userIden);
            System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherPanier(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListPanier.fxml"));
            Parent root = loader.load();
            ListPanierController paniers = loader.getController();
            container_client.getChildren().setAll(root);

            //paniers.setIdUser(Integer.valueOf(iduser.getText()));
            //System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void logDirection(MouseEvent event) {
        try {

            User user = new User();

            String requete = "select * from User where id='" + SingninController.userIden + "'";
            Statement st = null;
            try {
                st = MyConnection.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(requete);

                while (rs.next()) {
                    user.setNomUser(rs.getString("nomUser"));
                    user.setPrenomUser(rs.getString("prenomUser"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setTelUser(rs.getString("telUser"));
                    user.setTelBureauPro(rs.getString("telBureauPro"));
                    user.setSpecialitePart(rs.getString("specialitePart"));
                    user.setNomEntreprisePro(rs.getString("nomEntreprisePro"));
                    user.setGradePro(rs.getString("gradePro"));
                    user.setRoles(rs.getString("roles"));
                    user.setDateNaissUser(rs.getDate("dateNaissUser"));

                }
                System.out.println(user.getRoles());
                if (user.getRoles().equals("a:1:{i:0;s:15:\"ROLE_FREELANCER\";}")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ProfileFree.fxml"));
                    Parent root = loader.load();
                    container_client.getChildren().setAll(root);
                    ProfileFreeController modifProfileFree = loader.getController();
                    modifProfileFree.setIduser(Integer.parseInt(iduser.getText()));
                    modifProfileFree.setEmail(user.getEmail());
                    modifProfileFree.setNom(user.getNomUser());
                    modifProfileFree.setPrenom(user.getPrenomUser());
                    modifProfileFree.setNumP(user.getTelBureauPro());
                    modifProfileFree.setSpecialite(user.getSpecialitePart());

                    // iduser.getScene().setRoot(root);
                } else if (user.getRoles().equals("a:1:{i:0;s:8:\"ROLE_PRO\";}")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ProfilePro.fxml"));
                    Parent root = loader.load();
                    container_client.getChildren().setAll(root);
                    ProfileProController modifProfilePro = loader.getController();
                    modifProfilePro.setIduser(Integer.parseInt(iduser.getText()));

                    modifProfilePro.setEmail(user.getEmail());
                    modifProfilePro.setGrade(user.getGradePro());
                    modifProfilePro.setNom(user.getNomUser());
                    modifProfilePro.setPrenom(user.getPrenomUser());
                    modifProfilePro.setNomE(user.getNomEntreprisePro());
                    modifProfilePro.setNumBureau(user.getTelBureauPro());
                    modifProfilePro.setSpecialite(user.getSpecialitePart());
                    //iduser.getScene().setRoot(root);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ProfileClient.fxml"));
                    Parent root = loader.load();
                    container_client.getChildren().setAll(root);
                    ProfileClientController modifProfileClient = loader.getController();
                    modifProfileClient.setIduser(SingninController.userIden);

                    modifProfileClient.setEmail(user.getEmail());
                    modifProfileClient.setNom(user.getNomUser());
                    modifProfileClient.setNum(user.getTelUser());
                    modifProfileClient.setPrenom(user.getPrenomUser());
                    modifProfileClient.setUsername(user.getUsername());

                    System.out.println(user.getDateNaissUser());
                    LocalDate d = LocalDate.of(user.getDateNaissUser().getYear() + 1900,
                            user.getDateNaissUser().getMonth() + 1, user.getDateNaissUser().getDate());

                    System.out.println(d.getMonth().getValue());
                    System.out.println(d.getYear());
                    System.out.println(d.getDayOfMonth());
                    modifProfileClient.setDateNaiss(d);

                }

            } catch (SQLException ex) {
                Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LogoutBox(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
            Parent root = loader.load();
            SingninController sc = loader.getController();

            forum.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadActualite(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ActualiteEvent2FXML.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            ActualiteEvent2FXMLController actualite = loader.getController();
            actualite.setIduserEvent(SingninController.userIden);
            /*  System.out.println(iduser);
           SingninController.userIden=Integer.valueOf(iduser.getText());
            System.out.println(userid);*/
            //btactual.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void BonPlanUser(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListBonplanUser.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GallerieUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListGallerieUser.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GuideUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListGuideUser.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void xxxxx(ActionEvent event) {
        
         
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/accueilPanelClient.fxml"));
            Parent root = loader.load();
              
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


}
