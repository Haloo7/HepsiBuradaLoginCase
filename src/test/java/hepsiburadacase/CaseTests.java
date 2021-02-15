package hepsiburadacase;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageobject.ElementLocators;

public class CaseTests extends BaseUtil {

	@Parameters({ "username", "password", "appName", "loginExpectedResult","logoutButtonId" })
	@Test
	public void loginTestCase(String username, String password, String appName, String loginExpectedResult,String logoutButtonId)
			throws FileNotFoundException, IOException, InterruptedException {
		AndroidDriver<AndroidElement> driver = Capabilities(appName);
		ElementLocators element = new ElementLocators(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("close_button")));
		element1.click();
		if (element.promotion.isDisplayed()) {
			int elementX = element.promotion.getLocation().getX();
			int elementY = element.promotion.getLocation().getY();
			swipeAction(elementX, elementY, "Right");
		}
		element.accountIcon.click();
		element.userAccountLogin.click();
		element.loginEmailField.sendKeys(username);
		element.loginPasswordField.sendKeys(password);
		element.loginSubmitButton.click();
		String actualResult = element.welcomeAlertTitle.getText();
		Assert.assertTrue(actualResult.contains(loginExpectedResult), "Giriþ iþlemi gerceklestirilemedi.");
		element.okayPopup.click();
		element.afterLoginNameField.click();
		element.userFirstNameField.clear();
		RandomString sayiüret = new RandomString();
		String replaceUserName = sayiüret.randomUserName();
		element.userFirstNameField.sendKeys(replaceUserName);
		element.updateUserInf.click();
		element.nameReplaceSuccessPopupButton.click();
		element.discoverBottomNavigation.click();
		element.accountIcon.click();
		scrollProcess("partial", logoutButtonId);
		element.logoutButton.click();
		element.accountIcon.click();
		element.userAccountLogin.click();
		element.loginEmailField.sendKeys(username);
		element.loginPasswordField.sendKeys(password);
		element.loginSubmitButton.click();
		String nameControl=element.welcomeAlertText.getText();
		Assert.assertTrue(nameControl.contains(replaceUserName),"Ýsim degistirme isleminiz hatalidir.");
		element.okayPopup.click();
		scrollProcess("partial", logoutButtonId);
		element.logoutButton.click();
	}

	
	/*
	@Parameters({ "appName", "findSuperPrice", "findMoreThanOneImage" })
	@Test
	public void productTestCase(String appName, String findSuperPrice, String findMoreThanOneImage) throws Exception {
		AndroidDriver<AndroidElement> driver = Capabilities(appName);
		ElementLocators element = new ElementLocators(driver);
		TouchAction t=new TouchAction(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("close_button")));
		element1.click();
		/*
		boolean promotionDisplayed=element.promotion.isDisplayed();
		while(promotionDisplayed) {
			int elementX = element.promotion.getLocation().getX();
			int elementY = element.promotion.getLocation().getY();
			swipeAction(elementX, elementY, "Right");
			break;
		}
		scrollProcess("partialId", findSuperPrice);
		element.allProductButton.click();
		int a = element.superPriceProduct.size();
		System.out.println(a);
		if (a <= 0) {
			scrollProcess("partial", findMoreThanOneImage);
		}
		element.superPriceProductDetail.get(0).click();
		Thread.sleep(2000);
		screenSwipe();
		//element.superPriceProductImage.click();
		
		
		//element.addToCart.click();
	}
	*/
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		killNodes();
		startServer();
	}

	@AfterTest
	public void tearDown() {
		service.stop();
	}

}
