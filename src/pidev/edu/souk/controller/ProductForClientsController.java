/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.PanierService;
import pidev.edu.souk.services.ProductService;
import pidev.edu.souk.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ProductForClientsController implements Initializable {

    private GridPane GridPane;
    @FXML
    private TextField tf_findByName;
    @FXML
    private Button search_btn;
    @FXML
    private TextField prix_min;
    @FXML
    private TextField prix_max;
    @FXML
    private Button ByPrice_btn;
    @FXML
    private ComboBox<String> categ_btn;
    @FXML
    private RadioButton triAsc_btn;
    @FXML
    private RadioButton triDesc_btn;

    ProductService prodServ = new ProductService();
    ObservableList<Produit> produits = FXCollections.observableArrayList();
    int index = 0;
    //int rowsPerPage =6;

    @FXML
    private AnchorPane Dialog;
    @FXML
    private Hyperlink close_btn;
    @FXML
    private Label detail_ref_title;
    @FXML
    private Label detail_ref;
    @FXML
    private Label detail_date_expo_title;
    @FXML
    private Label detail_date_expo;
    @FXML
    private Label detail_categorie_title;
    @FXML
    private Label detail_categorie;
    @FXML
    private Label detail_matiere_title;
    @FXML
    private Label detail_matiere;
    @FXML
    private Label detail_prix_title;
    @FXML
    private Label detail_prix;
    @FXML
    private Label detail_quantite_title;
    @FXML
    private Label detail_quantite;
    @FXML
    private Label detail_dateExpir_title;
    @FXML
    private Label detail_date_expir;
    @FXML
    private Label detail_nom;
    @FXML
    private ImageView ImgV;
    @FXML
    private ListView<Produit> listView;
    @FXML
    private Button AddtoCart;
    @FXML
    private Label idProd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        produits = (ObservableList) prodServ.list();
        idProd.setVisible(false);
        
        //paginator.setPageCount(5);
        //paginator.setPageFactory(this::createPage);
        

//*****************************Charge comboBox Categories**********************      
        String req = "SELECT * FROM CATEGORIE";
        Statement st;
        try {

            st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            ObservableList<String> comboCat = FXCollections.observableArrayList();

            while (rs.next()) {
                comboCat.add(rs.getString("nomcategorie"));
            }

            categ_btn.setItems(comboCat);

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //*****************************Charge comboBox Categories**********************     
        //*****************************Populate ListView**********************      

    

        listView.setItems(produits);
        listView.getSelectionModel().clearSelection();
        listView.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {
            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
                ListCell<Produit> cell = new ListCell<Produit>() {

                    protected void updateItem(Produit item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.CENTER);

                            Image img;
                                img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + item.getUrlImgProduit());
                            ImageView imgView = new ImageView(img);
                            
//              
//            
//                          //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(false);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {
                                    Dialog.setVisible(true);
                                   System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getNomProduit());
                                    detail_ref.setText(item.getReferenceProd());
                                    detail_date_expo.setText(item.getDateExpoProduit().toString());
                                    detail_categorie.setText(item.getIdCategorie().getNomCategorie());
                                    detail_matiere.setText(item.getMatiereProduit());
                                    detail_prix.setText(String.valueOf(item.getPrixVenteProduit()) + "  DT");
                                    detail_quantite.setText(item.getQteDispoProduit().toString());
                                     idProd.setText(item.getIdProduit().toString());

                                    
                                    if (item.getDateExpirationProduit() != null) {
                                        detail_dateExpir_title.setVisible(true);
                                        detail_date_expir.setVisible(true);
                                        detail_date_expir.setText(item.getDateExpirationProduit().toString());
                                    } else {
                                        detail_dateExpir_title.setVisible(false);
                                        detail_date_expir.setVisible(false);

                                    }
                                       
                                }
                            });

                            //***********************************************************************
                            
                            
                            
                            //Button cart = new Button("Add to Cart.");
                            //Button wish = new Button("Add to WishList.");

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            //cart.setMaxWidth(120);
                            //wish.setMaxWidth(120);
                            //***********************************************************

                            Label nomProd = new Label(item.getNomProduit());
                            Label CategProd = new Label(item.getIdCategorie().getNomCategorie());
                            Label prixProd = new Label(String.valueOf(item.getPrixVenteProduit()) + "  DT");

                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);

                            Buttons.getChildren().add(detail);
                            //Buttons.getChildren().add(cart);
                            //Buttons.getChildren().add(wish);

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
//ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
                





                }
                

    //***********************************************************************
    //********************************Find && Order**************************
    @FXML
    private void findByName(ActionEvent event) {

        produits.clear();
        produits.addAll((ObservableList) prodServ.FindByName(tf_findByName.getText()));
        listView.setItems(produits);
        //-------------------------------------------------------------------------------
                listView.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
                ListCell<Produit> cell = new ListCell<Produit>() {

                    protected void updateItem(Produit item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + item.getUrlImgProduit());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getNomProduit());
                                    detail_ref.setText(item.getReferenceProd());
                                    detail_date_expo.setText(item.getDateExpoProduit().toString());
                                    detail_categorie.setText(item.getIdCategorie().getNomCategorie());
                                    detail_matiere.setText(item.getMatiereProduit());
                                    detail_prix.setText(String.valueOf(item.getPrixVenteProduit()) + "  DT");
                                    detail_quantite.setText(item.getQteDispoProduit().toString());

                                    if (item.getDateExpirationProduit() != null) {
                                        detail_dateExpir_title.setVisible(true);
                                        detail_date_expir.setVisible(true);
                                        detail_date_expir.setText(item.getDateExpirationProduit().toString());
                                    } else {
                                        detail_dateExpir_title.setVisible(false);
                                        detail_date_expir.setVisible(false);

                                    }

                                }
                            });

                            //***********************************************************************
                            Button cart = new Button("Add to Cart.");
                            Button wish = new Button("Add to WishList.");

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            cart.setMaxWidth(120);
                            wish.setMaxWidth(120);
                            //***********************************************************

                            Label nomProd = new Label(item.getNomProduit());
                            Label CategProd = new Label(item.getIdCategorie().getNomCategorie());
                            Label prixProd = new Label(String.valueOf(item.getPrixVenteProduit()) + "  DT");

                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);

                            Buttons.getChildren().add(detail);
                            Buttons.getChildren().add(cart);
                            Buttons.getChildren().add(wish);

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(produits.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    }

    @FXML
    private void findByPrice(ActionEvent event) {

        produits.clear();
        produits.addAll(prodServ.FindByPrice(Double.parseDouble(prix_min.getText()), Double.parseDouble(prix_max.getText())));

        //-------------------------------------------------------------------------------
                listView.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
                ListCell<Produit> cell = new ListCell<Produit>() {

                    protected void updateItem(Produit item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + item.getUrlImgProduit());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getNomProduit());
                                    detail_ref.setText(item.getReferenceProd());
                                    detail_date_expo.setText(item.getDateExpoProduit().toString());
                                    detail_categorie.setText(item.getIdCategorie().getNomCategorie());
                                    detail_matiere.setText(item.getMatiereProduit());
                                    detail_prix.setText(String.valueOf(item.getPrixVenteProduit()) + "  DT");
                                    detail_quantite.setText(item.getQteDispoProduit().toString());
                                    
                                    if (item.getDateExpirationProduit() != null) {
                                        detail_dateExpir_title.setVisible(true);
                                        detail_date_expir.setVisible(true);
                                        detail_date_expir.setText(item.getDateExpirationProduit().toString());
                                    } else {
                                        detail_dateExpir_title.setVisible(false);
                                        detail_date_expir.setVisible(false);

                                    }

                                }
                            });

                            //***********************************************************************
                            //Button cart = new Button("Add to Cart.");
                            //Button wish = new Button("Add to WishList.");

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            //cart.setMaxWidth(120);
                            //wish.setMaxWidth(120);
                            //***********************************************************

                            Label nomProd = new Label(item.getNomProduit());
                            Label CategProd = new Label(item.getIdCategorie().getNomCategorie());
                            Label prixProd = new Label(String.valueOf(item.getPrixVenteProduit()) + "  DT");

                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);

                            Buttons.getChildren().add(detail);
                            //Buttons.getChildren().add(cart);
                           // Buttons.getChildren().add(wish);

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------
        
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(produits.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");
    }

    @FXML
    private void findByCategory(ActionEvent event) throws SQLException {

        
        int id = 0;
        String q = "SELECT idCategorie from CATEGORIE where NomCategorie='" + categ_btn.getValue() + "'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            id = rs.getInt(1);
        }

        produits.clear();
        produits.addAll(prodServ.FindByCategory(id));
        
        
        //-------------------------------------------------------------------------------
                listView.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
            ListCell<Produit> cell = new ListCell<Produit>() {

                    protected void updateItem(Produit item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + item.getUrlImgProduit());
                            ImageView imgView = new ImageView(img);
//              
//            
//                          //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getNomProduit());
                                    detail_ref.setText(item.getReferenceProd());
                                    detail_date_expo.setText(item.getDateExpoProduit().toString());
                                    detail_categorie.setText(item.getIdCategorie().getNomCategorie());
                                    detail_matiere.setText(item.getMatiereProduit());
                                    detail_prix.setText(String.valueOf(item.getPrixVenteProduit()) + "  DT");
                                    detail_quantite.setText(item.getQteDispoProduit().toString());

                                    if (item.getDateExpirationProduit() != null) {
                                        detail_dateExpir_title.setVisible(true);
                                        detail_date_expir.setVisible(true);
                                        detail_date_expir.setText(item.getDateExpirationProduit().toString());
                                    } else {
                                        detail_dateExpir_title.setVisible(false);
                                        detail_date_expir.setVisible(false);

                                    }

                                }
                            });

                            //***********************************************************************
                            Button cart = new Button("Add to Cart.");
                            Button wish = new Button("Add to WishList.");

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            cart.setMaxWidth(120);
                            wish.setMaxWidth(120);
                            //***********************************************************

                            Label nomProd = new Label(item.getNomProduit());
                            Label CategProd = new Label(item.getIdCategorie().getNomCategorie());
                            Label prixProd = new Label(String.valueOf(item.getPrixVenteProduit()) + "  DT");

                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);

                            Buttons.getChildren().add(detail);
                            Buttons.getChildren().add(cart);
                            Buttons.getChildren().add(wish);

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------
        

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(produits.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    }

    @FXML
    private void OrderByAsc(ActionEvent event) {

        produits.clear();
        produits.addAll(prodServ.OrderByAsc());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(produits);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        
        
            System.out.println("///////////");
            triDesc_btn.setSelected(false);
        
    }

    @FXML
    private void OrderByDesc(ActionEvent event) {

        produits.clear();
        produits.addAll((ObservableList) prodServ.OrderByDesc());

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(produits);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        
        
            System.out.println("///////////");
            triAsc_btn.setSelected(false);
        
    }

    @FXML
    private void CloseDialog(ActionEvent event) {

        Dialog.setVisible(false);
    }
    
    
    
    
