import java.util.List;
import java.util.concurrent.TimeUnit;

import OnlinerUtils.Onliner;
import com.jayway.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class Task1L7 {

    private WebDriver driver;
    private Onliner onliner;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Java/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        onliner = new Onliner(driver);
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
        onliner.useOnlinerNavigation().goToOnliner();
        onliner.useOnlinerNavigation().loginToOnliner();
        onliner.useOnlinerNavigation().openOnlinerCart();
        List<WebElement> cartProductsBeforeAdding = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        driver.navigate().back();
        onliner.useOnlinerNavigation().openOnlinerFullCatalog();
        onliner.useOnlinerNavigation().openOnlinerRandomCatalogChapter();
        onliner.useOnlinerNavigation().openOnlinerRandomProductWithOffers();
        onliner.useOnlinerProductsManipulation().addProductToCartWithRandomOffer();
        onliner.useOnlinerNavigation().openOnlinerCart();
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
        onliner.useOnlinerProductsManipulation().removeAllProductsFromCart();
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