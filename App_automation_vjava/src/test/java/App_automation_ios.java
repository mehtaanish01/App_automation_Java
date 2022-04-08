import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class App_automation_ios {
    public static final String userName = "<userName>";
    public static final String accessKey = "<accessKey>";
    public static final String URL = "https://" + userName + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {

       try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("build", "Proverbial iOS");
            caps.setCapability("platformVersion", "15.0");
            caps.setCapability("deviceName", "iPhone 12");
            caps.setCapability("isRealMobile", true);

            //AppURL (Create from sample.ipa sample in project)
            caps.setCapability("app", "lt://APP100201841648760951043756");

            caps.setCapability("platformName", "iOS");
            caps.setCapability("name", "AppiOS");
            caps.setCapability("devicelog", true);
            caps.setCapability("network", true);


        IOSDriver driver = new IOSDriver(new URL("https://" + userName + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub"), caps);


            Thread.sleep(2000);

            //Changes color

            driver.findElement(MobileBy.id("color")).click();
            Thread.sleep(1000);

            //Back to black color
            driver.navigate().back();

            Thread.sleep(1000);

            //Changes the text to proverbial
            driver.findElement(MobileBy.id("Text")).click();
            Thread.sleep(1000);

            //toast is visible
            driver.findElement(MobileBy.id("toast")).click();
            Thread.sleep(1000);

            //notification is visible
            driver.findElement(MobileBy.id("notification")).click();
            Thread.sleep(2000);

            //Open the geolocation page
            driver.findElement(MobileBy.id("geoLocation")).click();
            Thread.sleep(4000);
            driver.navigate().back();
            Thread.sleep(1000);

            //Takes to speed test page
            driver.findElement(MobileBy.id("speedTest")).click();
            Thread.sleep(5000);
            driver.navigate().back();
            Thread.sleep(1000);

            //Opens the browser
            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();
            Thread.sleep(3000);

            MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Search");
            el4.click();
            el4.sendKeys("Lambdatest");

            //((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            driver.quit();

        } catch (Exception t) {
           System.out.println();

       }
    }
}

