package dev.t90.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Hooks {
    private WebDriver driver;
    private final Helpers helpers;

    public Hooks(Helpers helpers) {
        this.helpers = helpers;
    }

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
                WebDriverManager wdm = WebDriverManager.safaridriver().browserInDocker();
                driver = wdm.create();
                break;
            default:
                System.out.println("Browser not set at command line so using ChromeDriver");
                driver = WebDriverManager.chromedriver().create();
                break;
        }

        helpers.setDriver(driver);
        helpers.setWait(new WebDriverWait(driver, Duration.ofSeconds(6)));

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1500);

        driver.quit();
    }
}
