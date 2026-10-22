package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.PracticePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;

public class AdvancedElementsTest extends BaseTest {

    @Test(priority = 1)
    public void testCheckboxesAndDate() {
        PracticePage page = new PracticePage(getDriver());
        
        // Test Checkboxes
        page.selectDays(Arrays.asList("monday", "wednesday", "friday"));
        
        // Test DatePicker
        page.enterDateInPicker1("12/25/2024");
        // Add specific assertions based on your UI state changes if applicable
    }

    @Test(priority = 2)
    public void testFileUpload() {
        PracticePage page = new PracticePage(getDriver());
        
        // Create a temporary file to upload
        File tempFile = new File("src/test/resources/dummy.txt");
        try {
            tempFile.createNewFile();
            page.uploadSingleFile(tempFile.getAbsolutePath());
            // Assert that upload success message appears (you may need to add locator for the success text)
        } catch (Exception e) {
            Assert.fail("File upload failed: " + e.getMessage());
        } finally {
            tempFile.delete(); // cleanup
        }
    }

    @Test(priority = 3)
    public void testDynamicWebTable() {
        PracticePage page = new PracticePage(getDriver());
        String cpuUsage = page.getDynamicTableCPUValue("Chrome");
        Assert.assertNotNull(cpuUsage, "Chrome CPU usage should not be null");
        System.out.println("Chrome CPU Usage: " + cpuUsage);
    }

    @Test(priority = 4)
    public void testPaginationTable() {
        PracticePage page = new PracticePage(getDriver());
        // Attempt to find and select "Smartwatch" which might be on page 1 or 2
        page.selectProductFromPagination("Smartwatch");
        // Assertions would depend on verifying the checkbox is checked after locating
    }

    @Test(priority = 5)
    public void testSliderAndHover() {
        PracticePage page = new PracticePage(getDriver());
        
        // Test Slider
        page.moveSliderToRight(50);
        
        // Test Hover
        page.hoverAndClickMobile();
    }

    @Test(priority = 6)
    public void testMultipleWindows() {
        PracticePage page = new PracticePage(getDriver());
        boolean isWindowHandled = page.verifyNewTabHandling();
        Assert.assertTrue(isWindowHandled, "New tab did not open or navigate to the expected URL.");
    }
}