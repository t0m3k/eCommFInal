package dev.t90.POMPages;

import dev.t90.utils.Helpers;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(Helpers helpers) {
        WebDriver driver = helpers.getDriver();
        PageFactory.initElements(driver, this);

    }
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button.woocommerce-form-login__submit")
    private WebElement loginField;

    @FindBy(linkText = "Logout")
    private  WebElement logoutLink;

    @FindBy(linkText = "Dismiss")
    private WebElement dismiss;

    public void dismissBanner() {
        dismiss.click();
    }

    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitForm() { loginField.click(); }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        submitForm();
    }

    public boolean loginExpectSuccess(String username, String password) {
        login(username, password);
        try {
            logoutLink.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
