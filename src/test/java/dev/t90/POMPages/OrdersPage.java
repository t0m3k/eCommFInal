package dev.t90.POMPages;

import dev.t90.utils.SharedDictionary;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrdersPage {
    private final SharedDictionary dict;
    private WebDriver driver;

    public OrdersPage(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "tr:nth-child(1) > td.woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a")
    private WebElement orderNoField;

    public String getOrderId() {
        return orderNoField.getText().substring(1);
    }

}
