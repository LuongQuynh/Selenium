package Testmaster.Selenium;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/*Thực hiện các testcase sau bằng Selenium
 * 1. Thực hiện tìm kiếm một số sản phẩm trong hệ thống maiden.vn
 * với từ khóa "giấy"
 * 2. Thực hiện mua một sản phẩm bất kỳ trên kết quả tìm kiếm,
 * chắc chắn rằng AddToCart popup sẽ hiện lên với thông tin sản
 * phẩm đã chọn mua.
 * 3. Tiến hành mua hàng và xác nhận sản phẩm đã mua với số lượng
 * tương ứng tồn tại trong giỏ hàng
 * 4. Trong trường hợp người dùng mua 1 sản phẩm 02 lần thì 
 * chắc chắn rằng số lượng của sản phẩm này trong giỏ hàng sẽ được
 * cộng dồn
 * 
 * 
 * 
 * 
 */

public class AddToCartTest {
	WebDriver driver;

	@Before
	public void InitTestCase() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.get("http://maiden.vn");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("a.close-tannm-modal>b")).click();
		Thread.sleep(1000);
		WebElement txtSearch = this.driver.findElement(By.cssSelector("input#txtSearchInput"));
		txtSearch.sendKeys("Giấy");
		Thread.sleep(1000);
		txtSearch.sendKeys(Keys.ENTER);

	}

	@After
	public void closeTestCase() throws InterruptedException {

		Thread.sleep(10000);
		this.driver.close();

	}

	@Test
	public void SearchSheet() throws InterruptedException {
		List<WebElement> LstItem = this.driver.findElements(By.cssSelector("li.product-item"));
		Assert.assertEquals(LstItem.size(), 20);

	}

	@Test
	public void randomProduct() {
		List<WebElement> LstItem = this.driver.findElements(By.cssSelector("li.product-item"));
		Random rand = new Random();
		int randomProduct = rand.nextInt(LstItem.size());
		LstItem.get(randomProduct).click();
		WebElement txtHead = this.driver.findElement(By.cssSelector("h1.item-name"));
		System.out.println(txtHead.getText());

	}

	@Test
	public void buyProduct() throws InterruptedException {
		List<WebElement> LstItem = this.driver.findElements(By.cssSelector("li.product-item"));
		Random rand = new Random();
		int randomProduct = rand.nextInt(LstItem.size());
		LstItem.get(randomProduct).click();
		WebElement txtHead = this.driver.findElement(By.cssSelector("h1.item-name"));
		System.out.println(txtHead.getText());
		WebElement items = this.driver.findElement(By.cssSelector("input#txtAmount"));
		System.out.println("so luong dat mua: " + items.getAttribute("value"));
		Thread.sleep(1000);
		this.driver.findElement(By.cssSelector("button.mybtn-purchase")).click();
		Thread.sleep(2000);
		this.driver.findElement(By.cssSelector("a.mybtn-purchase")).click();
		Thread.sleep(2000);
		WebElement product = this.driver.findElement(By.cssSelector("input.cart-product-item-cell-qty-select"));

		System.out.println("so luong san pham thanh toan: " + product.getAttribute("value"));
		Assert.assertEquals(1, Integer.parseInt(product.getAttribute("value")));

	}

	@Test
	public void doubleBuy() throws InterruptedException {
		List<WebElement> LstItem = this.driver.findElements(By.cssSelector("li.product-item"));
		Random rand = new Random();
		int randomProduct = rand.nextInt(LstItem.size());
		LstItem.get(randomProduct).click();
		WebElement txtHead = this.driver.findElement(By.cssSelector("h1.item-name"));
		System.out.println(txtHead.getText());
		WebElement items = this.driver.findElement(By.cssSelector("input#txtAmount"));
		System.out.println("so luong dat mua: " + items.getAttribute("value"));
		Thread.sleep(1000);
		this.driver.findElement(By.cssSelector("button.mybtn-purchase")).click();
		Thread.sleep(2000);
		this.driver.findElement(By.cssSelector("a.mybtn-purchase")).click();
		Thread.sleep(2000);
		WebElement product = this.driver.findElement(By.cssSelector("input.cart-product-item-cell-qty-select"));

		System.out.println("so luong san pham thanh toan: " + product.getAttribute("value"));
		this.driver.navigate().back();
		Thread.sleep(3000);
		this.driver.findElement(By.cssSelector("button.mybtn-purchase")).click();
		Thread.sleep(2000);
		this.driver.findElement(By.cssSelector("a.mybtn-purchase")).click();
		Thread.sleep(2000);
		WebElement productNext = this.driver.findElement(By.cssSelector("input.cart-product-item-cell-qty-select"));

		System.out.println("so luong san pham mua tiep: " + productNext.getAttribute("value"));
		Assert.assertEquals(2, Integer.parseInt( productNext.getAttribute("value")));

	}

}
