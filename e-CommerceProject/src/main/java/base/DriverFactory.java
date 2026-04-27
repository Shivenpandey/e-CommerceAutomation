package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver
        = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        // Disable password manager completely
        prefs.put("credentials_enable_service",
            false);
        prefs.put(
            "profile.password_manager_enabled",
            false);
        prefs.put(
            "profile.password_manager_leak_detection",
            false);

        // Disable notifications
        prefs.put(
            "profile.default_content_setting_values"
            + ".notifications", 2);

        // Disable all infobars
        prefs.put(
            "profile.default_content_settings"
            + ".popups", 0);

        options.setExperimentalOption("prefs", prefs);

        // Disable automation flags
        options.setExperimentalOption(
            "excludeSwitches",
            new String[]{"enable-automation"});
        options.setExperimentalOption(
            "useAutomationExtension", false);

        // All arguments
        options.addArguments("--no-sandbox");
        options.addArguments(
            "--disable-dev-shm-usage");
        options.addArguments(
            "--disable-notifications");
        options.addArguments(
            "--disable-popup-blocking");
        options.addArguments(
            "--disable-save-password-bubble");
        options.addArguments(
            "--disable-features="
            + "PasswordLeakDetection,"
            + "SafeBrowsingEnhancedProtection");
        options.addArguments(
            "--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments(
            "--disable-blink-features="
            + "AutomationControlled");

        driver.set(new ChromeDriver(options));
        getDriver().manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}