import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Task1L7 {

    @Test
    public void testOnliner() throws InterruptedException {
        //создали драйвер
        System.setProperty("webdriver.chrome.driver", "C:/Java/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //зашли на онлайнер
        driver.get("https://www.onliner.by/");
        //кликнули Войти
        driver.findElement(By.cssSelector("#userbar>div>div>div>div.auth-bar__item.auth-bar__item--text")).click();
        //ввели логин
        driver.findElement(By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/form/div[1]/div/div[2]/div/div/div/div/input"))
              .sendKeys("pit2006@tut.by");
        //ввели пароль
        driver.findElement(By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/form/div[2]/div/div/div/div/input"))
              .sendKeys("15289748");
        //нажали Логин и подождали загрузку
        driver.findElement(By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/form/div[3]/button")).submit();
        Thread.sleep(2000);
        //нажали Каталог и подождали
        driver.findElement(By.className("b-main-navigation__link")).click();
        Thread.sleep(2000);
        //получили Классификаторы каталога и кликнули и подождали
        List<WebElement> catalogLinks = driver.findElements(By.className("catalog-navigation-classifier__item "));
        System.out.println(catalogLinks.size());
        int index = (int) (Math.random() * catalogLinks.size());
        catalogLinks.get(index).click();
        Thread.sleep(2000);
        //получили Классификаторы раздела каталога и кликнули и подождали
        WebElement fullBlock = driver.findElement(By.xpath("//div[@class=\"catalog-navigation-list__category\" and @style=\"display: block;\"]"));
        List<WebElement> aLeftLinks = fullBlock.findElements(By.className("catalog-navigation-list__aside-item"));
        System.out.println(aLeftLinks.size());
        int index2 = (int) (Math.random() * aLeftLinks.size());
        aLeftLinks.get(index2).click();
        Thread.sleep(2000);
        //получили Классификаторы типа товара и кликнули и подождали
        WebElement activeBlock = aLeftLinks.get(index2)
                                           .findElement(By.xpath(
                                                   "//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]"));
        List<WebElement> aRightLinks = activeBlock.findElements(By.className("catalog-navigation-list__dropdown-item"));
        System.out.println(aRightLinks.size());
        int index3 = (int) (Math.random() * aRightLinks.size());
        aRightLinks.get(index3).click();
        Thread.sleep(2000);
        //получили товары со страницы и открыли рандомный
        boolean isAddedToBuy = false;
        while (!isAddedToBuy) {
            List<WebElement> products = driver.findElements(By.xpath("//a[@data-bind=\"attr: {href: product.html_url}\"]"));
            System.out.println(products.size());
            int index4 = (int) (Math.random() * products.size());
            products.get(index4).click();
            Thread.sleep(5000);
            //нажали в корзину и подождали
            WebElement buyBlock = driver.findElement(
                    By.xpath("//div[@class=\"product-aside__item product-aside__item--highlighted state_add-to-cart product-aside__item--byn\"]"));
            if (buyBlock.findElement(By.xpath("//a[@class=\"button button_orange product-aside__item-button\"]")).isDisplayed()) {
                buyBlock.findElement(By.xpath("//a[@class=\"button button_orange product-aside__item-button\"]")).click();
                isAddedToBuy = true;
            }
            else {
                driver.navigate().back();
            }
            Thread.sleep(2000);
        }
        //открыли корзину и подождали
        driver.findElement(By.className("b-top-navigation-cart__link")).click();
        Thread.sleep(2000);
        //получили список товаров в корзине и подождали
        List<WebElement> productsToBuy = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        System.out.println(productsToBuy.size());
        Thread.sleep(2000);
        //удалили товар из корзины и подождали
        driver.findElement(By.className("cart-product__remove")).click();
        Thread.sleep(2000);
        //проверили удаление
        List<WebElement> emptyProductsToBuy = driver.findElements(By.xpath("//div[@class=\"cart-product\"]"));
        System.out.println(emptyProductsToBuy.size());
        Thread.sleep(2000);
        driver.close();
    }
}