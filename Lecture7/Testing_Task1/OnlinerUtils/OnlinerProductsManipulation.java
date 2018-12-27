package OnlinerUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnlinerProductsManipulation {

    private WebDriver driver;
    private OnlinerNavigation onlinerNavigation;

    public OnlinerProductsManipulation(WebDriver driver) {
        this.driver = driver;
        onlinerNavigation = new OnlinerNavigation(driver);
    }

    public void addProductToCartWithRandomOffer() {
        driver.findElement(By.className("item")).click();
        System.out.println("Product: " + driver.findElement(By.className("catalog-masthead__title")).getText());
        driver.navigate().back();
        boolean isOfferAvailableForCart = false;
        int attemps = 3;
        while (!isOfferAvailableForCart) {
            List<WebElement> offers = driver.findElements(By.className("offers-list__button_basket"));
            if (offers.size() != 0) {
                isOfferAvailableForCart = true;
                int offersIndex = (int) (Math.random() * offers.size());
                System.out.println("Shop: " + offers.get(offersIndex)
                                                    .findElement(By.xpath("../../td[@class=\"b-cell-4\"]//a[@class=\"logo\"]//img")).getAttribute("alt"));
                System.out.println("Shop link: " + offers.get(offersIndex)
                                                         .findElement(By.xpath("../../td[@class=\"b-cell-4\"]//a[@class=\"logo\"]")).getAttribute("href"));
                offers.get(offersIndex).click();
            } else {
                if (attemps == 0) {
                    throw new RuntimeException("Too many tries to find product for cart adding... stopping test");
                } else {
                    attemps -= 1;
                    System.out.println("No offers with ability to cart adding. Trying another product (" + attemps + " attemps left)");
                    onlinerNavigation.openOnlinerFullCatalog();
                    onlinerNavigation.openOnlinerRandomCatalogChapter();
                    onlinerNavigation.openOnlinerRandomProductWithOffers();
                }
            }
        }
    }

    public void removeAllProductsFromCart() {
        boolean isProductWithinCart = false;
        try {
            driver.findElement(By.className("cart-product__remove"));
            isProductWithinCart = true;
        } catch (NoSuchElementException noSuchElementEx) {
            System.out.println("No products within cart");
        }
        while (isProductWithinCart) {
            try {
                Thread.sleep(500);
                driver.findElement(By.xpath(".//a[@class=\"cart-product__remove\"]")).click();
            } catch (NoSuchElementException noSuchElementEx) {
                isProductWithinCart = false;
                System.out.println("All products were removed within cart");
            } catch (InterruptedException interruptedEx) {
                System.out.println("Something went wrong with products removing");
            }
        }
    }
}