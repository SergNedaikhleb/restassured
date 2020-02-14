package com.restassured.example.proxy;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.ProxySpecification;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

public class ProxyExample {

    public static RequestSpecBuilder rspec;
    public static RequestSpecification rp;

    @BeforeClass
    public static void Init() {
        RestAssured.baseURI="http://localhost:8085/student";
        RestAssured.proxy("localhost", 5555);

        ProxySpecification ps = new ProxySpecification("localhost", 5555, "http");
        rspec = new RequestSpecBuilder();
        rspec.setProxy(ps);

        rp = rspec.build();
    }

    @Test
    public void sendRequest(){

        RestAssured.given()
                .spec(rp)
                .when().get("/list")
                .then()
                .log().body();
    }

    @Test
    public void sendRequest2(){
          ProxySpecification ps = new ProxySpecification("localhost", 5556, "http");

        RestAssured.given()
                .proxy(ps)
                .when().get("/list")
                .then()
                .log().body();
    }
}
