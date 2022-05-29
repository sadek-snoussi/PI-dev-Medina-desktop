/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.services.ProductService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ListProductPartnerController implements Initializable {

    @FXML
    private TableView<Produit> table_prod;
    @FXML
    private TableColumn<Produit, String> ref_prod;
    @FXML
    private TableColumn<Produit, Date> date_prod;
    @FXML
    private TableColumn<Produit, String> nom_prod;
    @FXML
    private TableColumn<Produit,Categorie> categ_prod;
    @FXML
    private TableColumn<Produit,String> matiere_prod;
    @FXML
    private TableColumn<Produit,Integer> status_prod;
    @FXML
    private Button add_btn;
    @FXML
    private Button remove_btn;
    @FXML
    private Button edit_btn;
    
    
    
     ProductService prodServ = new ProductService();   
     final ObservableList produits =(ObservableList) prodServ.list();
     
     private final IntegerProperty index=new SimpleIntegerProperty();
     Produit p =new Produit();
     
     
     
     
    @FXML
    private Button Details_prod_btn;
    @FXML
    private Hyperlink close_dialog_btn;
    @FXML
    private DialogPane dialog;
    @FXML
    private Label detail_nom;
    @FXML
    private Label detail_ref;
    @FXML
    private Label detail_date_expo;
    @FXML
    private Label detail_categorie;
    @FXML
    private Label detail_matiere;
    @FXML
    private Label detail_prix;
    @FXML
    private Label detail_quantite;
    @FXML
    private Label detail_date_expir;
    @FXML
    private Label detail_dateExpir_title;
    @FXML
    private Label detail_ref_title;
    @FXML
    private Label detail_date_expo_title;
    @FXML
    private Label detail_categorie_title;
    @FXML
    private Label detail_matiere_title;
    @FXML
    private Label detail_prix_title;
    @FXML
    private Label detail_quantite_title;
    @FXML
    private Button add_cart_btn;
    @FXML
    private Button add_wishlist;
    @FXML
    private VBox vbox;
    @FXML
    private TableColumn<Produit,Date> dateExpir_prod;
    @FXML
    private TextField tf_recherche;
    @FXML
    private Label selectBtnWarning;
    @FXML
    private Button OkayWarning;
    private AnchorPane warningBox;
    @FXML
    private Button warning_btn;
    @FXML
    private Label selectBtnWarningNom;
    @FXML
    private Label continuer;
    @FXML
    private Label title_warning;
    @FXML
    private AnchorPane warningBox_remove;
    @FXML
    private AnchorPane warningBox_edit;
    @FXML
    private Label editBtnWarning;
    @FXML
    private Label continuer_edit;
    @FXML
    private Button editfinal_btn;
    @FXML
    private Button OkayWarning1;
    @FXML
    private Label EditBtnWarningNom;
    @FXML
    private Label title_warning1;
    @FXML
    private AnchorPane main;

     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table_prod.setVisible(true);
        add_btn.setVisible(true);
        remove_btn.setVisible(true);
        edit_btn.setVisible(true);
        dialog.setVisible(false);
        close_dialog_btn.setVisible(false);
        
        
        
       
        
                    detail_nom.setVisible(false);
                    
                    detail_ref_title.setVisible(false);
                    detail_ref.setVisible(false);

                    detail_date_expo_title.setVisible(false);
                    detail_date_expo.setVisible(false);

                    detail_categorie_title.setVisible(false);
                    detail_categorie.setVisible(false);
                    
                    detail_matiere_title.setVisible(false);
                    detail_matiere.setVisible(false);
                    
                    detail_prix_title.setVisible(false);
                    detail_prix.setVisible(false);
                    
                    detail_quantite_title.setVisible(false);
                    detail_quantite.setVisible(false);
                    
                    detail_dateExpir_title.setVisible(false);
                    detail_date_expir.setVisible(false);
        
                    
                    

                    
        
        ref_prod.setCellValueFactory(new PropertyValueFactory<>("referenceProd"));
        date_prod.setCellValueFactory(new PropertyValueFactory<>("dateExpoProduit"));
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));

        matiere_prod.setCellValueFactory(new PropertyValueFactory<>("matiereProduit"));
        //photo_prod.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>());
        
        //status_prod.setCellValueFactory(new PropertyValueFactory<>("validiteProduit"));   
        
            
        categ_prod.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        categ_prod.setCellFactory(new Callback<TableColumn<Produit, Categorie>, TableCell<Produit, Categorie>>() {
            @Override
            public TableCell<Produit, Categorie> call(TableColumn<Produit, Categorie> param) {

                TableCell<Produit, Categorie> catCell = new TableCell<Produit, Categorie>(){

                @Override
                protected void updateItem(Categorie item, boolean empty) {
                    if (item != null) {
                        Label catLabel = new Label(item.getNomCategorie());
                        setGraphic(catLabel);
                    }
                }                    
            };               

            return catCell;                
                
                
                
            }
        });
        
        
        
        dateExpir_prod.setCellValueFactory(new PropertyValueFactory<>("dateExpirationProduit"));
