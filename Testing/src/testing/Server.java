package testing;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.Selenium;
import java.net.BindException;
import java.net.ServerSocket;
 
public class Server {
    public static SeleniumServer server;
    public static void startSeleniumServer() throws Exception {
 
       try {
     ServerSocket serverSocket = new ServerSocket(RemoteControlConfiguration.DEFAULT_PORT);
     serverSocket.close();
             //Server not up, start it
             try {
              RemoteControlConfiguration rcc = new RemoteControlConfiguration();
              rcc.setPort(RemoteControlConfiguration.DEFAULT_PORT);
              server = new SeleniumServer(false, rcc);
 
             } catch (Exception e) {
                 System.err.println("Could not create Selenium Server because of: "
                         + e.getMessage());
                 e.printStackTrace();
             }
             try {
              server.start();
              System.out.println("Server started");
             } catch (Exception e) {
                 System.err.println("Could not start Selenium Server because of: "
                         + e.getMessage());
                 e.printStackTrace();
             }
         } catch (BindException e) {
             System.out.println("Selenium server already up, will reuse...");
         }
 }
 
 public static void stopSeleniumServer(Selenium selenium){
 selenium.stop();
 if (server != null)
       {
          try
          {
          selenium.shutDownSeleniumServer();
          server.stop();
 
             server = null;
          }
          catch (Exception e)
          {
             e.printStackTrace();
          }
       }
 }
}