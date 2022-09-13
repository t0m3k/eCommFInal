package dev.t90.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyClick {

    private final WebDriver driver;

    public MyClick(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element, Integer scroll) {
        scroll(element, scroll);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(drv -> {
            try {
                element.click();
            } catch (ElementClickInterceptedException err) {
                System.out.println("Element intercepted, scrolling...");
                scroll(75);
                return false;
            } catch (NoSuchElementException e) {
                System.out.println("Trying to click on hamburger menu.");
                driver.findElement(By.id("site-navigation-menu-toggle")).click();
                return false;
            }
            return true;
        });
    }

    public void scroll(Integer scroll) {
        // Create new actions element
        Actions action = new Actions(driver);
        try {
            //  scroll down given value
            action.scrollByAmount(0, scroll)
                    .build().perform();
        } catch (Throwable e) {
            System.out.println("Scrolling error: " + e);
        }
    }

    public void scroll(WebElement element, Integer scroll) {
        // Create new actions element
        Actions action = new Actions(driver);
        try {
            // Use action element to scroll down to object
            action.moveToElement(element)
                    // and finally execute the action
                    .build().perform();
        } catch (Throwable e) {
            System.out.println("Couldn't scroll element: " + element.toString());
            System.out.println("Error type: " + e);
        }
        scroll(scroll);
    }
}
