package assignments.day4;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlayWithHyperlinks {

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {

		System.setProperty("webdriver.chrome.driver", "./driverFile/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://leafground.com/pages/Link.html");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("'Find where am supposed to go without clicking me?' links to : " + driver
				.findElement(By.linkText("Find where am supposed to go without clicking me?")).getAttribute("href"));

		System.out.println("\nVerifying is broken Link:");

		HttpURLConnection huc = (HttpURLConnection) (new URL(
				driver.findElement(By.linkText("Verify am I broken?")).getAttribute("href")).openConnection());
		huc.setRequestMethod("HEAD");
		huc.connect();

		if (huc.getResponseCode() > 400) {
			System.out.println("Invalid link with Error Code " + huc.getResponseCode());
		} else {
			System.out.println("Link is Valid");
		}

		System.out.println("\nFinding no. of links in a this Page:");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("The number of links is " + links.size());

		System.out.println("\nClicking Home to navigate!!!");
		driver.findElement(By.linkText("Go to Home Page")).click();

		Thread.sleep(2000);
		driver.close();

	}

}
