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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.services.ProductService;
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class EditProductController implements Initializable {

    @FXML
    private ComboBox<String> categ_prod;
    @FXML
    private TextField nom_prod;
    @FXML
    private TextField ref_prod;
    @FXML
    private TextField matiere_prod;
    @FXML
    private TextField prixBase_prod;
    @FXML
    private TextField prixVente_prod;
    @FXML
    private DatePicker DateExpiration;
    @FXML
    private TextField Qte_dispo_prod;
    @FXML
    private Button edit_prod_btn;
    @FXML
    private TextField id_prod;
    @FXML
    private Hyperlink back_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        id_prod.setVisible(false);
        
        String req="SELECT * FROM CATEGORIE";
        Statement st;
        try {
            
            st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);     
            
            ObservableList<String> comboCat = FXCollections.observableArrayList();
            
            while (rs.next()) {
            comboCat.add(rs.getString("nomcategorie"));
            }
            
            categ_prod.setItems(comboCat);

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }    

    @FXML
    private void edit_product(ActionEvent event) throws SQLException {
        
        
            String requete="SELECT * FROM CATEGORIE WHERE NOMCATEGORIE='"
                +categ_prod.getValue()+"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet result= st.executeQuery(requete);
            Categorie cat=new Categorie();

            while (result.next()) {
            cat.setIdCategorie(result.getInt(1));
            cat.setNomCategorie(result.getString(2));
            cat.setTypeCategorie(result.getString(3));
                
        }
        
        Produit p = new Produit();
        
        
        //                java.sql.Date date = java.sql.Date.valueOf(DateExpiration.getValue());

                p.setIdProduit(Integer.parseInt(id_prod.getText()));
                p.setNomProduit(nom_prod.getText());
                p.setMatiereProduit(matiere_prod.getText());
                p.setPrixBaseProduit(Double.parseDouble(prixBase_prod.getText()));
                p.setPrixVenteProduit(Double.parseDouble(prixVente_prod.getText()));           
                //p.getDateExpirationProduit(date);
                p.setReferenceProd(ref_prod.getText());  
                p.setQteDispoProduit(Integer.parseInt(Qte_dispo_prod.getText())); 
                p.setIdCategorie(cat);
                
        
        
        ProductService prodServ=new ProductService();
        prodServ.update(p,p.getIdProduit());
        
            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getNomProduit()+" a été Modifié avec succés");
            alert.show();
            //***********************************
        
        
    }
    
    
    
    
        @FXML
    private void back(ActionEvent event) throws IOException {
        
        
            
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/ListProductPartner.fxml"));
            Parent root = loader.load();
            ListProductPartnerController listprodpartnerCtrl = loader.getController();
            id_prod.getScene().setRoot(root);
            
            
            
    }

    
    
    
    //**************************************************************************
    //**************************************************************************
    
    

    public ComboBox<String> getCateg_prod() {
        return categ_prod;
    }

    public void setCateg_prod(ComboBox<String> categ_prod) {
        this.categ_prod = categ_prod;
    }

    public TextField getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(TextField nom_prod) {
        this.nom_prod = nom_prod;
    }

    public TextField getRef_prod() {
        return ref_prod;
    }

    public void setRef_prod(TextField ref_prod) {
        this.ref_prod = ref_prod;
    }

    public TextField getMatiere_prod() {
        return matiere_prod;
    }

    public void setMatiere_prod(TextField matiere_prod) {
        this.matiere_prod = matiere_prod;
    }

    public TextField getPrixBase_prod() {
        return prixBase_prod;
    }

    public void setPrixBase_prod(TextField prixBase_prod) {
        this.prixBase_prod = prixBase_prod;
    }

    public TextField getPrixVente_prod() {
        return prixVente_prod;
    }

    public void setPrixVente_prod(TextField prixVente_prod) {
        this.prixVente_prod = prixVente_prod;
    }

    public DatePicker getDateExpiration() {
        return DateExpiration;
    }

    public void setDateExpiration(DatePicker DateExpiration) {
        this.DateExpiration = DateExpiration;
    }

    public TextField getQte_dispo_prod() {
        return Qte_dispo_prod;
    }

    public void setQte_dispo_prod(TextField Qte_dispo_prod) {
        this.Qte_dispo_prod = Qte_dispo_prod;
    }

    public Button getEdit_prod_btn() {
        return edit_prod_btn;
    }

    public void setEdit_prod_btn(Button edit_prod_btn) {
        this.edit_prod_btn = edit_prod_btn;
    }

    public TextField getId_prod() {
        return id_prod;
    }

    public void setId_prod(TextField id_prod) {
        this.id_prod = id_prod;
    }




    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
