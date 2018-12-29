package Onliner;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class Page {

    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5, 500);
        this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    WebDriver getDriver() {
        return driver;
    }

    Wait<WebDriver> getWait() {
        return wait;
    }
}