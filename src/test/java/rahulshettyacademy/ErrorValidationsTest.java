package rahulshettyacademy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";

	    ProductCatalogue productCatalogue=landingPage.loginApplication("automate111@gmail.com", "hkhila@1997");
	    Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); 
	  
	}
	@Test
	public void productErrorValidation()
	{
		String productName="ZARA COAT 3";
        ProductCatalogue productCatalogue=landingPage.loginApplication("automate222@gmail.com", "Akhila@1997");
		
	    productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}

}
