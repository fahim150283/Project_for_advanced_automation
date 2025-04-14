package TestCases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestAllureReport2 {

    @Test
    public void S2_TestCase1() {
        System.out.println("Test Case Passed");
    }

    @Test
    public void S2_TestCase2() {
        System.out.println("Test Case Failed");
        Assert.fail("Intentional Failure for demo.");
    }

    @Test
    public void S2_TestCase3() {
        System.out.println("Test Case Skipped");
        throw new SkipException("Intentional Skip for demo.");
    }
}
