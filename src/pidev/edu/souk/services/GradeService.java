package pidev.edu.souk.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.edu.souk.Iservices.GestionGrades;
import pidev.edu.souk.entities.Statistiques;
import pidev.edu.souk.entities.User;
import pidev.edu.souk.utils.MyConnection;

/**
 *
 * @author amalb
 */
public class GradeService implements GestionGrades {

    public static int totaleJ = 0;
    public static int totaleF = 0;
    public static int totaleM = 0;
    public static int totaleA = 0;
    public static int totaleMai = 0;
    public static int totaleJuin = 0;
    public static int totaleJuillet = 0;
    public static int totaleAout = 0;

    public static int totaleCatEpicerieJ = 0;
    public static int totaleCatPatisserieJ = 0;
    public static int totalePoterieJ = 0;
    public static int totaleCatBijouterieJ = 0;
    public static int totaleCatTextileJ = 0;

      public static int totaleCatEpicerieF = 0;
    public static int totaleCatPatisserieF = 0;
    public static int totalePoterieF= 0;
    public static int totaleCatBijouterieF = 0;
    public static int totaleCatTextileF = 0;
    
      public static int totaleCatEpicerieM = 0;
    public static int totaleCatPatisserieM = 0;
    public static int totalePoterieM = 0;
    public static int totaleCatBijouterieM = 0;
    public static int totaleCatTextileM = 0;
    
      public static int totaleCatEpicerieA = 0;
    public static int totaleCatPatisserieA = 0;
    public static int totalePoterieA = 0;
    public static int totaleCatBijouterieA = 0;
    public static int totaleCatTextileA = 0;
    
      public static int totaleCatEpicerieMai = 0;
    public static int totaleCatPatisserieMai = 0;
    public static int totalePoterieMai = 0;
    public static int totaleCatBijouterieMai = 0;
    public static int totaleCatTextileMai = 0;
    
      public static int totaleCatEpicerieJuin = 0;
    public static int totaleCatPatisserieJuin = 0;
    public static int totalePoterieJuin = 0;
    public static int totaleCatBijouterieJuin = 0;
    public static int totaleCatTextileJuin = 0;
    
      public static int totaleCatEpicerieJuillet = 0;
    public static int totaleCatPatisserieJuillet = 0;
    public static int totalePoterieJuillet = 0;
    public static int totaleCatBijouterieJuillet = 0;
    public static int totaleCatTextileJuillet = 0;
    
