package com.eBay;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
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

public class BusinessAccount {
	

	public static WebDriver driver;
	
	@Test
	@Parameters("browserName")
	public void business(String browserName ) throws IOException {
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
			
			ResourceBundle rb = ResourceBundle.getBundle("config");
			String url = rb.getString("url");
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//verifying homepage
			String homePageActualTitle = driver.getTitle();
			String homePageExpectedTitle=rb.getString("HomepageTitle");
			Assert.assertEquals(homePageActualTitle, homePageExpectedTitle,"ActualTitle and ExpectedTitle both are not Same");
			
			//clicking sign option
			driver.findElement(By.xpath("(//a[text()='Sign in'])[1]")).click();
			driver.findElement(By.id("create-account-link")).click();
			
			//clicking Business Account Radio button
			driver.findElement(By.id("businessaccount-radio")).click();
			
			//taking screenshot of Business Account
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File("D:\\Selenium\\eBay\\Screenshots\\BusinessAcc.jpg");
			Files.copy(src,des );
			
			//explicitly Wait for locating the element
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ifhItem0")));
			
			//clicking FAQs option
			driver.findElement(By.id("ifhItem0")).click();
			
			//taking screenshot of FAQs option
			File src1 = ts.getScreenshotAs(OutputType.FILE);
			File des1 = new File("D:\\Selenium\\eBay\\Screenshots\\FAQs.jpg");
			Files.copy(src1,des1 );
			
			//clicking Survey option
			driver.findElement(By.id("ifhItem1")).click();
			
			//explicitly Wait for locating the Survey element
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ifhOverlayTitle")));
			
			//taking screenshot of Survey option
			File src2 = ts.getScreenshotAs(OutputType.FILE);
			File des2 = new File("D:\\Selenium\\eBay\\Screenshots\\Survey.jpg");
			Files.copy(src2,des2 );
	}
}
