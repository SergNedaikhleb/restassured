package com.response.extraction.examples.jsonpathexamples;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.path.xml.NodeChildrenImpl;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.path.xml.XmlPath.*;



public class CurrencyExchangeXMLPathExamples {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";
    }

    // 1) Extract count value from response
    @Test
    public void test001(){
       String count =  given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then().extract().path("query.@yahoo:count");

        System.out.println("The count is: "+count);
    }

    // 2) Extract lang value from the response
    @Test
    public void test002(){
        String lang =  given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then().extract().path("query.@yahoo:lang");

        System.out.println("The lang is: "+lang);

    }

    // 3) Extract "Name": "USD/USD" from the first rate array
    @Test
    public void test003(){
        String name =  given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then().extract().path("query.results.rate[0].Name");

        System.out.println("The lang is: "+name);

    }

    // 4) Get the values under rate as String
    @Test
    public void test004(){
        String xml =   given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();

        String valuesUnderRate = with(xml).get("query.results.rate").toString();

        System.out.println("The values under rate are: "+ valuesUnderRate);

    }

   //  5) Get the values under rate as String
    @Test
    public void test005(){
       NodeChildrenImpl sizeOfNode =   given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .then().extract()
                .path("query.results.rate");
        System.out.println("The size of rate is: "+sizeOfNode.size());
    }


    //  6) Get all the Names from the Response as String
    @Test
    public void test006(){
        String xml =   given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String names = with(xml).get("query.results.rate.findAll{it.Name}.Name").toString();

        System.out.println("The name are: "+names);
    }

    //  7) Get all the info for name USD/AUD
    @Test
    public void test007(){
        String xml =   given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String values = with(xml).get("query.results.rate.findAll{it.Name=='USD/AUD'}.Name").toString();

        System.out.println("The values are: "+values);
    }

    // 8) Extracting using attribute id="INR=X" and getting the Rate..using **
    @Test
    public void test008(){
        String xml =   given()
                .param("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\",")
                .param("format", "xml")
                .param("env", "store://datatables.org/alltableswithkeys")
                .param("diagnostics", "true")
                .when()
                .get("/yql")
                .andReturn()
                .asString();
        String values = with(xml).get("**.findAll{it.@id=='USDINR'}.Rate").toString();
        System.out.println("The rate is: "+values);
    }

}
