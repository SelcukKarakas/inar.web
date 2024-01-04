import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1)Open the URL 2)Click "WebOrder" button on top bar. 3)Enter valid username "Inar" and
 * password "Academy". 4)Click "Logout" button. 5)Verify logout successfully.
 */

public class WO_003_LP_03 extends Hooks {

    @Test
    public void testLogoutFunctionality() {
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

        // Click "Logout" button.
        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        logoutButton.click();

        // Verify logout successfully.
        WebElement titleElement = driver.findElement(By.xpath("//h2[contains(text(), 'Login')]"));
        String title = titleElement.getText();
        assertEquals("Login", title, "We have to see 'Login' title after logout process");
    }

}

