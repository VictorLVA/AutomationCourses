package Helper.OnlinerUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class OnlinerNavigation {

    private static final String onlinerURI = "https://www.onliner.by/";
    private static final String onlinerLogin = "playtika.autotest@tut.by";
    private static final String onlinerPassword = "123456123";

    private OnlinerNavigation() {
    }

    public static void goToOnliner(WebDriver driver) {
        driver.get(onlinerURI);
        System.out.println("Onliner was opened.");
    }

    public static void loginToOnliner(WebDriver driver) {
        driver.findElement(By.cssSelector("div.auth-bar__item--text")).click();
        driver.findElement(By.cssSelector("input.auth-input[type=text]"))
              .sendKeys(onlinerLogin);
        driver.findElement(By.cssSelector("input.auth-input[type=password]"))
              .sendKeys(onlinerPassword);
        driver.findElement(By.cssSelector("button.auth-button")).submit();
        System.out.println("Login to Onliner was performed");
    }

    public static void openOnlinerFullCatalog(Wait<WebDriver> waiter) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-main-navigation__link"))).click();
        System.out.println("Onliner full catalog was opened");
    }

    public static void openOnlinerRandomCatalogChapter(WebDriver driver) {
        List<WebElement> classifiersLinks = driver.findElements(By.className("catalog-navigation-classifier__item "));
        int classifiersLinksIndex = (int) (Math.random() * classifiersLinks.size());
        classifiersLinks.get(classifiersLinksIndex).click();
        System.out.println("Classifier: " + classifiersLinks.get(classifiersLinksIndex).getText());
        WebElement selectedCatalogBlock = driver.findElement(By.xpath("//div[@class=\"catalog-navigation-list__category\" and @style=\"display: block;\"]"));
        List<WebElement> categoriesLinks = selectedCatalogBlock.findElements(By.className("catalog-navigation-list__aside-item"));
        int categoriesLinksIndex = (int) (Math.random() * categoriesLinks.size());
        categoriesLinks.get(categoriesLinksIndex).click();
        System.out.println("Category: " + categoriesLinks.get(categoriesLinksIndex)
                                                         .findElement(By.className("catalog-navigation-list__aside-title"))
                                                         .getText());
        WebElement activeCategory = driver.findElement(By.className("catalog-navigation-list__aside-item_active"));
        List<WebElement> activeCategoryChapters = activeCategory.findElements(By.className("catalog-navigation-list__dropdown-item"));
        int activeCategoryChaptersIndex = (int) (Math.random() * activeCategoryChapters.size());
        System.out.println("Chapter: " + activeCategoryChapters.get(activeCategoryChaptersIndex)
                                                               .findElement(By.className("catalog-navigation-list__dropdown-title"))
                                                               .getText());
        activeCategoryChapters.get(activeCategoryChaptersIndex).click();
    }

    public static void openOnlinerRandomProductWithOffers(WebDriver driver, Wait<WebDriver> waiter) {
        boolean areOffersExist = false;
        int attemps = 3;
        while (!areOffersExist) {
            List<WebElement> productsOffers = driver.findElements(By.xpath("//a[@class=\"schema-product__button button button_orange\"]"));
            if (productsOffers.size() != 0) {
                areOffersExist = true;
                int productsOffersIndex = (int) (Math.random() * productsOffers.size());
                productsOffers.get(productsOffersIndex).click();
            } else {
                if (attemps == 0) {
                    throw new RuntimeException("Too many tries to find product with offers... stopping test");
                } else {
                    attemps -= 1;
                    System.out.println("No offers for products with the selected chapter. Trying another chapter (" + attemps + " attemps left)");
                    openOnlinerFullCatalog(waiter);
                    openOnlinerRandomCatalogChapter(driver);
                }
            }
        }
    }

    public static void openOnlinerCart(Wait<WebDriver> waiter) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-top-navigation-cart__link"))).click();
        System.out.println("Cart was opened");
    }
}