      public static int totaleCatEpicerieAout = 0;
    public static int totaleCatPatisserieAout = 0;
    public static int totalePoterieAout = 0;
    public static int totaleCatBijouterieAout = 0;
    public static int totaleCatTextileAout = 0;
    
    
    @Override
    public ArrayList<Statistiques> listGrades() {
        ArrayList<Statistiques> grades = new ArrayList<Statistiques>();
        try {
            String requete = "select * from statistiques";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Statistiques grade = new Statistiques();
                grade.setId(rs.getInt("id"));
                grade.setGrade(rs.getString("grade"));
                grade.setNbproduitsVendu(rs.getInt("nbproduits_vendu"));
                grade.setNbvideoPost(rs.getInt("nbvideo_post"));

                grades.add(grade);

            }
        } catch (SQLException ex) {

        }
        return grades;

    }

    @Override
    public Statistiques findByNomGrade(String grade) {
        Statistiques grades = new Statistiques();
        try {
            String requete = "select * from Statistiques where grade='" + grade + "'";

            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                grades.setId(rs.getInt("id"));
                grades.setGrade(grade);
                grades.setNbproduitsVendu(rs.getInt("nbproduits_vendu"));
                grades.setNbvideoPost(rs.getInt("nbvideo_post"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }

    @Override
    public void MAJGrade(Statistiques grade, String nomgrade) {
        try {

            String requete = "update statistiques set nbproduits_vendu=?,"
                    + "nbvideo_post=? where grade=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, grade.getNbproduitsVendu());
            pst.setInt(2, grade.getNbvideoPost());
            pst.setString(3, nomgrade);

            pst.executeUpdate();
            System.out.println("Modification effectuée avec succés");

        } catch (SQLException ex) {
            Logger.getLogger(PartenaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int CountGradeSilver() {
        Integer silver = 0;
        try {
            String requete = "select gradePro  from User u  where gradePro='silver'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                silver += 1;
            }

        } catch (SQLException ex) {

        }
        System.out.println("***********" + silver);
        return silver;
    }

    @Override
    public int CountGradeGold() {
        Integer gold = 0;
        try {
            String requete = "select gradePro from User u  where gradePro='gold'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                gold += 1;
            }

        } catch (SQLException ex) {

        }
        System.out.println("***********" + gold);
        return gold;

    }

    @Override
    public int CountGradePlatinuim() {
        Integer plat = 0;
        try {
            String requete = "select gradePro  from User u  where gradePro='platinuim'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                plat += 1;
            }

        } catch (SQLException ex) {

        }
        System.out.println("***********" + plat);
        return plat;

    }

    @Override
    public void calculernbreCommandesPerMonth() {

        String requete = "SELECT * FROM commande JOIN panier ON commande.panier_id=panier.id ";

        try {
            Statement statement = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = statement.executeQuery(requete);
            while (rs.next()) {
                if (rs.getDate("dateCommande").getMonth() + 1 == 1) {
                    totaleJ += 1;
                    System.out.println("janvier" + totaleJ + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 2) {
                    totaleF += 1;
                    System.out.println("fevrier" + totaleF + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 3) {
                    totaleM += 1;
                    System.out.println("mars" + totaleM + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 4) {
                    totaleA += 1;
                    System.out.println("avril" + totaleA + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 5) {
                    totaleMai += 1;
                    System.out.println("mai" + totaleMai + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 6) {
                    totaleJuin += 1;
                    System.out.println("juin" + totaleJuin + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 7) {
                    totaleJuillet += 1;
                    System.out.println("juillet" + totaleJuillet + "date" + rs.getDate("dateCommande"));

                } else if (rs.getDate("dateCommande").getMonth() + 1 == 8) {
                    totaleAout += 1;
                    System.out.println("aout" + totaleAout + "date" + rs.getDate("dateCommande"));
                }

            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public void calculernbreCommandesPerCategoryProduct() {
        String requete = "SELECT nomCategorie as nomC , dateCommande as date  FROM panier"
                + " join commande on commande.user_id=panier.user_id "
                + "JOIN produit ON panier.produit_id=produit.idProduit "
                + "join categorie on produit.idCategorie=categorie.idCategorie  "
                + "where panier.flag=1 ";

        try {
            Statement statement = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = statement.executeQuery(requete);
            while (rs.next()) {

                System.out.println("date**********" + rs.getString("date") + "month*****" + rs.getDate("date").getMonth());
                System.out.println("cate**********" + rs.getString("nomC"));
                if (rs.getDate("date").getMonth() + 1 == 1) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieJ++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieJ++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieJ++;
                        //System.out.println("++++++++++++++++++++" + totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieJ++;
                        // System.out.println("++++++++++++++++++++" + totaleCatBijouterie);
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileJ++;
                    }
                }

                System.out.println("1111111111111111111111111111");
                if (rs.getDate("date").getMonth() + 1 == 2) {

                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieF++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieF++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieF++;

                        // System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieF++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileF++;
                    }

                }

                System.out.println("2222222222222222222222");
                if (rs.getDate("date").getMonth() + 1 == 3) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieM++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieM++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieM++;
                        //System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }

                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieM++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileM++;
                    }

                }

                System.out.println("3333333333333333333333333333333333333333");
                if (rs.getDate("date").getMonth() + 1 == 4) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieA++;
                    }

                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieA++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieA++;
                        // System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieA++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileA++;
                    }

                }
                System.out.println("444444444444444444444444444444444444");
                if (rs.getDate("date").getMonth() + 1 == 5) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieMai++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieMai++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieMai++;
                        //  System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieMai++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileMai++;
                    }

                }
                if (rs.getDate("date").getMonth() + 1 == 6) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieJuin++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieJuin++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieJuin++;
                        // System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieJuin++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileJuin++;
                    }

                }
                if (rs.getDate("date").getMonth() + 1 == 7) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieJuillet++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieJuillet++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieJuillet++;
//                     System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieJuillet++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileJuillet++;
                    }

                }
                if (rs.getDate("date").getMonth() + 1 == 8) {
                    if ("patisserie".equals(rs.getString("nomC"))) {
                        totaleCatPatisserieAout++;
                    }
                    if ("poterie".equals(rs.getString("nomC"))) {
                        totalePoterieAout++;
                    }
                    if ("epicerie".equals(rs.getString("nomC"))) {
                        totaleCatEpicerieAout++;
                        // System.out.println("++++++++++++++++++++"+totaleCatEpicerie);
                    }
                    if ("bijouterie".equals(rs.getString("nomC"))) {
                        totaleCatBijouterieAout++;
                    }
                    if ("textile".equals(rs.getString("nomC"))) {
                        totaleCatTextileAout++;
                    }

                }

                System.out.println("hereeeeeeeeeeeee");

            }
        } catch (SQLException ex) {

        }
    }

}
