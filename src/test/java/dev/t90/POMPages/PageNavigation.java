package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageNavigation {
    private final WebDriver driver;
    private final Helpers helpers;

    public PageNavigation(Helpers helpers) {
        this.helpers = helpers;
        this.driver = helpers.getDriver();
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

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;


    public void goHome() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }

    public void goShop() {
        helpers.click(shopLink);
    }

    public void goCart() {
        helpers.click(cartLink);
    }

    public void goAccount() {
        helpers.click(myAccountLink);
    }

    public void goOrders() {
        goAccount();
        helpers.click(ordersLink);
    }

    public void logout() {
        goAccount();
        helpers.click(logoutLink);
    }
}
