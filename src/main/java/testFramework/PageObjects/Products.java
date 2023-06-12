package testFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testFramework.AbstractComponents.AbstractComponent;

public class Products extends AbstractComponent {
	WebDriver driver;

	public Products(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "mb-3")
	List<WebElement> products;
	By prodLoc = By.className("mb-3");

	public void addProdsToCart(String itemsToBuy) {

		waitForElementToAppear(prodLoc);

		int itemsCount = products.size();

		for (int i = 0; i < itemsCount; i++) {

			if (products.get(i).findElement(By.cssSelector(".card-body h5")).getText().equalsIgnoreCase(itemsToBuy)) {

				products.get(i).findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;

			}

		}
		
	}

}
