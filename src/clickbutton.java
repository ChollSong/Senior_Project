import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.Double;
import java.util.*;

public class clickbutton {
    
            public static AppiumDriver  wd;
            public static Random random = new Random();
            static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            public static Long times;
            private static String text = "App";
            public static String tittle;




            public static void main(String[] args) throws MalformedURLException {
                
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("appium-version", "1.7.2");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", "5.1.1");
                capabilities.setCapability("deviceName", "EP73249JM1");
                capabilities.setCapability("unicodeKeyboard", "true");
                capabilities.setCapability("app", "/Users/Kaneks/Desktop/Senior/apk/ApiDemos-debug.apk");
                wd = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


//              System.out.println("Start");
//              System.out.print("Input times : ");

//              Scanner scanner = new Scanner(System.in);
//              long times = scanner.nextLong();
//              times = times*60000;
//              long startTime = System.currentTimeMillis();
//              long endTime = startTime+times;




            }

            public static void goBack(){
                try{
                    {
                        
                        ((AndroidDriver) wd).pressKeyCode(AndroidKeyCode.BACK);
            
                    }
                        }catch(Exception e)
                        {
                            System.out.println("ERROR:back");
                        }
            }

            public static void goIndex(int i){
                try{
                    {
                        
                        WebElement el =  wd.findElement(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true).instance("+i+")"));   
                        el.click();
            
                    }
                        }catch(Exception e)
                        {
                            System.out.println("ERROR:index");
                        }
            }
                
}
          
