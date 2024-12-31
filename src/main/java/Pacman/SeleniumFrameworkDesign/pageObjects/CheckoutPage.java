package Pacman.SeleniumFrameworkDesign.pageObjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class CheckoutPage extends AbstarctComponent {
	WebDriver driver;
	
public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}



@FindBy(xpath="(//input[@type='text'])[2]")
WebElement cvvBox;

@FindBy(xpath="(//input[@type='text'])[3]")
WebElement nameOnCard;

@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement selectCountry;

@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
WebElement countrySelected;

@FindBy(xpath="//a[normalize-space()='Place Order']")
WebElement placeOrderBtn;

By countrySelection=By.className("list-group");

public void enterCardDetails(String cvv, String name, String countrySelection)
{
	cvvBox.sendKeys(cvv);
	nameOnCard.sendKeys(name);
	selectCountry.sendKeys(countrySelection);
}

public ConfirmationPage selectCountryDropdown()
{
	waitForElemetToAppear(countrySelection);
	countrySelected.click();
	placeOrderBtn.click();
	ConfirmationPage confirmationPage=new ConfirmationPage(driver);
	return confirmationPage;
	
}



}