/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.services.ProductService;
import pidev.edu.souk.utils.EditingCellDouble;
import pidev.edu.souk.utils.EditingCellInt;
import pidev.edu.souk.utils.EditingCellVente;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class GestionStockController implements Initializable {

    @FXML
    private Button edit_btn;
    @FXML
    private Button bilan_btn;
    @FXML
    private TableView<Produit> stock_table;
    @FXML
    private TableColumn<Produit, String> ref_prod;
    @FXML
    private TableColumn<Produit, String> dateExpo_prod;
    @FXML
    private TableColumn<Produit, String> nom_prod;
    @FXML
    private TableColumn<Produit, String> categ_prod;
    @FXML
    private TableColumn<Produit, Double> prixBase_Prod;
    @FXML
    private TableColumn<Produit, Double> prixVente_Prod;
    @FXML
    private TableColumn<Produit, Integer> Quantite_prod;

    ProductService prodServ = new ProductService();
    ObservableList produits = (ObservableList) prodServ.list();

    private IntegerProperty index = new SimpleIntegerProperty();
    Produit p = new Produit();

    private double prixBase = 0.0;
    private double prixVente = 0.0;

    int i = 0;

    @FXML
    private AnchorPane Bilan_dialog;
    @FXML
    private Hyperlink Close_btn;
    @FXML
    private TableColumn<Produit, String> bilan_ref;
    @FXML
    private TableColumn<Produit, String> bilan_nom;
    @FXML
    private TableColumn<Produit, String> bilan_datExpo;
    @FXML
    private TableColumn<Produit, Integer> bilan_Qte;
    @FXML
    private TableColumn<Produit, Double> bilan_prixBase;
    @FXML
    private TableColumn<Produit, Double> bilan_PrixVente;
    @FXML
    private TableColumn<Produit, String> bilan_Gain;
    @FXML
    private Label QteTotale;
    @FXML
    private Label GainTotal;
    @FXML
    private TableView<Produit> Bilan_table;
    @FXML
    private TextField tf_recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ref_prod.setCellValueFactory(new PropertyValueFactory<>("ReferenceProd"));
        dateExpo_prod.setCellValueFactory(new PropertyValueFactory<>("dateExpoProduit"));
        nom_prod.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));

        categ_prod.setCellValueFactory(new PropertyValueFactory<>("dateExpirationProduit"));
