import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCalcProcessing {

    @DataProvider(name = "testData")
    public Object[][] testDataProvider() {
        return new Object[][]{
                {5, 2, '+'},
                {5, 2, '-'},
                {5, 2, '*'},
                {5, 0, '/'},
                {5, 2, '/'}
        };
    }

    @Test(dataProvider = "testData")
    public static void properOperationSelected(double number1, double number2, char operation) {
        Helper.CalcProcessing.doCalc(number1, number2, operation);
    }
}
