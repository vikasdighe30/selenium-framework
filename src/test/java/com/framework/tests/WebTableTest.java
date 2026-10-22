package com.framework.tests;

import com.framework.base.BaseTest;
import com.framework.pages.PracticePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTableTest extends BaseTest {

    @Test
    public void testStaticBookTablePrice() {
        PracticePage practicePage = new PracticePage(getDriver());
        String price = practicePage.getBookPrice("Learn Java");
        Assert.assertEquals(price, "500");
    }
}