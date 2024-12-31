package Pacman.SeleniumFrameworkDesign.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pacman.SeleniumFrameworkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	//public ChromeDriver driver;
	public LandingPage landingPage;
	 
	public WebDriver initializeDriver() throws IOException
	{	
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")
				+"//src//main//java//Pacman//SeleniumFrameworkDesign//Resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			/*WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();*/
			ChromeOptions chromeOptions = new ChromeOptions(); //*************If webdriver is not wrking use this
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			/*System.setProperty("webdriver.chrome.driver", "D:/Ready To Rumble/Eclipse/Drivers/chromedriver.exe");
			WebDriver driver= new ChromeDriver();*/
		
	    }
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "D:/Ready To Rumble/Eclipse/Drivers/msedgedriver.exe");
			 driver=new EdgeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:/Ready To Rumble/Eclipse/Drivers/geckodriver.exe");
			 driver= new FirefoxDriver();
			
		}
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		return driver;
		

}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver=(ChromeDriver) initializeDriver();
		landingPage =new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void closetesting()
	{
		driver.quit();
	}
}
