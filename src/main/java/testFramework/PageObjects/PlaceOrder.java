package testFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testFramework.AbstractComponents.AbstractComponent;

public class PlaceOrder extends AbstractComponent{

	WebDriver driver;
	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement count;
	@FindBy(className="action__submit")
	WebElement placeOrder;

	@FindBy(css=".ta-item span")
	List<WebElement> listedCountries;
	
	public void addDetails() {
		count.sendKeys("Ind");

		for (WebElement country : listedCountries) {

			if (country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}
		
	}

	public OrderConfirm placeOrder() {
		placeOrder.click();
		
		OrderConfirm confirm=new OrderConfirm(driver);
		return confirm;
	}

}
