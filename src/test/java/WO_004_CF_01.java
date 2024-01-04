import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select "HomeDecor"
 * from Product dropdown. 6-) Enter "5" as quantity number. 7-) Enter "15" as discount
 * percentage. 8-) Click on the "Calculate" button. 9-) Verify that the total amount is
 * successfully displayed.
 */

public class WO_004_CF_01 extends Hooks {

    public final int QUANTITY = 5;

    public final int DISCOUNT = 15;

    @Test
    void testCalculateFunctionalityWithValidInputs() {
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

        // Select "HomeDecor" from Product dropdown.
        Select select = new Select(driver.findElement(By.cssSelector(".form-select")));
        select.selectByValue("HomeDecor");

        // Find Unit Price Of Product
        WebElement unitPriceBox = driver.findElement(By.id("unitPriceInput"));
        int unitPrice = Integer.parseInt(unitPriceBox.getAttribute("value"));

        // Enter "5" as quantity number.
        WebElement quantityBox = driver.findElement(By.xpath("(//input[@value='0'])[1]"));
        quantityBox.sendKeys(QUANTITY + "");

        // Enter "15" as discount percentage.
        WebElement discountBox = driver.findElement(By.xpath("(//div/div/input)[2]"));
        discountBox.sendKeys(DISCOUNT + "");

        // Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();

        // Verify that the total amount is successfully displayed.

        // Calculation of Expected Value
        long expectedValue = Math.round((unitPrice * QUANTITY / 100.0) * (100 - DISCOUNT));

        WebElement totalBox = driver.findElement(By.cssSelector("#totalInput"));
        long actualValue = Long.parseLong(totalBox.getAttribute("value"));
        assertEquals(expectedValue, actualValue, "Calculate button does not work properly");
    }

}

