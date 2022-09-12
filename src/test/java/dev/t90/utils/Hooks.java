package dev.t90.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    private WebDriverManager wdm;

    @Before
    public void setUp() {

        String browser = System.getProperty("browser");
        System.out.println("Browser set to: " + browser);
        if (browser == null) {
            browser = "";
        }
        switch (browser) {
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "safari":
                wdm = WebDriverManager.safaridriver().browserInDocker();
                driver = wdm.create();
                break;
            default:
                System.out.println("Browser not set at command line so using ChromeDriver");
                driver = WebDriverManager.chromedriver().create();
                break;
        }

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
