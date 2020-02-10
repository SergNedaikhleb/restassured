package com.checkresponsetime.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.*;
import com.jayway.restassured.specification.*;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class VerifyingTime {

    static RequestSpecBuilder builder;
    static RequestSpecification rspec;
    static ResponseSpecBuilder responseBuilder;
    static ResponseSpecification responseSpec;

    static Map<String, Object> responseHeaders = new HashMap<String, Object>();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://query.yahooapis.com/streaming";
        RestAssured.basePath = "/v1/public";

        builder = new RequestSpecBuilder();

        builder.addParam("q", "select * from yahoo.finance.xchange where pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\"");
        builder.addParam("format", "json");
        builder.addParam("env", "store://datatables.org/alltableswithkeys");

        rspec = builder.build();

        // Building response
        responseHeaders.put("Content-Type", "application/json:charset=utf=8");
        responseHeaders.put("Server", "ATS");

        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectBody("query.count", equalTo(6));
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectHeaders(responseHeaders);
        responseBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);


        responseSpec = responseBuilder.build();

        }

        // 1) GET the time value
            @Test
            public void test001() {
                long responseTime = given()
                        .spec(rspec)
                        .log().all()
                        .when().get("/yql")
                        .timeIn(TimeUnit.SECONDS);
                System.out.println("The time taken is: "+responseTime+" seconds");

                given()
                        .spec(rspec)
                        .log().all()
                        .when().get("/yql")
                        .then()
                   //     .time(lessThan(5L), TimeUnit.SECONDS);
                .spec(responseSpec);
    }

}
