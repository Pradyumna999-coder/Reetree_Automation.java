package com.Comman;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Utils.DateUtils;
import com.Utils.GlobalPropertiesFile;
import com.Utils.ReadPropertiesFile;

public class Keywords extends BaseClass{

	protected static Robot robot;

	protected static TakesScreenshot screen;
	protected static WebDriverWait wait;

	ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();

	protected static Select select;

	protected static GlobalPropertiesFile globalProperties = new GlobalPropertiesFile();

	public static void launchBrowser(String browserName) {

		switch (browserName.trim().toLowerCase()) {

		case "chrome":

			System.setProperty("webdriver.chrome.driver", globalProperties.globalProperties("chromePath"));
			DriverUtils.setDriver(new ChromeDriver());

			break;

		case "firefox":

			System.setProperty("webdriver.gecko.driver", globalProperties.globalProperties("firefoxPath"));
			DriverUtils.setDriver(new FirefoxDriver());

			break;

		default:

			System.out.println("Case no match found");

			break;

		}

		DriverUtils.getDriver().manage().window().maximize();
		DriverUtils.getDriver().get(globalProperties.globalProperties("ApplicationUrl"));

	}

	public static void closeBrowser() {
		DriverUtils.getDriver().close();
	}
	public static void click(WebElement WebElement) {

		WebElement.click();

	}

	public static void fetchScreenshotUsingRobot() {

		try {
			robot = new Robot();
		} catch (AWTException e) {

			System.out.println(e.getMessage());
		}

		Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

		BufferedImage buffImage = robot.createScreenCapture(rectangle);
		try {
			ImageIO.write(buffImage, "png", new File("E://ScreenShot_" + DateUtils.getSystemDate() + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void fetchScreenshotUsingRobot(int xOffSet, int yOffSet) {

		try {
			robot = new Robot();
		} catch (AWTException e) {

			System.out.println(e.getMessage());
		}

		Rectangle rectangle = new Rectangle(new Dimension(xOffSet, yOffSet));

		BufferedImage buffImage = robot.createScreenCapture(rectangle);
		try {
			ImageIO.write(buffImage, "png", new File("E://ScreenShot_" + DateUtils.getSystemDate() + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void moveToWebElement(WebElement WebElement) {

		Actions action = new Actions(DriverUtils.getDriver());
		action.moveToElement(WebElement).perform();
	}

	public static void clickOnRadioButton(List<WebElement> WebElements, String radioBtnvalue) {

		for (WebElement element : WebElements) {
			if (element.getText().equals(radioBtnvalue)) {
				element.click();

			}
		}
	}

	public static void selectAllCheckBoxs(List<WebElement> WebElements) {

		for (WebElement element : WebElements) {

			element.click();

		}
	}

	public static void selectParticullarCheckBox(List<WebElement> WebElements, String value) {

		for (WebElement element : WebElements) {
			if (element.getText().equals(value)) {
				element.click();
			}
		}
	}

	public static void selectMultipleCheckBox(List<WebElement> WebElements, String... value) { // using
																		// varr

		for (WebElement element : WebElements) {
			 for(int i=0; i < value.length; i++) {

			if(element.getText().equals(value[i])) {
			element.click();

		}
	}
		}
	}
	

	public static void selectDropdownValue(WebElement webElement, String value) 
	{
		select = new Select(webElement);
		try {
			select.selectByVisibleText(value);
		} catch (RuntimeException e) {
			try{
				select.selectByValue(value);
			}catch(RuntimeException e1){
				
				System.out.println("");
			}
		}

	}

	public static void getTextOnElement(WebElement webElement) {

		webElement.getText();
	}

	public String getDefaultSelectedValueInDropdown(WebElement webElement) {

		select = new Select(webElement);
		return select.getFirstSelectedOption().getText();

		
	}

	public static void waitForObjectVisibilityOfElementLocated(WebElement webElement,int timeOut) {

		wait = new WebDriverWait(DriverUtils.getDriver(), timeOut);
		wait.until(ExpectedConditions.visibilityOf(webElement));
		

	}

	public static void fetchScreenShot() {
		screen = (TakesScreenshot) DriverUtils.getDriver();
		File src = screen.getScreenshotAs(OutputType.FILE);
		String path = "E://ScreenShot_" + DateUtils.getSystemDate() + ".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String screenShotForReport() {
		screen = (TakesScreenshot) DriverUtils.getDriver();
		File src = screen.getScreenshotAs(OutputType.FILE);
		String path = "E://ScreenShot_" + DateUtils.getSystemDate() + ".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return path;
	}
	public static void isDisplayed(WebElement webElement) {

		webElement.isDisplayed();

	}

	public static void getCurrentUrl() {

		DriverUtils.getDriver().getCurrentUrl();

	}
}
