import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jayway.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Helper.OnlinerUtils.OnlinerNavigation.goToOnliner;
import static Helper.OnlinerUtils.OnlinerNavigation.loginToOnliner;
import static Helper.OnlinerUtils.OnlinerNavigation.openOnlinerCart;
import static Helper.OnlinerUtils.OnlinerNavigation.openOnlinerFullCatalog;
import static Helper.OnlinerUtils.OnlinerNavigation.openOnlinerRandomCatalogChapter;
import static Helper.OnlinerUtils.OnlinerNavigation.openOnlinerRandomProductWithPrice;
import static Helper.OnlinerUtils.OnlinerProductsManipulation.addProductToCartWithRandomOffer;
import static Helper.OnlinerUtils.OnlinerProductsManipulation.removeAllProductsFromCart;
import static Helper.WebDriverUtils.WebDriverUtils.useChromeWebDriver;
import static com.jayway.restassured.RestAssured.given;

public class Task1L7 {

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
        WebDriver myChromeDriver = useChromeWebDriver();
        myChromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Wait<WebDriver> myWaiter = new WebDriverWait(myChromeDriver, 10, 500);
        goToOnliner(myChromeDriver);
        loginToOnliner(myChromeDriver);
        openOnlinerCart(myWaiter);
        List<WebElement> cartProductsBeforeAdding = myChromeDriver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        myChromeDriver.navigate().back();
        openOnlinerFullCatalog(myWaiter);
        openOnlinerRandomCatalogChapter(myChromeDriver);
        openOnlinerRandomProductWithPrice(myChromeDriver);
        addProductToCartWithRandomOffer(myChromeDriver, myWaiter);
        openOnlinerCart(myWaiter);
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
        WebDriver myChromeDriver = useChromeWebDriver();
        myChromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Wait<WebDriver> myWaiter = new WebDriverWait(myChromeDriver, 10, 500);
        goToOnliner(myChromeDriver);
        loginToOnliner(myChromeDriver);
        openOnlinerCart(myWaiter);
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