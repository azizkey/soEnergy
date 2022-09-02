package so.energy.project.x.pages.home;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps {
    private final HomePage page;

    public HomePageSteps() {
        this.page = new HomePage();
    }

    @Given("User hovers the {string} tab and click {string}")
    public void userHoversTheMenuAndClickDress(final String tab, final String dress) {
        page.gotoHome();
        page.hover(tab, dress);
    }

    @And("Change the view of the products to List view")
    public void changeTheViewOfTheProductsToListView() {
        page.changeToListView();
    }

    @And("Add {int} to the cart")
    public void addProductToTheCart(final int product) {
        page.addToCart(product);
    }

    @And("Proceed to checkout")
    public void proceedToCheckout() {
        page.proceedToCheckout();
    }

    @And("Increase the quantity to {int}")
    public void increaseTheQuantityTo(final int quantity) {
        page.increaseQuantityAndContinue(quantity);
    }

    @And("Log in using {string} and {string}")
    public void logInUsingUsernameAndPassword(final String username, final String password) {
        page.login(username, password);
    }

    @And("Confirm {string} and shipping")
    public void confirmAddressAndShipping(final String address) {
        page.confirmAddresses(address);
    }

    @When("User checkout")
    public void userCheckout() {
        page.checkout();
    }

    @Then("Console should display {string} and ${double}")
    public void consoleShouldDisplayNameAndPrice(final String name, final double price) {
        page.displayCorrectInfo(name, price);
    }
}
