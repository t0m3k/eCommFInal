package dev.t90.tests;

import dev.t90.POMPages.*;
import dev.t90.utils.Helpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.CoreMatchers.is;


public class ShopSteps {
    private final Helpers helpers;
    private final PageNavigation page;
    private final LoginPage login;
    private final CartPage cart;
    private final ShopPage shop;
    private final OrdersPage ordersPage;

    public ShopSteps(Helpers helpers) {
        this.helpers = helpers;
        page = new PageNavigation(helpers);
        cart = new CartPage(helpers);
        login = new LoginPage(helpers);
        shop = new ShopPage(helpers);
        ordersPage = new OrdersPage(helpers);
    }

    @Given("I am logged in using {string} and {string}")
    public void iAmLoggedInUsingAnd(String username, String password) {
        page.goHome();

        // we don't have to dismiss the banner as we are using custom helpers.click function which scrolls down to make element visible
        //login.dismissBanner();
        boolean didWeLogin = login.loginExpectSuccess(username, password);
        MatcherAssert.assertThat("Login successful", didWeLogin);
    }

    @When("I add {int} item\\(s) to the basket")
    public void i_add_item_s_to_the_basket(Integer howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println("Going to shop.");
            page.goShop();
            System.out.println("Adding random item to cart.");
            shop.addToCartRnd();
        }
    }

    @When("I use promo code {string}")
    public void i_use_promo_code(String coupon) {
        System.out.println("Going to cart");
        page.goCart();
        System.out.println("Typing in and applying coupon.");
        cart.setCoupon(coupon);
        cart.applyCoupon();
    }

    @Then("I get {double}% discount")
    public void i_get_discount(Double discValue) {
        System.out.println("Getting price values.");
        var discount = cart.getDiscount();
        var subTotal = cart.getSubTotal();
        var delivery = cart.getDelivery();
        var total= cart.getTotal();

        System.out.println("Asserting price values.");
        MatcherAssert.assertThat("Discount equals 15% of subtotal", (int)Math.round(subTotal * discValue)/100, is(discount));
        MatcherAssert.assertThat("Total equals subtotal - discount + delivery", total, is(subTotal - discount + delivery));

        page.logout();

    }

    @When("I go to checkout")
    public void i_go_to_checkout() {
        System.out.println("Going to cart");
        page.goCart();
        MatcherAssert.assertThat("Successfully went to checkout from cart", cart.goCheckout());
    }

    @When("I checkout")
    public void i_checkout() {
        i_go_to_checkout();

        CheckoutPage checkout = new CheckoutPage(helpers);

        checkout.placeOrder("Harry","Potter","4 Privet Drive","Surrey","RG12 9FG","7777 777 777","hp@hogwart.co.uk");

        helpers.addDict("orderId", checkout.getOrderId());

    }
    @Then("my order is visible")
    public void my_order_is_visible() {
        page.goOrders();
        String order = (String)helpers.readDict("orderId");
        MatcherAssert.assertThat(ordersPage.getOrderId(), is(order));

        page.logout();

    }

    @When("I remove all items from basket")
    public void i_remove_all_items_from_basket() {
        page.goCart();
        System.out.println("Removing cart items");
        cart.removeAllItems();
    }
    @Then("there is nothing in basket")
    public void there_is_nothing_in_basket() {
        page.goCart();
        MatcherAssert.assertThat("Basket is empty", cart.isBasketEmpty());
    }
}
