/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import pidev.edu.souk.entities.Event;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.UserEvent;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author ASUS I7
 */
public class EvenementService {
    
    public void addEvenement(Event E) {
        try {
            //////// Avec statement////////////

            String requete = "INSERT INTO event (nomEvent,objectifEvent,lieux,descriptionevent,nbreStand,nbrePlace,urlafficheevent,dateEvent) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, E.getNomEvent());
            pst.setString(2, E.getObjectifEvent());
            pst.setString(3, E.getLieux());
            pst.setString(4, E.getDescriptionevent());
            pst.setInt(5, E.getNbrePlace());
            pst.setInt(6, E.getNbreStand());
            pst.setString(7, E.getUrlafficheevent());
            pst.setDate(8, (Date) E.getDateEvent());
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void UpdateEvenement(Event E, int id) {
        try {
            String requete2 = "UPDATE event SET nomEvent=?,lieux=?,descriptionevent=? WHERE idEvent=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(requete2);
            pst2.setInt(4, id);
            pst2.setString(1, E.getNomEvent());
            pst2.setString(2, E.getLieux());
            pst2.setString(3, E.getDescriptionevent());
            
            pst2.executeUpdate();
            System.out.println("Modification effectuée avec succés");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Event> listerEvenement() {
        ArrayList<Event> myList = new ArrayList<Event>();
        try {
            
            String req3 = "SELECT * FROM event where nbreStand>0 and nbrePlace>0";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(req3);
            while (rs.next()) {
                // Event E= new Event();
                Event E2 = new Event();
                E2.setIdEvent(rs.getInt("idEvent"));
                E2.setNomEvent(rs.getString("nomEvent"));
                
                E2.setDateEvent(rs.getDate("dateEvent"));
                E2.setNbreStand(rs.getInt("nbreStand"));
                
                E2.setLieux(rs.getString("lieux"));
                E2.setNbrePlace(rs.getInt("nbrePlace"));
                E2.setDescriptionevent(rs.getString("descriptionevent"));
                E2.setUrlafficheevent(rs.getString("urlafficheevent"));
                
                myList.add(E2);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
        
    }
    
    public Event findEventById(int id) {
        Event event = new Event();
        
        try {
            String requete = "select * from event where idevent='" + id + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                event.setIdEvent(id);
                
                event.setNomEvent(rs.getString("nomEvent"));
                event.setUrlafficheevent(rs.getString("urlafficheevent"));
                //   event.setDateEvent(rs.getDate("dateEvent"));
                event.setNbreStand(rs.getInt("nbreStand"));
                event.setObjectifEvent(rs.getString("objectifEvent"));
                event.setLieux(rs.getString("lieux"));
                event.setNbrePlace(rs.getInt("nbrePlace"));
                event.setDescriptionevent(rs.getString("descriptionevent"));
                event.setTypeEvent(rs.getString("typeEvent"));
                event.setUrlafficheevent(rs.getString("urlafficheevent"));
                
            }
            
        } catch (SQLException ex) {
            
        }
        
        return event;
    }
    
    public void deleteEvenement(Event E) {
        try {
            // Event event =new Event();
            String req = "Delete from event Where idevent=?";
            PreparedStatement pst2 = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst2.setInt(1, E.getIdEvent());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveFile(File f, String name) {
        
        InputStream inStream = null;
        OutputStream outStream = null;
        
        try {
            
            File Copyfile = new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\img\\" + name);
            
            inStream = new FileInputStream(f);
            outStream = new FileOutputStream(Copyfile);
            
            byte[] buffer = new byte[1024];
            
            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0) {
                
                outStream.write(buffer, 0, length);
                
            }
            
            inStream.close();
            outStream.close();

            //delete the original file
            //f.delete();
            System.out.println("File is copied successful!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void inscription(UserEvent inscri) {
        try {
            java.util.Date date_util = new java.util.Date();
            java.sql.Date datesql = new java.sql.Date(date_util.getTime());
            String requete = "INSERT INTO user_event (event_id,nom,prenom,adressemail,dateInscri,user_id) VALUES (?,?,?,?,?,?)";
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            pst.setInt(1, inscri.getEventId().getIdEvent());
            pst.setString(2, inscri.getNom());
            pst.setString(3, inscri.getPrenom());
            pst.setString(4, inscri.getAdressemail());
            pst.setDate(5, datesql);
            
            pst.setInt(6, inscri.getUserId().getId());
            pst.executeUpdate();

            // e.setNbrePlace(e.getNbrePlace() - 1);
            System.out.println(datesql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void downNbplace(Event e, int id) {
        try {
            String req6 = "UPDATE event SET nbrePlace=? WHERE idEvent=? ";
            PreparedStatement pst8 = MyConnection.getInstance().getCnx().prepareStatement(req6);
            pst8.setInt(1, e.getNbrePlace());
            pst8.setInt(2, id);
            pst8.executeUpdate();
        } catch (SQLException ex) {
            
        }
    }
    
    public ArrayList<Event> findByDate(String d) {
        ArrayList<Event> myList = new ArrayList<Event>();
        
        try {
            String requete1 = "select * from event where dateEvent=" + d;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete1);
            while (rs.next()) {
                
                Event E2 = new Event();
                E2.setIdEvent(rs.getInt("idEvent"));
                E2.setNomEvent(rs.getString("nomEvent"));
                
                E2.setDateEvent(rs.getDate("dateEvent"));
                E2.setNbreStand(rs.getInt("nbreStand"));
                
                E2.setLieux(rs.getString("lieux"));
                E2.setNbrePlace(rs.getInt("nbrePlace"));
                E2.setDescriptionevent(rs.getString("descriptionevent"));
                E2.setUrlafficheevent(rs.getString("urlafficheevent"));
                
                myList.add(E2);
                
            }
        } catch (SQLException ex) {
            
        }
        return myList;
    }
    
    public ArrayList<UserEvent> listerInscri() {
        ArrayList<UserEvent> myList = new ArrayList<UserEvent>();
        try {
            
            String req9 = "SELECT * FROM user_event";
            Statement st4 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st4.executeQuery(req9);
            while (rs.next()) {
                // Event E= new Event();
                UserEvent inscri = new UserEvent();
                
                inscri.setIdInscri(rs.getInt("idInscri"));
                inscri.setDateInscri(rs.getDate("dateInscri"));
                inscri.setNom(rs.getString("nom"));
                inscri.setPrenom(rs.getString("prenom"));
                inscri.setAdressemail(rs.getString("adressemail"));
                
                
                myList.add(inscri);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
        
    }
    
    
    
    
     public ObservableList<Event> findByLieu(String l) {
             ObservableList<Event> listEvenement = FXCollections.observableArrayList();
          System.out.println("  lllllllllll");
        try {
            String requete6 = "SELECT * FROM event where lieux '" + l + "'";
            System.out.println("    999999999");
            Statement st5 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs4 = st5.executeQuery(requete6);
            while (rs4.next()) {
                
                Event E2 = new Event();
                E2.setIdEvent(rs4.getInt("idEvent"));
                E2.setNomEvent(rs4.getString("nomEvent"));
                
                E2.setDateEvent(rs4.getDate("dateEvent"));
                E2.setNbreStand(rs4.getInt("nbreStand"));
                
                E2.setLieux(rs4.getString("lieux"));
                E2.setNbrePlace(rs4.getInt("nbrePlace"));
                E2.setDescriptionevent(rs4.getString("descriptionevent"));
                E2.setUrlafficheevent(rs4.getString("urlafficheevent"));
                
                listEvenement.add(E2);
                System.out.println(E2);
                
            }
        } catch (SQLException ex) {
            
        }
        return listEvenement;
    }
   
    
}
