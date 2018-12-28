package Onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class Page {

    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5, 500);
    }

    WebDriver getDriver() {
        return driver;
    }

    Wait<WebDriver> getWait() {
        return wait;
    }
}