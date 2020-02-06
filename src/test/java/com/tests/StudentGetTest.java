package com.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.*;

import static com.jayway.restassured.RestAssured.*;

public class StudentGetTest {

    @BeforeClass
    public static void init() {

        RestAssured.baseURI="http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/student";
    }

    @Test
    public void getAllStudentInformation() {
        /* given()
        set cookies, add auth, adding parameters, setting header info
            .when()
         GET, POST, PUT, DELETE...etc
            .then()
              Validate status code, extract response, extract headers, cookies, extract the response body
        */

       Response resp =  given()
                .when()
                .get("/list");
       // Validate the status code

   //    System.out.println(resp.body().prettyPrint());

       given()
                .when()
                .get("/list")
                // Validate the status code
                .then().statusCode(200);
    }

    @Test
    public void getStudentInfo() {

       Response respOneStudent =  given()
                .when()
                .get("/1");

      //  System.out.println(respOneStudent.body().prettyPrint());

                given()
                .when()
                .get("/1").then().statusCode(200);
    }

    @Test
    public void getStudentFromFA() {
        Response respFinAnalisys = given().when()
                .get("/list?programme=Financial Analysis&limit=2");

        System.out.println(respFinAnalisys.body().prettyPeek()); // print body, but with header

        Response respFinAnalisysTwo =
        given().param("programme", "Financial Analysis")
                .param("limit", 2)
                .when()
                .get("/list");

        System.out.println(respFinAnalisysTwo.body().prettyPeek());
    }
}
