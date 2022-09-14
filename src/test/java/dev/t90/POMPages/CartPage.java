package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage {
    private final Helpers helpers;
    private final WebDriver driver;

    public CartPage(Helpers helpers) {
        this.helpers = helpers;
        this.driver = helpers.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "coupon_code")
    private WebElement inputCouponField;

    @FindBy(css = "[name=apply_coupon]")
    private WebElement applyCouponField;


    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[1]/td/span/bdi")
    private WebElement subTotalElement;
    private final String discountElement = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[2]/td/span";

    @FindBy(xpath = "//*[@id=\"shipping_method\"]/li/label/span/bdi")
    private WebElement deliveryElement;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[4]/td/strong/span/bdi")
    private WebElement totalElement;


    @FindBys(@FindBy(css = ".cart_item"))
    private List<WebElement> basketItems;

    public void setCoupon(String coupon) {
        inputCouponField.clear();
        inputCouponField.sendKeys(coupon);
    }

    public void applyCoupon() {
        helpers.click(applyCouponField);
    }

    public int getSubTotal() {
        return getPrice(subTotalElement);
    }

    public int getDiscount() {
        helpers.getWait().until(drv -> {
                    drv.findElement(By.xpath(discountElement));
                    return true;
                }
        );
        return getPrice(driver.findElement(By.xpath(discountElement)));

    }

    public int getDelivery() {
        return getPrice(deliveryElement);
    }

    public int getTotal() {
        return getPrice(totalElement);
    }

    public boolean goCheckout() {

        try {
            helpers.click(By.partialLinkText("Proceed to checkout"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void removeAllItems() {
        System.out.println("Removing " + basketItems.size() + " basket items.");
        basketItems.forEach(e -> {
            helpers.click(By.cssSelector(".remove"));
            waitUntilLoadingIsDone();
        });
    }

    public boolean isBasketEmpty() {
        try {
            helpers.getWait().until(drv -> drv.findElement(By.cssSelector(".cart-empty")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    private void waitUntilLoadingIsDone() {
        // there are two elements telling you that page is "loading", both of them have class blockOverlay
        // we will use this class to wait until loading is done
        helpers.getWait().until(
                // We use expected Conditions as it has nice function to confirm invisibilty of idem
                ExpectedConditions
                        // and we will provide it with element using css selector.
                        .invisibilityOfElementLocated(By.cssSelector(".blockOverlay")));
    }
    private int getPrice(WebElement element) {
        return Math.round(Integer.parseInt(element.getText().substring(1).replace(".", "")));
    }

}
