import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jayway.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static OnlinerUtils.OnlinerNavigation.goToOnliner;
import static OnlinerUtils.OnlinerNavigation.loginToOnliner;
import static OnlinerUtils.OnlinerNavigation.openOnlinerCart;
import static OnlinerUtils.OnlinerNavigation.openOnlinerFullCatalog;
import static OnlinerUtils.OnlinerNavigation.openOnlinerRandomCatalogChapter;
import static OnlinerUtils.OnlinerNavigation.openOnlinerRandomProductWithOffers;
import static OnlinerUtils.OnlinerProductsManipulation.addProductToCartWithRandomOffer;
import static OnlinerUtils.OnlinerProductsManipulation.removeAllProductsFromCart;
import static com.jayway.restassured.RestAssured.given;

public class Task1L7 {

    private static WebDriver driver;
    private static Wait<WebDriver> wait;

    @BeforeClass
    public void setupChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5, 500);
    }

    @Test
    public void pingOnliner() {
        Response onlinerResponse = given().when().get("https://www.onliner.by/");
        Assert.assertEquals(
                onlinerResponse.statusCode(),
                200,
                "Cannot access Onliner (https://www.onliner.by/) -"
        );
    }

    @Test(dependsOnMethods = "pingOnliner")
    public void addProductToCart() {
        System.out.println("\n=== Testing product addition to the cart ===");
        goToOnliner(driver);
        loginToOnliner(driver);
        openOnlinerCart(wait);
        List<WebElement> cartProductsBeforeAdding = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        driver.navigate().back();
        openOnlinerFullCatalog(wait);
        openOnlinerRandomCatalogChapter(driver);
        openOnlinerRandomProductWithOffers(driver, wait);
        addProductToCartWithRandomOffer(driver, wait);
        openOnlinerCart(wait);
        List<WebElement> cartProductsAfterAdding = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                cartProductsAfterAdding.size(),
                cartProductsBeforeAdding.size() + 1,
                "Product wasn't added to the cart - "
        );
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void removeProductFromCart() {
        System.out.println("\n=== Testing products deleting from the cart ===");
        removeAllProductsFromCart(driver);
        List<WebElement> cartAfterRemoving = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                cartAfterRemoving.size(),
                0,
                "Product wasn't deleted from the cart - "
        );
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }
}