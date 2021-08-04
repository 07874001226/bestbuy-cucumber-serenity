package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ProductTest {

    static String name = "oppo" + TestUtils.getRandomValue();
    static String type = "mobile";
    static double price = 10.50;
    static String upc = "219372";
    static double shipping = 10.48;
    static String description = "opp mobile";
    static String manufacturer = "opp store";
    static String model =  "String";
    static String url = "String";
    static String image = "String";

    @Steps
    ProductSteps productSteps;
    @When("^User sends a GET request to the categories endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        productSteps.getAllProducts().statusCode(200);
    }

    @When("^I create a new categories by providing the information name & id$")
    public void iCreateANewCategoriesByProvidingTheInformationNameId() {
        productSteps.createProducts(name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(201).extract().response()
                .body().jsonPath().getLong("id");

    }

    @When("^I send GET request to categories endpoint with Id \"([^\"]*)\" , I should received the categories information$")
    public void iSendGETRequestToCategoriesEndpointWithIdIShouldReceivedTheCategoriesInformation(String id)  {
        productSteps.getProductById(150115).statusCode(200).log().all();
    }

    @When("^I update a created categories providing the new name$")
    public void iUpdateACreatedCategoriesProvidingTheNewName() {


            name = name + "_Updated";
            type = type;

            productSteps.updateProducts(150115,name, type).statusCode(200).log().all();
    }

    @When("^I delete a created categories ,they must get back a valid status code is 200$")
    public void iDeleteACreatedCategoriesTheyMustGetBackAValidStatusCodeIs(int id) {
        productSteps.deleteProducts(150115).statusCode(200).log().all();

    }

    @Then("^I verify the categories is deleted$")
    public void iVerifyTheCategoriesIsDeleted() {
        productSteps.getProductById(150115).statusCode(404).log().all();

    }
}
