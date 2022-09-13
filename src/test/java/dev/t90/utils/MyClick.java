package dev.t90.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MyClick {

    private final WebDriver driver;

    public MyClick(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element, Integer scroll) {
        scroll(element, scroll);
        element.click();
    }

    public void scroll(WebElement element, Integer scroll) {

        // Create new actions element
        Actions action = new Actions(driver);

        // Use action element to scroll down to object
        action.moveToElement(element)
                // then scroll down another 100 px to clear the banner
                .scrollByAmount(0, scroll)
                // and finally execute the action
                .build().perform();
    }
}
