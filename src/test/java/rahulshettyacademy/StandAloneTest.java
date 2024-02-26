package rahulshettyacademy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("automate111@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Akhila@1997");
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter
		(s->s.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(productName)).
		findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));
		List<WebElement> cartProducts=driver.findElements(By.cssSelector("[class='cartSection'] h3"));

		
		Boolean flag=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(flag);
		Actions a=new Actions(driver);
		System.out.println(driver.findElement(By.cssSelector("[class='totalRow'] button")).getText());
		//Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("window.scrollBy(0,500)");
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("[class='totalRow'] button")) ); 
		//driver.findElement(By.cssSelector("[class='totalRow'] button")).click();
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='totalRow'] button"))).click();
		//a.moveToElement(driver.findElement(By.cssSelector("[class='totalRow'] button"))).click().build().perform();
		//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[class='totalRow'] button")))).click();
	
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='totalRow'] button"))).click();
		//driver.findElement(By.cssSelector("[class='totalRow'] button")).click();
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		js.executeScript("window.scrollBy(0,500)");
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector(".action__submit")) ); 
		//driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//
	}

}
