package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.HiddenAjaxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HiddenAjaxTest extends BaseTest {

    @BeforeMethod
    public void navigateToAjaxPage() {
        // Navigate directly to the specific page before the test runs
        getDriver().get("https://testautomationpractice.blogspot.com/p/gui-elements-ajax-hidden.html");
    }

    @Test(priority = 1)
    public void testHiddenInputToggle() {
        HiddenAjaxPage ajaxPage = new HiddenAjaxPage(getDriver());
        
        // Toggle and fill
        ajaxPage.toggleAndFillHiddenInput("Selenium Automation");
        // Add an assertion here if you want to verify the text was entered using getAttribute("value")
    }

    @Test(priority = 2)
    public void testHiddenCheckboxToggle() {
        HiddenAjaxPage ajaxPage = new HiddenAjaxPage(getDriver());
        
        // Toggle and check
        ajaxPage.toggleAndSelectHiddenCheckbox();
    }

    @Test(priority = 3)
    public void testAjaxContentLoading() {
        HiddenAjaxPage ajaxPage = new HiddenAjaxPage(getDriver());
        
        // Trigger AJAX and get the new status text
        String newStatus = ajaxPage.triggerAjaxAndWait();
        
        // Assert that the status is no longer "Ready"
        Assert.assertFalse(newStatus.contains("Ready"), "AJAX content did not load correctly.");
        System.out.println("New AJAX Status: " + newStatus);
    }
}