//        categ_prod.setCellFactory(new Callback<TableColumn<Produit, Categorie>, TableCell<Produit, Categorie>>() {
//            @Override
//            public TableCell<Produit, Categorie> call(TableColumn<Produit, Categorie> param) {
//
//                TableCell<Produit, Categorie> catCell = new TableCell<Produit, Categorie>() {
//
//                    @Override
//                    protected void updateItem(Categorie item, boolean empty) {
//                        if (item != null) {
//                            Label catLabel = new Label(item.getNomCategorie());
//                            setGraphic(catLabel);
//                        }
//                    }
//                };
//
//                return catCell;
//
//            }
//        });

        prixBase_Prod.setCellValueFactory(new PropertyValueFactory<>("prixBaseProduit"));

        Callback<TableColumn<Produit, Double>, TableCell<Produit, Double>> PrixBasecell = new Callback<TableColumn<Produit, Double>, TableCell<Produit, Double>>() {
            @Override
            public TableCell<Produit, Double> call(TableColumn<Produit, Double> param) {
                return new EditingCellDouble();

            }

        };

        prixBase_Prod.setCellFactory(PrixBasecell);

        prixBase_Prod.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Double> t) {
                Produit pp = t.getTableView().getItems().get(t.getTablePosition().getRow());

                if (t.getNewValue() != null) {
                    pp.setPrixBaseProduit(t.getNewValue());
                } else {
                    pp.setPrixBaseProduit(t.getOldValue());
                }

            }
        });

        prixVente_Prod.setCellValueFactory(new PropertyValueFactory<>("prixVenteProduit"));
        Callback<TableColumn<Produit, Double>, TableCell<Produit, Double>> PrixVentecell = new Callback<TableColumn<Produit, Double>, TableCell<Produit, Double>>() {
            @Override
            public TableCell<Produit, Double> call(TableColumn<Produit, Double> param) {

                return new EditingCellVente();
            }

        };

        prixVente_Prod.setCellFactory(PrixVentecell);

        prixVente_Prod.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Double> t) {
                Produit pp = t.getTableView().getItems().get(t.getTablePosition().getRow());

                if (t.getNewValue() != null) {
                    pp.setPrixVenteProduit(t.getNewValue());
                } else {
                    pp.setPrixVenteProduit(t.getOldValue());
                }

            }
        });

        Quantite_prod.setCellValueFactory(new PropertyValueFactory<>("qteDispoProduit"));
        Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>> Qtecell = new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
            @Override
            public TableCell<Produit, Integer> call(TableColumn<Produit, Integer> param) {

                return new EditingCellInt();

            }
        };

        Quantite_prod.setCellFactory(Qtecell);

        Quantite_prod.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Integer> t) {
                Produit pp = t.getTableView().getItems().get(t.getTablePosition().getRow());

                if (t.getNewValue() != null) {
                    pp.setQteDispoProduit(t.getNewValue());
                } else {
                    pp.setQteDispoProduit(t.getOldValue());
                }

            }
        });

        stock_table.setItems(produits);

        stock_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {

                index.set(produits.indexOf(newValue));
                System.out.println(produits.indexOf(newValue));

                p = (Produit) produits.get(index.get());

                System.out.println(p);

            }

        });

    }
    //------------------------------------------------------------------------
    //------------------------------Buttons------------------------------------------

    @FXML
    private void edit_prod(ActionEvent event) {

        int i = index.get();
        if (i > -1) {

            prodServ.update(p, p.getIdProduit());
            //produits=(ObservableList) prodServ.list();
            stock_table.getSelectionModel().clearSelection();
        }

    }

    @FXML
    private void genererBilan(ActionEvent event) {

        Bilan_dialog.setVisible(true);
        Bilan_table.setItems(produits);

        bilan_ref.setCellValueFactory(new PropertyValueFactory<>("ReferenceProd"));
        bilan_nom.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        bilan_datExpo.setCellValueFactory(new PropertyValueFactory<>("dateExpoProduit"));
        bilan_Qte.setCellValueFactory(new PropertyValueFactory<>("qteVenduProduit"));
        bilan_prixBase.setCellValueFactory(new PropertyValueFactory<>("prixBaseProduit"));
        bilan_PrixVente.setCellValueFactory(new PropertyValueFactory<>("prixVenteProduit"));

        bilan_Gain.setCellFactory(new Callback<TableColumn<Produit, String>, TableCell<Produit, String>>() {
            @Override
            public TableCell<Produit, String> call(TableColumn<Produit, String> param) {
                return new TableCell<Produit, String>/* gainCell = new TableCell<Produit, String>*/() {

                    protected void updateItem(String item, boolean empty) {

                        if (i < produits.size()) {
                            Produit prod = (Produit) produits.get(i);

                            double xx = (prod.getPrixVenteProduit() - prod.getPrixBaseProduit()) * prod.getQteVenduProduit();
                            System.out.println(prod.getNomProduit() + "----------" + xx);
                            item = String.valueOf(xx);
                            Label GainLabel = new Label(item);

                            System.out.println(prod.getNomProduit() + "++++++++++++" + GainLabel.getText());

                            setGraphic(GainLabel);

                            i++;

                        }

                    }
                };

            }
        ;

        });
     

        
        
        
        
        int somme = 0;
        double gain = 0;
        for (int v = 0; v < produits.size(); v++) {
            Produit pp = (Produit) produits.get(v);
            somme = somme + pp.getQteVenduProduit();

            gain = gain + ((pp.getPrixVenteProduit() - pp.getPrixBaseProduit()) * pp.getQteVenduProduit());

        }
        QteTotale.setText(String.valueOf(somme));

        GainTotal.setText(String.valueOf(gain));

    }

    public double getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    @FXML
    private void Close(ActionEvent event) {
        Bilan_dialog.setVisible(false);
    }

    @FXML
    private void rechercheStock(KeyEvent event) {

        stock_table.getItems().clear();
        stock_table.refresh();
        produits.addAll(prodServ.rechercheStock(1, tf_recherche.getText()));

    }

}
