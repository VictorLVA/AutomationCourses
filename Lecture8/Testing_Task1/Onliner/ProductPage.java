package Onliner;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Page {

    @FindBys(@FindBy
            (className = "catalog-navigation-classifier__item "))
    private List<WebElement> classifiersLinks;

    @FindBys(@FindBy
            (xpath = "//div[@class=\"catalog-navigation-list__category\" and @style=\"display: block;\"]" +
                    "//div[@class=\"catalog-navigation-list__aside-item\"]"))
    private List<WebElement> categoriesLinks;

    @FindBys(@FindBy
            (xpath = "//div[@class=\"catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active\"]" +
                    "//a[@class=\"catalog-navigation-list__dropdown-item\"]"))
    private List<WebElement> activeCategoryChapters;

    @FindBys(@FindBy(
            xpath = "//a[@class=\"schema-product__button button button_orange\"]"))
    private List<WebElement> productsOffersButtons;

    @FindBy(className = "item")
    private WebElement descriptionLink;

    @FindBy(className = "catalog-masthead__title")
    private WebElement productTitle;

    @FindBys(@FindBy(
            className = "offers-list__button_basket"))
    List<WebElement> offers;

    ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public void addProductToCartWithRandomOffer() {
        getWait().until(ExpectedConditions.elementToBeClickable(descriptionLink)).click();
        System.out.println("Product: " + productTitle.getText());
        getDriver().navigate().back();
        boolean isOfferAvailableForCart = false;
        int attemps = 3;
        while (!isOfferAvailableForCart) {
            if (offers.size() != 0) {
                isOfferAvailableForCart = true;
                int offersIndex = (int) (Math.random() * offers.size());
                System.out.println("Shop: " + offers.get(offersIndex)
                                                    .findElement(By.xpath("../../td[@class=\"b-cell-4\"]//a[@class=\"logo\"]//img")).getAttribute("alt"));
                System.out.println("Shop link: " + offers.get(offersIndex)
                                                         .findElement(By.xpath("../../td[@class=\"b-cell-4\"]//a[@class=\"logo\"]")).getAttribute("href"));
                getWait().until(ExpectedConditions.elementToBeClickable(offers.get(offersIndex))).click();
            } else {
                if (attemps == 1) {
                    throw new RuntimeException("Too many tries to find product for cart adding... stopping test");
                } else {
                    attemps -= 1;
                    System.out.println("No offers with ability to cart adding. Trying another product (" + attemps + " attemps left)");
                    reSelectChapter();
                    reSelectProduct();
                }
            }
        }
    }

    private void reSelectChapter() {
        int classifiersLinksIndex = (int) (Math.random() * classifiersLinks.size());
        getWait().until(ExpectedConditions.elementToBeClickable(classifiersLinks.get(classifiersLinksIndex))).click();
        System.out.println("Classifier: " + classifiersLinks.get(classifiersLinksIndex).getText());
        int categoriesLinksIndex = (int) (Math.random() * categoriesLinks.size());
        getWait().until(ExpectedConditions.elementToBeClickable(categoriesLinks.get(categoriesLinksIndex))).click();
        System.out.println("Category: " + categoriesLinks.get(categoriesLinksIndex)
                                                         .findElement(By.className("catalog-navigation-list__aside-title"))
                                                         .getText());
        int activeCategoryChaptersIndex = (int) (Math.random() * activeCategoryChapters.size());
        System.out.println("Chapter: " + activeCategoryChapters.get(activeCategoryChaptersIndex)
                                                               .findElement(By.className("catalog-navigation-list__dropdown-title"))
                                                               .getText());
        getWait().until(ExpectedConditions.elementToBeClickable(activeCategoryChapters.get(activeCategoryChaptersIndex))).click();
    }

    private void reSelectProduct() {
        boolean areOffersExist = false;
        int attemps = 3;
        while (!areOffersExist) {
            if (productsOffersButtons.size() != 0) {
                areOffersExist = true;
                int productsOffersIndex = (int) (Math.random() * productsOffersButtons.size());
                productsOffersButtons.get(productsOffersIndex).click();
            } else {
                if (attemps == 1) {
                    throw new RuntimeException("Too many tries to find product with offers... stopping test");
                } else {
                    attemps -= 1;
                    System.out.println("No offers for products with the selected chapter. Trying another chapter (" + attemps + " attemps left)");
                    reSelectChapter();
                }
            }
        }
    }
}