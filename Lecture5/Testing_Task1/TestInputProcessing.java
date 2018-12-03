import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestInputProcessing {

    private static BufferedReader numberReader;
    private static BufferedReader operationReader;

    static {
        try {
            numberReader = new BufferedReader(new FileReader(".\\Testing_Task1\\userInputNumber.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            operationReader = new BufferedReader(new FileReader(".\\Testing_Task1\\userInputOperation.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void testInputAssignedFalseAtStart() {
        Assert.assertFalse(Helper.InputProcessing.isInputAssigned());
    }

    @Test(dependsOnMethods = "testInputAssignedFalseAtStart")
    public static void testSettingValueInputAssigned() {
        Helper.InputProcessing.setInputAssigned(true);
        Assert.assertTrue(Helper.InputProcessing.isInputAssigned());
        Helper.InputProcessing.setInputAssigned(false);
        Assert.assertFalse(Helper.InputProcessing.isInputAssigned());
    }

    @Test
    public static void testIsQuitNeeded() {
        Assert.assertTrue(Helper.InputProcessing.isQuitNeeded("quit"));
        Assert.assertTrue(Helper.InputProcessing.isQuitNeeded("QUIT"));
        Assert.assertFalse(Helper.InputProcessing.isQuitNeeded("quit1"));
        Assert.assertFalse(Helper.InputProcessing.isQuitNeeded("2quit"));
        Assert.assertFalse(Helper.InputProcessing.isQuitNeeded("exit"));
    }

    @Test(dependsOnMethods = "testSettingValueInputAssigned")
    public static void testUserInputNumber() {
        Assert.assertEquals(Helper.InputProcessing.getUserNumber(numberReader), -2d);
        Assert.assertEquals(Helper.InputProcessing.getUserNumber(numberReader), 3d);
        Assert.assertEquals(Helper.InputProcessing.getUserNumber(numberReader), -4.4d);
        Assert.assertEquals(Helper.InputProcessing.getUserNumber(numberReader), 5.5d);
        Assert.assertEquals(Helper.InputProcessing.getUserNumber(numberReader), 0d);
    }

    @Test(dependsOnMethods = "testUserInputNumber")
    public static void testUserInputOperation() {
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), '+');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), '-');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), '*');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), '/');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), ' ');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), ' ');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), ' ');
        Assert.assertEquals(Helper.InputProcessing.getUserOperation(operationReader), ' ');
    }
}