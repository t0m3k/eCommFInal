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

    public ShopSteps(Helpers helpers) {
        this.helpers = helpers;
        page = new PageNavigation(helpers);
    }

    @Given("I am logged in using {string} and {string}")
    public void iAmLoggedInUsingAnd(String username, String password) {
        page.goHome();

        LoginPage login = new LoginPage(helpers);

        // we don't have to dissmiss the banner as we are using custom helpres.click function which scrolls down to make element visible
        //login.dismissBanner();
        boolean didWeLogin = login.loginExpectSuccess(username, password);
        MatcherAssert.assertThat("Login successful", didWeLogin);
    }

    @When("I add item to the basket")
    public void i_add_item_to_the_basket() {
        System.out.println("Going to shop.");
        page.goShop();
        ShopPage shop = new ShopPage(helpers);
        System.out.println("Adding random item to cart.");
        shop.addToCartRnd();
    }

    @When("I use promo code {string}")
    public void i_use_promo_code(String coupon) {
        System.out.println("Going to cart");
        page.goCart();
        CartPage cart = new CartPage(helpers);
        System.out.println("Typing in and applying coupon.");
        cart.setCoupon(coupon);
        cart.applyCoupon();
    }

    @Then("I get {double}% discount")
    public void i_get_discount(Double discValue) {
        CartPage cart = new CartPage(helpers);
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
        CartPage cart = new CartPage(helpers);
        MatcherAssert.assertThat("Successfully went to checkout from cart", cart.goCheckout());
    }

    @When("I checkout")
    public void i_checkout() {
        i_go_to_checkout();

        CheckoutPage checkout = new CheckoutPage(helpers);

        checkout.placeOrder("Harry","Potter","4 Privet Drive","Surrey","RG12 9FG","7777 777 777","hp@hogwart.co.uk");

        helpers.addDict("orderId", checkout.getOrderId());

    }
    @When("I go to My Orders")
    public void i_go_to_my_my_orders() {
        page.goOrders();
    }
    @Then("my order is visible")
    public void my_order_is_visible() {
        OrdersPage ordersPage = new OrdersPage(helpers);
        String order = (String)helpers.readDict("orderId");
        MatcherAssert.assertThat(ordersPage.getOrderId(), is(order));

        page.logout();

    }
}
