package com.students.com.students.loggingexample;

import com.student.base.TestBase;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class LoggingResponseValues extends TestBase {

    @Test // This test will print out all the response headers
    public void test001() {
        System.out.println("-----------Printing Response Headers-----------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
         //       .log().params()
                .when()
                .get("/list")
                .then()
                .log().headers()
                .statusCode(200);
    }

    @Test // This test will print the response status line
    public void test002() {
        System.out.println("-----------Printing Response Status Line-----------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/list")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test // This test will print the response body
    public void test003() {
        System.out.println("-----------Printing Response Status Body-----------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .when()
                .get("/list")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test // This test will print the response in case of an error
    public void test004() {
        System.out.println("-----------Printing Response Status Body-----------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)// error -1
                .when()
                .get("/list")
                .then()
                .log().ifError()
                .statusCode(200);
    }

}
