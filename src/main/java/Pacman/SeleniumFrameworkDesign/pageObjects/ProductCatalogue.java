package Pacman.SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Pacman.SeleniumFrameworkDesign.AbstractComponent.AbstarctComponent;

public class ProductCatalogue extends AbstarctComponent
{
	WebDriver driver;
	
public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super (driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}



@FindBy(xpath="//div[contains(@class, 'mb-3')]")
List<WebElement> products;
@FindBy(className="ng-animating")
WebElement spinner;

By productBy=By.xpath("//div[contains(@class, 'mb-3')]");
By addToCart=By.xpath(".//div[@class='card-body']/button[2]");
By toastContainer=By.id("toast-container");

public List<WebElement> getProductList()
{
	waitForElemetToAppear(productBy);
	return products;
}

public WebElement getProductByName(String productName)
{
	WebElement prod= getProductList().stream().filter(product->product.findElement(By.xpath(".//div[@class='card-body']//b"))
			.getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
	return prod;
}
public void addProuctToCart(String productName)
{	WebElement prod=getProductByName(productName);
	prod.findElement(addToCart).click();
	waitForElemetToAppear(toastContainer);
	waitForElementtoDisappear(spinner);
}








}