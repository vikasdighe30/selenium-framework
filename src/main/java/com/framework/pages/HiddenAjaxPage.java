package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HiddenAjaxPage {
    // Removed unused 'private WebDriver driver;' field
    private WebDriverWait wait;

    // --- Locators ---
    @FindBy(xpath = "//button[text()='Toggle Input Box 2']") 
    private WebElement toggleInput2Btn;
    
    @FindBy(xpath = "//input[@type='text' and not(@id='input1')] | //input[@id='input2']") 
    private WebElement hiddenInput2;

    @FindBy(xpath = "//button[text()='Toggle Checkbox 2']") 
    private WebElement toggleCheckbox2Btn;
    
    @FindBy(xpath = "//input[@type='checkbox' and @id='checkbox2']") 
    private WebElement hiddenCheckbox2;

    @FindBy(xpath = "//button[text()='Load AJAX Content']") 
    private WebElement loadAjaxBtn;

    @FindBy(xpath = "//*[contains(text(), 'Status:')]") 
    private WebElement statusText;

    public HiddenAjaxPage(WebDriver driver) {
        // Removed 'this.driver = driver;' because it's not needed as a class field
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Methods ---
    
    public void toggleAndFillHiddenInput(String text) {
        toggleInput2Btn.click();
        wait.until(ExpectedConditions.visibilityOf(hiddenInput2));
        hiddenInput2.clear();
        hiddenInput2.sendKeys(text);
    }

    public void toggleAndSelectHiddenCheckbox() {
        toggleCheckbox2Btn.click();
        wait.until(ExpectedConditions.visibilityOf(hiddenCheckbox2));
        if (!hiddenCheckbox2.isSelected()) {
            hiddenCheckbox2.click();
        }
    }

    public String triggerAjaxAndWait() {
        String initialStatus = statusText.getText();
        loadAjaxBtn.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(statusText, initialStatus)));
        return statusText.getText();
    }
}