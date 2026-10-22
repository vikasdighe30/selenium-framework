package com.framework.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class PracticePage {
    private WebDriver driver;
    private Actions actions;

    // --- Form Elements ---
    @FindBy(id = "name") private WebElement nameField;
    @FindBy(id = "email") private WebElement emailField;
    @FindBy(id = "phone") private WebElement phoneField;
    @FindBy(id = "textarea") private WebElement addressField;
    @FindBy(id = "male") private WebElement maleRadio;
    @FindBy(id = "female") private WebElement femaleRadio;
    @FindBy(id = "country") private WebElement countryDropdown;
    @FindBy(xpath = "//button[text()='Submit' or @type='submit']") private WebElement submitBtn;
    @FindBy(xpath = "//input[@type='checkbox' and contains(@id, 'day')]") private List<WebElement> daysCheckboxes;
    @FindBy(id = "datepicker") private WebElement datePicker1;

    // --- Interaction Elements ---
    @FindBy(xpath = "//button[text()='Simple Alert']") private WebElement simpleAlertBtn;
    @FindBy(xpath = "//button[text()='Confirmation Alert']") private WebElement confirmationAlertBtn;
    @FindBy(xpath = "//button[text()='Prompt Alert']") private WebElement promptAlertBtn;
    @FindBy(id = "demo") private WebElement alertResultText;
    @FindBy(xpath = "//button[text()='Copy Text']") private WebElement copyTextBtn;
    @FindBy(id = "field1") private WebElement field1;
    @FindBy(id = "field2") private WebElement field2;
    @FindBy(id = "draggable") private WebElement sourceDrag;
    @FindBy(id = "droppable") private WebElement targetDrop;
    @FindBy(id = "slider") private WebElement sliderElement;
    @FindBy(xpath = "//button[text()='Point Me']") private WebElement pointMeHoverBtn;
    @FindBy(xpath = "//a[text()='Mobiles']") private WebElement hoverMenuMobiles;

    // --- File Upload ---
    @FindBy(id = "singleFileInput") private WebElement singleFileInput;
    @FindBy(xpath = "//button[text()='Upload Single File']") private WebElement uploadSingleBtn;

    // --- Web Tables ---
    @FindBy(xpath = "//table[@name='BookTable']//tr") private List<WebElement> staticTableRows;
    @FindBy(xpath = "//table[contains(., 'CPU')]//tr") private List<WebElement> dynamicTableRows;
    @FindBy(xpath = "//table[contains(., 'Smartphone')]//tr") private List<WebElement> paginationTableRows;
    @FindBy(xpath = "//ul[@id='pagination']//li/a") private List<WebElement> paginationLinks;

    // --- Window Handling ---
    @FindBy(xpath = "//button[text()='New Tab']") private WebElement newTabBtn;

    public PracticePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // --- Form Methods ---
    public void fillDataEntryForm(String name, String email, String phone, String address, String gender, String country) {
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
        phoneField.clear();
        phoneField.sendKeys(phone);
        addressField.clear();
        addressField.sendKeys(address);

        if (gender.equalsIgnoreCase("female")) {
            femaleRadio.click();
        } else {
            maleRadio.click();
        }

        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
    }

    public void selectDays(List<String> daysToSelect) {
        for (WebElement checkbox : daysCheckboxes) {
            String dayValue = checkbox.getAttribute("value");
            if (daysToSelect.contains(dayValue) && !checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void enterDateInPicker1(String date) {
        datePicker1.clear();
        datePicker1.sendKeys(date);
        datePicker1.sendKeys(Keys.ESCAPE); 
    }

    public void submitForm() {
        submitBtn.click();
    }

    // --- File Upload Method ---
    public void uploadSingleFile(String absoluteFilePath) {
        singleFileInput.sendKeys(absoluteFilePath);
        uploadSingleBtn.click();
    }

    // --- Alert Methods ---
    public void triggerSimpleAlert() {
        simpleAlertBtn.click();
        driver.switchTo().alert().accept();
    }

    public String handlePromptAlert(String input) {
        promptAlertBtn.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(input);
        alert.accept();
        return alertResultText.getText();
    }

    // --- Interaction Methods ---
    public void performDoubleClick() {
        actions.doubleClick(copyTextBtn).perform();
    }

    public String getField2Value() {
        return field2.getAttribute("value");
    }

    public void performDragAndDrop() {
        actions.dragAndDrop(sourceDrag, targetDrop).perform();
    }

    public String getDroppableText() {
        return targetDrop.getText();
    }

    public void moveSliderToRight(int xOffset) {
        actions.dragAndDropBy(sliderElement, xOffset, 0).perform();
    }

    public void hoverAndClickMobile() {
        actions.moveToElement(pointMeHoverBtn).perform();
        actions.moveToElement(hoverMenuMobiles).click().perform();
    }

    // --- Web Table Methods ---
    public String getBookPrice(String bookName) {
        for (int i = 2; i <= staticTableRows.size(); i++) {
            String currentBook = driver.findElement(By.xpath("//table[@name='BookTable']//tr[" + i + "]/td[1]")).getText();
            if (currentBook.equalsIgnoreCase(bookName)) {
                return driver.findElement(By.xpath("//table[@name='BookTable']//tr[" + i + "]/td[4]")).getText();
            }
        }
        return null;
    }

    public String getDynamicTableCPUValue(String browserName) {
        for (int i = 1; i < dynamicTableRows.size(); i++) {
            WebElement row = dynamicTableRows.get(i);
            if (row.getText().contains(browserName)) {
                return row.findElement(By.xpath("./td[3]")).getText();
            }
        }
        return null;
    }

    public void selectProductFromPagination(String productName) {
        boolean found = false;
        if (paginationLinks.isEmpty()) {
            checkCurrentPageForProduct(productName);
            return;
        }

        for (WebElement page : paginationLinks) {
            page.click();
            found = checkCurrentPageForProduct(productName);
            if (found) break;
        }
    }

    private boolean checkCurrentPageForProduct(String productName) {
        for (int i = 1; i < paginationTableRows.size(); i++) {
            WebElement row = paginationTableRows.get(i);
            String name = row.findElement(By.xpath("./td[2]")).getText();
            if (name.equals(productName)) {
                row.findElement(By.xpath("./td[4]/input[@type='checkbox']")).click();
                return true;
            }
        }
        return false;
    }

    // --- Window & Shadow DOM Methods ---
    public boolean verifyNewTabHandling() {
        String originalWindow = driver.getWindowHandle();
        newTabBtn.click();
        
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        
        boolean isNewTabValid = driver.getCurrentUrl().contains("opencart"); 
        driver.close(); 
        driver.switchTo().window(originalWindow); 
        return isNewTabValid;
    }

    public void enterTextInShadowDom(String text) {
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement inputInShadow = shadowRoot.findElement(By.cssSelector("input[type='text']"));
        inputInShadow.sendKeys(text);
    }
}