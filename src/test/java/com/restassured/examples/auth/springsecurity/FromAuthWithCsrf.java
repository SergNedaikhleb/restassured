package com.restassured.examples.auth.springsecurity;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FromAuthWithCsrf {

    public static SessionFilter filter;

    @BeforeClass
    public static void init() {
        filter = new SessionFilter();

        RestAssured.baseURI = "http://localhost:8085/";

        RestAssured.given().auth()
                .form("user", "user", new FormAuthConfig("/login", "uname", "pwd")
            //    .withAutoDetectionOfCsrf()
                 .withCsrfFieldName("_csrf"))
                        .filter(filter)
                .get();
        //   System.err.println(filter.getSessionId());
    }

    @Test
    public void getAllStudents() {
        RestAssured.given()
                .sessionId(filter.getSessionId())
                .get("/student/list")
                .then()
                .log().all();
    }
}
