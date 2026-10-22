package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.dataproviders.TestDataProvider;
import com.framework.pages.PracticePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormSubmissionTest extends BaseTest {

    @Test(dataProvider = "DirectFormData", dataProviderClass = TestDataProvider.class)
    public void testFormSubmission(String name, String email, String phone, String address, String gender, String country) {
        PracticePage practicePage = new PracticePage(getDriver());
        practicePage.fillDataEntryForm(name, email, phone, address, gender, country);
        practicePage.submitForm();
        
        Assert.assertTrue(getDriver().getTitle().contains("Automation Testing Practice"));
    }
}