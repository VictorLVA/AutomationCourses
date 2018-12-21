import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestInputProcessing {

    @DataProvider(name = "dataForQuit")
    public Object[][] dataForProperQuit() {
        return new Object[][]{
                {"quit"},
                {"QUIT"},
                {"qui"},
                {"quit1"},
                {"2quit"},
                {"exit"}
        };
    }

    @Test
    public static void testFlagsFalseAtStart() {
        Assert.assertFalse(
                Helper.InputProcessing.isFirstNumberAssigned(),
                "Boolean 'isFirstNumberAssigned' isn't 'FALSE' at the start of application -"
        );
        Assert.assertFalse(
                Helper.InputProcessing.isOperationAssigned(),
                "Boolean 'isOperationAssigned' isn't 'FALSE' at the start of application -"
        );
        Assert.assertFalse(
                Helper.InputProcessing.isSecondNumberAssigned(),
                "Boolean 'isSecondNumberAssigned' isn't 'FALSE' at the start of application -"
        );
    }

    @Test
    public static void testSettingValueInputAssigned() {
        Helper.InputProcessing.setFlagsValue(true);
        Assert.assertTrue(
                Helper.InputProcessing.isFirstNumberAssigned(),
                "Cannot assign the value 'TRUE' for the 'isFirstNumberAssigned' boolean -"
        );
        Assert.assertTrue(
                Helper.InputProcessing.isOperationAssigned(),
                "Cannot assign the value 'TRUE' for the 'isOperationAssigned' boolean -"
        );
        Assert.assertTrue(
                Helper.InputProcessing.isSecondNumberAssigned(),
                "Cannot assign the value 'TRUE' for the 'isSecondNumberAssigned' boolean -"
        );
        Helper.InputProcessing.setFlagsValue(false);
        Assert.assertFalse(
                Helper.InputProcessing.isFirstNumberAssigned(),
                "Cannot assign the value 'FALSE' for the 'isFirstNumberAssigned' boolean -"
        );
        Assert.assertFalse(
                Helper.InputProcessing.isOperationAssigned(),
                "Cannot assign the value 'FALSE' for the 'isOperationAssigned' boolean -"
        );
        Assert.assertFalse(
                Helper.InputProcessing.isSecondNumberAssigned(),
                "Cannot assign the value 'FALSE' for the 'isSecondNumberAssigned' boolean -"
        );
    }

    @Test(dataProvider = "dataForQuit")
    public static void testProperIsQuitNeeded(String quitInput) {
        if (quitInput.equalsIgnoreCase("quit")) {
            Assert.assertTrue(
                    Helper.InputProcessing.isQuitNeeded(quitInput),
                    "'" + quitInput + "' input isn't work properly with the Quit function -"
            );
        } else {
            Assert.assertFalse(
                    Helper.InputProcessing.isQuitNeeded(quitInput),
                    "'" + quitInput + "' input isn't work properly with the Quit function -"
            );
        }
    }

    @Test
    public static void testUserInputNumber() {
        String properInput = "-5.0";
        String invalidInput = "w";
        Assert.assertEquals(
                Helper.InputProcessing.getUserNumber(properInput),
                -5.0d,
                "Application cannot work correctly with user input -"
        );
        Assert.assertEquals(
                Helper.InputProcessing.getUserNumber(invalidInput),
                0d,
                "Application cannot work correctly with user input -"
        );
    }

    @Test
    public static void testUserInputOperation() {
        String properInput = "*";
        String invalidInput = "-5.0";
        Assert.assertEquals(
                Helper.InputProcessing.getUserOperation(properInput),
                '*',
                "Application cannot work correctly with user input -"
        );
        Assert.assertEquals(
                Helper.InputProcessing.getUserOperation(invalidInput),
                ' ',
                "Application cannot work correctly with user input -"
        );
    }
}