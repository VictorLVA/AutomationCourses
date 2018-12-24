package Helper;

public class ChromeDriverSettings {

    private ChromeDriverSettings() {
    }

    public static String getDriverName() {
        return "webdriver.chrome.driver";
    }

    public static String getLocation() {
        return "C:/Java/chromedriver/chromedriver.exe";
    }
}