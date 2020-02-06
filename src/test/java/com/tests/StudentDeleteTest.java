package com.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.given;

public class StudentDeleteTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/student";
    }

    @Test
    public void deleteStudent() {
       given()
               .when()
               .delete("/101")
               .then()
               .statusCode(204);
    }
}
