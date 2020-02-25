package com.specification.examples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RequestSpecificationExampleSecond {

    static RequestSpecBuilder builder;
    static RequestSpecification rspec;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        builder = new RequestSpecBuilder();
        builder.addParam("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",");
        builder.addParam("format", "json");
        builder.addParam("env", "store://datatables.org/alltableswithkeys");

        rspec = builder.build();

    }

    // 1) Assert on count value

    @Test
    public void test001() {
         given()
                 .spec(rspec)
                //.param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\"")
               // .param("format", "json")
               // .param("env", "store://datatables.org/alltableswithkeys")
               // .param("diagnostics", "true")
                 .log().all()
                .when()
                .get("/yql")
                .then().log().body()
               // .extract()
               // .path("query.count");
                .statusCode(200);
    }
}
