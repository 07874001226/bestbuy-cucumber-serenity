package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.Endpoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {

    @Step("Get the all Products ")
    public ValidatableResponse getAllProducts() {
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(Endpoints.GET_ALL_PRODUCTS_RECORD)
                .then();
    }

    @Step("Creating new products with setName: {0}, setType: {1}, setPrice: {2}, setUpc: {3},setShipping: {4},setDescription: {5},setManufacturer: {6},setModel: {7},setUrl: {8},setImage: {9}"
    )
    public ValidatableResponse createProducts(String name, String type, double price, double shipping, String upc, String description, String manufacturer, String model, String url, String image) {


        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(Endpoints.CREATE_NEW_PRODUCTS_RECORD)
                .then();

    }


    @Step("Delete product")
    public ValidatableResponse deleteProducts(int id) {

        return SerenityRest.rest()
                .given()
                .pathParam("id", 185230)
                .log().all()
                .when()
                .delete(Endpoints.DELETE_PRODUCTS_BY_ID)
                .then();

    }

    @Step
    public ValidatableResponse getProductById(int id) {
        return SerenityRest.rest()

                .given()
                .pathParam("id", id)
                .log().all()
                .when()
                .get(Endpoints.GET_PRODUCTS_BY_ID)
                .then();

    }


    @Step("Updating products with :setName: {0}, setType: {1}")
    public ValidatableResponse updateProducts(int id,String name, String type){
        ProductPojo productPojo = new ProductPojo();

        productPojo.setName(name);
        productPojo.setType(type);


        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(productPojo)
                .patch(Endpoints.GET_UPDATE_PRODUCTS_BY_ID)
                .then();
    }

}

