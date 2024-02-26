package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//getcartproductslist
	@FindBy(css="[class='cartSection'] h3")
	List<WebElement> productTitles;
	@FindBy(css=".totalRow button")
	 WebElement checkoutEle;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean flag=productTitles.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return flag;
		
	}
	public CheckoutPage GoToCheckout()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",checkoutEle ); 
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
		
	}
	
}
