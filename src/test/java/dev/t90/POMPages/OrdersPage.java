package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {

    public OrdersPage(Helpers helpers) {
        WebDriver driver = helpers.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr:nth-child(1) > td.woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a")
    private WebElement orderNoField;

    public String getOrderId() {
        return orderNoField.getText().substring(1);
    }

}
