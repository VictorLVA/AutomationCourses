import java.util.List;
import java.util.concurrent.TimeUnit;

import Helper.ChromeDriverSettings;
import com.jayway.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class Task1L7 {

    private static final String onlinerURI = "https://www.onliner.by/";
    private static final String onlinerLogin = "playtika.autotest@tut.by";
    private static final String onlinerPassword = "123456123";

    @Test
    public void pingOnliner() {
        Response onlinerResponse = given().when().get(onlinerURI);
        Assert.assertEquals(
                onlinerResponse.statusCode(),
                200,
                "Cannot access Onliner (" + onlinerURI + ") -"
        );
    }

    @Test(dependsOnMethods = "pingOnliner")
    public void addProductToCart() {
        System.setProperty(ChromeDriverSettings.getDriverName(), ChromeDriverSettings.getLocation());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 500);
        driver.get(onlinerURI);
        driver.findElement(By.cssSelector("div.auth-bar__item.auth-bar__item--text")).click();
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=text]"))
              .sendKeys(onlinerLogin);
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=password]"))
              .sendKeys(onlinerPassword);
        driver.findElement(By.cssSelector("button.auth-button.auth-button_primary.auth-button_middle.auth-form__button.auth-form__button_width_full")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-main-navigation__link"))).click();
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
        List<WebElement> offers = driver.findElements(By.className("product-aside__item"));
        if (offers.size() == 1) {
            try {
                offers.get(0).findElement(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")).click();
            } catch (NoSuchElementException noSuchElementEx) {
                throw new RuntimeException("Single offer cannot be added to the cart. Test will be stopped and failed.");
            }
        } else {
            boolean isAvailableToCart = false;
            attemps = 5;
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
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class=\"button button_orange product-aside__item-button\"]")));
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
        driver.findElement(By.className("b-top-navigation-cart__link")).click();
        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                cartProducts.size(),
                1,
                "Product wasn't added to the cart - "
        );
        driver.close();
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void removeProductFromCart() {
        System.setProperty(ChromeDriverSettings.getDriverName(), ChromeDriverSettings.getLocation());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 500);
        driver.get(onlinerURI);
        driver.findElement(By.cssSelector("div.auth-bar__item.auth-bar__item--text")).click();
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=text]"))
              .sendKeys(onlinerLogin);
        driver.findElement(By.cssSelector("input.auth-input.auth-input_primary.auth-input_base.auth-form__input.auth-form__input_width_full[type=password]"))
              .sendKeys(onlinerPassword);
        driver.findElement(By.cssSelector("button.auth-button.auth-button_primary.auth-button_middle.auth-form__button.auth-form__button_width_full")).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-top-navigation-cart__link"))).click();
        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        int cartProductsIndex = (int) (Math.random() * cartProducts.size());
        cartProducts.get(cartProductsIndex).findElement(By.className("cart-product__remove")).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class=\"cart-product\"]"))));
        List<WebElement> emptyCart = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                emptyCart.size(),
                cartProducts.size() - 1,
                "Product wasn't deleted from the cart - "
        );
        driver.close();
    }
}