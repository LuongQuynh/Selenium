package Testmaster.Selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginMaiDen {
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
//
		driver.get("http://maiden.vn");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("a.close-tannm-modal>b")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.header-user")).click();
		Thread.sleep(1000);
	}

	@After
	public void CloseTC() {
		this.driver.quit();
	}

	@Test
	public void LoginBankValue() throws InterruptedException {

		WebElement EmailField = this.driver.findElement(By.cssSelector("input#inputEmail"));
		EmailField.click();

		WebElement PsdField = this.driver.findElement(By.cssSelector("input#inputPassword"));
		PsdField.click();
		WebElement btnLogin = this.driver.findElement(By.cssSelector("button.btn-signin"));
		btnLogin.click();
		Thread.sleep(1000);
		String TextToolTip = EmailField.getAttribute("data-original-title");
		System.out.println(TextToolTip);
		Assert.assertEquals(TextToolTip, "Tên đăng nhâp/Email không thể để trống.");

	}

	@Test
	public void LoginUserNotExit() throws InterruptedException {
		WebElement EmailField = this.driver.findElement(By.cssSelector("input#inputEmail"));
		EmailField.sendKeys("abcd");

		WebElement PsdField = this.driver.findElement(By.cssSelector("input#inputPassword"));
		PsdField.sendKeys("12345");
		Thread.sleep(3000);
		WebElement btnLogin = this.driver.findElement(By.cssSelector("button.btn-signin"));
		btnLogin.click();
		Thread.sleep(3000);
		WebElement TextPopUP = this.driver.findElement(By.cssSelector("span.notify-text"));
		System.out.println(TextPopUP.getText());
		Thread.sleep(1000);
		Assert.assertEquals(TextPopUP.getText(), "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng kiểm tra lại");
	}

	@Test
	public void LoginWrongPassWord() throws InterruptedException {
		WebElement EmailField = this.driver.findElement(By.cssSelector("input#inputEmail"));
		EmailField.sendKeys("Luong Quynh");

		WebElement PsdField = this.driver.findElement(By.cssSelector("input#inputPassword"));
		PsdField.sendKeys("12345");
		Thread.sleep(3000);
		WebElement btnLogin = this.driver.findElement(By.cssSelector("button.btn-signin"));
		btnLogin.click();
		Thread.sleep(3000);
		WebElement TextPopUP = this.driver.findElement(By.cssSelector("span.notify-text"));
		System.out.println(TextPopUP.getText());
		Thread.sleep(1000);
		Assert.assertEquals(TextPopUP.getText(), "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng kiểm tra lại");
	}

	@Test
	public void LoginTruePassWord() throws InterruptedException {
		WebElement EmailField = this.driver.findElement(By.cssSelector("input#inputEmail"));
		EmailField.sendKeys("Luong Quynh");

		WebElement PsdField = this.driver.findElement(By.cssSelector("input#inputPassword"));
		PsdField.sendKeys("Quynh12345");
		Thread.sleep(3000);
		WebElement btnLogin = this.driver.findElement(By.cssSelector("button.btn-signin"));
		btnLogin.click();
		Thread.sleep(3000);
		WebElement TextPopUP = this.driver.findElement(By.cssSelector("span.notify-text"));
		System.out.println(TextPopUP.getText());
		Thread.sleep(1000);
		Assert.assertEquals(TextPopUP.getText(), "Tên đăng nhập hoặc mật khẩu không hợp lệ. Vui lòng kiểm tra lại");
	}

}
