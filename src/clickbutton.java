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
            public static Long times;


            public static void main(String[] args) throws MalformedURLException {
                
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("appium-version", "1.7.2");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("platformVersion", "5.1.1");
                capabilities.setCapability("deviceName", "EP73249JM1");
                capabilities.setCapability("unicodeKeyboard", "true");
                capabilities.setCapability("app", "/Users/Kaneks/Desktop/Senior/apk/com.eclipsim.gpsstatus2_8.0.170-170_minAPI14(nodpi)_apkmirror.com.apk");
                capabilities.setCapability("fullReset", "true");
                wd = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                
// Template if you want to use timeout
//              System.out.println("Start");
//              System.out.print("Input times : ");

//              Scanner scanner = new Scanner(System.in);
//              long times = scanner.nextLong();
//              times = times*60000;
//              long startTime = System.currentTimeMillis();
//              long endTime = startTime+times;
//
//             
//
//              while (System.currentTimeMillis()<endTime)
                
                int x = getClickableNum();
                System.out.println(x);
           
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
            
            //if it error return -1
            public static int getClickableNum(){
                int count = -1;
                try{
                    {
                        List<MobileElement> el = (List<MobileElement>) wd.findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)"));
                        count = el.size();
                        return count;
                    }
                        }catch(Exception e)
                        {
                            System.out.println("ERROR:getNUM");
                        }
                return count;
            }
            
            //if it error return ERROR:name
            public static String getName(){
                String answer = "ERROR:name";
                try{
                    {
                        
                        String name = wd.getPageSource();
                        int hashed = name.hashCode();
                        answer = Integer.toString(hashed);                  
                        return answer;
            
                    }
                        }catch(Exception e)
                        {
                            System.out.println("ERROR:name");
                        }
                return answer;
            }
            
            
            
                
}
                    
