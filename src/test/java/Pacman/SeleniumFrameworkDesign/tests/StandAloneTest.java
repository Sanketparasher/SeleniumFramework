package Pacman.SeleniumFrameworkDesign.tests;

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

import Pacman.SeleniumFrameworkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChromeDriver driver = new ChromeDriver();
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("testuserpac@gmail.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Pacman@101");
		driver.findElement(By.id("login")).click();
		// invoking WebDriverWait class
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3')]")));
		List<WebElement>products=driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
		WebElement prod= products.stream().filter(product->product.findElement(By.xpath(".//div[@class='card-body']//b"))
				.getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();
		
		
		//Waiting until "Product added to cart container is not visible"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//Waiting until the loading animation is not further visible
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		//After the animation are no further visible we will click on Cart
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		//Finding the number of element present in the cart and storing it as a list
		List<WebElement>cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));		
		System.out.println(cartProducts.size());
		//On the cart page verifying if the product selected is present
		Boolean testResult=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().contains(productName));
		Assert.assertTrue(testResult);
		//Click on checkout
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		//Fill in the fields in payment page
		//Fill in the cvv
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("123");
		//Fill in the name on the card
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Sanket Parasher");
		//provide the country Ind in autosuggestive dropdown
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("list-group")));
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		String ConfirmMsg=driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}
	

}
