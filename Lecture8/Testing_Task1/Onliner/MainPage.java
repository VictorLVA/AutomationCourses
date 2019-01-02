package Onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {

    @FindBy(css = "div.auth-bar__item--text")
    private WebElement basicLoginButton;

    @FindBy(className = "b-top-navigation-cart__link")
    private WebElement cartLink;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void open() {
        getDriver().get("https://www.onliner.by/");
        System.out.println("Onliner main page was opened");
    }

    public LoginPage goToLoginPage() {
        basicLoginButton.click();
        System.out.println("Onliner login page was opened");
        return new LoginPage(getDriver());
    }

    public CartPage goToCartPage() {
        getWait().until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        System.out.println("Onliner cart page was opened");
        return new CartPage(getDriver());
    }
}