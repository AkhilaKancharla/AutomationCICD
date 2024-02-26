package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	 WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")
	 WebElement selectCountry;
	@FindBy(css=".action__submit")
	 private WebElement submit;
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		WaitForElementToAppear(results);
		selectCountry.click();
	}
	public  ConfirmationPage submitOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",submit ); 
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}
	

}
