package com.student.paypalexamples.base;

  // static imports
import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.*;

public class BaseClass {
    public static String accessToken;
    public static final String clientId = "Ab8UjU9DoJviAZhj-XQzuMHJ4u2uxne3TdeyzLsq4Hcggw4xiTNQYHoSau-xauet0bhmp0JVYyANBgl-";
    public static final String clientSecret="E03CwZYP-f5tvrX6NN0smKVoe5R9_Wy6mEbC1rUpbeAlJSU6GbFExCei59_Nj3GTNHq-brk1PDS5r3Ix";

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://api.sandbox.paypal.com";
        RestAssured.basePath = "/v1";

        accessToken = given()
                .parameters("grant_type", "client_credentials")
                .auth()
                .preemptive().basic(clientId, clientSecret)
                .when()
   //             .post("https://api.sandbox.paypal.com/v1/oauth2/token")
                .post("/oauth2/token")
                .then()
                .log().all()
        .extract().path("access_token");

        System.out.println("The token is: "+accessToken);
    }

    @Test
    public void something() {
    }
}
