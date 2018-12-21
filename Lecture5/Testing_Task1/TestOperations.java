import Results.AdditionResult;
import Results.DivisionResult;
import Results.MultiplicationResult;
import Results.Result;
import Results.SubtractionResult;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestOperations {

    @DataProvider(name = "numbersForOperationsTesting")
    public Object[][] testDataProvider() {
        return new Object[][]{
                {0, 5},
                {13, 0},
                {0, -5},
                {2, -5},
                {13, 2},
                {134.3, -9764.4},
                {-134.3, 9764.4},
                {-1975468.123, -8324571.75364},
                {1975468.123, 8324571.75364}
        };
    }

    @Test(dataProvider = "numbersForOperationsTesting")
    public static void testAddition(double number1, double number2) {
        Result sumResult = new AdditionResult(number1, number2);
        Assert.assertEquals(
                sumResult.getResult(),
                number1 + number2,
                "The addition function works incorrectly -"
        );
    }

    @Test(dataProvider = "numbersForOperationsTesting")
    public static void testSubtraction(double number1, double number2) {
        Result differenceResult = new SubtractionResult(number1, number2);
        Assert.assertEquals(
                differenceResult.getResult(),
                number1 - number2,
                "The subtraction function works incorrectly -"
        );
    }

    @Test(dataProvider = "numbersForOperationsTesting")
    public static void testMultiplication(double number1, double number2) {
        Result productResult = new MultiplicationResult(number1, number2);
        Assert.assertEquals(
                productResult.getResult(),
                number1 * number2,
                "The multiplication function works incorrectly -"
        );
    }

    @Test(dataProvider = "numbersForOperationsTesting")
    public static void testDivision(double number1, double number2) {
        Result quotientResult = new DivisionResult(number1, number2);
        Assert.assertEquals(
                quotientResult.getResult(),
                number1 / number2,
                "The division function works incorrectly -"
        );
    }
}
