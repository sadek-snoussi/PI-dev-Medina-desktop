/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.controller;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pidev.edu.souk.entities.Categorie;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.services.ProductService;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ProductValidatorController implements Initializable {

    @FXML
    private Label lab_title;
    @FXML
    private Button non_valid_btn;
    @FXML
    private Button valid_btn;
    @FXML
    private TableView<Produit> table_prodvalid;
    @FXML
    private TableColumn<Produit, String> nom_prod;
    @FXML
    private TableColumn<Produit, Date> dateExpo_prod;
    @FXML
    private TableColumn<Produit, Categorie> categ_prod;
    @FXML
    private TableColumn<Produit, Double> prix_prod;
    
    
    
     ProductService prodServ = new ProductService();   
     final ObservableList produits =(ObservableList) prodServ.listValidite();
     
     private IntegerProperty index=new SimpleIntegerProperty();
     Produit p =new Produit();
     User u=new User();
    @FXML
    private AnchorPane validProd_container;
    @FXML
    private ImageView ImgV;

     
     //@FXML
    //private AnchorPane validProd_container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    //validProd_container.setVisible(true);  
    
    
    //while (!produits.isEmpty()) {            
            
                
            
        
    nom_prod.setCellValueFactory(new PropertyValueFactory<Produit, String>("nomProduit"));
    dateExpo_prod.setCellValueFactory(new PropertyValueFactory<Produit, Date>("dateExpoProduit"));
    


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
        

    
    
    
    prix_prod.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prixVenteProduit"));
    

    
    //}
    
    table_prodvalid.setItems(produits);
    
    
    table_prodvalid.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
            
                
            index.set(produits.indexOf(newValue));           
            System.out.println(produits.indexOf(newValue+"-----------------------"));
            
            if(index.get() > -1){
            p=(Produit) produits.get(index.get());
            
            System.out.println(p);
            Image img=new Image("file:C:\\wamp64\\www\\Medina_VersionFinale\\web\\uploads\\ImgProduit\\" + p.getUrlImgProduit());
            ImgV.setImage(img);
            
            

            
            
            
            }
                        }
        
        
        
        
        
    });    
    
  
    }    

    @FXML
    private void non_valid_prod(ActionEvent event) throws SQLException {
        
//        String req="Select * from USER where id="+p.getIdUser();
//        Statement st = MyConnection.getInstance().getCnx().createStatement();
//        ResultSet result = st.executeQuery(req);
//
//        String telUser=result.getString(17);
//        System.out.println("pppppppp   "+telUser+"   ppppppppp");
                
             

        
        int i = index.get();
        if(i>-1){   
            
            
            
              //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
             		try {
			// Construct data
			String apiKey = "apikey=" + "eipB6liWyns-OSLJGuSugoJiDcPyg5BM7UYbzimwBQ";
			String message = "&message=" + "Votre Produit intitulé '"+p.getNomProduit()+"' a été Rejeté.Veuillez Vérifier ses Coordonnées.";
			String sender = "&sender=" + "Souk El Medina";
			String numbers = "&numbers=" + "216"+p.getIdUser().getTelUser();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                System.out.println("-----------------------------------");
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			//return "Error "+e;
		}
                        

              //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
    
            prodServ.DenyProduct(p.getIdProduit());
            produits.remove(i);
            table_prodvalid.getSelectionModel().clearSelection();
            
            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getNomProduit()+" a été rejeté.");
            alert.show();
            //***********************************
            
        }
    }

    @FXML
    private void valid_prod(ActionEvent event) throws SQLException  {
        
        
//            String req="Select * from USER where id="+p.getIdUser();
//            Statement st = MyConnection.getInstance().getCnx().createStatement();
//            ResultSet rs = st.executeQuery(req);
//            
//            
//            
//            
//            
//            while (rs.next()) {
//
//                u.setId(rs.getInt(1));
//                u.setUsername(rs.getString(2));
//                u.setTelUser(rs.getString(17));
//            }
//            
//            System.out.println(u);
            
            
            
            int i = index.get();
            if(i>-1){
                
                //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                
                try {
                    // Construct data
                    String apiKey = "apikey=" + "eipB6liWyns-OSLJGuSugoJiDcPyg5BM7UYbzimwBQ";
                    String message = "&message=" + "Votre Produit intitulé '"+p.getNomProduit()+"' a été Validé et déja Mis en Vente.";
                    String sender = "&sender=" + "Souk El Medina";
                    String numbers = "&numbers=" + "216"+p.getIdUser().getTelUser();
                    
                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        //stringBuffer.append(line);
                        System.out.println("-----------------------------------");
                    }
                    rd.close();
                    
                    //return stringBuffer.toString();
                } catch (Exception e) {
                    System.out.println("Error SMS "+e);
                    //return "Error "+e;
                }
                
                //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
                
                
                
                prodServ.AcceptProduct(p.getIdProduit());
                produits.remove(i);
                table_prodvalid.refresh();
                table_prodvalid.getSelectionModel().clearSelection();
                
            //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getNomProduit()+" a été Accepté.");
            alert.show();
            //***********************************
                
            }
            

    }
    
    
    //------------------------------------------------------------------
    
//        public AnchorPane getValidProd_container() {
//        return validProd_container;
//    }

    
}
