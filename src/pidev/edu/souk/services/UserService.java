/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author amalb
 */
public class UserService
{
    public User connecter(String login) throws SQLException{
        
       
            String req="Select * from user WHERE username='"+login+"'or email='"+login+"'";
            Statement stat= MyConnection.getInstance().getCnx().createStatement();           
            ResultSet rs= stat.executeQuery(req);
            User user=new User();

            while (rs.next()) {     
                
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRoles(rs.getString("roles"));

        }
            
            
            
            
            //String username = rs.getString("username");
            // String mdp = rs.getString("password");
//            ArrayList<User>users=new ArrayList<User>();
//            user.setUsername(username);
//          users.add(user);
//          return users;

             /* if (rs.equals(null))
            {
                System.out.println("username inexistant");
                return null;
            }
             
            else return (User)rs.getArray(1);*/
          
                System.out.println("*************************************");
                System.out.println(user.toString());
                System.out.println("*************************************");
  
        return user;
            
            
     }
    
    
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
               
                
             }
            
           
         } catch (SQLException ex) {
           
         }
              System.out.println(user.toString());    
         return user;
    }

    
    
    
    
    
    
    
    

    }
    
    

