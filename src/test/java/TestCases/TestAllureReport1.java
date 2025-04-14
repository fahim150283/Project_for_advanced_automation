package TestCases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestAllureReport1 {

    @Test
    public void S1_TestCase1() {
        System.out.println("Test Case Passed");
    }

    @Test
    public void S1_TestCase2() {
        System.out.println("Test Case Failed");
        Assert.fail("Intentional Failure for demo.");
    }

    @Test
    public void S1_TestCase3() {
        System.out.println("Test Case Skipped");
        throw new SkipException("Intentional Skip for demo.");
    }
}
