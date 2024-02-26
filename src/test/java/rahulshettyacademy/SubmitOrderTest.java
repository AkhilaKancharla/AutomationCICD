package rahulshettyacademy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

//import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
        ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
	    Boolean flag=cartPage.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(flag);
	    CheckoutPage checkoutPage=cartPage.GoToCheckout();
	    checkoutPage.selectCountry("India");
	    ConfirmationPage confirmationPage=checkoutPage.submitOrder();
	    String message=confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	  
	}
	//To Verify ZARA COAt 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryFirst()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("automate111@gmail.com", "Akhila@1997");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//Hashmap
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "automate111@gmail.com");
//		map.put("password", "Akhila@1997");
//		map.put("product", "ZARA COAT 3");
		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "automate222@gmail.com");
//		map1.put("password", "Akhila@1997");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
	}
	
	//Method 1
	//@DataProvider
	//public Object[][] getData()
	//{
	//	return new Object [][] {{"automate111@gmail.com", "Akhila@1997","ZARA COAT 3"},{"automate222@gmail.com", "Akhila@1997","ADIDAS ORIGINAL"}};
		
	//}

}