//private Node createPage(int pageIndex) {
//
//        int fromIndex = pageIndex * rowsPerPage;
//        int toIndex = Math.min(fromIndex + rowsPerPage, produits.size());
//        listView.setItems(FXCollections.observableArrayList(produits.subList(fromIndex, toIndex)));
//
//        return new ListView(produits);
//    }

    @FXML
    private void AddToCart(ActionEvent event) {
        
        System.out.println("-----------------------------------");
        System.out.println(SingninController.userIden);
        System.out.println("-----------------------------------");

        Panier pn1 = new Panier();
        PanierService ps = new PanierService();
        Panier pn = new Panier();
//        Produit pr = listView.getSelectionModel().getSelectedItem();
        Produit pr=new Produit(Integer.parseInt(idProd.getText()));
        System.out.println("le produit est "+idProd.getText());
        System.out.println("le user est "+SingninController.userIden);
        System.out.println("le panier est "+pn.toString());
//        int y = Integer.valueOf(idUser.getText());
        System.out.println(pn.getId());
        boolean test;
        if(pn.getId()==null)
        {
            test=false;
        }
        else{
            test=true;
        }
        System.out.println(test);  
        System.out.println(pn.toString());
        try{
            pn=ps.findProduitInPanier(pr.getIdProduit(),SingninController.userIden);
            Dialog.setVisible(false);

            
        }
        catch(NoSuchElementException ex)
        {
          //   String a = idUser.getText();
       // int x = Integer.valueOf(a);
        User u = new User();
        u.setId(SingninController.userIden);
        pn1.setUserId(u);
        //p.setUser(x);
        pn1.setProduitId(pr);
        ps.ajouterPanier(pn1);
       Dialog.setVisible(false);

        }
        
        test=ps.findProduitInPanierBoolean(pn);
        System.out.println(pn.toString());
        System.out.println(test);
        if(test == true ){ 
            Panier pn2;
           
            pn2=ps.findPanierByIdProduit(pr.getIdProduit());
            System.out.println("pn2 "+pn2.getFlag());
            if(pn2.getFlag()==0){
             int x = ps.selectQuantiteProduit(pn);
            ps.ajouterQuantite(pn, x);
           // pn1=pn;   
            }
            else{
                User u = new User();
                u.setId(SingninController.userIden);
                pn1.setUserId(u);
                //p.setUser(x);
                pn1.setProduitId(pr);
                ps.ajouterPanier(pn1);
            }
        }
        else{
       // String a = idUser.getText();
        //int x = Integer.valueOf(a);
        User u = new User();
        u.setId(SingninController.userIden);
        pn1.setUserId(u);
        //p.setUser(x);
        pn1.setProduitId(pr);
        ps.ajouterPanier(pn1); 
        }
            
    }

        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    

    

    
    
    


