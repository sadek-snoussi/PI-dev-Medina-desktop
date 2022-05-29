/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.AdminService;
import pidev.edu.souk.services.PartenaireService;

/**
 * FXML Controller class
 *
 * @author amalb
 */
public class ListClientsAdminController implements Initializable {

    @FXML
    private TableView<User> listClientAdmin;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private Button detailsButton;
    @FXML
    private Button deleteButton;
    @FXML
    private DialogPane dialog;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label6;
    @FXML
    private Label nomD;
    @FXML
    private Label prenomD;
    @FXML
    private Label telD;
    @FXML
    private Label emailD;
    @FXML
    private ImageView imagV;
    @FXML
    private Hyperlink close_dialog_btn;
    @FXML
    private Label label11;
    @FXML
    private Label label21;
    @FXML
    private Label label31;
    @FXML
    private Label label61;
    @FXML
    private Button dialogmodif;
    @FXML
    private TextField nomText;
    @FXML
    private TextField prenomText;
    @FXML
    private TextField telText;
    @FXML
    private TextField emailText;
    @FXML
    private Hyperlink close_dialog_btn1;
    @FXML
    private DialogPane pannelDialogMod;
    @FXML
    private Label label41;
    @FXML
    private TextField usernameText;
    @FXML
    private Button updateButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsers();

        nom.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenomUser"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telUser"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        dialog.setVisible(false);
        close_dialog_btn.setVisible(false);
        imagV.setVisible(false);
        
        nomD.setVisible(false);
        prenomD.setVisible(false);
        telD.setVisible(false);
        emailD.setVisible(false);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label6.setVisible(false);
        
        
          close_dialog_btn1.setVisible(false);
        
        label11.setVisible(false);
        label21.setVisible(false);
        label31.setVisible(false);
        label41.setVisible(false);
        label61.setVisible(false);
       
        nomText.setVisible(false);
        prenomText.setVisible(false);
        telText.setVisible(false);
        emailText.setVisible(false);
        usernameText.setVisible(false);
        dialogmodif.setVisible(false);
        
      
        pannelDialogMod.setVisible(false);

    }

    @FXML
    private void deleteClients(ActionEvent event) {
        List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
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

    private void loadUsers() {
        AdminService as = new AdminService();
        ArrayList<User> Partenaires = as.listClientAdmin();
        ObservableList observableList = FXCollections.observableArrayList(Partenaires);
        listClientAdmin.setItems(observableList);
    }

    @FXML
    private void updateClient(ActionEvent event) {
           List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

         pannelDialogMod.setVisible(true);
        //dialog.setVisible(true);
        close_dialog_btn1.setVisible(true);
        
          label11.setVisible(true);
        label21.setVisible(true);
        label31.setVisible(true);
        label41.setVisible(true);
        label61.setVisible(true);
       
        nomText.setVisible(true);
        prenomText.setVisible(true);
        telText.setVisible(true);
        usernameText.setVisible(true);
        emailText.setVisible(true);
        dialogmodif.setVisible(true);
        
        nomText.setText(user.getNomUser());
        prenomText.setText(user.getPrenomUser());
        telText.setText(user.getTelUser());
        usernameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
    }

    @FXML
    private void detailsClient(ActionEvent event) {
             List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        dialog.setVisible(true);
        close_dialog_btn.setVisible(true);
      imagV.setVisible(true);
        nomD.setVisible(true);
        prenomD.setVisible(true);
       
        telD.setVisible(true);
        emailD.setVisible(true);

        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label6.setVisible(true);
        
         nomD.setText(user.getNomUser());
        prenomD.setText(user.getPrenomUser());
        telD.setText(user.getTelUser());
        emailD.setText(user.getEmail());
    }

    @FXML
    private void detailsButton(ActionEvent event) {
           List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

        dialog.setVisible(true);
        close_dialog_btn.setVisible(true);
      imagV.setVisible(true);
        nomD.setVisible(true);
        prenomD.setVisible(true);
       
        telD.setVisible(true);
        emailD.setVisible(true);

        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label6.setVisible(true);
        
         nomD.setText(user.getNomUser());
        prenomD.setText(user.getPrenomUser());
        telD.setText(user.getTelUser());
        emailD.setText(user.getEmail());
       

    }

    @FXML
    private void deleteButton(ActionEvent event) {
        List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
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
                detailsButton.setDisable(true);
                 updateButton.setDisable(true);

            }

        } catch (NullPointerException ex) {

        }
    }

    @FXML
    private void onselection(MouseEvent event) {
        User user;
        user = listClientAdmin.getSelectionModel().getSelectedItem();
        System.out.println(user.toString());
        if (!user.isEnabled()) {

            deleteButton.setDisable(false);
            detailsButton.setDisable(false);
            updateButton.setDisable(false);

        }
    }

    @FXML
    private void close_dialog(ActionEvent event) {
        
        dialog.setVisible(false);
        close_dialog_btn.setVisible(false);
        imagV.setVisible(false);

        nomD.setVisible(false);
        prenomD.setVisible(false);
       
        telD.setVisible(false);
        emailD.setVisible(false);

        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
       
        label6.setVisible(false);
    }

    @FXML
    private void dialogmodif(ActionEvent event) {
        User user =listClientAdmin.getSelectionModel().getSelectedItem();
        System.out.println("++++++++*************"+user.getId());
        user.setNomUser(nomText.getText());
        user.setPrenomUser(prenomText.getText());
        user.setUsername(usernameText.getText());
        user.setTelUser(telText.getText());
        user.setEmail(emailText.getText());
        PartenaireService ps=new PartenaireService();
        ps.modifierProfileClient(user, user.getId());
         loadUsers();
    }

    @FXML
    private void close_dialog_modif(ActionEvent event) {
         
        close_dialog_btn1.setVisible(false);
        

      pannelDialogMod.setVisible(false);

       label11.setVisible(false);
        label21.setVisible(false);
        label31.setVisible(false);
        label41.setVisible(false);
        label61.setVisible(false);
       
        nomText.setVisible(false);
        prenomText.setVisible(false);
        telText.setVisible(false);
        usernameText.setVisible(false);
        emailText.setVisible(false);
        dialogmodif.setVisible(false);

    }

    @FXML
    private void updateButton(ActionEvent event) {
          List<User> users = listClientAdmin.getSelectionModel().getSelectedItems();
        User user = users.stream().findFirst().get();

         pannelDialogMod.setVisible(true);
        //dialog.setVisible(true);
        close_dialog_btn1.setVisible(true);
        
          label11.setVisible(true);
        label21.setVisible(true);
        label31.setVisible(true);
        label41.setVisible(true);
        label61.setVisible(true);
       
        nomText.setVisible(true);
        prenomText.setVisible(true);
        telText.setVisible(true);
        usernameText.setVisible(true);
        emailText.setVisible(true);
        dialogmodif.setVisible(true);
        
        nomText.setText(user.getNomUser());
        prenomText.setText(user.getPrenomUser());
        telText.setText(user.getTelUser());
        usernameText.setText(user.getUsername());
        emailText.setText(user.getEmail());
    }

}
