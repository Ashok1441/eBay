package com.eBay;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UKeBayAcc {
	
	public static WebDriver driver;
	String browserName="chrome";
	@Test
	public void eBayUK( ) throws IOException {
			if(browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(browserName.equals("edge")) {
				WebDriverManager.edgedriver().setup();
			    driver= new EdgeDriver();
			}
			else if(browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();	
			}
			else {
				Reporter.log("Given Browser name is Mismatched", true);
			}
			
			driver.get("https://www.ebay.co.uk/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			String acHometitle=driver.getTitle();
			String exHomeTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";
			Assert.assertEquals(acHometitle, exHomeTitle, "Title is Mismatched");
			
			driver.findElement(By.xpath("//a[text()='register']")).click();
			
			driver.findElement(By.id("firstname")).sendKeys("Ashok kumar", Keys.TAB);
			driver.findElement(By.id("lastname")).sendKeys("Pasula", Keys.TAB);
			driver.findElement(By.id("Email")).sendKeys("ashoklucky577@gmail.com",Keys.TAB);
			driver.findElement(By.id("password")).sendKeys("@shok1234");
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File("D:\\Selenium\\eBay\\Screenshots\\PersonalAccUK.png");
			Files.copy(src,des );
			
			driver.findElement(By.id("businessaccount-radio")).click();
			driver.findElement(By.id("businessName")).sendKeys("Ashok kumar", Keys.TAB);
			driver.findElement(By.id("businessEmail")).sendKeys("ashoklucky577@gmail.com",Keys.TAB);
			driver.findElement(By.id("bizPassword")).sendKeys("@shok1234");
			
			WebElement selAdd = driver.findElement(By.id("businessCountry"));
			
			Select sel = new Select(selAdd);
			sel.selectByValue("GB");
			
			File src1 = ts.getScreenshotAs(OutputType.FILE);
			File des1 = new File("D:\\Selenium\\eBay\\Screenshots\\BusinessAccUK.png");
			Files.copy(src1,des1);

	}


}
