import org.testng.Assert;
import org.testng.annotations.Test;

public class TestInputProcessing {

    @Test
    public static void testInputAssignedFalsedAtStart() {
        Assert.assertFalse(Helper.InputProcessing.isInputAssigned());
    }

    @Test(dependsOnMethods = "testInputAssignedFalsedAtStart")
    public static void testSettingValueInputAssigned() {
        Helper.InputProcessing.setInputAssigned(true);
        Assert.assertTrue(Helper.InputProcessing.isInputAssigned());
        Helper.InputProcessing.setInputAssigned(false);
        Assert.assertFalse(Helper.InputProcessing.isInputAssigned());
    }
}