//        dateExpir_prod.setCellFactory(new Callback<TableColumn<Produit, Date>, TableCell<Produit, Date>>() {
//            @Override
//            public TableCell<Produit, Date> call(TableColumn<Produit, Date> param) {
//
//                TableCell<Produit, Date> DateExpirCell = new TableCell<Produit, Date>(){
//
//                protected void updateItem(Produit item, boolean empty) {
//                    if (item != null && item.getDateExpirationProduit()!=null) {
//                        Label dateExpirLabel = new Label(item.getDateExpirationProduit().toString());
//                        setGraphic(dateExpirLabel);
//                    }else{
//                        if(item.getDateExpirationProduit()==null){
//                            
//                        Label NodateExpirLabel = new Label();
//                        NodateExpirLabel.setText("Produit non-Expirable.");
//                        setGraphic(NodateExpirLabel);
//                        }
//                    }
//                }                    
//            };               
//
//            return DateExpirCell;                
//                
//                
//                
//            }
 //       });
        
        

        status_prod.setCellValueFactory(new PropertyValueFactory<>("validiteProduit"));
        status_prod.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
            @Override
            public TableCell<Produit, Integer> call(TableColumn<Produit, Integer> param) {

                return new TableCell<Produit, Integer>/* statusCell = new TableCell<Produit, Integer>()*/(){

                            
                    
                protected void updateItem(Integer item, boolean empty) {
                    if (item != null ) {
                        
                            Label statusLabel = new Label();
                            statusLabel.setVisible(true);




                        if(item==0){
                            statusLabel.setText("En Cours.");
                            statusLabel.setStyle("-fx-background-color: #FF9900;");

                        }
                         if(item==1){
                            statusLabel.setText("Non-Verifié.");
                            statusLabel.setStyle("-fx-background-color: #ee4545;");

                        }
                        if(item==2){
                            statusLabel.setText("Vérifié.");
                            statusLabel.setStyle("-fx-background-color: #5CB85C;");

                        }                   
                        
                       
                        setGraphic(statusLabel);
                    }
                }                    
            };               

            //return statusCell;                
                
                
                
            }
        });

        
        table_prod.setItems(produits);
        
        table_prod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
            
                
            index.set(produits.indexOf(newValue));           
            System.out.println(produits.indexOf(newValue));
            
            p=(Produit) produits.get(index.get());
            
           System.out.println(p);


            }
   
    });    

    } 
    
    
    
    
    
    
    //------------------------------------------------------------------------
    //------------------------------Buttons------------------------------------------
    
    
    
    @FXML
    private void add_prod(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/PannelUser.fxml"));
            Parent root = loader.load();
            PannelUserController pannelUser = loader.getController();
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/AddProduct.fxml"));
            Parent root2 = loader2.load();
            pannelUser.getContainer_client().getChildren().setAll(root2);
            //AddProductController addprodCtrl = loader2.getController();
            table_prod.getScene().setRoot(root);
    }

    @FXML
    private void remove_prod(ActionEvent event) {
        
        int i = index.get();
        System.out.println(i);
        if(i>-1){
            System.out.println("*******************************");
            prodServ.delete(p.getIdProduit());
            produits.remove(i);
            table_prod.getSelectionModel().clearSelection();
            warningBox_remove.setVisible(false);
            table_prod.setDisable(false);

            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getNomProduit()+" a été supprimé avec succés");
            alert.show();
            //***********************************
         }else{
            if(i<0){
            warningBox.setVisible(true);
            selectBtnWarning.setText("Veuillez s'il vous plait selectionner la ligne à supprimer !");
        }
        }
        
    }

    @FXML
    private void edit_prod(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/edu/souk/gui/EditProduct.fxml"));
        Parent root = loader.load();
        EditProductController editProdCtrl = loader.getController();
        
        editProdCtrl.getRef_prod().setText(p.getReferenceProd());
        editProdCtrl.getNom_prod().setText(p.getNomProduit());
//        editProdCtrl.getCateg_prod().setValue(p.getIdCategorie().getNomCategorie());
        editProdCtrl.getMatiere_prod().setText(p.getMatiereProduit());
        editProdCtrl.getPrixBase_prod().setText(String.valueOf( p.getPrixBaseProduit()));
        editProdCtrl.getPrixVente_prod().setText(String.valueOf(p.getPrixVenteProduit()));
        //editProdCtrl.getDateExpiration().setValue(p.getDateExpirationProduit());
        editProdCtrl.getQte_dispo_prod().setText(p.getQteDispoProduit().toString());
        editProdCtrl.getId_prod().setText(p.getIdProduit().toString());
        
        table_prod.getScene().setRoot(root);
        
        
    }

    @FXML
    private void Details_prod(ActionEvent event) {
        
                    dialog.setVisible(true);
                    close_dialog_btn.setVisible(true);
                
                    add_cart_btn.setVisible(true);
                    add_wishlist.setVisible(true);
                
                    detail_ref_title.setVisible(true);
                    detail_date_expo_title.setVisible(true);
                    detail_categorie_title.setVisible(true);
                    detail_matiere_title.setVisible(true);
                    detail_prix_title.setVisible(true);
                    detail_quantite_title.setVisible(true);
                    detail_dateExpir_title.setVisible(true);
                
                
                
                    vbox.setVisible(true);
                    Image img;
                    img = new Image("file:C:\\wamp64\\www\\Medina\\web\\uploads\\ImgProduit\\"+p.getUrlImgProduit());


                    
                    ImageView imgView=new ImageView(img);
                    
                    imgView.setFitWidth(210);
                    imgView.setPreserveRatio(true);
                    imgView.setSmooth(true);
                    imgView.setCache(true);
                    
                    
                    vbox.getChildren().add(imgView);
                    
                    detail_nom.setVisible(true);
                    detail_nom.setText(p.getNomProduit());
 
                    detail_ref.setVisible(true);
                    detail_ref.setText(p.getReferenceProd());

                    detail_date_expo.setVisible(true);
                    detail_date_expo.setText(p.getDateExpoProduit().toString());

                    detail_categorie.setVisible(true);
                    //detail_categorie.setText(p.getIdCategorie().getNomCategorie());

                    detail_matiere.setVisible(true);
                    detail_matiere.setText(p.getMatiereProduit());

                    detail_prix.setVisible(true);
                    detail_prix.setText(String.valueOf(p.getPrixVenteProduit())+"  DT");

                    detail_quantite.setVisible(true);
                    detail_quantite.setText(p.getQteDispoProduit().toString());

                    
                    
                    if(p.getDateExpirationProduit()!=null){
                    detail_dateExpir_title.setVisible(true);
                    detail_date_expir.setVisible(true);
                    detail_date_expir.setText(p.getDateExpirationProduit().toString());
                    }else{
                    detail_dateExpir_title.setVisible(false);    
                    detail_date_expir.setVisible(false);

                    }
                        
                
                
                
                
                
                
                


    }

    @FXML
    private void close_dialog(ActionEvent event) {
        
                        dialog.setVisible(false);
                        close_dialog_btn.setVisible(false);
        
                        add_cart_btn.setVisible(false);
                        add_wishlist.setVisible(false);
        
        
                    detail_ref_title.setVisible(false);
                    detail_date_expo_title.setVisible(false);
                    detail_categorie_title.setVisible(false);
                    detail_matiere_title.setVisible(false);
                    detail_prix_title.setVisible(false);
                    detail_quantite_title.setVisible(false);
                    detail_dateExpir_title.setVisible(false);
                    
                    vbox.getChildren().removeAll(vbox.getChildren());
                    vbox.setVisible(false);

        
                    detail_nom.setVisible(false);
                    detail_ref.setVisible(false);
                    detail_date_expo.setVisible(false);
                    detail_categorie.setVisible(false);
                    detail_matiere.setVisible(false);
                    detail_prix.setVisible(false);
                    detail_quantite.setVisible(false);
        
        

    }

    @FXML
    private void rechercheProd(KeyEvent event) {
        
        produits.clear();
        table_prod.refresh();
        produits.addAll(prodServ.rechercheStock(1, tf_recherche.getText()));
        
        
        
        
    }

    @FXML
    private void CloseWarning(ActionEvent event) {
        
        warningBox_remove.setVisible(false);
        table_prod.setDisable(false);

       
        
    }

    @FXML
    private void show_warning(ActionEvent event) {

        if(p.getNomProduit()!=null){
            
        selectBtnWarning.setText("Vous êtes sur le point de supprimer le produit");
        selectBtnWarning.setVisible(true);
        selectBtnWarningNom.setText(p.getNomProduit()+" .Cette Operation sera Définitive.");
        selectBtnWarningNom.setVisible(true);
        warningBox_remove.setVisible(true);
        continuer.setVisible(true);
        remove_btn.setVisible(true);
        OkayWarning.setText("Non.");
        table_prod.setDisable(true);

                 
        }else{
            
            title_warning.setText("Error.");
            selectBtnWarningNom.setText("Veuillez selectionner la ligne à supprimer.");
            selectBtnWarning.setVisible(false);
            remove_btn.setVisible(false);
            OkayWarning.setText("Fermer");
            warningBox_remove.setVisible(true);
            continuer.setVisible(false);
            table_prod.setDisable(true);

        }
    }

    @FXML
    private void showEditWarning(ActionEvent event) {
        
         
        
        if(p.getNomProduit()!=null){
            
        title_warning1.setText("Warning.");
        editBtnWarning.setText("Vous êtes sur le point de Modifier le produit");
        editBtnWarning.setVisible(true);
        EditBtnWarningNom.setText(p.getNomProduit()+". Il sera de nouveau en Attente.");
        EditBtnWarningNom.setVisible(true);
        warningBox_edit.setVisible(true);
        continuer_edit.setVisible(true);
        editfinal_btn.setVisible(true);
        OkayWarning1.setText("Non.");
        table_prod.setDisable(false);

        
        }else{
            
            title_warning1.setText("Error.");
            EditBtnWarningNom.setText("Veuillez selectionner la ligne à Modifier.");
            editBtnWarning.setVisible(false);
            editfinal_btn.setVisible(false);
            OkayWarning1.setText("Fermer");
            warningBox_edit.setVisible(true);
            continuer_edit.setVisible(false);
            table_prod.setDisable(true);

        }
                
    }

    @FXML
    private void CloseEditWarning(ActionEvent event) {
         warningBox_edit.setVisible(false);
         table_prod.setDisable(false);

    }
        
    


   
}
