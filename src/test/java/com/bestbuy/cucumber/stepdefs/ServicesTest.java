package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.bestbuyinfo.ServicesSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class ServicesTest {
    static String name = "Samsung Services" + TestUtils.getRandomValue();


    @Steps
    ServicesSteps servicesSteps;


    @When("^User sends a GET request to the services endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheServicesEndpointTheyMustGetBackAValidStatusCode() {
        servicesSteps.getAllServices().log().all().statusCode(200);
    }

    @When("^I create a new services by providing the information name$")
    public void iCreateANewServicesByProvidingTheInformationName() {
        servicesSteps.createServices(name).statusCode(201).extract().response().body().jsonPath();
    }


    @When("^I send GET request to services endpoint with Id \"([^\"]*)\" , I should received the services information$")
    public void iSendGETRequestToServicesEndpointWithIdIShouldReceivedTheServicesInformation(String id)  {
        servicesSteps.getServicesById(6).statusCode(200).log().all();
    }

    @When("^I update a created services providing the new name$")
    public void iUpdateACreatedServicesProvidingTheNewName() {


            name = name + "_Updated";

            servicesSteps.updateServices(6,name).statusCode(200).log().all();
    }



    @When("^I delete a created services ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedServicesTheyMustGetBackAValidStatusCodeIs(int id) {
        servicesSteps.deleteServices(21).statusCode(200).log().all();
    }

    @Then("^I verify the services is created is deleted$")
    public void iVerifyTheServicesIsCreatedIsDeleted() {
        servicesSteps.getServicesById(21).statusCode(404).log().all();
    }
}
