package Ecommerce;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class StandAloneApp {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("pintuprajapati162@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pradeep@123");
		driver.findElement(By.id("login")).click();
		

	}

}
