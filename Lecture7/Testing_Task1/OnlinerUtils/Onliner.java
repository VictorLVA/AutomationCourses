package OnlinerUtils;

import org.openqa.selenium.WebDriver;

public class Onliner {

    private OnlinerNavigation onlinerNavigation;
    private OnlinerProductsManipulation onlinerProductsManipulation;

    public Onliner(WebDriver driver) {
        this.onlinerNavigation = new OnlinerNavigation(driver);
        this.onlinerProductsManipulation = new OnlinerProductsManipulation(driver,onlinerNavigation);
    }

    public OnlinerNavigation useOnlinerNavigation() {
        return onlinerNavigation;
    }

    public OnlinerProductsManipulation useOnlinerProductsManipulation() {
        return onlinerProductsManipulation;
    }
}