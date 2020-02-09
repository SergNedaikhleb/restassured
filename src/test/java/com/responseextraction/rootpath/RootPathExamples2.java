package com.responseextraction.rootpath;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;

public class RootPathExamples2 {
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
        RestAssured.rootPath = "query.results.rate";

        parameters.put("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\"");
        parameters.put("format", "json");
        parameters.put("env", "store://datatables.org/alltableswithkeys");
    }

    @AfterClass
    public static void tearDown(){
        RestAssured.reset();
    }

    // 2 Assert on Single Name
    @Test
    public void test002() {
        given().when()
                .get("/yql")
                .then()
                .body("query.results.rate.Name", hasItem("USD/INR"));
    }

    // 3 Assert on Multiple Name
    @Test
    public void test003() {
        given().when()
                .get("/yql")
                .then()
                .body("query.results.rate.Name", hasItems("USD/INR", "USD/THB", "USD/BRL"));
    }

    @Test
    public void test004() {
        given().when()
                .get("/yql")
                .then()
                .body("query.results.rate.Name", hasItem("USD/INR"))
                .body("id", hasItem("USDCAD"))
                .body("query.results.rate.Name", hasItems("USD/INR", "USD/THB", "USD/BRL"));
    }
}
