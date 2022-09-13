package dev.t90.tests;

import dev.t90.POMPages.*;
import dev.t90.utils.SharedDictionary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.CoreMatchers.is;


public class ShopSteps {
    private final SharedDictionary dict;
    private final PageNavigation page;

    public ShopSteps(SharedDictionary dict) {
        this.dict = dict;
        page = new PageNavigation(dict);
    }

    @Given("I am logged in using {string} and {string}")
    public void iAmLoggedInUsingAnd(String username, String password) {
        page.goHome();

        LoginPage login = new LoginPage(dict);
        login.dismissBanner();
        boolean didWeLogin = login.loginExpectSuccess(username, password);
        MatcherAssert.assertThat("Login successful", didWeLogin);
    }

    @When("I add item to the basket")
    public void i_add_item_to_the_basket() {
        page.goShop();
        ShopPage shop = new ShopPage(dict);
        shop.addToCartRnd();
    }

    @When("I use promo code {string}")
    public void i_use_promo_code(String coupon) {
        CartPage cart = (CartPage) dict.readDict("cart");

        cart.setCoupon(coupon);
        cart.applyCoupon();
    }

    @Then("I get {double}% discount")
    public void i_get_discount(Double discValue) {
        CartPage cart = (CartPage) dict.readDict("cart");
        var discount = cart.getDiscount();
        var subTotal = cart.getSubTotal();
        var delivery = cart.getDelivery();
        var total= cart.getTotal();

        MatcherAssert.assertThat("Discount equals 15% of subtotal", (int)Math.round(subTotal * discValue)/100, is(discount));
        MatcherAssert.assertThat("Total equals subtotal - discount + delivery", total, is(subTotal - discount + delivery));

        page.logout();

    }

    @When("I go to checkout")
    public void i_go_to_checkout() {
        CartPage cart = (CartPage) dict.readDict("cart");
        MatcherAssert.assertThat("Successfully went to checkout from cart", cart.goCheckout());
    }

    @When("I checkout")
    public void i_checkout() {
        CheckoutPage checkout = new CheckoutPage(dict);

        checkout.placeOrder("Harry","Potter","4 Privet Drive","Surrey","RG12 9FG","7777 777 777","hp@hogwart.co.uk");

        dict.addDict("orderId", checkout.getOrderId());

    }
    @When("I go to My Orders")
    public void i_go_to_my_my_orders() {
        page.goOrders();
    }
    @Then("my order is visible")
    public void my_order_is_visible() {
        OrdersPage ordersPage = new OrdersPage(dict);
        String order = (String)dict.readDict("orderId");
        MatcherAssert.assertThat(ordersPage.getOrderId(), is(order));

        page.logout();

    }

    @And("I go to cart")
    public void iGoToCart() {
        page.goCart();
        CartPage cart = new CartPage(dict);
        dict.addDict("cart", cart);

    }
}
