/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import javafx.scene.control.ListCell;
import pidev.edu.souk.controller.CelluleValidVideoController;
import pidev.edu.souk.controller.CelluleVideoPartenaireController;
import pidev.edu.souk.entities.Videodiy;

/**
 *
 * @author hp
 */
public class videoCellListViewAdmin extends ListCell<Videodiy>{
     @Override
    public void updateItem(Videodiy v, boolean empty) {

        super.updateItem(v, empty);
        
        if (empty || v == null) {
         setText(null);
         setGraphic(null);
     } else {
            CelluleValidVideoController data = new CelluleValidVideoController();
            
            
            data.setInfo(v);
            
            setGraphic(data.getCellList());
     }
    
}
}
