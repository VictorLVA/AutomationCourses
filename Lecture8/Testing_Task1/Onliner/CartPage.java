package Onliner;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {

    @FindBy(xpath = "//a[@href=\"https://catalog.onliner.by\"]")
    private WebElement catalogLink;

    @FindBy(className = "cart-product__remove")
    private WebElement cartProductDeletingLink;

    @FindBys(@FindBy(
            xpath = "//div[@class=\"cart-product\"]"))
    private List<WebElement> cartProductsList;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public CatalogPage goToCatalogPage() {
        getWait().until(ExpectedConditions.elementToBeClickable(catalogLink)).click();
        System.out.println("Onliner catalog page was opened");
        return new CatalogPage(getDriver());
    }

    public void removeAllProductsFromCart() {
        boolean isProductWithinCart = false;
        try {
            cartProductDeletingLink.isDisplayed();
            isProductWithinCart = true;
        } catch (NoSuchElementException noSuchElementEx) {
            System.out.println("No products within cart");
        }
        while (isProductWithinCart) {
            try {
                Thread.sleep(500);
                cartProductDeletingLink.click();
            } catch (NoSuchElementException noSuchElementEx) {
                isProductWithinCart = false;
                System.out.println("All products were removed within cart");
            } catch (InterruptedException interruptedEx) {
                System.out.println("Something went wrong with products removing");
            }
        }
    }

    public int getCartSize() {
        return cartProductsList.size();
    }
}