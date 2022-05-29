/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.gui.InfoPartController;
import pidev.edu.souk.services.AdminService;
import pidev.edu.souk.services.PartenaireService;
import pidev.edu.souk.test.Main;
import sun.plugin2.jvm.RemoteJVMLauncher;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ListPartenairesAdminController implements Initializable {

    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> specialite;
    @FXML
    private TableView<User> listPartAdmin;
    @FXML
    private MenuItem deleteUsers;
    @FXML
    private MenuItem modifier;
    @FXML
    private MenuItem details;
    @FXML
    private DialogPane dialog;
    @FXML
    private Hyperlink close_dialog_btn;
    @FXML
    private ImageView imagV;
    @FXML
    private Label nomD;
    @FXML
    private Label prenomD;
    @FXML
    private Label telD;
    @FXML
    private Label roleD;
    @FXML
    private Label specialiteD;
    @FXML
    private Label emailD;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;

    @FXML
    private Button stats;
    @FXML
    private Button detailButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private DialogPane pannelDialogMod;
    @FXML
    private Label label11;
    @FXML
    private Label label21;
    @FXML
    private Label label61;
    @FXML
    private TextField nomText;
    @FXML
    private TextField prenomText;
    @FXML
    private TextField emailText;
    @FXML
    private Label label41;
    @FXML
    private TextField nomEText;
    @FXML
    private Label label31;
    @FXML
    private TextField telText;
    @FXML
    private Label label71;
    @FXML
    private TextField roleText;
    @FXML
    private Label label81;
    @FXML
    private TextField specialiteText;
    @FXML
    private Button dialogmodif;
    @FXML
    private Hyperlink close_dialog_btn1;

    public static int identifiant = 0;

    public Label getNomD() {
        return nomD;
    }

    public void setNomD(String nomD) {
        this.nomD.setText(nomD);
    }

    public Label getPrenomD() {
        return prenomD;
    }

    public void setPrenomD(String prenomD) {
        this.prenomD.setText(prenomD);
    }

    public Label getTelD() {
        return telD;
    }

    public void setTelD(String telD) {
        this.telD.setText(telD);
    }

    public Label getRoleD() {
        return roleD;
    }

    public void setRoleD(String roleD) {
        this.roleD.setText(roleD);
    }

    public Label getSpecialiteD() {
        return specialiteD;
    }

    public void setSpecialiteD(String specialiteD) {
        this.specialiteD.setText(specialiteD);
    }

    public Label getEmailD() {
        return emailD;
    }

    public void setEmailD(String emailD) {
        this.emailD.setText(emailD);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsers();
        nom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telBureauPro"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialitePart"));
        role.setCellValueFactory(new PropertyValueFactory<>("typeUser"));
        // action.setCellFactory(cellFactory);
        listPartAdmin.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        dialog.setVisible(false);
        close_dialog_btn.setVisible(false);
        imagV.setVisible(false);

        nomD.setVisible(false);
        prenomD.setVisible(false);
        roleD.setVisible(false);
        specialiteD.setVisible(false);
        telD.setVisible(false);
        emailD.setVisible(false);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        stats.setVisible(false);

        pannelDialogMod.setVisible(false);

        label11.setVisible(false);
        label21.setVisible(false);
        label31.setVisible(false);
        label41.setVisible(false);
        label61.setVisible(false);
        label71.setVisible(false);
        label81.setVisible(false);

        nomText.setVisible(false);
        prenomText.setVisible(false);
        telText.setVisible(false);
        emailText.setVisible(false);
        nomEText.setVisible(false);
        roleText.setVisible(false);
        specialiteText.setVisible(false);
        dialogmodif.setVisible(false);
        close_dialog_btn.setVisible(false);
        close_dialog_btn1.setVisible(false);

    }

    private void loadUsers() {
        AdminService as = new AdminService();
        ArrayList<User> Partenaires = as.listPartenaireAdmin();
        ObservableList observableList = FXCollections.observableArrayList(Partenaires);
        listPartAdmin.setItems(observableList);
    }

    @FXML
    private void deletePart(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        System.out.println(users);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation Dialog");
        alert.setHeaderText(null);

        alert.setContentText("are you sure to delete selected users? ");
        Optional<ButtonType> action2 = alert.showAndWait();
        AdminService as = new AdminService();
        if (action2.get() == ButtonType.OK) {
            as.supprimerClient(users);

        }
        loadUsers();

    }

    @FXML
    private void updatePart(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        pannelDialogMod.setVisible(true);
        //dialog.setVisible(true);
        close_dialog_btn1.setVisible(true);

        label11.setVisible(true);
        label21.setVisible(true);
        label31.setVisible(true);
        label41.setVisible(true);
        label61.setVisible(true);
        label71.setVisible(true);
        label81.setVisible(true);

        nomText.setVisible(true);
        prenomText.setVisible(true);
        telText.setVisible(true);
        emailText.setVisible(true);
        nomEText.setVisible(true);
        roleText.setVisible(true);
        specialiteText.setVisible(true);
        dialogmodif.setVisible(true);

        nomText.setText(user.getNomUser());
        prenomText.setText(user.getPrenomUser());
        System.out.println("teeeeeeeeeeeeeellll" + user.getTelBureauPro());
        telText.setText(user.getTelBureauPro());
        nomEText.setText(user.getNomEntreprisePro());
        emailText.setText(user.getEmail());
        roleText.setText(user.getTypeUser());
        specialiteText.setText(user.getSpecialitePart());
    }

    @FXML
    private void detailsPart(ActionEvent event) {

        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        dialog.setVisible(true);
        close_dialog_btn.setVisible(true);

        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\logo\\" + user.getUrlLogoPro());

        // System.out.println(user.toString());
        imagV.setImage(img);

        imagV.setVisible(true);
        imagV.setFitWidth(210);
        imagV.setPreserveRatio(true);
        imagV.setSmooth(true);
        imagV.setCache(true);

        nomD.setVisible(true);
        prenomD.setVisible(true);
        roleD.setVisible(true);
        specialiteD.setVisible(true);
        telD.setVisible(true);
        emailD.setVisible(true);

        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        stats.setVisible(true);

        nomD.setText(user.getNomUser());
        prenomD.setText(user.getPrenomUser());
        telD.setText(user.getTelBureauPro());
        emailD.setText(user.getEmail());
        roleD.setText(user.getTypeUser());
        specialiteD.setText(user.getSpecialitePart());

    }

    @FXML
    private void close_dialog(ActionEvent event) {

        dialog.setVisible(false);
        close_dialog_btn.setVisible(false);
        imagV.setVisible(false);

        nomD.setVisible(false);
        prenomD.setVisible(false);
        roleD.setVisible(false);
        specialiteD.setVisible(false);
        telD.setVisible(false);
        emailD.setVisible(false);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        stats.setVisible(false);

    }

    @FXML
    private void GenererStat(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();
        identifiant = user.getId();
        //System.out.println(user.toString());
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));
//
//            Parent root = loader.load();
//            PannelAdminController pannelAdmin = loader.getController();
//
//            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/infoPart.fxml"));
//
//            Parent root2 = loader.load();
//
//            InfoPartController infoPart = loader2.getController();
//
//            infoPart.setIduser(user.getId());
//            pannelAdmin.getContainer_admin().getChildren().setAll(root2);
//            label11.getScene().setRoot(root);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelAdmin.fxml"));

            System.out.println(loader.getCharset());
            Parent root = loader.load();

            PannelAdminController infoPart = loader.getController();

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/infoPart.fxml"));

            Parent root2 = loader2.load();
            InfoPartController infoPart2 = loader2.getController();

            infoPart.getContainer_admin().getChildren().setAll(root2);

            stats.getScene().setRoot(root);

