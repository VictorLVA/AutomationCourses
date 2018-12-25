package Helper.OnlinerUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    }

    public static void loginToOnliner(WebDriver driver) {
        driver.findElement(By.cssSelector("div.auth-bar__item.auth-bar__item--text")).click();
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=text]"))
              .sendKeys(onlinerLogin);
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=password]"))
              .sendKeys(onlinerPassword);
        driver.findElement(By.cssSelector("button.auth-button.auth-button_primary.auth-button_middle.auth-form__button.auth-form__button_width_full")).submit();
    }

    public static void openOnlinerFullCatalog(Wait<WebDriver> waiter) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-main-navigation__link"))).click();
    }

    public static void openOnlinerRandomCatalogChapter(WebDriver driver) {
        List<WebElement> classifiersLinks = driver.findElements(By.className("catalog-navigation-classifier__item "));
        int classifiersLinksIndex = (int) (Math.random() * classifiersLinks.size());
        classifiersLinks.get(classifiersLinksIndex).click();
        WebElement selectedCatalogBlock = driver.findElement(By.xpath("//div[@class=\"catalog-navigation-list__category\" and @style=\"display: block;\"]"));
        List<WebElement> categoriesLinks = selectedCatalogBlock.findElements(By.className("catalog-navigation-list__aside-item"));
        int categoriesLinksIndex = (int) (Math.random() * categoriesLinks.size());
        categoriesLinks.get(categoriesLinksIndex).click();
        WebElement activeCategory = driver.findElement(
                By.xpath("//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]"));
        List<WebElement> activeCategoryChapters = activeCategory.findElements(By.className("catalog-navigation-list__dropdown-item"));
        int activeCategoryChaptersIndex = (int) (Math.random() * activeCategoryChapters.size());
        activeCategoryChapters.get(activeCategoryChaptersIndex).click();
    }

    public static void openOnlinerRandomProductWithPrice(WebDriver driver) {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class=\"schema-product__group\"]"));
        boolean isPriceExist = false;
        int attemps = 5;
        while (!isPriceExist) {
            int productsIndex = (int) (Math.random() * products.size());
            try {
                products.get(productsIndex).findElement(By.xpath(".//div[@class=\"schema-product__price\"]"));
                isPriceExist = true;
                products.get(productsIndex).findElement(By.xpath(".//div[@class=\"schema-product__title\"]")).click();
            } catch (NoSuchElementException noSuchElementEx) {
                if (attemps == 0) {
                    throw new RuntimeException("To many tries... stopping test");
                } else {
                    attemps -= 1;
                    System.out.println("No offers for the selected product. Trying another product (" + attemps + " attemps left)");
                }
            }
        }
    }

    public static void openOnlinerCart(Wait<WebDriver> waiter) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-top-navigation-cart__link"))).click();
    }
}