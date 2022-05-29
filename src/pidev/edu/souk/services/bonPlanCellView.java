/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListCell;
import pidev.edu.souk.controller.CellBonPlanUserController;
import pidev.edu.souk.entities.Bonplan;

/**
 *
 * @author khali
 */
public class bonPlanCellView extends ListCell<Bonplan> {
    @Override
    public void updateItem(Bonplan bonplan,boolean empty){
        super.updateItem(bonplan, empty);
        if (bonplan != null){
            CellBonPlanUserController data = new CellBonPlanUserController();   
            data.setInfo(bonplan);
            setGraphic(data.getCellPanier());
        }
}
}
