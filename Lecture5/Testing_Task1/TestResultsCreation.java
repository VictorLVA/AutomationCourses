import Factory.ResultsFactory;
import Results.Result;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestResultsCreation {

    @DataProvider(name = "dataForResultsCreation")
    public Object[][] dataForOperationSelection() {
        return new Object[][]{
                {5, 2, '+', "The sum = ", 7},
                {5, 2, '-', "The difference = ", 3},
                {5, 2, '*', "The product = ", 10},
                {5, 2, '/', "The quotient = ", 2.5}
        };
    }

    @Test(dataProvider = "dataForResultsCreation")
    public static void testProperResultCreated(double number1, double number2, char operation, String expectedText, double expectedResult) {
        Result result = ResultsFactory.createResult(number1, number2, operation);
        Assert.assertEquals(
                result.getResult(),
                expectedResult,
                "Result wasn't calculated properly -"
        );
        Assert.assertEquals(
                result.getText(),
                expectedText,
                "Result text wasn't choose properly -"
        );
    }
}
