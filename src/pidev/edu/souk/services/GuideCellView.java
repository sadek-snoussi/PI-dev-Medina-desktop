/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import javafx.scene.control.ListCell;
import pidev.edu.souk.controller.CellGallerieUserController;
import pidev.edu.souk.controller.CellGuideUserController;
import pidev.edu.souk.entities.Guide;

/**
 *
 * @author khali
 */
public class GuideCellView extends ListCell<Guide> {
    
        @Override
    public void updateItem(Guide guide,boolean empty){
        super.updateItem(guide, empty);
        if (guide != null){
            CellGuideUserController data = new CellGuideUserController();   
            data.setInfo(guide);
            setGraphic(data.getCellPanier());
        }
}
    
    
}
