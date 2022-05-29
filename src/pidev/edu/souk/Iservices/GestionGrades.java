/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import java.util.List;
import pidev.edu.souk.entities.Statistiques;

/**
 *
 * @author amalb
 */
public interface GestionGrades {

    public List<Statistiques> listGrades();

    public Statistiques findByNomGrade(String grade);

    public int CountGradeSilver();

    public int CountGradeGold();

    public int CountGradePlatinuim();

    public void calculernbreCommandesPerMonth();
    public void calculernbreCommandesPerCategoryProduct();
   

    public void MAJGrade(Statistiques grade, String nomgrade);
}
