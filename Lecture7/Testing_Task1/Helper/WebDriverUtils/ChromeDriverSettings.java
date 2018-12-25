package Helper.WebDriverUtils;

class ChromeDriverSettings {

    private ChromeDriverSettings() {
    }

    static String getDriverName() {
        return "webdriver.chrome.driver";
    }

    static String getLocation() {
        return "C:/Java/chromedriver/chromedriver.exe";
    }
}