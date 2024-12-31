package Pacman.SeleniumFrameworkDesign.pageObjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class CartPage extends AbstarctComponent {
	WebDriver driver;
	
public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}



@FindBy(xpath="//div[@class='cartSection']/h3")
List<WebElement>cartProducts;

@FindBy(xpath="//li[@class='totalRow']/button")
WebElement checkoutBtn;

public Boolean verifyProductDisplay(String productName)
{
	Boolean testResult=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().contains(productName));
	System.out.println(cartProducts.size());
	return testResult;
}
public CheckoutPage clickOnCheckout()
{
	checkoutBtn.click();
	CheckoutPage checkoutPage=new CheckoutPage(driver);
	return checkoutPage;
}





}