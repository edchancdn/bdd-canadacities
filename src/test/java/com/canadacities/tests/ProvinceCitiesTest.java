package com.canadacities.tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Optional;

public class ProvinceCitiesTest {

    Response response;
    List<String> cities;

    @Given("User have access to url {string}.")
    public void user_have_access_to_url(String url) {
        //System.out.println("arg0 = " + url);
        RestAssured.baseURI = url;
        RestAssured.port = 8080;
    }

    @When("user call end point {string} with province code {string}.")
    public void user_call_end_point_with_province_code(String path, String provinceId) {
        //System.out.println("path = " + path);
        //System.out.println("provinceCode = " + provinceId);

        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.get(path, provinceId);

        JsonPath jsonPathEvaluator = response.jsonPath();
        cities = jsonPathEvaluator.getList("name");

        //for (int i = 0; i < cities.size(); i++) {
        //    System.out.println("City: " + cities.get(i));
        //}
    }

    @Then("status code should be {int}.")
    public void status_code_should_be(Integer status) {
        int sc = response.statusCode();
        Assert.assertEquals(Optional.of(status), Optional.of(sc));
        //System.out.println("Status = " + Optional.of(sc));
    }

    @And("it should have content type {string}.")
    public void it_should_have_content_type(String arg0) {
        String header = response.header("Content-Type");
        Assert.assertEquals(arg0, header);
        //System.out.println("Content-Type = " + header);
    }

    @Then("it should have a city count of {int}.")
    public void itShouldHaveACityCountOfCity_count(Integer cityCount) {
        Assert.assertEquals(Optional.ofNullable(cityCount), Optional.ofNullable(cities.size()));
        //System.out.println("City count = " + Optional.ofNullable(cities.size()));
        //System.out.println("");
    }
}
