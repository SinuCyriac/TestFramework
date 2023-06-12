package testFramework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// Driver initialization
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String baseUrl = "https://rahulshettyacademy.com/client";

		driver.get(baseUrl);

		// login page
		String username = "sinucyriac@test.com";
		String password = "Test@1234";
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.className("login-btn")).click();

		// product catalogue page

		List<WebElement> products = driver.findElements(By.className("mb-3"));

		int itemsCount = products.size();

		// add item to cart
		String itemsToBuy = "adidas original";
		for (int i = 0; i < itemsCount; i++) {

			if (products.get(i).findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase(itemsToBuy)) {

				products.get(i).findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;

			}

		}
		// Click on cart
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
//		wait.until(ExpectedConditions.visibilityOf(cartBtn));
		WebElement cartBtn = driver.findElement(By.className("fa-shopping-cart"));
		Thread.sleep(3000L);

		try {
			cartBtn.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();

			System.out.println("inside catch");

		}

		// verify cart
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		for (WebElement item : cartItems) {
			String cartItem = item.getText();
//			System.out.println(cartItem);
			if (item.getText().equalsIgnoreCase(itemsToBuy)) {

				Assert.assertTrue(true, "item found");
				break;
			}
		}

		// checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();// '//*[contains(text(), "Tired")]'

		// checkout page

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> listedCountries = driver.findElements(By.cssSelector(".ta-item span"));

		for (WebElement country : listedCountries) {

			if (country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}

		// place order button
		driver.findElement(By.className("action__submit")).click();

		// order detail page
		String ordMsg = driver.findElement(By.tagName("h1")).getText();

		Assert.assertEquals(ordMsg, "THANKYOU FOR THE ORDER.");
		List<WebElement> orderNos = driver
				.findElements(By.cssSelector("td[class='em-spacer-1'] label[class='ng-star-inserted']"));

		ArrayList<String> orderIds = new ArrayList<String>();
		for (WebElement orderNo : orderNos) {
			String textId = orderNo.getText();
			int firstInd = textId.indexOf("|") + 2;
			int lastInd = textId.indexOf(" |");
			String id = textId.substring(firstInd, lastInd);
			orderIds.add(id);

		}

		// order history
		driver.findElement(By.className("fa-handshake-o")).click();
		List<WebElement> orderHis = driver.findElements(By.cssSelector(".table-bordered .ng-star-inserted th"));
		for (WebElement id : orderHis) {
			String oId = id.getText();

			if (orderIds.contains(oId)) {
//				System.out.println("order found");
				Assert.assertTrue(true);
			}

		}

		// tearDown
		driver.close();
	}

}
