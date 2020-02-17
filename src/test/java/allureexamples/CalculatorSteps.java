package allureexamples;

import app.MySimpleCalculator;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class CalculatorSteps {

    @Step("Adding two numbers: {0} & {1}")
    public double add2Numbers(double n1, double n2) {
        MySimpleCalculator calc = new MySimpleCalculator();
        return calc.add2Num(n1, n2);
    }

    @Attachment(value="{0}", type ="text/plain")
    public String writeText(String message) {
        return message;
    }

    @Attachment(value="image", type ="image/png")
    public byte[] attachImage(byte[] image) {
        return image;
    }
}
