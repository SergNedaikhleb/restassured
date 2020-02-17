package allureexamples;

import app.MySimpleCalculator;
import io.qameta.allure.*;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;

@Epic("all tools for calculation")
@Feature("Calculator for performing mathematical operation")
public class MyCalculatorTest {

    @Description("this test check the sum")
    @Test(description="some description")
    public void tc1() {
        MySimpleCalculator calculate = new MySimpleCalculator();
        calculate.add2Num(3.0,5.0);
        MatcherAssert.assertThat(calculate.add2Num(3.0,5.0), equalTo(8.0));
    }

    @TmsLink("https://en.wikipedia.org/wiki/TestLink")
    @Description("this test check the sub")
    @Test(description="some description")
    public void tc2() {
        MySimpleCalculator calculate = new MySimpleCalculator();
        MatcherAssert.assertThat(calculate.sub2Num(5.0,4.0), equalTo(1.0));
    }

    @Link("https://en.wikipedia.org/wiki/Software_testing")
    @Description("this test check the multiply")
    @Test(description="some description")
    public void tc3() {
        MySimpleCalculator calculate = new MySimpleCalculator();
        MatcherAssert.assertThat(calculate.mul2Num(5.0,4.0), equalTo(20.0));
    }

    @Issue("https://en.wikipedia.org/wiki/Issue")
    @Description("this test check the division")
    @Test(description="some description")
    public void tc4() {
        MySimpleCalculator calculate = new MySimpleCalculator();
        MatcherAssert.assertThat(calculate.div2Num(5.0,4.0), equalTo(20.0));
    }

}
