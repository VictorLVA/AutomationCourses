import Factory.ResultsFactory;
import Results.Result;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestResultsCreation {

    @DataProvider(name = "dataForResultsCreation")
    public Object[][] dataForOperationSelection() {
        return new Object[][]{
                {5, 2, '+', "The sum = 7.0"},
                {5, 2, '-', "The difference = 3.0"},
                {5, 2, '*', "The product = 10.0"},
                {5, 2, '/', "The quotient = 2.5"}
        };
    }

    @Test(dataProvider = "dataForResultsCreation")
    public static void testProperResultCreated(double number1, double number2, char operation, String expectedResult) {
        Result result = ResultsFactory.createResult(number1, number2, operation);
        Assert.assertEquals(
                result.toString(),
                expectedResult,
                "Result wasn't created properly -"
        );
    }
}
