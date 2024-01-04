import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the order page. 5-) Select "ScreenSaver"
 * from Product dropdown. 6-) Leave blank the quantity box. 7-) Enter "20" as discount
 * percentage. 8-) Click on the "Calculate" button. 9-) Verify the invalid Quantity error
 * message is displayed.
 */

public class WO_005_CF_02 extends Hooks {

    @Test
    void testCalculateFunctionalityWithInvalidInputs() {
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

        // Select "ScreenSaver" from Product dropdown.
        Select select = new Select(driver.findElement(By.cssSelector(".form-select")));
        select.selectByValue("ScreenSaver");

        // Enter "20" as discount percentage.
        WebElement discountBox = driver.findElement(By.xpath("(//div/div/input)[2]"));
        discountBox.sendKeys("20");

        // Click on the "Calculate" button.
        WebElement calculateButton = driver.findElement(By.xpath("//button[@type='submit']"));
        calculateButton.click();

        // Verify the invalid Quantity error message is displayed.
        WebElement errorMessageElement = driver.findElement(By.xpath("(//span[@class='error text-danger'])[1]"));
        String errorMessage = errorMessageElement.getText();
        assertEquals("Field 'Quantity' must be greater than zero.", errorMessage, "Error message does not appear");
    }

}

