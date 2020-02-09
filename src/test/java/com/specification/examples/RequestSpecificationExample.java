package com.specification.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static com.jayway.restassured.RestAssured.given;

public class RequestSpecificationExample {
    static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";
    static RequestSpecBuilder builder;
    static RequestSpecification requestSpec;

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
    }

    @Test
    public void test001() {
        given()
        .spec(requestSpec)
                .when().get("/search")
                .then().log().all();
    }

    @Test
    public void test002() {
        given()
                .spec(requestSpec)
                .when().get("/search")
                .then().log().all();
    }
}
