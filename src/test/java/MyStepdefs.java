import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static dev.t90.utils.Hooks.driver;

public class MyStepdefs {
    @Given("I am logged in using {string} and {string}")
    public void iAmLoggedInUsingAnd(String arg0, String arg1) {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }
    @Given("I am on the {string} page")
    public void i_am_on_the_page(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I add item to the basket")
    public void i_add_item_to_the_basket() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I use promo code {string}")
    public void i_use_promo_code(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I get {int}% discount")
    public void i_get_discount(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
