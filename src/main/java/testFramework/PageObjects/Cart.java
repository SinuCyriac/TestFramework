package testFramework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testFramework.AbstractComponents.AbstractComponent;

public class Cart extends AbstractComponent {

	WebDriver driver;

	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;

	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;

	public boolean verifyCart(String itemsToBuy) {
		Boolean match = false;
		for (WebElement item : cartItems) {
			String cartItem = item.getText();

			if (cartItem.equalsIgnoreCase(itemsToBuy)) {

				match = true;
				break;
			}
		}
		return match;
	}

	public PlaceOrder checkOutOrder() {
		checkOutBtn.click();

		PlaceOrder place = new PlaceOrder(driver);
		return place;
	}

}
