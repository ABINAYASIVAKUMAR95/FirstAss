package org.card;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Gateway {
WebDriver driver;

@Given("The user is in HomePage")
public void the_user_is_in_HomePage() {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\My Pc\\eclipse-abi95\\Success\\driver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://demo.guru99.com/payment-gateway/index.php");
	
}

@Given("The user can test the card details")
public void the_user_can_test_the_card_details() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Generate Card Number']")).click();
	String pwid=driver.getWindowHandle();
	System.out.println(pwid);
	Set<String> allWindowId = driver.getWindowHandles();
	for(String v:allWindowId) {
		if(!v.equals(pwid)) {
			driver.switchTo().window(v);
		}
	}
	Thread.sleep(3000);
	WebElement Gcard=driver.findElement(By.xpath("//h4[@style='text-align:center;font-size:24px;font-weight:400;color:#555;line-height:36px;']"));
	String name = Gcard.getText();
	System.out.println(name);
	WebElement cvv=driver.findElement(By.xpath("//h4[contains(text(),'CVV')]"));
	String name1 = cvv.getText();
	System.out.println(name1);
	WebElement Exp=driver.findElement(By.xpath("//h4[contains(text(),'Exp')]"));
	String name2 = Exp.getText();
	System.out.println(name2);
	WebElement limit=driver.findElement(By.xpath("//h4[contains(text(),'Credit Limit')]"));
	String name3 = limit.getText();
	System.out.println(name3);
}

@Given("The user can check the credit card")
public void the_user_can_check_the_credit_card() throws InterruptedException {
	Thread.sleep(3000);
	driver.get("http://demo.guru99.com/payment-gateway/check_credit_balance.php");
	driver.findElement(By.xpath("//input[@id='card_nmuber']")).sendKeys("1212121212121212");
   driver.findElement(By.xpath("//input[@type='submit']")).click();
}

@When("The user navigates to payment process page")
public void the_user_navigates_to_payment_process_page() 
{
driver.get("http://demo.guru99.com/payment-gateway/purchasetoy.php");
driver.findElement(By.xpath("//input[@type='submit']")).click();
driver.findElement(By.xpath("//input[@id='card_nmuber']")).sendKeys("1212121212121212");
WebElement month = driver.findElement(By.xpath("//select[@name='month']"));
Select sc=new Select(month);
sc.selectByIndex(4);
WebElement year = driver.findElement(By.xpath("//select[@name='year']"));
Select sca=new Select(year);
sca.selectByIndex(4);
driver.findElement(By.xpath("//input[@id='cvv_code']")).sendKeys("123");
driver.findElement(By.xpath("//input[@value='Pay $20.00']")).click();
}

@Then("The user can see the successfull message")
public void the_user_can_see_the_successfull_message() {
WebElement payment = driver.findElement(By.xpath("//h2[text()='Payment successfull!']"));
String name = payment.getText();
System.out.println(name);

}
}