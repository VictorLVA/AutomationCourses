package OnlinerUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class OnlinerNavigation {

    private static final String ONLINER_URI = "https://www.onliner.by/";
    private static final String ONLINER_LOGIN = "playtika.autotest@tut.by";
    private static final String ONLINER_PASSWORD = "123456123";

    private WebDriver driver;
    private Wait<WebDriver> wait;

    OnlinerNavigation(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5, 500);
    }

    void goToOnliner() {
        driver.get(ONLINER_URI);
        System.out.println("Onliner was opened.");
    }

    void loginToOnliner() {
        driver.findElement(By.cssSelector("div.auth-bar__item--text")).click();
        driver.findElement(By.cssSelector("input.auth-input[type=text]"))
              .sendKeys(ONLINER_LOGIN);
        driver.findElement(By.cssSelector("input.auth-input[type=password]"))
              .sendKeys(ONLINER_PASSWORD);
        driver.findElement(By.cssSelector("button.auth-button")).submit();
        System.out.println("Login to Onliner was performed");
    }

    void openOnlinerFullCatalog() {
        List<WebElement> catalogLinks = driver.findElements(By.xpath("//a[contains(@href,\"catalog\")]"));
        for (WebElement eachCatalogLink : catalogLinks) {
            if (eachCatalogLink.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(eachCatalogLink)).click();
                System.out.println("Onliner full catalog was opened");
                return;
            }
        }
    }

    void openOnlinerRandomCatalogChapter() {
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

    void openOnlinerRandomProductWithOffers() {
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
                    openOnlinerFullCatalog();
                    openOnlinerRandomCatalogChapter();
                }
            }
        }
    }

    void openOnlinerCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-top-navigation-cart__link"))).click();
        System.out.println("Cart was opened");
    }
}