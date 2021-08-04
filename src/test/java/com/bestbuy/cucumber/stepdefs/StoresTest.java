package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.bestbuyinfo.StoresSteps;
import com.bestbuy.testbase.TestBase;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StoresTest {

        static String name = "ipad";
        static String type = "tablet";
        static String address = "12";
        static String address2 = "wembley";
        static String city = "London";
        static String state = "abcd";
        static String zip = "HA0 2PR";
        static int lat = 0;
        static int lng = 0;
        static String hours = "string";

        @Steps
        StoresSteps storesSteps;

        @When("^User sends a GET request to the stores endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheStoresEndpointTheyMustGetBackAValidStatusCode() {
            storesSteps.getAllStores().log().all().statusCode(200);
    }

    @When("^I create a new stores by providing the information name type  address addressTwo  city state zip lat \"([^\"]*)\" lng \"([^\"]*)\" hours \"([^\"]*)\"$")
    public void iCreateANewStoresByProvidingTheInformationNameTypeAddressAddressTwoCityStateZipLatLngHours(String arg0, String arg1, String arg2) {
        storesSteps.createNewStore(name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(201);

    }

    @When("^I send GET request to stores endpoint with Id \"([^\"]*)\" , I should received the store information$")
    public void iSendGETRequestToStoresEndpointWithIdIShouldReceivedTheStoreInformation(String arg0) {
        storesSteps.getStoresById(18).statusCode(200).log().all();
    }

    @When("^I update a created store providing the new name type and hours$")
    public void iUpdateACreatedStoreProvidingTheNewNameTypeAndHours() {
        storesSteps.updateStores(6, name, type).statusCode(200).log().all();
    }

    @When("^I delete a created store ,they must get back a valid status code is 200$")
    public void iDeleteACreatedStoreTheyMustGetBackAValidStatusCodeIs(int id) {
        storesSteps.deleteStores(19).statusCode(200).log().all();
    }

    @Then("^I verify the store is deleted$")
    public void iVerifyTheStoreIsDeleted() {
        storesSteps.getStoresById(19).statusCode(404).log().all();
    }
}
