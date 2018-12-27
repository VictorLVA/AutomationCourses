package OnlinerUtils;

import org.openqa.selenium.WebDriver;

public class Onliner {

    private OnlinerNavigation onlinerNavigation;
    private OnlinerProductsManipulation onlinerProductsManipulation;

    public Onliner(WebDriver driver) {
        this.onlinerNavigation = new OnlinerNavigation(driver);
        this.onlinerProductsManipulation = new OnlinerProductsManipulation(driver, onlinerNavigation);
    }

    public void goToOnliner() {
        onlinerNavigation.goToOnliner();
    }

    public void loginToOnliner() {
        onlinerNavigation.loginToOnliner();
    }

    public void openOnlinerFullCatalog() {
        onlinerNavigation.openOnlinerFullCatalog();
    }

    public void openOnlinerRandomCatalogChapter() {
        onlinerNavigation.openOnlinerRandomCatalogChapter();
    }

    public void openOnlinerRandomProductWithOffers() {
        onlinerNavigation.openOnlinerRandomProductWithOffers();
    }

    public void openOnlinerCart() {
        onlinerNavigation.openOnlinerCart();
    }

    public void addProductToCartWithRandomOffer() {
        onlinerProductsManipulation.addProductToCartWithRandomOffer();
    }

    public void removeAllProductsFromCart() {
        onlinerProductsManipulation.removeAllProductsFromCart();
    }
}