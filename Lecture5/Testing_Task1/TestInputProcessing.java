import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestInputProcessing {

    private static final String MESSAGE_TEST_RESOURCES_EXCEPTION = "Please check the test resources file - ";
    private static final String FILEPATH_FILE_WITH_NUMBERS = ".\\Testing_Task1\\userInputNumber.txt";
    private static final String FILEPATH_FILE_WITH_OPERATIONS = ".\\Testing_Task1\\userInputOperation.txt";
    private static final String REGEX_DOUBLE = "^[-+]?[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$";
    private static final String REGEX_OPERATIONS = "[-+*/]";

    private static BufferedReader numberReader;
    private static BufferedReader operationReader;

    static {
        try {
            numberReader = new BufferedReader(new FileReader(FILEPATH_FILE_WITH_NUMBERS));
        } catch (FileNotFoundException fileEx) {
            System.out.println(MESSAGE_TEST_RESOURCES_EXCEPTION + FILEPATH_FILE_WITH_NUMBERS);
        }
    }

    static {
        try {
            operationReader = new BufferedReader(new FileReader(FILEPATH_FILE_WITH_OPERATIONS));
        } catch (FileNotFoundException fileEx) {
            System.out.println(MESSAGE_TEST_RESOURCES_EXCEPTION + FILEPATH_FILE_WITH_OPERATIONS);
        }
    }

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
    public static void testUserInputNumber() throws IOException {
        String numberFromFile;
        while ((numberFromFile = numberReader.readLine()) != null) {
            if (numberFromFile.matches(REGEX_DOUBLE)) {
                Assert.assertEquals(
                        Helper.InputProcessing.getUserNumber(numberFromFile),
                        Double.parseDouble(numberFromFile),
                        "Application cannot work correctly with user input (numbers) -"
                );
            } else {
                Assert.assertEquals(
                        Helper.InputProcessing.getUserNumber(numberFromFile),
                        0d,
                        "Application cannot work correctly with user input (numbers) -"
                );
            }
        }
    }

    @Test
    public static void testUserInputOperation() throws IOException {
        String operationFromFile;
        while ((operationFromFile = operationReader.readLine()) != null) {
            if (operationFromFile.matches(REGEX_OPERATIONS)) {
                Assert.assertEquals(
                        Helper.InputProcessing.getUserOperation(operationFromFile),
                        operationFromFile.charAt(0),
                        "Application cannot work correctly with user input (operation) -"
                );
            } else {
                Assert.assertEquals(
                        Helper.InputProcessing.getUserOperation(operationFromFile),
                        ' ',
                        "Application cannot work correctly with user input (operation) -"
                );
            }
        }
    }
}