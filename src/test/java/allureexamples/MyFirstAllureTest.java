package allureexamples;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("all tools for calculation")
@Feature("i just pass")
public class MyFirstAllureTest {

    @Story("my first allure test")
    @Test
    public void tc1() {
        Assert.assertTrue(true);
    }
}
