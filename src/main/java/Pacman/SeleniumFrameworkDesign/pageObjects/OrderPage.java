package Pacman.SeleniumFrameworkDesign.pageObjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class OrderPage extends AbstarctComponent {
	WebDriver driver;
	
public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}



@FindBy(xpath="//tbody/tr/td[2]")
List<WebElement>productNames;

@FindBy(xpath="//li[@class='totalRow']/button")
WebElement checkoutBtn;

public Boolean verifyOrdertDisplay(String productName)
{
	Boolean testResult=productNames.stream().anyMatch(cartProduct->cartProduct.getText().contains(productName));
	System.out.println(productNames.size());
	return testResult;
}

}





