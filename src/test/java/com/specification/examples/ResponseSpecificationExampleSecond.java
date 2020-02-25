package com.specification.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static com.jayway.restassured.RestAssured.given;

public class ResponseSpecificationExampleSecond {

    static RequestSpecBuilder builder;
    static RequestSpecification rspec;

    static ResponseSpecBuilder responsebuilder;
    static ResponseSpecBuilder responseSpec;

    static Map<String, Object> responseHeaders = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        builder = new RequestSpecBuilder();

        builder = new RequestSpecBuilder();
        builder.addParam("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",");
        builder.addParam("format", "json");
        builder.addParam("env", "store://datatables.org/alltableswithkeys");

        rspec = builder.build();

        // Building response
        responseHeaders.put("Content-Type", "application/json;charset=utf-8");
        responseHeaders.put("Server", "ATS");

        responsebuilder = new ResponseSpecBuilder();
        responsebuilder.expectBody("query.count", equalTo(6));
        responsebuilder.expectStatusCode(200);
        responsebuilder.expectHeaders(responseHeaders);

   //     responseSpec = responsebuilder.build();
    }

    // 1) Assert on
    @Test
    public void test001() {
        given()
                .spec(rspec)
                .log().all()
                .when()
                .get("/yql")
                .then();
       //         .spec(responseSpec);
    }
}
