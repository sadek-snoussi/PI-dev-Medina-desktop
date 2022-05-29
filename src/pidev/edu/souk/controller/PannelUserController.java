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
import java.util.ArrayList;
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
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class PannelUserController implements Initializable {

    @FXML
    private Label iduser;
    @FXML
    private HBox hboximage;
    @FXML
    private HBox hboxmenu;
    @FXML
    private AnchorPane container_client;
    @FXML
    private ImageView souk;
    @FXML
    private Button accueil;
    private MenuButton menudemande;
    @FXML
    private ImageView panier;
    @FXML
    private ImageView login;

    @FXML
    private ImageView LogoutBox;

    public static int iduser2 = 0;
    @FXML
    private Button forum;
    @FXML
    private MenuButton espacePart;
    @FXML
    private MenuItem modifProfile;
    @FXML
    private MenuItem videos;
    @FXML
    private MenuItem ajoutVideo;
    @FXML
    private MenuItem btactual;
    @FXML
    private MenuItem btreserver;
    @FXML
    private MenuItem MesProd_btn;
    @FXML
    private MenuItem MonStock_Btn;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser.setText(Integer.toString(iduser));
    }

    public AnchorPane getContainer_client() {
        return container_client;
    }

    public void setContainer_client(AnchorPane container_client) {
        this.container_client = container_client;
    }

    public void notshow() {

        PartenaireService ps = new PartenaireService();

        System.out.println("----- " + iduser.getText());

        User user = ps.finUserById(SingninController.userIden);
        iduser2 = SingninController.userIden;
        System.out.println(SingninController.userIden);
        System.out.println("**********" + iduser.getText());

        System.out.println(user.getTypeUser() + "********************");
        /*if ("pro".equals(user.getTypeUser()) || "freelancer".equals(user.getTypeUser())) {
            menudemande.setVisible(false);
            //forum.setVisible(false);
            //forum2.setVisible(true);
            espacePart.setVisible(true);
        } else if ("client".equals(user.getTypeUser())) {
            menudemande.setVisible(true);
            espacePart.setVisible(false);
            //profileclient.setVisible(true);
        }*/

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iduser.setVisible(false);
        container_client.setVisible(true);
       // menudemande.setVisible(true);

        //profileclient.setVisible(false);
        forum.setVisible(true);
//        forum2.setVisible(false);
        espacePart.setVisible(true);
        // profileclient.setVisible(false);
        LogoutBox.setVisible(true);

        System.out.println("+++++ " + SingninController.userIden);

        notshow();
    }

    private void demanderPartenariatPro(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ajouterDemandePro.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            AjouterDemandeProController ajoutDemandePro = loader.getController();
            ajoutDemandePro.setIduser(Integer.parseInt(iduser.getText()));

            menudemande.setVisible(false);

        } catch (IOException ex) {

        }
    }

    private void demanderPartenariatFree(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ajouterDemandefreelancer.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            AjouterDemandefreelancerController ajoutDemandeFree = loader.getController();

            ajoutDemandeFree.setIduser(Integer.parseInt(iduser.getText()));

            menudemande.setVisible(false);

            //iduser.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void modifierProfile(ActionEvent event) {

        try {

            User user = new User();

            String requete = "select * from User where id='" + Integer.parseInt(iduser.getText()) + "'";
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
                    modifProfileClient.setIduser(Integer.parseInt(iduser.getText()));

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

    private void connecter(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/singnin.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SignupBox(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/signup.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
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
                    modifProfileFree.setIduser(SingninController.userIden);
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
                    modifProfilePro.setIduser(SingninController.userIden);

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
    private void afficherPanier(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListPanier.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ListProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ProductForClients.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
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

            accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forum(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/afficheVideoClientFXML.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            AfficheVideoClientFXMLController showVideo = loader.getController();
            showVideo.setIdUser(Integer.valueOf(iduser.getText()));
            System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void videosPartenaire(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AffichageVideoPartenaire.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addVideo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/addVideoFXML.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
            AddVideoFXMLController addvid = loader.getController();
            addvid.setIdUser(PannelUserController.iduser2);
            System.out.println("je suis entrein d'afficher l'd du user " + iduser.getText());
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
            actualite.setIduserEvent(Integer.parseInt(iduser.getText()));
            /*  System.out.println(iduser);
           SingninController.userIden=Integer.valueOf(iduser.getText());
            System.out.println(userid);*/
            //btactual.getScene().setRoot(root);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void loadstand(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/EventStandListFXML.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);

            EventStandListFXMLController reserver = loader.getController();
            reserver.setIduser(Integer.parseInt(iduser.getText()));
            //  userid = Integer.valueOf(iduser.getText());
            // System.out.println(userid);

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
    private void MesProduits(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListProductPartner.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    

    @FXML
    private void MonStock(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/GestionStock.fxml"));
            Parent root = loader.load();
            container_client.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(PannelUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    }

