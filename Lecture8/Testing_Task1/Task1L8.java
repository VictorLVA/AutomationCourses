import Onliner.CartPage;
import Onliner.CatalogChapterPage;
import Onliner.CatalogPage;
import Onliner.LoginPage;
import Onliner.MainPage;
import Onliner.ProductPage;
import com.jayway.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class Task1L8 {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
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
        MainPage onlinerMainPage = new MainPage(driver);
        onlinerMainPage.open();
        LoginPage onlinerLoginPage = onlinerMainPage.goToLoginPage();
        onlinerLoginPage.performLogin();
        CartPage onlinerCartPage = onlinerMainPage.goToCartPage();
        int cartProductsCountBeforeAdding = onlinerCartPage.getCartSize();
        CatalogPage onlinerCatalogPage = onlinerCartPage.goToCatalogPage();
        CatalogChapterPage onlinerCatalogChapterPage = onlinerCatalogPage.goToOnlinerRandomCatalogChapter();
        ProductPage onlinerProductPage = onlinerCatalogChapterPage.goToRandomProductWithOffers();
        onlinerProductPage.addProductToCartWithRandomOffer();
        onlinerMainPage.goToCartPage();
        int cartProductsCountAfterAdding = onlinerCartPage.getCartSize();
        Assert.assertEquals(
                cartProductsCountAfterAdding,
                cartProductsCountBeforeAdding + 1,
                "Product wasn't added to the cart - "
        );
    }

    @Test(dependsOnMethods = "addProductToCart")
    public void removeProductFromCart() {
        System.out.println("\n=== Testing products deleting from the cart ===");
        CartPage onlinerCartPage = new CartPage(driver);
        onlinerCartPage.removeAllProductsFromCart();
        int cartProductsCountAfterRemoving = onlinerCartPage.getCartSize();
        Assert.assertEquals(
                cartProductsCountAfterRemoving,
                0,
                "Product wasn't deleted from the cart - "
        );
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }
}