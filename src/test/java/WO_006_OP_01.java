import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select "MyMoney"
 * from Product dropdown. 6-) Enter "8" as quantity number. 7-) Enter "20" as discount
 * percentage. 8-) Click on the "Calculate" button. 9-) Enter "Inar Academy" as Name. 10-)
 * Enter "1100 Congress Ave" as Street. 11-) Enter "Austin" as City. 12-) Enter "TX"
 * State. 13-) Enter "78701" as Zip Code(number). 14-) Select "Visa" as Card Type. 15-)
 * Enter "4938281746192845" as Card Number. 16-) Enter "11/28" Expire Date(mm/yy format).
 * 17-) Click "Process"" button. 18-) Verify the confirmation message is displayed. 19-)
 * Navigate to view all orders page. 20-) Verify the order is successfully placed.
 */

public class WO_006_OP_01 extends Hooks {

    @Test
    void testOrderPlacement() throws InterruptedException {

        List<String> orderData = new ArrayList<>();

        orderData.add("Inar Academy");
        orderData.add("MyMoney");
        orderData.add("8");
        orderData.add(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        orderData.add("1100 Congress Ave");
        orderData.add("Austin");
        orderData.add("TX");
        orderData.add("78701");
        orderData.add("Visa");
        orderData.add("4938281746192845");
        orderData.add("11/28");

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

        // Enter "8" as quantity number.
        WebElement quantityBox = driver.findElement(By.xpath("(//input[@value='0'])[1]"));
        quantityBox.sendKeys("8");

        // Enter "20" as discount percentage.
        WebElement discountBox = driver.findElement(By.xpath("(//div/div/input)[2]"));
        discountBox.sendKeys("20");

        // Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();

        // Enter "Inar Academy" as Name.
        WebElement nameBox = driver.findElement(By.cssSelector("[placeholder='Enter your name']"));
        nameBox.sendKeys(orderData.get(0));

        // Enter "1100 Congress Ave" as Street.
        WebElement streetBox = driver.findElement(By.id("street"));
        streetBox.sendKeys(orderData.get(4));

        // Enter "Austin" as City.
        WebElement cityBox = driver.findElement(By.xpath("//input[@id='city']"));
        cityBox.sendKeys(orderData.get(5));

        // Enter "TX" State
        WebElement stateBox = driver.findElement(By.xpath("//input[contains(@placeholder, 'state')]"));
        stateBox.sendKeys(orderData.get(6));

        // Enter "78701" as Zip Code(number).
        WebElement zipBox = driver.findElement(By.id("zip"));
        zipBox.sendKeys(orderData.get(7));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scroll(0, 1000)");

        Thread.sleep(1000);

        // Select "Visa" as Card Type.
        WebElement cardType = driver.findElement(By.xpath("//input[@value='Visa']"));
        cardType.click();

        // Enter "4938281746192845" as Card Number.
        WebElement cardNumberBox = driver.findElement(By.id("cardNumber"));
        cardNumberBox.sendKeys(orderData.get(9));

        // Enter "11/28" Expire Date(mm/yy format)
        WebElement expireDateBox = driver.findElement(By.xpath("(//div/input)[15]"));
        expireDateBox.sendKeys(orderData.get(10));

        // Click "Process"" button.
        WebElement processButton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        processButton.click();

        // Verify verification message displayed
        WebElement verificationElement = driver.findElement(By.xpath("//div[@role='alert']"));
        String verificationMessage = verificationElement.getText();
        assertEquals("New order has been successfully added.", verificationMessage,
                "Verification message is not displayed");

        // Navigate to View all orders Tab
        jsExecutor.executeScript("window.scroll(0, -1000);");

        Thread.sleep(1000);

        // Navigate to view all orders page.
        WebElement viewAllOrdersTab = driver.findElement(By.cssSelector("a[href='/weborder/view-orders']"));
        viewAllOrdersTab.click();

        // Verify the order is successfully placed.
        List<WebElement> allOrders = driver.findElements(By.xpath("//tbody/tr"));
        List<WebElement> dataOfLastOrder = allOrders.get(allOrders.size() - 1).findElements(By.xpath("td"));

        for (int i = 0; i < orderData.size(); i++) {
            assertEquals(orderData.get(i), dataOfLastOrder.get(i + 1).getText());
        }
    }

}

