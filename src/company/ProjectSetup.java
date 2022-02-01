package company;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;



public class ProjectSetup{
	public WebDriver driver;
	
	//Setup Browser
	@BeforeClass
	public void Setup(){
		try {
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium Tools\\ChromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://192.168.1.199:2236");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Write Test case for login functionality.
	@Test(priority=0)
	public void Login(){
		try {
			String expectedTitle = "Acumen";
			String actualTitle = "";
			driver.findElement(By.id("userName")).sendKeys("superadmin");
			driver.findElement(By.id("userPassword")).sendKeys("password");
			driver.findElement(By.id("btnLogin")).click();
			actualTitle = driver.getTitle();
			if(actualTitle.equals(expectedTitle)){
				System.out.println("Login Successfully");
			}else{
				System.out.println("Login Failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Create Company
	@Test(priority=1, dependsOnMethods="Login", groups="Company")
	public void CreateCompany(){
		try {
			driver.findElement(By.xpath(".//*[@id='main-menu-navigation']/li/ul/li[1]/a")).click();
			driver.findElement(By.xpath(".//*[@id='main-menu-navigation']/li/ul/li/a")).click();
			driver.findElement(By.xpath(".//*[@id='main-menu-navigation']/li/ul/li/ul[1]/li/a")).click();
			driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div[2]/div[1]/div/div[2]/a")).click();
			driver.findElement(By.id("Entity_Name")).sendKeys("WEAVERS FURNISHING LTD");
			driver.findElement(By.id("EntityShortName")).sendKeys("WFL");
			driver.findElement(By.id("Email")).sendKeys("wfl@gmail.com");
			driver.findElement(By.id("PhoneNo")).sendKeys("01627556536");
			driver.findElement(By.xpath(".//*[@id='step-1']/div/button")).click();
			driver.findElement(By.id("Bill_Street")).sendKeys("113,ELEPHANT ROAD");
			Select division = new Select(driver.findElement(By.id("Bill_Div")));
			division.selectByIndex(3);
			Select city = new Select(driver.findElement(By.id("Bill_City")));
			city.selectByIndex(1);
			Select thana = new Select(driver.findElement(By.id("Bill_Thana")));
			thana.selectByIndex(2);
			Select country = new Select(driver.findElement(By.id("Bill_Country")));
			country.selectByIndex(18);
			driver.findElement(By.id("checkboxtrue")).click();
			driver.findElement(By.xpath(".//*[@id='step-2']/div/button")).click();
			Select FinancialYear = new Select(driver.findElement(By.id("FinancialYearId")));
			FinancialYear.selectByIndex(1);
			Select Currency = new Select(driver.findElement(By.id("CurrencyId")));
			Currency.selectByIndex(8);
//			driver.findElement(By.xpath(".//*[@id='btnAdd']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void CloseBrowser(){
		driver.findElement(By.xpath(".//*[@id='navbar-mobile']/ul[2]/li[2]/a/span[1]/span")).click();
		driver.findElement(By.xpath(".//*[@id='navbar-mobile']/ul[2]/li[2]/div/a[5]")).click();
//		driver.close();
		String LoginPageTitle = driver.getTitle();
		Assert.assertEquals(LoginPageTitle, "Login Page - Acumen Admin", "LogOut Successfully");
	}
	
}
