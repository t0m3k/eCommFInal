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

    public void click(By by) {
        try {
            click(driver.findElement(by));
        } catch (StaleElementReferenceException e) {
            System.out.println("Stale element");
            WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(6));
            myWait.ignoring(StaleElementReferenceException.class)
                    .until(drv -> {
                        click(by);
                        return true;
                    });
        }
    }

    public void click(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(50)).until(drv -> {
            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element intercepted, scrolling...");
                scroll(100);
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
}

