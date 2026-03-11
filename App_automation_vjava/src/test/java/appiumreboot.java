import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class appiumreboot {

    static AndroidDriver driver;
    //You can get device udid from adb devices command or from settings in case of android, in case of ios you can get the same xcode after attaching your ios device
    static String udid = "your_device_udid";


    public static void main(String[] args) {

        try {

            // Start first Appium session
            startDriver();

            System.out.println("Device session started");

            // Reboot device
            rebootDevice();

            // Quit old session
            driver.quit();

            // Wait for device to reconnect and boot
            waitForDeviceBoot();

            // Start new Appium session
            startDriver();

            System.out.println("New session started after reboot");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startDriver() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("appium:platformName", "Android");
        caps.setCapability("appium:deviceName", "Pixel_Device");
        caps.setCapability("appium:udid", udid);
        caps.setCapability("appium:automationName", "UiAutomator2");

        caps.setCapability("appPackage", "com.android.settings");
        caps.setCapability("appActivity", ".Settings");

        caps.setCapability("noReset", true);
        caps.setCapability("newCommandTimeout", 300);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/wd/hub"),
                caps
        );
    }

    public static void rebootDevice() throws Exception {

        System.out.println("Rebooting device...");

        Runtime.getRuntime().exec("adb -s " + udid + " reboot");

        Thread.sleep(30000);
    }

    public static void waitForDeviceBoot() throws Exception {

        System.out.println("Waiting for device to reconnect...");

        Process waitDevice = Runtime.getRuntime().exec("adb -s " + udid + " wait-for-device");
        waitDevice.waitFor();

        System.out.println("Device connected to ADB");

        while (true) {

            Process process = Runtime.getRuntime().exec(
                    new String[]{"adb", "-s", udid, "shell", "getprop", "sys.boot_completed"}
            );

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String output = reader.readLine();

            if ("1".equals(output)) {
                System.out.println("Android boot completed");
                break;
            }

            Thread.sleep(3000);
        }

        System.out.println("Waiting for UI services...");

        Thread.sleep(10000);
    }
}