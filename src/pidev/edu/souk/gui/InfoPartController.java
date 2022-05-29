/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.edu.souk.controller.ListPartenairesAdminController;

import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.services.AdminService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class InfoPartController implements Initializable {

    @FXML
    private BarChart<Produit, String> ProduitsChart;
    @FXML
    private NumberAxis NumberPAxis;
    @FXML
    private CategoryAxis DatePAxis;
    @FXML
    private Label iduser;

    public static String Janvier = "Janvier";
    public static String Fevrier = "Février";
    public static String Mars = "Mars";
    public static String Avril = "Avril";
    public static String Mai = "Mai";
    public static String Juin = "Juin";
    public static String Juillet = "Juillet";
    public static String Aout = "Aout";
    public static String Septembre = "Septembre";
    public static String Octobre = "Octobre";
    public static String Novembre = "Novembre";
    public static String Decembre = "Decembre";
    public static Integer JanvierP = 0;
    public static Integer FevrierP = 0;
    public static Integer MarsP = 0;
    public static Integer AvrilP = 0;
    public static Integer MaiP = 0;
    public static Integer JuinP = 0;
    public static Integer JuilletP = 0;
    public static Integer AoutP = 0;
    public static Integer SeptembreP = 0;
    public static Integer OctobreP = 0;
    public static Integer NovembreP = 0;
    public static Integer DecembreP = 0;

    public static Integer JanvierVendu = 0;
    public static Integer FevrierVendu = 0;
    public static Integer MarsVendu = 0;
    public static Integer AvrilVendu = 0;
    public static Integer MaiVendu = 0;
    public static Integer JuinVendu = 0;
    public static Integer JuilletVendu = 0;
    public static Integer AoutVendu = 0;
    public static Integer SeptembreVendu = 0;
    public static Integer OctobreVendu = 0;
    public static Integer NovembreVendu = 0;
    public static Integer DecembreVendu = 0;

    public static Integer JanvierV = 0;
    public static Integer FevrierV = 0;
    public static Integer MarsV = 0;
    public static Integer AvrilV = 0;
    public static Integer MaiV = 0;
    public static Integer JuinV = 0;
    public static Integer JuilletV = 0;
    public static Integer AoutV = 0;
    public static Integer SeptembreV = 0;
    public static Integer OctobreV = 0;
    public static Integer NovembreV = 0;
    public static Integer DecembreV = 0;
    @FXML
    private TableView<Produit> listProduits;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, String> taille;
    @FXML
    private TableColumn<Produit, String> Matiere;
    @FXML
    private TableColumn<Produit, Double> prix;
    @FXML
    private TableColumn<Produit, Integer> qtevendu;
    @FXML
    private TableView<Videodiy> listVideos;
    @FXML
    private TableColumn<Videodiy, String> titre;
    @FXML
    private TableColumn<Videodiy, String> duree;
    @FXML
    private TableColumn<Videodiy, String> dateExpo;
    @FXML
    private TableColumn<Videodiy, String> description;
    @FXML
    private Label totalp;
    @FXML
    private Label totalV;
    @FXML
    private Label label2;
    @FXML
    private Label label1;
    @FXML
    private AnchorPane anchorid;

    public Label getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser.setText(String.valueOf(iduser));
    }

    AdminService as = new AdminService();

    User user = as.finUserById(ListPartenairesAdminController.identifiant);
    //   User user = as.finUserById(1);
    ArrayList<Produit> Produits = as.listProduitsByUser(user);

    ArrayList<Videodiy> Videos = as.listVideosByUser(user);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitsChart.setScaleY(1);

        ObservableList observableListp = FXCollections.observableArrayList(Produits);
        listProduits.setItems(observableListp);
        ObservableList observableListv = FXCollections.observableArrayList(Videos);
        listVideos.setItems(observableListv);

        nom.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        taille.setCellValueFactory(new PropertyValueFactory<>("tailleProduit"));
        Matiere.setCellValueFactory(new PropertyValueFactory<>("matiereProduit"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prixVenteProduit"));
        qtevendu.setCellValueFactory(new PropertyValueFactory<>("qteVenduProduit"));

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        duree.setCellValueFactory(new PropertyValueFactory<>("dureeVideo"));
        dateExpo.setCellValueFactory(new PropertyValueFactory<>("dateExpoVideo"));
        description.setCellValueFactory(new PropertyValueFactory<>("descriptionVideo"));

        XYChart.Series Produits_Dispo = new XYChart.Series();
        XYChart.Series Produits_Vendu = new XYChart.Series<>();
        XYChart.Series Videos_Post = new XYChart.Series<>();

        Produits_Dispo.setName("Produits Disponible");
        Produits_Vendu.setName("Produis Vendus");
        Videos_Post.setName("Videos Postées");

        for (Produit elem : Produits) {
            System.out.println("++++++++++" + elem);
            if (elem.getDateExpoProduit().getMonth() == 1) {
                JanvierP += elem.getQteDispoProduit();
                JanvierVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 2) {
                FevrierP += elem.getQteDispoProduit();
                FevrierVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 3) {
                MarsP += elem.getQteDispoProduit();
                MarsVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 4) {
                AvrilP += elem.getQteDispoProduit();
                AvrilVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 5) {
                MaiP += elem.getQteDispoProduit();
                MaiVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 6) {
                JuinP += elem.getQteDispoProduit();
                JuinVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 7) {
                JuilletP += elem.getQteDispoProduit();
                JuilletVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 8) {
                AoutP += elem.getQteDispoProduit();
                AoutVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 9) {
                SeptembreP += elem.getQteDispoProduit();
                SeptembreVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 10) {
                OctobreP += elem.getQteDispoProduit();
                OctobreVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 11) {
                NovembreP += elem.getQteDispoProduit();
                NovembreVendu += elem.getQteVenduProduit();

            }
            if (elem.getDateExpoProduit().getMonth() == 12) {
                DecembreP += elem.getQteDispoProduit();
                DecembreVendu += elem.getQteVenduProduit();

            }

            Produits_Dispo.getData().add(new XYChart.Data(Janvier, JanvierP));
            Produits_Dispo.getData().add(new XYChart.Data(Fevrier, FevrierP));
            Produits_Dispo.getData().add(new XYChart.Data(Mars, MarsP));
            Produits_Dispo.getData().add(new XYChart.Data(Avril, AvrilP));
            Produits_Dispo.getData().add(new XYChart.Data(Mai, MaiP));
            Produits_Dispo.getData().add(new XYChart.Data(Juin, JuinP));
            /* Produits_Dispo.getData().add(new XYChart.Data(Juillet, JuilletP));
            Produits_Dispo.getData().add(new XYChart.Data(Aout, AoutP));
            Produits_Dispo.getData().add(new XYChart.Data(Septembre, SeptembreP));
            Produits_Dispo.getData().add(new XYChart.Data(Octobre, OctobreP));
            Produits_Dispo.getData().add(new XYChart.Data(Novembre, NovembreP));
            Produits_Dispo.getData().add(new XYChart.Data(Decembre, DecembreP));*/

            Produits_Vendu.getData().add(new XYChart.Data(Janvier, JanvierVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Fevrier, FevrierVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Mars, MarsVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Avril, AvrilVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Mai, MaiVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Juin, JuilletVendu));
            /* Produits_Vendu.getData().add(new XYChart.Data(Juillet, JuilletVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Aout, AoutVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Septembre, SeptembreVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Octobre, OctobreVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Novembre, NovembreVendu));
            Produits_Vendu.getData().add(new XYChart.Data(Decembre, DecembreVendu));*/

        }

        for (Videodiy elem : Videos) {
            if (elem.getDateExpoVideo().getMonth() == 1) {
                JanvierV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 2) {
                FevrierV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 3) {
                MarsV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 4) {
                AvrilV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 5) {
                MaiV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 6) {
                JuinV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 7) {
                JuilletV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 8) {
                AoutV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 9) {
                SeptembreV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 10) {
                OctobreV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 11) {
                NovembreV += 1;

            }
            if (elem.getDateExpoVideo().getMonth() == 12) {
                DecembreV += 1;

            }
            Videos_Post.getData().add(new XYChart.Data(Janvier, JanvierV));
            Videos_Post.getData().add(new XYChart.Data(Fevrier, FevrierV));
            Videos_Post.getData().add(new XYChart.Data(Mars, MarsV));
            Videos_Post.getData().add(new XYChart.Data(Avril, AvrilV));
            Videos_Post.getData().add(new XYChart.Data(Mai, MaiV));
            Videos_Post.getData().add(new XYChart.Data(Juin, JuinV));
            /*Videos_Post.getData().add(new XYChart.Data(Juillet, JuilletV));
            Videos_Post.getData().add(new XYChart.Data(Aout, AoutV));
            Videos_Post.getData().add(new XYChart.Data(Septembre, SeptembreV));
            Videos_Post.getData().add(new XYChart.Data(Octobre, OctobreV));
            Videos_Post.getData().add(new XYChart.Data(Novembre, NovembreV));
            Videos_Post.getData().add(new XYChart.Data(Decembre, DecembreV));*/

        }
        ProduitsChart.getData().addAll(Produits_Dispo, Produits_Vendu, Videos_Post);

   int totalqteProdVendu = 0;
        int totalvid = 0;
        for (Produit prod : Produits) {
            totalqteProdVendu += prod.getQteVenduProduit();

        }
        for (Videodiy vid : Videos) {
            totalvid += 1;
        }
        totalp.setText(String.valueOf(totalqteProdVendu));
        totalV.setText(String.valueOf(totalvid));

    }

}
