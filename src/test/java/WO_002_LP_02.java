import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 *1-) Open the URL.
 *2-) Click "WebOrder" button on top bar.
 *3-) Enter invalid username "InvalidUserName" and/or password "InvalidPassword".
 *4-) Click on the "Login" button
 *5-) Verify that the appropriate error message is displayed.
 */



    public class WO_002_LP_02 extends Hooks {

        @Test
        void testLoginFailure() {
            // Click "WebOrder" button on top bar.
            WebElement webOrderTab = driver.findElement(By.xpath("(//a[contains(text(), 'Weborder')])[1]"));
            webOrderTab.click();

            // Enter invalid username "InvalidUserName" and/or password "InvalidPassword".
            WebElement usernameBox = driver.findElement(By.cssSelector(".mb-4 .form-control"));
            WebElement passwordBox = driver.findElement(By.cssSelector("input[type='password']"));

            usernameBox.sendKeys("InvalidUserName");
            passwordBox.sendKeys("InvalidPassword");

            // Click on the "Login" button
            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Verify that the appropriate error messages is displayed.
            List<String> expectedList = Arrays.asList("Invalid username", "Invalid password");
            List<String> actualList = new ArrayList<>();
            List<WebElement> alerts = driver.findElements(By.cssSelector("[role='alert']"));
            for (WebElement element : alerts) {
                actualList.add(element.getText());
            }
            assertEquals(expectedList, actualList);
        }

    }

