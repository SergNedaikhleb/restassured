package com.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;
import org.testng.annotations.*;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;

public class StudentsPut extends TestBase {

    @Test
    public void updateStudent() {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");
        courses.add("Data Learning + Machine Learning");

        Student student = new Student();
        student.setFirstName("TejUpdatedTej");
        student.setLastName("HegdeUpdated");
        student.setEmail("xyz@blablamail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .put("/101")
                .then()
                .statusCode(200);
    }
}
