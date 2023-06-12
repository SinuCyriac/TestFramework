package testFramework;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import testFramework.PageObjects.Cart;
import testFramework.PageObjects.MyOrders;
import testFramework.PageObjects.OrderConfirm;
import testFramework.PageObjects.PlaceOrder;
import testFramework.PageObjects.Products;
import testFramework.TestComponents.BaseTest;

public class StandaloneTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		// Driver initialization

		
		// login page
		String username = "sinucyriac@test.com";
		String password = "Test@1234";

		Products prod = lan.userLogin(username, password);

		// product catalog page
		String itemsToBuy = "adidas original";

		prod.addProdsToCart(itemsToBuy);

		// Click on cart
		Cart cart = prod.clickOnCart();

		// verify cart

		Boolean match = cart.verifyCart(itemsToBuy);
		Assert.assertTrue(match);
		// checkout button
		PlaceOrder place = cart.checkOutOrder();

		// checkout page
		place.addDetails();
		// place order button
		OrderConfirm confirm = place.placeOrder();

		// order detail page
		String ordMsg = confirm.confirmOrder();
		Assert.assertEquals(ordMsg, "THANKYOU FOR THE ORDER.");
		ArrayList<String> orderIds = confirm.storeOrderId();
		MyOrders orderHis = confirm.clickOnOrders();

		// order history page
		Boolean check = orderHis.verifyOrder(orderIds);
		Assert.assertTrue(check);

		// tearDown
	}

}
