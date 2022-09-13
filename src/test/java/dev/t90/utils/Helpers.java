package dev.t90.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class Helpers {

    private final Map<String, Object> sharedMap = new HashMap<>();
    private WebDriver driver;
    private WebDriverWait wait;
    private MyClick click;

    public void addDict(String key, Object value) {
        sharedMap.put(key, value);
    }

    public Object readDict(String key) {
        return sharedMap.get(key);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        this.click = new MyClick(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public boolean containsKey(String key) {
        return sharedMap.containsKey(key);
    }

    public void click(WebElement element) {
        click.click(element);
    }

    public void click(By by) {
        click.click(by);
    }
}
