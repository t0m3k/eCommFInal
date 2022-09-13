package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private final Helpers helpers;
    private final WebDriver driver;

    public CheckoutPage(Helpers helpers) {
        this.helpers = helpers;
        this.driver = helpers.getDriver();
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
        setElement(cityInput, city);
    }

    public void setPostcode(String postcode) {
        setElement(postcodeInput, postcode);
    }

    public void setPhone(String phone) {
        setElement(phoneInput, phone);
    }

    public void setEmail(String email) {
        setElement(emailInput, email);
    }

    public void placeOrder() {
        helpers.click(By.id("place_order"));
    }

    public void placeOrder(String firstName, String lastName, String street, String city, String postcode, String phone, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setStreet(street);
        setCity(city);
        setPostcode(postcode);
        setPhone(phone);
        setEmail(email);
        placeOrder();
    }

    public String getOrderId() {
        var myWait = helpers.getWait();
        myWait.until((drv) -> drv.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")));

        return driver.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")).getText();
    }

    private void setElement(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }
}
