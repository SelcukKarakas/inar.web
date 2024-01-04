import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

	protected static WebDriver driver;

	protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeAll
	public static void setUp() {
		String browser = System.getProperty("browserType", "chrome");

		switch (browser) {
			case "firefox" -> {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--width-1920");
				firefoxOptions.addArguments("--height-1080");
				driver = new FirefoxDriver(firefoxOptions);
			}
			case "edge" -> {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--start-maximized");
				edgeOptions.addArguments("--ignore-certificate-error");
				driver = new EdgeDriver(edgeOptions);
			}

			default -> {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--ignore-certificate-error");
				driver = new ChromeDriver(chromeOptions);
			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://InarAcademy:Fk160621.@test.inar-academy.com");

		if (browser.equalsIgnoreCase("firefox")) {
			driver.navigate().refresh();
		}

	}


	@AfterAll public static void tearDown() {
		if (driver != null)
			driver.quit();
	}

}