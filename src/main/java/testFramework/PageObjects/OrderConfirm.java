package testFramework.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testFramework.AbstractComponents.AbstractComponent;

public class OrderConfirm extends AbstractComponent {

	WebDriver driver;

	public OrderConfirm(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "h1")
	WebElement msg;

	@FindBy(css = "td[class='em-spacer-1'] label[class='ng-star-inserted']")
	List<WebElement> orderNos;

	public String confirmOrder() {
		String ordMsg = msg.getText();
		return ordMsg;

	}

	public ArrayList<String> storeOrderId() {
		ArrayList<String> orderIds = new ArrayList<String>();
		for (WebElement orderNo : orderNos) {
			String textId = orderNo.getText();
			int firstInd = textId.indexOf("|") + 2;
			int lastInd = textId.indexOf(" |");
			String id = textId.substring(firstInd, lastInd);
			orderIds.add(id);

		}
		return orderIds;

	}

}
