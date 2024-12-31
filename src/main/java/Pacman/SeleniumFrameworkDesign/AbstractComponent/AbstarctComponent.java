package Pacman.SeleniumFrameworkDesign.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pacman.SeleniumFrameworkDesign.pageObjects.CartPage;
import Pacman.SeleniumFrameworkDesign.pageObjects.OrderPage;

public class AbstarctComponent {
	WebDriver driver;
	public AbstarctComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderBtn;
	public void waitForElemetToAppear(By findBy)
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

}
	public void waitForWebElemetToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementtoDisappear(WebElement ele)
	{	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage clickCartButton()
	{
		cartBtn.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage clickOrderButton()
	{
		orderBtn.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
}
