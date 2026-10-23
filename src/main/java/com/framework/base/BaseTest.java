package com.framework.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.framework.utils.ConfigReader;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThread =
            new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("") String browser) {

        // If browser not passed from testng.xml
        if (browser == null || browser.trim().isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driverThread.set(driver);

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Long.parseLong(
                                ConfigReader.getProperty("implicitWait"))));

        getDriver().manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        Long.parseLong(
                                ConfigReader.getProperty("pageLoadTimeout"))));

        getDriver().get(
                ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (getDriver() != null) {

            getDriver().quit();

            driverThread.remove();
        }
    }
}