package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.bestbuyinfo.CategoriesSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CategoriesTest {
    static String name = "Speaker" + TestUtils.getRandomValue();
    static String id = "abcd1234" + TestUtils.getRandomValue();
    static String categoryId;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^User sends a GET request to the categories endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        categoriesSteps.getAllCategories().log().all().statusCode(200);
    }

    @When("^I create a new categories by providing the information name & id$")
    public void iCreateANewCategoriesByProvidingTheInformationNameId() {
        categoriesSteps.createCategories(name, id).statusCode(201).extract().response().body().jsonPath();
    }

    @When("^I send GET request to categories endpoint with Id \"([^\"]*)\" , I should received the categories information$")
    public void iSendGETRequestToCategoriesEndpointWithIdIShouldReceivedTheCategoriesInformation(String id)  {
        categoriesSteps.getCategoriesById("abcat0100000").statusCode(200).log().all();
    }

    @When("^I update a created categories providing the new name$")
    public void iUpdateACreatedCategoriesProvidingTheNewName() {
        name = name + "_Updated";
        id = id + "_Updated";


        categoriesSteps.updateCategories(name, "abcat0020001").statusCode(200).log().all();

    }

    @When("^I delete a created categories ,they must get back a valid status code is 200$")
    public void iDeleteACreatedCategoriesTheyMustGetBackAValidStatusCodeIs(int id) {
        categoriesSteps.deleteCategories("abcat0020004").statusCode(200).log().all();
    }

    @Then("^I verify the categories is deleted$")
    public void iVerifyTheCategoriesIsDeleted() {
        categoriesSteps.getCategoriesById("abcat0020004").statusCode(404).log().all();
    }
}
