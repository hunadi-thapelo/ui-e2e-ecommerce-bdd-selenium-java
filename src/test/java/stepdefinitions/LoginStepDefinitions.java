package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    @Given("the customer is on the login page")
    public void the_customer_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The customer is on the login page");
    }

    @When("the customer logs in with username and password")
    public void the_customer_logs_in_with_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("the customer logs in with username and password");
    }

    @Then("the customer should be redirected to the home page")
    public void the_customer_should_be_redirected_to_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("the customer should be redirected to the home page");
    }

}
