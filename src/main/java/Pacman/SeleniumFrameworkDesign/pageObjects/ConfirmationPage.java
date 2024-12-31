package Pacman.SeleniumFrameworkDesign.pageObjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent {
	WebDriver driver;
	
public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}





@FindBy(className="hero-primary")
WebElement checkoutBtn;


public String confirmationMsg()
{
	String ConfirmMsg=checkoutBtn.getText();
	
	return ConfirmMsg;
}





}