import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1.)Open the URL 2.)Click "Weborder" button on top navbar 3.)Enter valid username as
 * "Inar" and password as "Academy" 4.)Navigate to Order page 5.)Select product
 * as"ScreenSaver" on dropdown 6.)Enter the quantity as "3" 7.)Enter the discount as "20"
 * 8.)Click on "Calculate" button 9.)Enter "Steve" as name 10.)Enter "1100 Congress Ave"as
 * Street. 11.)Enter "Austin" as City. 12.)Enter "TX" State. 13.)Enter "78640" as ZipCode.
 * 14.)Select card type "Visa" 15.)Enter "4938281746192845" as card number 16.)Enter
 * "12/24" as expire date 17.)Click on "Reset" button 18.)Verify that text field under
 * "Customer Information" and "Payment Information" will be empty.
 */

public class WO_011_OP_06 extends Hooks {

    @Test
    void testResetButtonFunctionality() {
        // Click "Weborder" button on top navbar
        WebElement webOrderTab = driver.findElement(By.cssSelector("[href='/weborder']"));
        webOrderTab.click();

        // Enter valid username as "Inar" and password as "Academy"
        WebElement usernameTextBox = driver.findElement(By.id("login-username-input"));
        WebElement passwordTextBox = driver.findElement(By.id("login-password-input"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameTextBox.sendKeys("Inar");
        passwordTextBox.sendKeys("Academy");
        loginButton.click();

        // Navigate to Order page
        WebElement orderTab = driver.findElement(By.xpath("//a[text()='Order']"));
        orderTab.click();

        // Select product as "ScreenSaver" on dropdown
        Select dropdown = new Select(driver.findElement(By.id("productSelect")));
        dropdown.selectByValue("ScreenSaver");

        // Enter the quantity as "3"
        WebElement quantityBox = driver.findElement(By.id("quantityInput"));
        quantityBox.sendKeys("3");

        // Enter the discount as "20"
        WebElement discountBox = driver.findElement(By.id("discountInput"));
        discountBox.sendKeys("20");

        // Click on "Calculate" button
        WebElement calculateButton = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
        calculateButton.click();

        // Enter "Steve" as name
        WebElement nameTextBox = driver.findElement(By.id("name"));
        nameTextBox.sendKeys("Steve");

        // Enter "1100 Congress Ave" as Street.
        WebElement streetTextBox = driver.findElement(By.id("street"));
        streetTextBox.sendKeys("1100 Congress Ave");

        // Enter "Austin" as City.
        WebElement cityTestBox = driver.findElement(By.id("city"));
        cityTestBox.sendKeys("Austin");

        // Enter "TX" State.
        WebElement stateTextBox = driver.findElement(By.id("state"));
        stateTextBox.sendKeys("TX");

        // Enter "78640" as Zip Code.
        WebElement zipCodeTextBox = driver.findElement(By.id("zip"));
        zipCodeTextBox.sendKeys("78640");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scroll(0, 1000)");

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Select card type "Visa"
        WebElement cardType = driver.findElement(By.id("visa"));
        cardType.click();

        // Enter "4938281746192845" as card number
        WebElement cardNumberTextField = driver.findElement(By.id("cardNumber"));
        cardNumberTextField.sendKeys("4938281746192845");

        // Enter "12/24" as expire date
        WebElement expireDateTextBox = driver.findElement(By.id("expiryDate"));
        expireDateTextBox.sendKeys("12/24");

        // Click on "Reset" button
        WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset']"));
        resetButton.click();

        // Verify that text field under "Customer Information" and "Payment Information
        // will be empty.
        List<WebElement> dataOfPaymentAndCustomer = driver.findElements(By.cssSelector(".order-form >div >input"));
        for (WebElement element : dataOfPaymentAndCustomer) {
            String value = element.getAttribute("value");
            assertTrue(value.isEmpty());
        }
    }

}
