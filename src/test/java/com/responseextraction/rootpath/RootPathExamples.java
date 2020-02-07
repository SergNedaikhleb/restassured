package com.responseextraction.rootpath;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

public class RootPathExamples {
    static HashMap<String, Object> parameters = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://query.yahooapis.com";
        RestAssured.basePath = "/v1/public";


    }

}
