package com.tests;

import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import org.testng.annotations.*;
import com.student.model.Student;

import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.*;

public class StudentPostTest extends TestBase {

    @Test
    public void createNewStudent() {
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");
        courses.add("Data Learning");

        Student student = new Student();
        student.setFirstName("Tej");
        student.setLastName("Hegde");
        student.setEmail("xyz@blablamail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .post()
                .then()
                .statusCode(201);
    }
}
