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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.souk.Iservices.GestionPartenaireAdmin;
import pidev.edu.souk.Iservices.GestionPartenaires;
import pidev.edu.souk.entities.Guide;
import pidev.edu.souk.entities.Panier;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author amalb
 */
public class PartenaireService implements GestionPartenaires
{
    @Override
     public void ajouterClient(User user) {
        try {
            String requete = "insert into User (username,username_canonical,email,email_canonical,"
                    + "password,nomUser,prenomUser,dateNaissUser,telUser,typeUser,adresse,roles,enabled,nbrPointFidelite) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            pst.setString(6, user.getNomUser());
            pst.setString(7, user.getPrenomUser());
            pst.setDate(8, (Date)user.getDateNaissUser());
            pst.setString(9, user.getTelUser());
            pst.setString(10, "client");
            pst.setString(11, user.getAdresse());
            pst.setString(12, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
            pst.setInt(13, 1);
            pst.setInt(14, 0);

            pst.executeUpdate();
             System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }
     @Override
    public User finUserById(int id)
    {
          User user=new User();
         
         try {
             String requete = "select * from User where id='"+id+ "'"; 
             Statement st=MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requete);
            
     
     
             while(rs.next())
             {
                 user.setId(rs.getInt("id"));
                 user.setNomUser(rs.getString("nomUser"));
                 user.setPrenomUser(rs.getString("prenomUser"));
                 user.setEmail(rs.getString("email"));
                 user.setTelBureauPro(rs.getString("telBureauPro"));
                 user.setSpecialitePart(rs.getString("specialitePart"));
                 user.setNomEntreprisePro(rs.getString("nomEntreprisePro"));
                 user.setGradePro(rs.getString("gradePro"));
                 user.setRoles(rs.getString("roles"));
                 user.setPartenariat(rs.getInt("partenariat"));
                 user.setTypeUser(rs.getString("typeUser"));
                 user.setNbrPointFidelite(rs.getInt("nbrPointFidelite"));
               
                
             }
            
           
         } catch (SQLException ex) {
           
         }
              System.out.println(user.toString());    
         return user;
    }
     @Override
    public Boolean findUserByEmail(String email) {
               User user = null;
        String req = "select * from User where email =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, email );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               user=new User();
               user.setId(resultSet.getInt("id"));
               user.setEmail(resultSet.getString("email"));
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    
    }
    
    @Override
    public Boolean findUserByUsername(String username) {
        User user = null;
        String req = "select * from User where username =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            preparedStatement.setString(1, username );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               user=new User();
               user.setId(resultSet.getInt("id"));
               user.setUsername(resultSet.getString("username"));
                 
            }
        } catch (SQLException ex) {
            
        }
        if (user == null) {
            return false;
        }
        return true;
    }
    @Override
    public void modifierProfileClient(User user,int id) {
         try {
             
             String requete = "update User set nomUser=?,prenomUser=?,email=?,"
                     + "email_canonical=?,telUser=?,adresse=?,username=?,username_canonical=?,dateNaissUser=? where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.setString(1, user.getNomUser());
             pst.setString(2, user.getPrenomUser());
             pst.setString(3, user.getEmail());
             pst.setString(4, user.getEmail());
            // pst.setString(5, user.getPassword());
           
             pst.setString(5, user.getTelUser());
             pst.setString(6, user.getAdresse());
             pst.setString(7, user.getUsername());
             pst.setString(8, user.getUsername());
              pst.setDate(9,(Date)user.getDateNaissUser());
             pst.setInt(10, id);
             pst.executeUpdate();
             System.out.println("Modification effectuée avec succés");
             
         } catch (SQLException ex) {
             Logger.getLogger(PartenaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierProfilePro(User user,int id) {
           try {
             System.out.println(user.toString());
             String requete = "update User set nomUser=?,prenomUser=?,email=?,"
                     + "email_canonical=?,gradePro=?,telBureauPro=?,specialitePart=?,nomEntreprisePro=? where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.setString(1, user.getNomUser());
             pst.setString(2, user.getPrenomUser());
             pst.setString(3, user.getEmail());
             pst.setString(4, user.getEmail());
           
             pst.setString(5, user.getGradePro());
             pst.setString(6, user.getTelBureauPro());
             
             pst.setString(7, user.getSpecialitePart());
             pst.setString(8, user.getNomEntreprisePro());
             pst.setInt(9, id);
            
             pst.executeUpdate();
             System.out.println("Modification Profile Professionnel effectuée avec succés");
             
         } catch (SQLException ex) {
             Logger.getLogger(PartenaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierProfileFree(User user,int id) {
         try {
             System.out.println(user.toString());
             String requete = "update User set nomUser=?,prenomUser=?,email=?,"
                     + "email_canonical=?,telBureauPro=?,specialitePart=? where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
             pst.setString(1, user.getNomUser());
             pst.setString(2, user.getPrenomUser());
             pst.setString(3, user.getEmail());
             pst.setString(4, user.getEmail());
           
           
             pst.setString(5, user.getTelBureauPro());
            
             pst.setString(6, user.getSpecialitePart());
            
             pst.setInt(7, id);
             pst.executeUpdate();
             System.out.println("Modification Profile Professionnel effectuée avec succés");
             
         } catch (SQLException ex) {
             Logger.getLogger(PartenaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void listPartenaireContact() {
        List<User> Partenaires = new ArrayList<User>();
         try {
             String requete = "select * from User where partenariat=1";
             Statement st=MyConnection.getInstance().getCnx().createStatement(); 
             ResultSet rs=st.executeQuery(requete);
             while(rs.next())
             {
                 User user=new User();
                 user.setEmail(rs.getString(4));
                 user.setNomEntreprisePro(rs.getString("nomEntreprisePro"));
                 user.setAdresse(rs.getString("adresse"));
                 user.setTypeUser(rs.getString("typeUser"));
                 Partenaires.add(user);
                 
                 
             }
                     } catch (SQLException ex) {
             Logger.getLogger(PartenaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void RechercherPartenaire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutDemandePartenariatPro(User user,int id) {
        try {
            String requete = "Update User set telBureauPro=?,typeUser=?,adresse=?,specialitePart=?,gradePro=?,urlLogoPro=?,partenariat=?,nomEntreprisePro=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
           pst.setString(1,user.getTelBureauPro());
            pst.setString(2, "pro");
            pst.setString(3, user.getAdresse());
            pst.setString(4, user.getSpecialitePart());
            pst.setString(5, user.getGradePro());
            pst.setString(6, user.getUrlLogoPro());
            pst.setInt(7, 0);
            pst.setString(8, user.getNomEntreprisePro());
            pst.setInt(9, id);

            pst.executeUpdate();
           
           
      
             System.out.println("ajout demande de partenariat effectuée avec succés");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }
    
    

    @Override
    public void ajoutDemandePartenariatFree(User user,int id) {
          try {
            String requete = "Update User set telBureauPro=?,typeUser=?,adresse=?,specialitePart=?,urlLogoPro=?,partenariat=? where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
           pst.setString(1,user.getTelBureauPro());
            pst.setString(2, "freelancer");
            pst.setString(3, user.getAdresse());
            pst.setString(4, user.getSpecialitePart());
            pst.setString(5, user.getUrlLogoPro());
            pst.setInt(6, 0);
            pst.setInt(7, id);
            pst.executeUpdate();
             System.out.println("ajout demande de partenariat effectuée avec succés");
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }

   public void saveFile(File f,String name) {


        InputStream inStream = null;
	      OutputStream outStream = null;
		
    	try{
    		
            
    	       File Copyfile =new File("C:\\xampp\\htdocs\\Medina_VersionFinale\\web\\uploads\\logo\\"+name);
    		
    	    inStream = new FileInputStream(f);
    	    outStream = new FileOutputStream(Copyfile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
    	  
    	    	outStream.write(buffer, 0, length);
    	 
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    //delete the original file
    	    //f.delete();
    	    
    	    System.out.println("File is copied successful!");
    	    
    	}catch(IOException e){
    	    e.printStackTrace();
    	}
        

    
    
}
   
   
   
    @Override
    public int selectQuantiteProduit(int  idUser) {

        int quatiteProduit = 0;
        try {
            ArrayList<Integer> listQuantiteProduit = new ArrayList<Integer>();
            String requete = "SELECT * FROM panier where user_id='" + idUser + "'and flag="+0;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Panier panier = new Panier();
                panier.setQuantiteProduit(rs.getInt(5));
                quatiteProduit=quatiteProduit+panier.getQuantiteProduit();
            }
            try {
                quatiteProduit = listQuantiteProduit.stream().findFirst().get();
            } catch (NoSuchElementException ex) {

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return quatiteProduit;
    }
    
    @Override
      public int gestionPromotionCommande(int iduser) 
      {
          int prom=0;
          User u=finUserById(iduser);
          System.out.println("***user****"+u.toString());
          int p=u.getNbrPointFidelite();
          System.out.println("***nbrePoitns****"+p);
          p=p+selectQuantiteProduit(iduser)*10;
             System.out.println("***nbre Poitns apres commande****"+p);
          if(p<50)
          {
              prom=0;
              
          }
          else if(p<300 && p>=50)
          {
              prom=10;
              u.setNbrPointFidelite(p-200);
          }
          else if(p>=300)
          {
              prom=30;
               u.setNbrPointFidelite(p-300);
          }
          System.out.println("usssserrrrrrr"+u.toString());
          modifierPointFidelite(u, iduser);
          
          return prom;
      }
      
      
      public void modifierPointFidelite(User user, int id)
      {
           try {
             
             String requete = "update User set nbrPointFidelite=? where id=?";
             PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
           
              pst.setInt(1, user.getNbrPointFidelite());
             pst.setInt(2, id);
             pst.executeUpdate();
             System.out.println("Modification effectuée avec succés");
             
         } catch (SQLException ex) {
          
         }
          
      }
  
    
}
