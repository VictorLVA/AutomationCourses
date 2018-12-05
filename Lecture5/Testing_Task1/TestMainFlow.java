import Factory.ResultsFactory;
import Helper.InputProcessing;
import Results.Result;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestMainFlow {

    private static double number1;
    private static double number2;
    private static char operation;

    @Test
    public static void testGetFirstNumber() {
        number1 = Helper.InputProcessing.getUserNumber("25.3");
        Assert.assertEquals(
                number1,
                Double.parseDouble("25.3"),
                "First number isn't get correctly -"
        );
    }

    @Test(dependsOnMethods = "testGetFirstNumber")
    public static void testGetOperation() {
        operation = Helper.InputProcessing.getUserOperation("+");
        Assert.assertEquals(
                operation,
                "+".charAt(0),
                "Operation isn't get correctly -"
        );
    }

    @Test(dependsOnMethods = "testGetOperation")
    public static void testGetSecondNumber() {
        number2 = Helper.InputProcessing.getUserNumber("125.3");
        Assert.assertEquals(
                number2,
                Double.parseDouble("125.3"),
                "Second number isn't get correctly -"
        );
    }

    @Test(dependsOnMethods = "testGetSecondNumber")
    public static void testGetResult() {
        Result result = ResultsFactory.createResult(number1, number2, operation);
        Assert.assertEquals(
                result.getResult(),
                150.6d,
                "Result is calculated incorrectly -"
        );
    }

    @AfterClass
    public static void setFlagsValue() {
        InputProcessing.setFlagsValue(false);
    }
}