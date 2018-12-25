package Helper.WebDriverUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static Helper.WebDriverUtils.ChromeDriverSettings.getDriverName;
import static Helper.WebDriverUtils.ChromeDriverSettings.getLocation;

public class WebDriverUtils {

    private WebDriverUtils() {
    }

    public static WebDriver useChromeWebDriver() {
        System.setProperty(getDriverName(), getLocation());
        return new ChromeDriver();
    }
}