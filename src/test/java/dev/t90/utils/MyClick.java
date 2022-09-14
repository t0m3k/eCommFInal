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
                scroll(50);
                return false;
            } catch (NoSuchElementException e) {
                System.out.println("Trying to click on hamburger menu.");
                click(By.id("site-navigation-menu-toggle"));
                return false;
            }
            return true;
        });
    }

    public void scroll(Integer scroll) {
        Actions action = new Actions(driver);
        try {
            //  scroll down given value
            action.scrollByAmount(0, scroll)
                    .build().perform();
        } catch (InvalidArgumentException e) {
            System.out.println("Scrolling error, trying JS.");
            // Above will not work on firefox, while not preferred, use js on firefox to scroll
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("scrollBy(0, " + scroll + ");");
        }
    }
}

