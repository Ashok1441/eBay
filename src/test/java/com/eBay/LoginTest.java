package com.eBay;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	@Test
	public void eBayLoginTest() throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		ResourceBundle rb = ResourceBundle.getBundle("config");
		String url = rb.getString("url");
		String lgId = rb.getString("loginId");
		String pwd = rb.getString("password");
		
		//open given url
		driver.get(url);
		
		//verifying homepage
		String homePageActualTitle = driver.getTitle();
		String homePageExpectedTitle=rb.getString("HomepageTitle");
		Assert.assertEquals(homePageActualTitle, homePageExpectedTitle);
		Reporter.log("eBay Home page is Displayed", true);
		Thread.sleep(2000);
		
		//clicking sign option and verifying the sign inpage
		driver.findElement(By.xpath("(//a[text()='Sign in'])[1]")).click();
		String signInPageActualTitle = driver.getTitle();
		String signInPageExceptedTitle=rb.getString("SignPageTitle");
		Assert.assertEquals(signInPageActualTitle, signInPageExceptedTitle);
		Reporter.log("eBay sign page is Displayed", true);
		Thread.sleep(2000);
		
		//signIn
		driver.findElement(By.id("userid")).sendKeys(lgId);
		Thread.sleep(1000);
		driver.findElement(By.id("signin-continue-btn")).click();
		driver.findElement(By.id("pass")).sendKeys(pwd);
		Thread.sleep(1000);
		driver.findElement(By.id("sgnBt")).click();
		Reporter.log("SignIN is successful", true);
		
		//mouse overaction on my eBay element
		Actions ac = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//a[text()='My eBay']"));
		ac.moveToElement(ele).perform();
		Thread.sleep(2000);
		
		//clicking summary option
		driver.findElement(By.xpath(" //a[text()=' Summary']")).click();
		
		//taking screenshot of obtain page
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("D:\\Selenium\\eBay\\src\\test\\ScreenShots\\eBayscreenshot.jpg");
		Files.copy(src,des );
		
		
		
		
		
	}

}
