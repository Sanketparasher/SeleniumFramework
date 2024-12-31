package Pacman.SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;
import Pacman.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Pacman.SeleniumFrameworkDesign.pageObjects.CartPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.CheckoutPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.LandingPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.OrderPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData", groups = {"purchaseOrder"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{

		ProductCatalogue productCatelogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products=productCatelogue.getProductList();
		productCatelogue.addProuctToCart(input.get("product"));
		CartPage cartPage=productCatelogue.clickCartButton();
		Boolean testResult=cartPage.verifyProductDisplay(input.get("product"));		
		Assert.assertTrue(testResult);
		CheckoutPage checkoutPage=cartPage.clickOnCheckout();
		checkoutPage.enterCardDetails("123", "Sanket", "Ind");
		checkoutPage.selectCountryDropdown();
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		String ConfirmMsg= confirmationPage.confirmationMsg();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHIstoryTest()
	{
		ProductCatalogue productCatelogue=landingPage.loginApplication("testuserpac@gmail.com", "Pacman@101");
		OrderPage orderPage=productCatelogue.clickOrderButton();
		Assert.assertTrue(orderPage.verifyOrdertDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("email", "testuserpac@gmail.com");
		map.put("password", "Pacman@101");
		map.put("productName", "IPHONE 13 PRO");
		
		HashMap<String, String> map1=new HashMap<String, String>();
		map1.put("email", "testuserpac@gmail.com");
		map1.put("password", "Pacman@101");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object [][] {{map},{map1}};
	}

}
