import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select
 * "SportsEquipment" from Product dropdown. 6-) Enter "1" as quantity number. 7-) Enter
 * "10" as discount percentage. 8-) Click on the "Calculate" button. 9-) Enter "Inar
 * Academy" as Name. 10-) Enter "1100 Congress Ave" as Street. 11-) Enter "Austin" as
 * City. 12-) Enter "TX" State. 13-) Enter "78701" as Zip Code(number). 14-) Enter
 * "4938220192845" as Card Number. 15-) Enter "09/26" Expire Date(mm/yy format). 16-)
 * Click "Process"" button. 17-) Verify the Card Type error message is displayed.
 */

public class WO_009_OP_04 extends Hooks {

    @Test
    void testOrderPlacementWithoutCalculation() throws InterruptedException {
        // Click "WebOrder" button on top bar.
        WebElement webOrderTab = driver.findElement(By.xpath("(//a[contains(text(), 'Weborder')])[1]"));
        webOrderTab.click();

        // Enter "Inar" as username and "Academy" password.
        WebElement usernameBox = driver.findElement(By.cssSelector(".mb-4 .form-control"));
        WebElement passwordBox = driver.findElement(By.cssSelector("input[type='password']"));

        usernameBox.sendKeys("Inar");
        passwordBox.sendKeys("Academy");

        // Click on the "Login" button.
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Navigate to the order page.
        WebElement orderTab = driver.findElement(By.cssSelector("[href='/weborder/order']"));
        orderTab.click();

        // Select "SportsEquipment" from Product dropdown.
        Select select = new Select(driver.findElement(By.cssSelector(".form-select")));
        select.selectByValue("SportsEquipment");

        // Enter "1" as quantity number.
        WebElement quantityBox = driver.findElement(By.xpath("(//input[@value='0'])[1]"));
        quantityBox.sendKeys("1");

        // Enter "10" as discount percentage.
        WebElement discountBox = driver.findElement(By.xpath("(//div/div/input)[2]"));
        discountBox.sendKeys("10");

        // Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();

        // Enter "Inar Academy" as Name.
        WebElement nameBox = driver.findElement(By.cssSelector("[placeholder='Enter your name']"));
        nameBox.sendKeys("Inar Academy");

        // Enter "1100 Congress Ave" as Street.
        WebElement streetBox = driver.findElement(By.id("street"));
        streetBox.sendKeys("1100 Congress Ave");

        // Enter "Austin" as City.
        WebElement cityBox = driver.findElement(By.xpath("//input[@id='city']"));
        cityBox.sendKeys("Austin");

        // Enter "TX" State
        WebElement stateBox = driver.findElement(By.xpath("//input[contains(@placeholder, 'state')]"));
        stateBox.sendKeys("TX");

        // Enter "78701" as Zip Code(number).
        WebElement zipBox = driver.findElement(By.id("zip"));
        zipBox.sendKeys("78701");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scroll(0, 1000)");

        Thread.sleep(1000);

        // Enter "4938220192845" as Card Number.
        WebElement cardNumberBox = driver.findElement(By.id("cardNumber"));
        cardNumberBox.sendKeys("4938220192845");

        // Enter "09/26" Expire Date(mm/yy format).
        WebElement expireDateBox = driver.findElement(By.xpath("(//div/input)[15]"));
        expireDateBox.sendKeys("09/26");

        // Click "Process"" button.
        WebElement processButton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        processButton.click();

        // Verify the Card Type error message is displayed.
        WebElement errorElement = driver.findElement(By.xpath("//em[text()='Card type cannot be empty']"));
        String errorMessage = errorElement.getText();
        assertEquals("Card type cannot be empty", errorMessage);
    }

}

