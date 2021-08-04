package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Endpoints;
import com.bestbuy.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CategoriesSteps {
    @Step("Get the all Categories ")
    public ValidatableResponse getAllCategories() {
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.GET_ALL_CATEGORIES_RECORD)
                .then();
    }

    @Step("Creating new categories with setName: {0},setId: {1}"
    )
    public ValidatableResponse createCategories(String name,String id) {


        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post(Endpoints.CREATE_CATEGORIES_RECORD)
                .then();
    }

    @Step("Delete categories")
    public ValidatableResponse deleteCategories(String id) {

        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .log().all()
                .when()
                .delete(Endpoints.DELETE_CATEGORIES_BY_ID)
                .then();

    }

    @Step
    public ValidatableResponse getCategoriesById(String id){
        CategoriesPojo categoriesPojo = new CategoriesPojo();

        return SerenityRest.rest()

                .given()
                .pathParam("id",id)
                .log().all()
                .when()
                .get(Endpoints.GET_CATEGORIES_BY_ID)
                .then();

    }



    @Step("Updating services with setName: {0}"
    )
    public ValidatableResponse updateCategories(String name,String id) {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);


        return SerenityRest.rest()
                .given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(categoriesPojo)
                .patch(Endpoints.GET_UPDATE_CATEGORIES_BY_ID)
                .then();
    }


}
