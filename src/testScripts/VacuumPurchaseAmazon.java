package testScripts;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class VacuumPurchaseAmazon {
	WebDriver driver;
	
	@Test
	public void PurchaseVacuumTest() {
		//Navigate to correct department
		navigateToDepartment();
		
		//Select the features
		selectFeatures();
		
		//Sort the results
		sortResults();
		
		//Select 2nd vacuum in the list
		selectVacuum();
		
		//Add vacuum to cart
		addToCart();
		
		//Navigate to the cart
		cartNavigation();
		
		//Delete warranty from cart
		deleteWarranty();
		
		//Proceed to checkout
		proceedToCheckout();
		
		//Login to my account
		loginToAccount();
		
		//Assert check
		assertTest();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		String webURL = "https://amazon.com";
		
		driver.get(webURL);
	}
	
	@AfterMethod
	public void afterMethod() {
		//driver.close();
	}
	
	public void navigateToDepartment() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement departmentElement = driver.findElement(By.cssSelector("span[class='nav-line-2']"));
		departmentElement.click();
		
		WebElement applianceElement = driver.findElement(By.linkText("Appliances"));
		applianceElement.click();
		
		WebElement vacuumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Vacuums & Floor Care")));
		vacuumElement.click();
		
		WebElement robotElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Robotic Vacuums")));
		robotElement.click();
	}
	
	public void selectFeatures() {
		WebElement featuresElement = driver.findElement(By.name("s-ref-checkbox-2631204011"));
		featuresElement.click();
		
		WebElement primeElement = driver.findElement(By.name("s-ref-checkbox-2470955011"));
		primeElement.click();
	}

	public void sortResults() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement sortElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("sort")));
		new Select(sortElement).selectByVisibleText("Avg. Customer Review");
	}
	
	public void selectVacuum() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result_1\"]/div/div[3]/div[1]/a/h2")));
		selectElement.click();
	}
	
	public void addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement twoYearCoverageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mbb-offeringID-2")));
		twoYearCoverageElement.click();
		
		WebElement addToCartElement = driver.findElement(By.id("add-to-cart-button"));
		addToCartElement.click();
	}
	
	public void cartNavigation() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='a-button-inner']")));
		cartElement.click();
	}
	
	public void deleteWarranty() {
		String roombaText = "Minibot X5 Robot Vacuum Cleaner For Hard Wood Tile Rugs Thin Carpets Pet Hairs With High Suction Mopping Household Sweeper Black";
		String actualText;
		WebElement deleteFirstItemElement = driver.findElement(By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div[1]/div[4]/div/div[1]/div/div/div[2]/ul/li[1]/span/a/span"));
		actualText = deleteFirstItemElement.getText();
		
		if(actualText.equals(roombaText)) {
			WebElement deleteElement = driver.findElement(By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div[2]/div[4]/div/div[1]/div/div/div[2]/div/span[1]"));
			deleteElement.click();
		}
		else {
			WebElement deleteElement = driver.findElement(By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div[1]/div[4]/div/div[1]/div/div/div[2]/div/span[1]"));
			deleteElement.click();
		}
	}
	
	public void proceedToCheckout() {
		WebElement checkoutElement = driver.findElement(By.name("proceedToCheckout"));
		checkoutElement.click();
	}
	
	public void loginToAccount() {
		String email = "shwarnock89@gmail.com";
		String password = "otis20";
		String onlyEmailURL = "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs"
				+ ".openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&"
				+ "openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%"
				+ "2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3F"
				+ "ie%3DUTF8%26action%3Dsign-out%26path%3D%252Fgp%252Fyourstore%252Fhome%26ref_%3Dnav_youraccount_signout%26signIn%3D1%26useRedirectOnSuccess%3D1";
		String onlyEmailURL2 = "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=amazon_checkout_us&openid.claimed_id=http%3A%2"
				+ "F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier"
				+ "_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid"
				+ ".net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fbuy%2Fsignin"
				+ "%2Fhandlers%2Fcontinue.html%3Fie%3DUTF8%26cartItemIds%3D%26eGCApp%3D%26hasWorkingJavascript%3D0%26isEGCOrder%3D0%26isFresh%3D0%2"
				+ "6oldCustomerId%3D%26oldPurchaseId%3D%26preInitiateCustomerId%3D%26purchaseInProgress%3D%26siteDesign%3D&pageId=amazon_checkout_u"
				+ "s&showRmrMe=0&siteState=isRegularCheckout.1%7CIMBMsgs.%7CisRedirect.0|";
		String actualURL = driver.getCurrentUrl();
		
		if(actualURL.equals(onlyEmailURL)||actualURL.equals(onlyEmailURL2)) {
			WebElement emailElement = driver.findElement(By.name("email"));
			WebElement continueElement = driver.findElement(By.id("continue"));
		
			emailElement.sendKeys(email);
			continueElement.click();
		
			WebElement passwordElement = driver.findElement(By.name("password"));
			WebElement signInElement = driver.findElement(By.id("signInSubmit"));
		
			passwordElement.sendKeys(password);
			signInElement.click();
		}
		else {
			WebElement emailElement = driver.findElement(By.name("email"));
			WebElement passwordElement = driver.findElement(By.name("password"));
			WebElement signInElement = driver.findElement(By.id("signInSubmit"));
			
			emailElement.sendKeys(email);
			passwordElement.sendKeys(password);
			signInElement.click();
		}
	}
	
	public void assertTest() {
		boolean assertTest = false;
		String expectedTitle = "Amazon.com Checkout";
		String actualTitle = driver.getTitle();
		
		if(actualTitle.equals(expectedTitle)) {
			assertTest = true;
		}
		Assert.assertEquals(assertTest, true);
	}
}
