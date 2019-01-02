package Onliner;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page {

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

    CatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    public CatalogChapterPage goToOnlinerRandomCatalogChapter() {
        int classifiersLinksIndex = (int) (Math.random() * classifiersLinks.size());
        classifiersLinks.get(classifiersLinksIndex).click();
        System.out.println("Classifier: " + classifiersLinks.get(classifiersLinksIndex).getText());
        int categoriesLinksIndex = (int) (Math.random() * categoriesLinks.size());
        categoriesLinks.get(categoriesLinksIndex).click();
        System.out.println("Category: " + categoriesLinks.get(categoriesLinksIndex)
                                                         .findElement(By.className("catalog-navigation-list__aside-title"))
                                                         .getText());
        int activeCategoryChaptersIndex = (int) (Math.random() * activeCategoryChapters.size());
        System.out.println("Chapter: " + activeCategoryChapters.get(activeCategoryChaptersIndex)
                                                               .findElement(By.className("catalog-navigation-list__dropdown-title"))
                                                               .getText());
        activeCategoryChapters.get(activeCategoryChaptersIndex).click();
        return new CatalogChapterPage(getDriver());
    }
}