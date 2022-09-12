package dev.t90.POMPages;

import dev.t90.utils.SharedDictionary;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private final SharedDictionary dict;
    private WebDriver driver;

    public CartPage(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "coupon_code")
    private WebElement inputCouponField;

    @FindBy(partialLinkText = "Proceed to checkout")
    private WebElement checkoutLink;

    @FindBy(css = "[name=apply_coupon]")
    private WebElement applyCouponField;



    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[1]/td/span/bdi")
    private WebElement subTotalElement;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[2]/td/span")
    private WebElement discountElement;

    @FindBy(xpath = "//*[@id=\"shipping_method\"]/li/label/span/bdi")
    private WebElement deliveryElement;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[4]/td/strong/span/bdi")
    private WebElement totalElement;

    public void setCoupon(String coupon) {
        inputCouponField.clear();
        inputCouponField.sendKeys(coupon);
    }

    public void applyCoupon() {
        applyCouponField.click();
    }

    public int getSubTotal() {
        return getPrice(subTotalElement);
    }
    public int getDiscount() {
        return getPrice(discountElement);
    }
    public int getDelivery() {
        return getPrice(deliveryElement);
    }
    public int getTotal() {
        return getPrice(totalElement);
    }

    public boolean goCheckout() {
        try {
            checkoutLink.click();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private int getPrice(WebElement element) {
        return Math.round(Integer.parseInt(element.getText().substring(1).replace(".", "")));
    }

}