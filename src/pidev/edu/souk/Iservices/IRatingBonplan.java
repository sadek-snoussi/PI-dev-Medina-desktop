/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.Iservices;

import org.controlsfx.control.Rating;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.RatingBonPlan;

/**
 *
 * @author khali
 */
public interface IRatingBonplan {
    
    public void AjouterNote(RatingBonPlan RB);
    public Double calculerNote(int idBp);
    public void UpdateMoyeneBonPlan(int idBonPlan);
    
    
}
