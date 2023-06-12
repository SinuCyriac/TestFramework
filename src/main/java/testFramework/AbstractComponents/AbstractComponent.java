package testFramework.AbstractComponents;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.PageObjects.Cart;
import testFramework.PageObjects.MyOrders;

public class AbstractComponent{
	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(className = "fa-shopping-cart")
	WebElement cartBtn;

	@FindBy(className="fa-handshake-o")
	WebElement myOrdersBtn;
	
	@FindBy(css=".table-bordered .ng-star-inserted th")
	List<WebElement> orderHis;
	
	public void waitForElementToAppear(By loc) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

	public Cart clickOnCart() throws InterruptedException {
		Thread.sleep(3000L);
		try {
			cartBtn.click();
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();

			System.out.println("inside catch");

		}
		Cart cart=new Cart(driver);
		return cart;
	}
	
	public MyOrders clickOnOrders() {
		myOrdersBtn.click();
		MyOrders orderHis=new MyOrders(driver);
		return orderHis;

	}

}
