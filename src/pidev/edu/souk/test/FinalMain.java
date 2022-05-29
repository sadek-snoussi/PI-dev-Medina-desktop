/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.edu.souk.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FinalMain {
    
    public static void main(String[] args) {
        
        
     ExecutorService executorService = Executors.newFixedThreadPool(10);

try
{

   

 

      executorService.execute(
        new Runnable()
        {
          public void run()
          {        
            Main.main(args);
          }
        }
      );

      executorService.execute(
        new Runnable()
        {
          public void run()
          {        
            testSound.main(args);
          }
        }
      );
  
}
finally
{
  executorService.shutdown();
}
        
    }
    
    
}
