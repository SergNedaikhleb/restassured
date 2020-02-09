package com.specification.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import static com.jayway.restassured.RestAssured.given;

public class ResponseSpecificationExample {
    static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";
    static RequestSpecBuilder builder;
    static RequestSpecification requestSpec;

    static ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";

        builder = new RequestSpecBuilder();
        builder.addQueryParam("query", "ipod");
        builder.addQueryParam("apiKey", APIKEY);
        builder.addQueryParam("format", "json");
        builder.addQueryParam("facet", "on");
        builder.addHeader("Accept", "*/*");

        requestSpec = builder.build();
        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
        responseBuilder.expectHeader("Server", "Mashery Proxy");
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectBody("query", equalTo("ipod"));
        responseBuilder.expectBody("numItems", equalTo(10));
        responseBuilder.expectBody("items.name",
                hasItem("Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A"));

        responseSpec = responseBuilder.build();
    }

    @Test
    public void test001() {
        given()
                .spec(requestSpec)
                .when().get("/search")
                .then().spec(responseSpec);
    }
    @Test
    public void test002() {
        given()
                .spec(requestSpec)
                .when().get("/search")
                .then().spec(responseSpec)
                .body("items.name",
                        hasItem("Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A"));
    }
}
