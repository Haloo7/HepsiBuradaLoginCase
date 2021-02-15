package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.offset.PointOption;

public class BaseUtil {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static void StartEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\StartEmulator.bat");
	}

	public static AndroidDriver<AndroidElement> Capabilities(String appName)
			throws FileNotFoundException, IOException, InterruptedException {

		FileInputStream file = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\resources\\general.properties");
		Properties prop = new Properties();
		prop.load(file);
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		String device = (String) prop.get("device");
		if (device.contains("Emulator")) {
		StartEmulator();
		}
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void killNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}

	public void scrollProcess(String locatorType, String locatorField) {

		switch (locatorType) {
		case "exactText":
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + locatorField + "\"));");
			break;
		case "partialId":
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"+".scrollIntoView(new UiSelector().resourceIdMatches(\".*" + locatorField + ".*\"))"));
			break;
		case "partialText":
			driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"+".scrollIntoView(new UiSelector().textContains(\"" + locatorField + "\"))"));
			break;
		}
	}

	public void swipeAction(int Xcoordinate, int Ycoordinate, String direction) {
		// get device width and height
		Dimension dimension = driver.manage().window().getSize();
		TouchAction t = new TouchAction(driver);
		int deviceHeight = dimension.getHeight();
		int deviceWidth = dimension.getWidth();
		System.out.println("Height x Width of device is: " + deviceHeight + " x " + deviceWidth);

		switch (direction) {
		case "Left":
			System.out.println("Swipe Right to Left");
			// define starting and ending X and Y coordinates
			int startX = deviceWidth - Xcoordinate;
			int startY = Ycoordinate; // (int) (height * 0.2);
			int endX = Xcoordinate;
			int endY = Ycoordinate;
			// perform swipe from right to left
			t.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
			break;

		case "Right":
			System.out.println("Swipe Left to Right");
			// define starting X and Y coordinates
			startX = Xcoordinate;
			startY = Ycoordinate;
			endX = deviceWidth - Xcoordinate;
			endY = Ycoordinate;
			// perform swipe from left to right
			t.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
			break;

		}
	}
	/*
	 * public void screenSwipe() { TouchAction t=new TouchAction(driver); final int
	 * ANIMATION_TIME = 200; // ms final int PRESS_TIME = 200; // ms Dimension dims
	 * = driver.manage().window().getSize(); System.out.println(dims); PointOption
	 * pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
	 * PointOption pointOptionEnd = PointOption.point(10, dims.height / 2); // init
	 * start point = center of screen
	 * 
	 * t.press(pointOptionStart).waitAction(WaitOptions.waitOptions(Duration.
	 * ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release().perform();
	 * 
	 * }
	 * 
	 * public void rightLeftSwipe(int DurationMs) { Dimension size =
	 * driver.manage().window().getSize(); System.out.println(size); int startx =
	 * (int) (size.width * 0.9); int endx = (int) (size.width * 0.01); int starty =
	 * size.height / 2; TouchAction t = new TouchAction(driver);
	 * t.press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx,
	 * starty))
	 * .waitAction(WaitOptions.waitOptions(Duration.ofMillis(DurationMs))).release()
	 * .perform(); }
	 * 
	 * public static void swipeHorizontal(AppiumDriver<AndroidElement> driver,
	 * double startPercentage, double finalPercentage, double anchorPercentage, int
	 * duration) throws Exception { Dimension size =
	 * driver.manage().window().getSize(); int anchor = (int) (size.height *
	 * anchorPercentage); int startPoint = (int) (size.width * startPercentage); int
	 * endPoint = (int) (size.width * finalPercentage); TouchAction t = new
	 * TouchAction(driver); t.longPress(PointOption.point(startPoint, anchor))
	 * .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
	 * .moveTo(PointOption.point(endPoint, anchor)).release().perform();
	 * 
	 * }
	 */
}
