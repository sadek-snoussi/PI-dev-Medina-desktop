/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.services;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

/**
 *
 * @author Sofienne
 */
public class ConcatPDFFiles {
    
    public void concat(){
        try {
            PDFMergerUtility ut = new PDFMergerUtility();
            File f1 = new File("C:\\test\\Commande.pdf");
            ut.addSource(f1);
            File f2 = new File("C:\\test\\FirstPdf.pdf") ;
            ut.addSource(f2);
            ut.setDestinationFileName("C:\\test\\Finale.pdf");
            ut.mergeDocuments();
            Desktop.getDesktop().open(new File("C:\\test\\Finale.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(ConcatPDFFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (COSVisitorException ex) {
            Logger.getLogger(ConcatPDFFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