//            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void detailsButton(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        dialog.setVisible(true);
        close_dialog_btn.setVisible(true);

        Image img;
        img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\logo\\" + user.getUrlLogoPro());

        // System.out.println(user.toString());
        imagV.setImage(img);

        imagV.setVisible(true);
        imagV.setFitWidth(210);
        imagV.setPreserveRatio(true);
        imagV.setSmooth(true);
        imagV.setCache(true);

        nomD.setVisible(true);
        prenomD.setVisible(true);
        roleD.setVisible(true);
        specialiteD.setVisible(true);
        telD.setVisible(true);
        emailD.setVisible(true);

        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        stats.setVisible(true);

        nomD.setText(user.getNomUser());
        prenomD.setText(user.getPrenomUser());
        telD.setText(user.getTelBureauPro());
        emailD.setText(user.getEmail());
        roleD.setText(user.getTypeUser());
        specialiteD.setText(user.getSpecialitePart());

    }

    @FXML
    private void deleteButton(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        System.out.println(users);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation Dialog");
        alert.setHeaderText(null);

        alert.setContentText("are you sure to delete selected users? ");
        Optional<ButtonType> action2 = alert.showAndWait();
        AdminService as = new AdminService();
        if (action2.get() == ButtonType.OK) {
            as.supprimerClient(users);

        }
        loadUsers();
    }

    @FXML
    private void init(MouseEvent event) {
        try {

            //listPartAdmin.selectionModelProperty().isNull();
            //  Boolean b=(boolean) listPartAdmin.getSelectionModel().selectedIndexProperty().isEqualTo(0).getValue();
            Boolean b = false;
            System.out.println(b + "********************");

            if (b == false) {
                deleteButton.setDisable(true);
                detailButton.setDisable(true);
                updateButton.setDisable(true);

            }

        } catch (NullPointerException ex) {

        }
    }

    @FXML
    private void onselection(MouseEvent event) {
        User user;
        user = listPartAdmin.getSelectionModel().getSelectedItem();
        System.out.println(user.toString());
        if (!user.isEnabled()) {

            deleteButton.setDisable(false);
            detailButton.setDisable(false);
            updateButton.setDisable(false);

        }

    }

    @FXML
    private void updateButton(ActionEvent event) {
        List<User> users = listPartAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        pannelDialogMod.setVisible(true);
        //dialog.setVisible(true);
        close_dialog_btn1.setVisible(true);

        label11.setVisible(true);
        label21.setVisible(true);
        label31.setVisible(true);
        label41.setVisible(true);
        label61.setVisible(true);
        label71.setVisible(true);
        label81.setVisible(true);

        nomText.setVisible(true);
        prenomText.setVisible(true);
        telText.setVisible(true);
        emailText.setVisible(true);
        nomEText.setVisible(true);
        roleText.setVisible(true);
        specialiteText.setVisible(true);
        dialogmodif.setVisible(true);

        nomText.setText(user.getNomUser());
        prenomText.setText(user.getPrenomUser());
        telText.setText(user.getTelBureauPro());
        nomEText.setText(user.getNomEntreprisePro());
        emailText.setText(user.getEmail());
        roleText.setText(user.getTypeUser());
        specialiteText.setText(user.getSpecialitePart());
    }

    @FXML
    private void dialogmodif(ActionEvent event) {
        User user = listPartAdmin.getSelectionModel().getSelectedItem();
        //System.out.println("++++++++*************" + user.getId());
        user.setNomUser(nomText.getText());
        user.setPrenomUser(prenomText.getText());
        user.setNomEntreprisePro(nomEText.getText());
        user.setTelBureauPro(telText.getText());
        user.setEmail(emailText.getText());
        user.setTypeUser(roleText.getText());
        user.setSpecialitePart(specialiteText.getText());
        System.out.println(user.toString() + "*****************");
        PartenaireService ps = new PartenaireService();
        if (user.getTypeUser().equals("pro")) {
            ps.modifierProfilePro(user, user.getId());
            System.out.println(user.toString() + "**********proooooo*******");
        } else if (user.getTypeUser().equals("freelancer")) {

            ps.modifierProfileFree(user, user.getId());
            System.out.println(user.toString() + "*******freeeeee**********");
        }
        loadUsers();
    }

    @FXML
    private void close_dialog_modif(ActionEvent event) {
        pannelDialogMod.setVisible(false);

        close_dialog_btn1.setVisible(false);
        label11.setVisible(false);
        label21.setVisible(false);
        label31.setVisible(false);
        label41.setVisible(false);
        label61.setVisible(false);
        label71.setVisible(false);
        label81.setVisible(false);

        nomText.setVisible(false);
        prenomText.setVisible(false);
        telText.setVisible(false);
        emailText.setVisible(false);
        nomEText.setVisible(false);
        roleText.setVisible(false);
        specialiteText.setVisible(false);
        dialogmodif.setVisible(false);

    }

}
