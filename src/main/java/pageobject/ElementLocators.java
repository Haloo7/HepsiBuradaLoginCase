package pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ElementLocators {

	public ElementLocators(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/com_appboy_inappmessage_slideup_container")
	public WebElement promotion;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/account_icon")
	public WebElement accountIcon;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/llUserAccountLogin")
	public WebElement userAccountLogin;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/etLoginEmail")
	public WebElement loginEmailField;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/etLoginPassword")
	public WebElement loginPasswordField;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/btnLoginLogin")
	public WebElement loginSubmitButton;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/alertTitle")
	public WebElement welcomeAlertTitle;

	@AndroidFindBy(id = "android:id/message")
	public WebElement welcomeAlertText;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement okayPopup;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/tvUserAccountUsername")
	public WebElement afterLoginNameField;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/etUserFirstName")
	public WebElement userFirstNameField;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Güncelle']")
	public WebElement updateUserInf;

	@AndroidFindBy(id = "android:id/button1")
	public WebElement nameReplaceSuccessPopupButton;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/ll_user_account_menu_logout")
	public WebElement logoutButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keþfet']")
	public WebElement discoverBottomNavigation;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/tvUserAccountUsername")
	public WebElement usernameField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Tümü']")
	public WebElement allProductButton;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/pi_product_list_item_image")
	public List<WebElement> superPriceProduct;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/tv_product_list_item_name")
	public List<WebElement> superPriceProductDetail;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/imagePager")
	public WebElement superPriceProductImage;

	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/leftIcon")
	public WebElement productLeftIcon;
	
	@AndroidFindBy(id = "com.pozitron.hepsiburada:id/product_detail_add_to_cart_text")
	public WebElement addToCart;
	

}

