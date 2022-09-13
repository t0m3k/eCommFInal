package dev.t90.POMPages;

import dev.t90.utils.SharedDictionary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopPage {
    private final SharedDictionary dict;

    public ShopPage(SharedDictionary dict) {
        this.dict = dict;
        WebDriver driver = dict.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBys(@FindBy(css = ".add_to_cart_button"))
    private List<WebElement> addToCartElements;

    public void addToCartRnd() {
        var element = (int) Math.round(Math.random() * (addToCartElements.size() - 1));
        addToCartElements.get(element).click();
        dict.getWait().until(drv -> drv.findElement(By.linkText("View cart")));
    }
}
