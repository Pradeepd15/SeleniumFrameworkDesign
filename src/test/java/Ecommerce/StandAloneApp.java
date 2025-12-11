package Ecommerce;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class StandAloneApp {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("pintuprajapati162@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pradeep@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod =products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink=\"/dashboard/cart\"]")).click();
		
		List<WebElement> lists = driver.findElements(By.cssSelector(".cartWrap h3"));
		boolean match = lists.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
		List<WebElement> dates = driver.findElements(By.cssSelector(".input.ddl"));
		for(int i=0;i<dates.size();i++) {
			Select select = new Select(dates.get(i));
			if(i==0) {
				    dates.get(i).click();
					select.selectByIndex(5);
			}
			else {
				dates.get(i).click();
				select.selectByIndex(27);
			}
		}
		
		driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")).sendKeys("india");
		List<WebElement> countries = driver.findElements(By.cssSelector(".list-group span"));
		
		WebElement countryName = countries.stream().filter(country-> country.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		countryName.click();
		WebElement actionButton = driver.findElement(By.cssSelector(".actions a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionButton);
		wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();
	}
}
