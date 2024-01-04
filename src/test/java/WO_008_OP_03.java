import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select "MyMoney"
 * from Product dropdown. 6-) Enter "8" as quantity number. 7-) Enter "20" as discount
 * percentage. 8-) Click on the "Calculate" button. 9-) Enter "Inar Academy" as Name. 10-)
 * Enter "1100 Congress Ave" as Street. 11-) Enter "Austin" as City. 12-) Enter "TX"
 * State. 13-) Enter "92@#83" as Zip Code. 14-) Select "American Express" as Card Type.
 * 15-) Enter "342738261027163" as Card Number. 16-) Enter "01/28" Expire Date(mm/yy
 * format). 17-) Click "Process"" button. 18-) Verify the invalid Zip Code error message
 * is displayed.
 */

public class WO_008_OP_03 extends Hooks {

    @Test
    void testOrderPlacementWithInvalidZipCode() throws InterruptedException {
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

        // Select "MyMoney" from Product dropdown.
        Select select = new Select(driver.findElement(By.cssSelector(".form-select")));
        select.selectByValue("MyMoney");

        // Enter "3" as quantity number.
        WebElement quantityBox = driver.findElement(By.xpath("(//input[@value='0'])[1]"));
        quantityBox.sendKeys("3");

        // Enter "17" as discount percentage.
        WebElement discountBox = driver.findElement(By.xpath("(//div/div/input)[2]"));
        discountBox.sendKeys("17");

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

        // Enter "92@#83" as Zip Code.
        WebElement zipBox = driver.findElement(By.id("zip"));
        zipBox.sendKeys("92@#83");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scroll(0, 1000)");

        Thread.sleep(1000);

        // Select "AmericanExpress" as Card Type.
        WebElement cardType = driver.findElement(By.xpath("//input[@value='American Express']"));
        cardType.click();

        // Enter "342738261027163" as Card Number.
        WebElement cardNumberBox = driver.findElement(By.id("cardNumber"));
        cardNumberBox.sendKeys("342738261027163");

        // Enter "11/28" Expire Date(mm/yy format)
        WebElement expireDateBox = driver.findElement(By.xpath("(//div/input)[15]"));
        expireDateBox.sendKeys("11/28");

        // Click "Process"" button.
        WebElement processButton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        processButton.click();

        // Verify the invalid Zip Code error message is displayed.
        WebElement errorMessageElement = driver
                .findElement(By.xpath("//em[normalize-space()='entered Zip Code is invalid']"));
        String errorMessage = errorMessageElement.getText();
        assertEquals("entered Zip Code is invalid", errorMessage);

    }

}

