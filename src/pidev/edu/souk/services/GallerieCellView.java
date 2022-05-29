/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import javafx.scene.control.ListCell;
import pidev.edu.souk.entities.Bonplan;
import pidev.edu.souk.entities.Gallerie;
import pidev.edu.souk.controller.CellGallerieUserController;

/**
 *
 * @author khali
 */
public class GallerieCellView extends ListCell<Gallerie> {
    
    @Override
    public void updateItem(Gallerie gallerie,boolean empty){
        super.updateItem(gallerie, empty);
        if (gallerie != null){
            CellGallerieUserController data = new CellGallerieUserController();   
            data.setInfo(gallerie);
            setGraphic(data.getCellPanier());
        }
}
    
    
}
