package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue; 

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Website")
	public void I_landed_on_Ecommerce_Website () throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Loggedin with username (.+) and password (.+)$")
	public void Loggedin_with_username_and_password(String username,String password)
	{
	  productCatalogue=landingPage.loginApplication(username,password);
		
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName)
	{
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String message=confirmationPage.getConfirmationMessage();
	    Assert.assertTrue(message.equalsIgnoreCase(string));
	    driver.close();
	
	}
	@When("Checkout and submit the order")
	public void checkout_and_submit_the_order() 
	{
        CartPage cartPage=productCatalogue.goToCartPage();
		
	    //Boolean flag=cartPage.VerifyProductDisplay(productName);
	   // Assert.assertTrue(flag);
	    CheckoutPage checkoutPage=cartPage.GoToCheckout();
	    checkoutPage.selectCountry("India");
	    confirmationPage=checkoutPage.submitOrder();
	}
	

}
