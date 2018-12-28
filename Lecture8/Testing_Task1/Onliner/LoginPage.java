package Onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page {

    @FindBy(css = "input.auth-input[type=text]")
    private WebElement loginField;

    @FindBy(css = "input.auth-input[type=password]")
    private WebElement passwordField;

    @FindBy(css = "button.auth-button")
    private WebElement submitButton;

    LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void performLogin() {
        fillLoginField();
        fillPasswordField();
        submitLogin();
    }

    private void fillLoginField() {
        loginField.sendKeys("playtika.autotest@tut.by");
        System.out.println("Login was entered");
    }

    private void fillPasswordField() {
        passwordField.sendKeys("123456123");
        System.out.println("Password was entered");
    }

    private void submitLogin() {
        submitButton.submit();
        System.out.println("Login was submitted");
    }
}