package com.restassured.examples.jsonassert;

import com.jayway.restassured.RestAssured;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonAssertExample {

    @Test
    public void getStudents() throws IOException {
        String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+
                File.separator+"file.txt")));

        String actualValue = RestAssured.given().when().get("http://localhost:8085/student/list").asString();

        System.out.println(expectedValue);
        System.out.println(actualValue);

        Assert.assertEquals(expectedValue, actualValue);

    }

    @Test
    public void getStudentsStrict() throws IOException, JSONException {
        String expectedValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+
                File.separator+"file.txt")));

        String actualValue = RestAssured.given().when().get("http://localhost:8085/student/list").asString();

        System.out.println(expectedValue);
        System.out.println(actualValue);

        JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.LENIENT);
        JSONAssert.assertEquals(expectedValue, actualValue, JSONCompareMode.STRICT);
    }
}
