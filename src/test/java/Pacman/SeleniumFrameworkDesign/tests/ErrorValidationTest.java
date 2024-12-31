package Pacman.SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;
import Pacman.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Pacman.SeleniumFrameworkDesign.pageObjects.CartPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.CheckoutPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.LandingPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ADIDAS ORIGINAL";
		landingPage.loginApplication("testuserpa@gmail.com", "Pacma@101");
		Assert.assertEquals("Incorrect email or password.",landingPage.errorMessage() );
	}
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatelogue=landingPage.loginApplication("testuserpac@gmail.com", "Pacman@101");
		List<WebElement>products=productCatelogue.getProductList();
		productCatelogue.addProuctToCart(productName);
		CartPage cartPage=productCatelogue.clickCartButton();
		Boolean testResult=cartPage.verifyProductDisplay("ADIDAS ORIGINAL");		
		Assert.assertTrue(testResult);
		
	}

}
