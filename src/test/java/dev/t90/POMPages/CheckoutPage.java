package dev.t90.POMPages;

import dev.t90.utils.SharedDictionary;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final SharedDictionary dict;
    private WebDriver driver;

    public CheckoutPage(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "billing_address_1")
    private WebElement streetInput;

    @FindBy(id = "billing_city")
    private WebElement cityInput;

    @FindBy(id = "billing_postcode")
    private WebElement postcodeInput;

    @FindBy(id = "billing_phone")
    private WebElement phoneInput;

    @FindBy(id = "billing_email")
    private WebElement emailInput;

    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    public void setFirstName(String name) {
        setElement(firstNameInput, name);
    }

    public void setLastName(String lastName) {
        setElement(lastNameInput, lastName);
    }

    public void setStreet(String name) {
        setElement(streetInput, name);
    }

    public void setCity(String city) {
        setElement(streetInput, city);
    }

    public void setPostcode(String postcode) {
        setElement(streetInput, postcode);
    }

    public void setPhone(String phone) {
        setElement(streetInput, phone);
    }

    public void setEmail(String email) {
        setElement(streetInput, email);
    }

    public void placeOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .until(drv -> {
                    drv.findElement(By.id("place_order")).click();
                    return true;
                });
    }


    public String getOrderId() {
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until((drv) -> drv.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")));

        return driver.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")).getText();
    }

    private void setElement(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }


}
