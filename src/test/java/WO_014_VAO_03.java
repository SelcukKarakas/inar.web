import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * 1-) Open the URL 2-) Click "WebOrder" button on top bar. 3-) Enter valid username
 * "Inar" and password "Academy". 4-) Navigate to the view all order page. 5-) Click "Add
 * More Data" "8" times. 6-) Click 1st, 3rd and 5th orders checkbox's.
 * 7-) Click "DeleteAll" button.
 * 8-) Verify the orders are deleted.
 */

public class WO_014_VAO_03 extends Hooks {

    @Test
    void testAllFunctionalityInViewAllOrderPage() throws InterruptedException {
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

        // Click "Add More Data" "8" times.
        WebElement addMoreDataButton = driver.findElement(By.xpath("//button[text()='Add More Data']"));

        for (int i = 0; i < 8; i++) {
            addMoreDataButton.click();
        }

        //Click 1st, 3rd and 5th orders checkbox's.
        List<WebElement> allOrders = driver.findElements(By.xpath("//tbody/tr"));
        List<String> linksOfDeletedOrders = new ArrayList<>();
        for (int i = 0; i <= 4; i += 2) {
            allOrders.get(i).findElement(By.tagName("input")).click();
            linksOfDeletedOrders.add(allOrders.get(i).findElement(By.tagName("a")).getAttribute("href"));
        }

        System.out.println(linksOfDeletedOrders);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0, 1000)");
        Thread.sleep(1000);

        //Click "DeleteAll" button.
        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Delete']")));
        js.executeScript("window.scroll(0, -1000)");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Check All']")));

        //Verify the orders are deleted.
        allOrders = driver.findElements(By.xpath("//tbody/tr"));
        ArrayList<String> linksOfRemainingOrders = new ArrayList<>();

        for (WebElement allOrder : allOrders) {
            linksOfRemainingOrders.add(allOrder.findElement(By.tagName("a")).getAttribute("href"));
        }

        for (String s : linksOfDeletedOrders) {
            assertFalse(linksOfRemainingOrders.contains(s));
        }

    }

}