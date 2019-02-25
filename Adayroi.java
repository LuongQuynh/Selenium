package Testmaster.Selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Adayroi {

	@Test
	public void testRegister() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://www.adayroi.com/");

		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		WebElement loginBtn = driver.findElement(By.cssSelector("div>span.header-username"));
		Actions actions = new Actions(driver);
		actions.moveToElement(loginBtn).perform();

		WebElement registerBtn = driver
				.findElement(By.xpath("//span[@class='c-button-text-style c-button__text-style--default']"));
		registerBtn.click();
		WebElement nameField = driver.findElement(By.cssSelector("input[name='fullname']"));
		nameField.sendKeys("Lương Thị Quỳnh");
		WebElement emailField = driver.findElement(By.cssSelector("input[name='email']"));
		emailField.sendKeys("quynhlt2511@gmail.com");
		WebElement pswField = driver.findElement(By.cssSelector("input[name='password']"));
		pswField.sendKeys("Password12345");
//		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		WebElement confirmPswField = driver.findElement(By.cssSelector("input[name='confirmPassword']"));
		confirmPswField.sendKeys("Password12345");
		WebElement checkBox = driver
				.findElement(By.cssSelector("span[role='checkbox']>div.recaptcha-checkbox-checkmark"));

		checkBox.click();
		WebElement button = driver.findElement(By.cssSelector("button[type='submit']"));
		button.click();


		driver.close();

	}

}
