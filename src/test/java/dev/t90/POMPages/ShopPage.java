package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShopPage {
    private final Helpers helpers;

    public ShopPage(Helpers helpers) {
        this.helpers = helpers;
        WebDriver driver = helpers.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBys(@FindBy(css = ".add_to_cart_button"))
    private List<WebElement> addToCartElements;

    public void addToCartRnd() {
        var element = (int) Math.round(Math.random() * (addToCartElements.size() - 1));
        helpers.click(addToCartElements.get(element));
        helpers.getWait().until(drv -> drv.findElement(By.linkText("View cart")));
    }
}
