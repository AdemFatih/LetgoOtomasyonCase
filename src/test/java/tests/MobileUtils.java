package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class MobileUtils {

    //Scroll için kulalanılır
    public static void scrollDownSmall(AndroidDriver driver) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("percent", 1.3); // %130 oranında scroll yapar
        params.put("left", 100);
        params.put("top", 500);
        params.put("width", 900);
        params.put("height", 1000);

        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
    }

//Ekran Görüntüsü alan ve bunu özel isimle klasöre atar
    public static void takeScreenshot(AndroidDriver driver, String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("./screenshots/" + fileName + ".png");
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved as: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
        }
    }

// Mobil cihazda geri tuşu olarak kullanılır
    public static void pressBack(AndroidDriver driver) {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

}
