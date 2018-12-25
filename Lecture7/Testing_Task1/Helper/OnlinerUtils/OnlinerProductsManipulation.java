package Helper.OnlinerUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class OnlinerProductsManipulation {

    private OnlinerProductsManipulation() {
    }

    public static void addProductToCartWithRandomOffer(WebDriver driver, Wait<WebDriver> waiter) {
        List<WebElement> offers = driver.findElements(By.className("product-aside__item"));
        if (offers.size() == 1) {
            try {
                offers.get(0).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")).click();
            } catch (NoSuchElementException noSuchElementEx) {
                throw new RuntimeException("Single offer cannot be added to the cart. Test will be stopped and failed.");
            }
        } else {
            boolean isAvailableToCart = false;
            int attemps = 5;
            while (!isAvailableToCart) {
                int offersIndex = (int) (Math.random() * offers.size());
                if (offersIndex == 0) {
                    try {
                        offers.get(offersIndex).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]"));
                        isAvailableToCart = true;
                        offers.get(offersIndex).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")).click();
                    } catch (NoSuchElementException noSuchElementEx) {
                        if (attemps == 0) {
                            throw new RuntimeException("To many tries... stopping test");
                        } else {
                            attemps -= 1;
                            System.out.println("Offer cannot be added to the cart. Trying another offer (" + attemps + " attemps left)");
                        }
                    }
                } else {
                    try {
                        offers.get(offersIndex).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]"));
                        isAvailableToCart = true;
                        Actions mouseActions = new Actions(driver);
                        mouseActions.moveToElement(offers.get(offersIndex)).click().build().perform();
                        waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")));
                        offers.get(offersIndex).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")).click();
                    } catch (NoSuchElementException noSuchElementEx) {
                        if (attemps == 0) {
                            throw new RuntimeException("To many tries... stopping test");
                        } else {
                            attemps -= 1;
                            System.out.println("Offer cannot be added to the cart. Trying another offer (" + attemps + " attemps left)");
                        }
                    }
                }
            }
        }
    }

    public static void removeAllProductsFromCart(WebDriver driver) {
        boolean isProductWithinCart = false;
        try {
            driver.findElement(By.className("cart-product__remove"));
            isProductWithinCart = true;
        } catch (NoSuchElementException noSuchElementEx) {
            System.out.println("No products within cart");
        }
        while (isProductWithinCart) {
            try {
                driver.findElement(By.className("cart-product__remove"));
                driver.findElement(By.xpath(".//a[@class=\"cart-product__remove\"]")).click();
                Thread.sleep(500);
            } catch (NoSuchElementException noSuchElementEx) {
                isProductWithinCart = false;
                System.out.println("All products were removed within cart");
            } catch (InterruptedException interruptedEx) {
                System.out.println("Something went wrong with products removing");
            }
        }
    }
}