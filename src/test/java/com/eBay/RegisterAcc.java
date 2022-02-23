package com.eBay;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterAcc {
	public static WebDriver driver;
	
	String browserName="chrome";
	@Test
	public void eBay( ) throws IOException {
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
			
			By ele = By.id("ifhItem0");
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated( ele));
			
			driver.findElement(By.id("ifhItem0")).click();	TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File("D:\\Selenium\\eBay\\Screenshots\\FAQs1.jpg");
			Files.copy(src,des );
			
			driver.findElement(By.id("ifhItem1")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ifhOverlayTitle")));
			
			File src1 = ts.getScreenshotAs(OutputType.FILE);
			File des1 = new File("D:\\Selenium\\eBay\\Screenshots\\Survey1.jpg");
			Files.copy(src1,des1 );
	}
}
