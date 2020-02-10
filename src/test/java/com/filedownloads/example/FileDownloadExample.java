package com.filedownloads.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class FileDownloadExample {

    // Download a file & Compare it with an Expected File
    // Check the size of the file
    @Test
    public void verifyDownloadFile() {
        // Read the input file

        File inputFile = new File(System.getProperty("user.dir")+File.separator+"geckodriver-v0.26.0-linux32.tar.gz");

        // Length of input file
        int expectedSize = (int)inputFile.length();

        System.out.println("The size of the expected file is: "+expectedSize+" bytes");

        // https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-linux32.tar.gz
        // Read the downloaded File
     byte[] actualValue =  given()
                .when()
                .get("https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-linux32.tar.gz")
                .then()
                .extract()
                .asByteArray();
        System.out.println("The size of the actual file is: "+actualValue.length+" bytes");

        Assert.assertEquals(expectedSize, actualValue.length);
    }
}
