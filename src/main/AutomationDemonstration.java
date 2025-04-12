package main;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationDemonstration {
	WebDriver driver;
	Scanner sc = new Scanner(System.in);

	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='H6-NpN _3N4_BX']")).click();
		Thread.sleep(2000);

		System.out.println("Enter email or phone: ");
		String email = sc.nextLine();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@class='r4vIwl BV+Dqf']")).sendKeys(email);
		driver.findElement(By.xpath("//button[@class='QqFHMw twnTnD _7Pd1Fp']")).click();
	}

	@Test(priority = 2)
	public void searchProduct() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Enter product name: ");
		String product = sc.nextLine();

		driver.findElement(By.xpath("//input[@class='Pke_EE']")).sendKeys(product);
		driver.findElement(By.xpath("//button[@class='_2iLD__']")).click();
	}

	@Test(priority = 3)
	public void addItemToCart() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@class='_53J4C-']")).click();
		Thread.sleep(2000);

		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> iterator = allWindows.iterator();

		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!mainWindowHandle.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				break;
			}
		}

		driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']")).click();
	}

	@Test(priority = 4)
	public void enterAddress() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='QqFHMw zA2EfJ _7Pd1Fp']")).click();
		driver.findElement(By.xpath("//button[@class='QqFHMw FA45gW _7Pd1Fp']")).click();
		driver.findElement(By.xpath("//button[@class='QqFHMw VuSC8m _7Pd1Fp']")).click();
	}

	@Test(priority = 5)
	public void payment() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='CREDIT']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@name='cardNumber']")).sendKeys("378282246310005");

		WebElement monthDropdown = driver.findElement(By.name("month"));
		Select select = new Select(monthDropdown);
		select.selectByVisibleText("05");

		WebElement yearDropdown = driver.findElement(By.name("year"));
		Select select1 = new Select(yearDropdown);
		select1.selectByVisibleText("27");

		driver.findElement(By.xpath("//input[@name='cvv']")).sendKeys("5666");
		driver.findElement(By.xpath("//input[@name='billing_pincode']")).sendKeys("700079");

		WebElement countryDropdown = driver.findElement(By.name("billing_country"));
		Select select2 = new Select(countryDropdown);
		select2.selectByVisibleText("India");

		driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("Kolkata");
		driver.findElement(By.xpath("//input[@name='billing_state']")).sendKeys("West Bengal,700126");
		driver.findElement(By.xpath("//textarea[@name='billing_address']")).sendKeys("Adamas University old girls hostel, barasat-barackpore road");

		driver.findElement(By.xpath("//button[@class='QqFHMw JO5Wdq TVgwvR _7Pd1Fp']")).click();
	}

	@AfterTest
	public void closeBrowser() {    
		driver.quit();
	}
}
