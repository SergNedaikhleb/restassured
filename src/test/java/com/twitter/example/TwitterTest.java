package com.twitter.example;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.given;

public class TwitterTest {

    String consumerKey = "4Ks3xN4LWqKNiHfEyUNho1IFM";
    String consumerSecret = "Dw1HS9L51jACX43k0MAgbiTpfbsjtBi2xlg0wMua6NE7wXDAST";
    String accessTokenSecret = "801910581301276675-y10eWc1TQEDtjvNwDTX5f1S3kw5RKAx";
    String secretToken = "hR7NGg30hgrU9Y66tqoYGJFF6A5vMsApeZZKeqCvg6LV2";

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Ignore
    @Test
    public void createTweet() {
        given()
                .auth().oauth(consumerKey, consumerSecret, accessTokenSecret, secretToken)
                .queryParam("status", "this is my first tweet from rest assured")
                .when()
                .post("/update.json")
                .then().log().all();
    }

    @Test
    public void getTweet() {
        given()
                .auth().oauth(consumerKey, consumerSecret, accessTokenSecret, secretToken)
                .queryParam("screen_name", "elements_rest")
                .when()
                .get("/user_timeline.json")
                .then().log().all();
    }

}
