package testFramework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(className="login-btn")
	WebElement loginBtn;
	
	public void launchUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public Products userLogin(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginBtn.click();
		Products prod=new Products(driver);
		return prod;
	}

}
