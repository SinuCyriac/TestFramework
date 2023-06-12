package testFramework.PageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testFramework.AbstractComponents.AbstractComponent;

public class MyOrders extends AbstractComponent{

	WebDriver driver;
	
	
	public MyOrders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".table-bordered .ng-star-inserted th")
	List<WebElement> orderHis;

	public boolean verifyOrder(ArrayList<String> orderIds) {
		Boolean check=false;
		for (WebElement id : orderHis) {
			String oId = id.getText();

			if (orderIds.contains(oId)) {
//				System.out.println("order found");
				check= true;
			}

		}
		return check;
	}

}
