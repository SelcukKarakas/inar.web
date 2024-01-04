
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1-) Open the URL. 2-) Click "WebOrder" button on top bar. 3-) Enter "Inar" as username
 * and "Academy" password. 4-) Click on the "Login" button. 5-) Verify that the user is
 * successfully logged in.
 */

public class WO_001_LP_01 extends Hooks {

	@Test
	void testLoginFunctionality() {
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

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#welcome-heading")));

		// Verify that the user is successfully logged in.
		String expected = "Welcome, Inar!";
		WebElement heading = driver.findElement(By.cssSelector("#welcome-heading"));
		String headingText = heading.getText();
		assertEquals(expected, headingText);
	}

}
