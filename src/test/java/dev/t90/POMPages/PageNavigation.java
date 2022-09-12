package dev.t90.POMPages;

import dev.t90.utils.SharedDictionary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageNavigation {
    private final SharedDictionary dict;
    private WebDriver driver;

    public PageNavigation(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(linkText = "Shop")
    private WebElement shopLink;
    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(linkText = "My account")
    private WebElement myAccountLink;

    @FindBy(linkText = "Orders")
    private WebElement ordersLink;


    public void goHome() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }

    public void goShop() {
        shopLink.click();
    }

    public void goCart() {
        cartLink.click();
    }

    public void goAccount() {
        myAccountLink.click();
    }

    public void goOrders() {
        goAccount();
        ordersLink.click();
    }

}
