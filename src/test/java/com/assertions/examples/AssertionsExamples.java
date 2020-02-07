package com.assertions.examples;

import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;

import static com.jayway.restassured.RestAssured.given;

public class AssertionsExamples {
    static final String APIKEY = "75e3u4sgb2khg673cbv2gjup";

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
    }

    // 1) Verify if the number of items is equal to 10
    @Test
    public void test001() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()//.log()
                .body("numItems", equalTo(10));
    }

    // 2) Verify query
    @Test
    public void test002() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()//.log()
                .body("query", equalTo("ipod"));
    }

    // 3) Check Single Name in ArrayList
    @Test
    public void test003() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItem("Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A"));
    }

    // 4) Check Multiple Name in ArrayList
    @Test
    public void test004() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItems("Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A",
                        "Refurbished Apple iPod Touch 16GB MGG82LLA - Space Gray (5th generation)"));
    }

    // 5) Verify the gift options for the first product (Checking Values inside Map using hasValue())
    @Test
    public void test005() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items[0].giftOptions", hasKey("allowGiftWrap"));
    }

    // 6) Get hashmap values inside a list
    @Test
    public void test006() {

     //   List<HashMap<String, Object>> values =
        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.findAll{it.name=='Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A'}",
                        hasItem
                                (hasEntry("name", "Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A")));
    }

    // 7) Checking multiple values in the same statement
    @Test
    public void test007() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.name", hasItem("Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A"))
                .body("items.findAll{it.name=='Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A'}",
                hasItem
                        (hasEntry("name", "Refurbished Apple iPod Touch 5th gen 16GB WiFi MP3 MP4 Digital Music Video Player MGG82LL/A")))
                .statusCode(200);
    }

    // 8) Logical Assertions
    @Test
    public void test008() {

        given()
                .queryParam("query", "ipod")
                .queryParam("apiKey", APIKEY)
                .queryParam("format", "json")
                .when()
                .get("/search")
                .then()
                .body("items.size()", equalTo(10))
                .body("items.size()", greaterThan(5))
                .body("items.size()", lessThan(11))
                .body("items.size()", greaterThanOrEqualTo(10))
                .body("items.size()", lessThanOrEqualTo(10));
    }
}
