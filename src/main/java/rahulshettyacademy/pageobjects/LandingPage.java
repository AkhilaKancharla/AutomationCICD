package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;

	public LandingPage (WebDriver driver) { 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//pageFactory
	//Another way of locating elements
	
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;
	

	@FindBy(css="input[value='Login']")
	WebElement login;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	//div[@class='ng-tns-c4-10 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	
	public ProductCatalogue loginApplication(String email,String pwd)
	{
		username.sendKeys(email);
		password.sendKeys(pwd);
		login.click();
		ProductCatalogue   productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMessage() {
		WaitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
