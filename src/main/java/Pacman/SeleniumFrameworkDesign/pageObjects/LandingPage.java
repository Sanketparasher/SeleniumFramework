package Pacman.SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class LandingPage extends AbstarctComponent {
	WebDriver driver;
	
public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}



@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement passwordElement;

@FindBy(id="login")
WebElement submit;
@FindBy(css="[class*='flyInOut']")
WebElement errorMessage;

public ProductCatalogue loginApplication(String email, String password )
{
	userEmail.sendKeys(email);
	passwordElement.sendKeys(password);
	submit.click();
	ProductCatalogue productCatelogue=new ProductCatalogue(driver);
	return productCatelogue;
}
public String errorMessage()
{	waitForWebElemetToAppear(errorMessage);
	return errorMessage.getText();
}
public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client");
}





}