package allureexamples;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

@Epic("Just an epic")
@Feature("pass feature")
public class EpicFeatureStoryTest {

    @Story("I am just a test which will always pass")
    @Test
    public void tc1() {
        assertTrue(true);

        CalculatorSteps steps = new CalculatorSteps();
        steps.writeText("This is important information");
    }

    @Story("This is an image attachment example")
    @Test
    public void tc2() throws IOException {

        CalculatorSteps steps = new CalculatorSteps();
        BufferedImage bufImage =
                ImageIO.read(new File(System.getProperty("user.dir")+ File.separator));

        ByteArrayOutputStream byteArrOS = new ByteArrayOutputStream();
        ImageIO.write(bufImage, "png", byteArrOS);

        byte[] imageData = byteArrOS.toByteArray();

        steps.attachImage(imageData);
    }
}
