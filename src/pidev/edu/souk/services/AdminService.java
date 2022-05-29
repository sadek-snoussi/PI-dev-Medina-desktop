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
import pidev.edu.souk.Iservices.GestionPartenaireAdmin;
import pidev.edu.souk.entities.Produit;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.entities.Videodiy;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author amalb
 */
public class AdminService implements GestionPartenaireAdmin {

    @Override
    public void validerDemandePartenariat(List<User> users) {
        for (User user : users) {

            try {
                User userfound = new User();

                userfound = finUserById(user.getId());

                if(userfound.getTypeUser().equals("pro"))
                {
                String requete = "Update  User set partenariat=1 ,roles='a:1:{i:0;s:8:\"ROLE_PRO\";}'   where id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, user.getId());
                pst.executeUpdate();}
                else if(userfound.getTypeUser().equals("freelancer"))
                {
                    String requete = "Update  User set partenariat=1 ,roles='a:1:{i:0;s:15:\"ROLE_FREELANCER\";}'   where id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, user.getId());
                pst.executeUpdate();
                    
                }
            } catch (SQLException ex) {

            }

        }
    }

    @Override
    public void supprimerDemandePartenariat(List<User> users) {
        for (User user : users) {

            try {
                User userfound = new User();

                userfound = finUserById(user.getId());

                String requete = "Update  User set partenariat=2 where id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, user.getId());
                pst.executeUpdate();
            } catch (SQLException ex) {

            }

        }
    }

    public User finUserById(int id) {
        User user = new User();

        try {
            String requete = "select * from User where id='" + id + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNomUser(rs.getString("nomUser"));
                user.setPrenomUser(rs.getString("prenomUser"));
                user.setEmail(rs.getString("email"));
                user.setTelBureauPro(rs.getString("telBureauPro"));
                user.setSpecialitePart(rs.getString("specialitePart"));
                user.setNomEntreprisePro(rs.getString("nomEntreprisePro"));
                user.setGradePro(rs.getString("gradePro"));
                user.setTypeUser(rs.getString("typeUser"));

                System.out.println(user.getNomUser());
            }

        } catch (SQLException ex) {

        }

        return user;
    }

    @Override
    public void supprimerClient(List<User> users) {
        for (User user : users) {

            try {
                User userfound = new User();

                userfound = finUserById(user.getId());

                String requete = "delete from User where id=?";
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, user.getId());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public ArrayList<User> listClientAdmin() {

        ArrayList<User> Clients = new ArrayList<User>();

        try {
            String requete = "select * from User where roles='a:1:{i:0;s:11:\"ROLE_CLIENT\";}'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setNomUser(rs.getString("nomUser"));
                user.setEmail(rs.getString("email"));
                user.setPrenomUser(rs.getString("prenomUser"));
                user.setAdresse(rs.getString("adresse"));
                user.setTelUser(rs.getString("telUser"));
                Clients.add(user);

            }
        } catch (SQLException ex) {

        }
        return Clients;
    }

    @Override
    public ArrayList<User> listPartenaireAdmin() {
        ArrayList<User> Partenaires = new ArrayList<User>();

        try {
            String requete = "select * from User where partenariat=1";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNomUser(rs.getString("nomUser"));
                user.setEmail(rs.getString("email"));
                user.setPrenomUser(rs.getString("prenomUser"));
                user.setUrlLogoPro(rs.getString("urlLogoPro"));
                user.setTelBureauPro(rs.getString("telBureauPro"));
                user.setTypeUser(rs.getString("typeUser"));
                user.setSpecialitePart(rs.getString("specialitePart"));
                user.setNomEntreprisePro(rs.getString("nomEntreprisePro"));
                Partenaires.add(user);

            }
        } catch (SQLException ex) {

        }
        return Partenaires;
    }

    @Override
    public ArrayList<User> listPartenaireNonValidesAdmin() {
        ArrayList<User> PartenairesNonValides = new ArrayList<User>();

        try {
            String requete = "select * from User where (partenariat=0 )and (( typeUser <>'client') and ( typeUser<>'admin')) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNomUser(rs.getString("nomUser"));
                user.setEmail(rs.getString("email"));
                user.setPrenomUser(rs.getString("prenomUser"));
                user.setTelBureauPro(rs.getString("telBureauPro"));
                user.setTypeUser(rs.getString("typeUser"));
                user.setSpecialitePart(rs.getString("specialitePart"));
                PartenairesNonValides.add(user);

            }
        } catch (SQLException ex) {

        }
        return PartenairesNonValides;
    }

    @Override
    public ArrayList<Produit> listProduitsByUser(User user) {

        ArrayList<Produit> Produits = new ArrayList<Produit>();

        try {
            String requete = "select * from produit where idUser='" + user.getId() + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Produit p = new Produit();
                p.setNomProduit(rs.getString("nomProduit"));

                p.setDateExpoProduit(rs.getDate("dateExpoProduit"));
                p.setIdProduit(rs.getInt("idProduit"));
                p.setQteVenduProduit(rs.getInt(14));
                p.setQteDispoProduit(rs.getInt(12));
                p.setTailleProduit(rs.getString(3));
                p.setMatiereProduit(rs.getString(4));
                p.setPrixVenteProduit(rs.getDouble(6));

                Produits.add(p);

            }

        } catch (SQLException ex) {

        }

        return Produits;

    }

    @Override
    public ArrayList<Videodiy> listVideosByUser(User user) {
        ArrayList<Videodiy> videos = new ArrayList<Videodiy>();
        try {
            String requete = "select * from Videodiy v ,User u where v.id_user=u.id and id='" + user.getId() + "'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Videodiy v = new Videodiy();
                v.setDureeVideo(rs.getString("dureeVideo"));
                
                v.setDateExpoVideo(rs.getDate("dateExpoVideo"));
                v.setDescriptionVideo(rs.getString("descriptionVideo"));
                v.setTitre(rs.getString(8));

                videos.add(v);

            }
        } catch (SQLException ex) {

        }
        return videos;

    }

    

}
