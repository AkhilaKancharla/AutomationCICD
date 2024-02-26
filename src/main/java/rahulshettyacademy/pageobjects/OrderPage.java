package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//getorderproductslist
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	 WebElement checkoutEle;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean flag=productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return flag;
		
	}
	
	
}
