package com.fileupload.example;

import org.testng.annotations.Test;
import java.io.File;
import static com.jayway.restassured.RestAssured.*;

public class FileUploadExample {

    @Test
    public void uploadFileExample() {
        // Upload a file to zamzar.com and convert it into a PDF file

        String apiKey = "GiVUYsF4A8ssq93FR48H";
        String endPoint = "https://sandbox.zamzar.com/v1/jobs";

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"testTxtFile.txt");
        System.out.println((inputFile.length()));

        given()
                .auth().basic(apiKey, "")
                .multiPart("source_file", inputFile)
                .multiPart("target_format", "pdf")
                .when().post(endPoint)
        .then()
            .log()
            .all();
    }
}
