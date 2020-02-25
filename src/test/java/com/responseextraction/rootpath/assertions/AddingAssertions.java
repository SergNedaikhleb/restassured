package com.responseextraction.rootpath.assertions;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.*;
import java.util.HashMap;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class AddingAssertions {
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";

        parameters.put("q",
                "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\"");
        parameters.put("format", "json");
        parameters.put("env", "store://datatables.org/alltableswithkeys");
    }

    // 1) Assert on count value
    @Test
    public void test001() {
       given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .body("query.count", equalTo(6));
    }

    // 2) Assert on Single Name
    @Test
    public void test002() {
        given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .body("query.results.rate[1].Name", equalTo("USD/INR"));
    }

    // 3) Assert on Single Name
    @Test
    public void test003() {
        given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .body("query.count", hasItems("USD/INR", "USD/THB", "USD/BRL"));

        given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .body("query.count", lessThan(4));
    }

    // 5) Adding multiple assertions in single test
    @Test
    public void test005() {
        given()
                .parameters(parameters)
                .when()
                .get("/yql")
                .then()
                .body("query.results.rate.Name", hasItem("USD/INR"))
                .body("query.count", lessThan(10))
                .body("query.count", greaterThan(4))
                .body("query.results.rate.Name", hasItems("USD/INR\", \"USD/THB\", \"USD/BRL"));
    }

    @Test
    public void test006() {
     int count =   given()
                .param("q",
                "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\"")
                .param("format", "json")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true").when()
                .when().get("/yql")
                .then().extract()
                .path("query.count");
             assertEquals(6, count);
    }

}
