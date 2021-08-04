package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Endpoints;
import com.bestbuy.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ServicesSteps {
    @Step("Get the all Services ")
    public ValidatableResponse getAllServices() {
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.GET_ALL_SERVICES_RECORD)
                .then();
    }

    @Step("Creating new services with setName: {0}"
    )
    public ValidatableResponse createServices(String name) {


        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post(Endpoints.CREATE_SERVICES_RECORD)
                .then();
    }

    @Step("Delete services")
    public ValidatableResponse deleteServices(int id) {

        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .log().all()
                .when()
                .delete(Endpoints.DELETE_SERVICES_BY_ID)
                .then();

    }

    @Step
    public ValidatableResponse getServicesById(int id) {

        return  SerenityRest.rest()

                .given()

                .pathParam("id", id)
                .log().all()

                .when()
                .get(Endpoints.GET_SERVICES_BY_ID)
                .then();

    }


    @Step("Updating services with setName: {0}"
    )
    public ValidatableResponse updateServices(int id,String name) {

        ServicesPojo servicesPojo = new ServicesPojo();

        servicesPojo.setName(name);


        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(servicesPojo)
                .patch(Endpoints.GET_UPDATE_SERVICES_BY_ID)
                .then();
    }


}
