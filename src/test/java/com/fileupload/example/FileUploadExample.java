package com.fileupload.example;

import org.testng.annotations.Test;
import java.io.File;
import static com.jayway.restassured.RestAssured.*;

public class FileUploadExample {

    @Test
    public void uploadFileExample() {

        String apiKey = "7873677966f8cc4238a9c7094d2b39e87bcf434b";
        String endPoint = "https://sandbox.zamzar.com/v1/jobs";

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"testTxtFile.txt");
        System.out.println((inputFile.length()));

        given()
                .auth().basic(apiKey, "")
                .multiPart("source_file", inputFile)
                .multiPart("target_format", "txt")
                .when().post(endPoint)
        .then()
            .log()
            .all();
    }
}
