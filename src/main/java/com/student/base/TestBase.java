package com.student.base;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8085;
        RestAssured.basePath="/student";
    }
}
