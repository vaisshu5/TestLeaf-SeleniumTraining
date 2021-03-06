package assignments.day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditLead {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./driverFile/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://leaftaps.com/opentaps/control/login");

		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("demosalesmanager");

		WebElement passWord = driver.findElement(By.id("password"));
		passWord.sendKeys("crmsfa");

		WebElement loginButton = driver.findElement(By.className("decorativeSubmit"));
		loginButton.click();

		// Logged in User clicks CRM/SFA
		WebElement homePage = driver.findElement(By.linkText("CRM/SFA"));
		homePage.click();

		// Leads Tab - > CreateLead
		WebElement leadsTab = driver.findElement(By.linkText("Leads"));
		leadsTab.click();

		WebElement createLeadButton = driver.findElement(By.linkText("Find Leads"));
		createLeadButton.click();

		driver.findElement(By.xpath("//label[text()='First name:']//following::input[@name='firstName'][3]"))
				.sendKeys("Test");

		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

		Thread.sleep(100);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId'][1]/a")).click();

		driver.findElement(By.linkText("Edit")).click();

		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys("Edited TestCompany");

		String companyName = driver.findElement(By.id("updateLeadForm_companyName")).getAttribute("value");

		driver.findElement(By.className("smallSubmit")).click();

		String title = driver.getTitle();
		System.out.println("Title of the current Page : " + title);

		System.out.println(driver.findElement(By.id("viewLead_companyName_sp")).getText());
		System.out.println(companyName);

		if ((driver.findElement(By.id("viewLead_companyName_sp")).getText()).contains(companyName)) {
			System.out.println("Edited Company Name successfully");
			driver.close();
		}

	}

}
