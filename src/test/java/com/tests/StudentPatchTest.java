package com.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.student.model.Student;
import org.testng.annotations.*;

import static com.jayway.restassured.RestAssured.given;

public class StudentPatchTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/student";
    }

    @Test
    public void patchStudent() {

        Student student = new Student();
        student.setEmail("xyzPatched123@blablamail.com");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .patch("/101")
                .then()
                .statusCode(200);
    }
}
