package com.paypalexamples.tests;

import com.jayway.restassured.http.ContentType;
import com.student.paypalexamples.base.BaseClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class PostAsString extends BaseClass {

    @Test
    public void createAPayment() {
        String body = "{\"bla\": \"something\"}";
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .when()
                .body("").post("/payment/payment")
                .then().log().all();
    }
}
