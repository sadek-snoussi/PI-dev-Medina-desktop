package pidev.edu.souk.services;



import com.restfb.BinaryAttachment;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.bytebuddy.dynamic.TargetType;
/**
 *
 * @author ASUS
 */
public class PartageFB {
     public void partager(String titre,String description ,String video) throws FileNotFoundException{
    

        
          String domain="https://www.google.fr/";
          //domain="https://google.fr/";
         String appId="1787510017937789";
         String appSecret="2592e1320b720189b457d5cb386fd0b4";
        String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=ads_management,publish_actions";
         
         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
         WebDriver driver= new ChromeDriver();
         driver.get(authURL);
         String accessToken="EAAZAZAuxum0X0BAPfaPQEUWjQZBqdRaU4J7CKEP5BkxYnP4vsXsGVk6Wl44ZCnp9wBfLL25NvbmXAPyYWMjAMfzROkHRN4LI4Vr7GcQBdimK11kEw3HbHUuJNy1tVl5KIGxr49sJ0TrEmZAXVJS2mnh2MmvRosZBbspLi7XSmDXhnDloZAO0TeyUf1vLZAez32VqZB4UTXRz06QZDZD" ;
         
         boolean ok=true;
         while(ok)
         {
             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
             {
                 String url =driver.getCurrentUrl();
                 //accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                 System.out.println(accessToken);
                
                 ok=false;
              }
             
         }
         
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
             // System.out.println(me.g0<etUsername());
              FileInputStream fs=new FileInputStream(new File(video));
              FacebookType publishPhotoResponse = fbClient.publish("me/videos", FacebookType.class,
  BinaryAttachment.with(video,fs),
  Parameter.with("message", titre+" "+description));


        
    
            
    }
}
