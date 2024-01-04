import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1-) Open the URL 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the view all order page. 5-) Click "Add
 * More Data" "4" times. 6-) Click "Check All" button. 7-) Verify all orders selected.
 */

public class WO_012_VAO_01 extends Hooks {

    @Test
    void testAllFunctionalityInViewAllOrderPage() {
        // Click "WebOrder" button on top bar.
        WebElement webOrderTab = driver.findElement(By.cssSelector("a[href='/weborder']"));
        webOrderTab.click();

        // Enter valid username "Inar" and password "Academy and click Login Button".
        WebElement usernameTextField = driver.findElement(By.id("login-username-input"));
        WebElement passwordTextField = driver.findElement(By.id("login-password-input"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameTextField.sendKeys("Inar");
        passwordTextField.sendKeys("Academy");
        loginButton.click();

        // Navigate to the view all order page.
        WebElement viewAllOrderTab = driver.findElement(By.xpath("//a[text()='View all orders']"));
        viewAllOrderTab.click();

        // Click "Add More Data" "4" times.
        WebElement addMoreDataButton = driver.findElement(By.xpath("//button[text()='Add More Data']"));

        for (int i = 0; i < 4; i++) {
            addMoreDataButton.click();
        }

        // Click "Check All" button.
        WebElement checkAllButton = driver.findElement(By.xpath("//button[text()='Check All']"));
        checkAllButton.click();

        // Verify all orders selected.
        List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//input[@class='form-check-input']"));

        for (WebElement element : allCheckBoxes) {
            assertTrue(element.isSelected());
        }
    }

}
