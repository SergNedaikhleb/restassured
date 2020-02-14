package com.restassures.examples.soapws;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.Test;

public class HolidayWsExamples {

    @Test
    public void getHolidays() {
        String wsdlUrl = "http://www.holidaywebservice.com//HolidayService_v2/HolidayService2.asmx?wsdl";
        String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"xml:hs=\"http://www.holidaywebservice.com/HolidayService_v2/\"";

        RestAssured.given()
                .contentType("text/xml")
                .body(payload)
                .post(wsdlUrl)
                .then().log().all();
    }
}
