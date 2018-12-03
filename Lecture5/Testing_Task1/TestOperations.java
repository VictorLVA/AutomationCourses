import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestOperations {

    @DataProvider(name = "testData")
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

    @Test(dataProvider = "testData")
    public static void testSum(double number1, double number2) {
        Assert.assertEquals(Helper.Operations.getSum(number1, number2), number1 + number2);
    }

    @Test(dataProvider = "testData")
    public static void testDiff(double number1, double number2) {
        Assert.assertEquals(Helper.Operations.getDiff(number1, number2), number1 - number2);
    }

    @Test(dataProvider = "testData")
    public static void testDer(double number1, double number2) {
        Assert.assertEquals(Helper.Operations.getDer(number1, number2), number1 * number2);
    }

    @Test(dataProvider = "testData")
    public static void testQuo(double number1, double number2) {
        Assert.assertEquals(Helper.Operations.getQuo(number1, number2), number1 / number2);
    }
}
