package com.response.extraction.examples.jsonpathexamples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.*;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class CurrencyExchangeJsonPathExamples {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }
    // 1) Extract count value from response
    @Test
    public void test001(){
       int count = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\"")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
             //.   .statusCode(200);
                .extract()
                .path("query.count");

        System.out.println("The value of count is: "+count);

    }

    // 2) Extract long value from response
    @Test
    public void test002(){

        String lang = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.lang");

        System.out.println("The value of lang is: "+lang);

    }

    // 3) Extract "Name:" "USD/USD" from the first rate array
    @Test
    public void test003(){

        String name = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate[0].Name");

        System.out.println("The value of lang is: "+name);

    }

    // 4) Get the values under rate
    @Test
    public void test004(){

        List<String> rateValue = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate");

        System.out.println("The value of lang is: "+rateValue);
        System.out.println("The size of raTE is: "+rateValue.size());
    }

    // 5) Get the size of rate
    @Test
    public void test005() {

        int sizeOfRate = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.size()");

        System.out.println("The size of rate is: " + sizeOfRate);
    }

    // 6) Get all Names from the Response
    @Test
    public void test006() {

        List<String> names = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.Rate");

        System.out.println("The size of rate is: " + names);
    }

    // 7) Get the all the Values for Name==USD/BRL
    @Test
    public void test007() {

        List<String> names = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.findAll{it.Name=='USD/BRL'}");
        System.out.println("The values are : " + names);
    }

    // 8) Get the rate Values for Name==USD/EUR
    @Test
    public void test008() {

        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.findAll{it.Name=='USD/EUR'}.Rate");
        System.out.println("The values are : " + values);
    }

    // 9) Get the Names which have Rate greater than 10
    @Test
    public void test009() {

     Response resp =  given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\"")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql");

       List<String> names = resp
               .then()
               .extract()
               .path("query.results.rate.findAll{it.Rate>10}.Name");

       System.out.println("The names which have an exchange rate greater than 30 are: " +names);
    }

    // 10) Get values that start with id = USDBR
    @Test
    public void test010() {
        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.findAll{it.id==~/USDB.*/}");

        System.out.println("The values start with USDB are: " + values);
    }

    // 11) Get values that start with id = USDBR
    @Test
    public void test011() {
        List<String> values = given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then()
                //.   .statusCode(200);
                .extract()
                .path("query.result.rate.findAll{it.id==~/.*UD/}");

        System.out.println("The values that end with id: UD are:" + values);
    }
}
