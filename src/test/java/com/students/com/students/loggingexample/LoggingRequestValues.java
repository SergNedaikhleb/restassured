package com.students.com.students.loggingexample;

import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static com.jayway.restassured.RestAssured.*;

public class LoggingRequestValues extends TestBase {


    @Test // This test will print out all the request headers
    public void test001() {
        System.out.println("-----------Printing Request Headers-----------");
        given()
                .log().headers()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    @Test // This test will print out all the request parameters
    public void test002() {
        System.out.println("-----------Printing Request Parameters-----------");
        given()
                .param("programme", "Computer Science")
                .param("limit", 1)
                .log().params()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @Test // This test will print out all the request body
    public void test003() {
        ArrayList<String> courses = new ArrayList<String>();
        System.out.println("-----------Printing Request Body-----------");
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
                .log().body()
                .when()
                .body(student)
                .post();
    }

    @Test // This test will print out all the details
    public void test004() {
        ArrayList<String> courses = new ArrayList<String>();
        System.out.println("-----------Printing All the Request Details-----------");
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
                .log().all()
                .when()
                .body(student)
                .post();
    }

    @Test // This test will print Request details if validations fails
    public void test005() {
        ArrayList<String> courses = new ArrayList<String>();
        System.out.println("-----------Printing All the Request Details if validation fails-----------");
        courses.add("Java");
        courses.add("C++");
        courses.add("Data Learning");

        Student student = new Student();
        student.setFirstName("Tej");
        student.setLastName("Hegde");
        student.setEmail("xyz1@blablamail.com");
        student.setProgramme("Computer Science");
        student.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .body(student)
                .post()
                .then().statusCode(201);
    }
}
