/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.test;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author admin
 */
public class testSound {
    
    public static Mixer mixer;
    public static Clip clip;

    
    public static void main(String[] args){
       //launch(args);
       
       
       Mixer.Info[]mixInfos=AudioSystem.getMixerInfo();
       mixer= AudioSystem.getMixer(mixInfos[0]);
        DataLine.Info dataInfo=new DataLine.Info(Clip.class, null);
        try {
            
            clip=(Clip)mixer.getLine(dataInfo);
            
        } catch (LineUnavailableException ex) {
                  ex.printStackTrace();
        }
       
        try {
            URL soundUrl=Main.class.getResource("/pidev/edu/souk/ressources/xx.wav");
            AudioInputStream audioStream=AudioSystem.getAudioInputStream(soundUrl);
            clip.open(audioStream);
            
        
        }catch(LineUnavailableException lue){
            lue.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (UnsupportedAudioFileException uf) {
            uf.printStackTrace();
        } 
       
       
       clip.start();
       
        do {    

           try {
               Thread.sleep(50);
           } catch (InterruptedException ex) {
           ex.printStackTrace();
           }
            
        } while (clip.isActive());
       
       
       
        }
    
}
