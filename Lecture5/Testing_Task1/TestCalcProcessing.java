import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCalcProcessing {

    @DataProvider(name = "testData")
    public Object[][] testDataProvider() {
        return new Object[][]{
                {5, 2, '+', 7},
                {5, 2, '-', 3},
                {5, 2, '*', 10},
                {5, 2, '/', 2.5}
        };
    }

    @DataProvider(name = "testData2")
    public Object[][] testDataProvider2() {
        return new Object[][]{
                {'+', "The sum = "},
                {'-', "The diff = "},
                {'*', "The der = "},
                {'/', "The quo = "}
        };
    }

    @Test(dataProvider = "testData")
    public static void testProperOperationSelected(double number1, double number2, char operation, double er) {
        Assert.assertEquals(Helper.CalcProcessing.getResultByOperation(number1, number2, operation), er);
    }

    @Test(dataProvider = "testData2")
    public static void testProperResultTextSelected(char operation, String er) {
        Assert.assertEquals(Helper.CalcProcessing.getResultTextByOperation(operation), er);
    }
}
