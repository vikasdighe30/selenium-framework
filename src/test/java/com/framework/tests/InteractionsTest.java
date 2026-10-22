package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.PracticePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InteractionsTest extends BaseTest {

    @Test
    public void testAlertHandling() {
        PracticePage practicePage = new PracticePage(getDriver());
        String confirmation = practicePage.handlePromptAlert("Tester Automation");
        Assert.assertTrue(confirmation.contains("Tester Automation"));
    }

    @Test
    public void testDoubleClickCopy() {
        PracticePage practicePage = new PracticePage(getDriver());
        practicePage.performDoubleClick();
        Assert.assertEquals(practicePage.getField2Value(), "Hello World!");
    }

    @Test
    public void testDragAndDrop() {
        PracticePage practicePage = new PracticePage(getDriver());
        practicePage.performDragAndDrop();
        Assert.assertEquals(practicePage.getDroppableText(), "Dropped!");
    }
}