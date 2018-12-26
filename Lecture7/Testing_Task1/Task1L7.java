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

    @BeforeClass
    public void setupChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/chromedriver/chromedriver.exe");
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
        WebDriver myChromeDriver = new ChromeDriver();
        myChromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Wait<WebDriver> myWait = new WebDriverWait(myChromeDriver, 5, 500);
        goToOnliner(myChromeDriver);
        loginToOnliner(myChromeDriver);
        openOnlinerCart(myWait);
        List<WebElement> cartProductsBeforeAdding = myChromeDriver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        myChromeDriver.navigate().back();
        openOnlinerFullCatalog(myWait);
        openOnlinerRandomCatalogChapter(myChromeDriver);
        openOnlinerRandomProductWithOffers(myChromeDriver, myWait);
        addProductToCartWithRandomOffer(myChromeDriver, myWait);
        openOnlinerCart(myWait);
        List<WebElement> cartProductsAfterAdding = myChromeDriver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                cartProductsAfterAdding.size(),
                cartProductsBeforeAdding.size() + 1,
                "Product wasn't added to the cart - "
        );
        myChromeDriver.close();
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void removeProductFromCart() {
        WebDriver myChromeDriver = new ChromeDriver();
        myChromeDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Wait<WebDriver> myWait = new WebDriverWait(myChromeDriver, 5, 500);
        goToOnliner(myChromeDriver);
        loginToOnliner(myChromeDriver);
        openOnlinerCart(myWait);
        removeAllProductsFromCart(myChromeDriver);
        List<WebElement> cartAfterRemoving = myChromeDriver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        Assert.assertEquals(
                cartAfterRemoving.size(),
                0,
                "Product wasn't deleted from the cart - "
        );
        myChromeDriver.close();
    }
}