import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
public class App_automation_android {
    public static String userName = "<userName>";
    public static String accessKey = "<accessKey>";
    private static RemoteWebDriver driver;

    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "Proverbial Android");
            capabilities.setCapability("deviceName", "Galaxy S21");
            capabilities.setCapability("platformVersion", "11");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("isRealMobile", true);

            //AppURL (Create from sample.apk sample in project)
            capabilities.setCapability("app", "lt://APP100201841649279030516217"); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);


            AppiumDriver driver = new AppiumDriver(new URL("https://" +userName + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub"), capabilities);


            MobileElement color = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/color"));
            //Changes color
            color.click();
            //Back to black color
            color.click();

            MobileElement text = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/Text"));
            //Changes the text to proverbial
            text.click();

            //toast is visible
            MobileElement toast = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/toast"));
            toast.click();

            //notification is visible
            MobileElement notification = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/notification"));
            notification.click();

            //Open the geolocation page
            MobileElement geo = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/geoLocation"));
            geo.click();
            Thread.sleep(5000);

            //takes back to home page
            MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Home");
            driver.navigate().back();
            Thread.sleep(5000);

            //Takes to speed test page
            MobileElement speedtest = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/speedTest"));
            speedtest.click();
            Thread.sleep(5000);
            driver.navigate().back();

            //Opens the browser
            MobileElement browser = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Browser"));
            browser.click();
            MobileElement el13 = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/url"));
            el13.sendKeys("https://www.lambdatest.com");
            MobileElement el14 = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/find"));
            el14.click();
            driver.quit();

            MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Browser");
            el2.click();

        } catch (AssertionError a) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            a.printStackTrace();
        }
// The driver.quit statement is required, otherwise the test continues to execute, leading to a timeout.
        driver.quit();
    }
    }
