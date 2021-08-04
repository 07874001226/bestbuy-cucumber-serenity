package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Endpoints;
import com.bestbuy.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {
    @Step("Get the all Stores ")
    public ValidatableResponse getAllStores() {
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.GET_ALL_STORES_RECORD)
                .then();
    }

    @Step("Creating new Store with name: {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip {6}, lat {7}, lng {8} and hours {9}")

    public ValidatableResponse createNewStore(String name, String type, String address, String address2, String city, String state
            , String zip,int lat, int lng, String hours) {

        StoresPojo storePojo = new StoresPojo();

        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);

        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(storePojo)
                .when()
                .post(Endpoints.CREATE_STORES_RECORD)
                .then();

    }


    @Step("Delete store")
    public ValidatableResponse deleteStores(int id) {

        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .log().all()
                .when()
                .delete(Endpoints.DELETE_STORES_BY_ID)
                .then();

    }


    @Step
    public ValidatableResponse getStoresById(int id) {

        return SerenityRest.rest()

                .given()
                .pathParam("id", id)
                .log().all()
                .when()
                .get(Endpoints.GET_STORES_BY_ID)
                .then();

    }


    @Step("Updating stores with setName: {0}, setType: {1}, "
    )
    public ValidatableResponse updateStores(int id,String name, String type) {

        StoresPojo storesPojo = new StoresPojo();

        storesPojo.setName(name);
        storesPojo.setType(type);


        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(Endpoints.GET_UPDATE_STORES_BY_ID)
                .then();
    }


}
