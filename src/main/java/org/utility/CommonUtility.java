package org.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUtility {
	public static WebDriver driver;

	public static WebDriver launchBrowser(String browsername) {
		switch (browsername) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.err.println("Invalid BrowSer Name");
			throw new WebDriverException();
		}

		return driver;
	}

	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);

	}

	public static void sendkeys(WebElement e, String value) {
		e.sendKeys(value);
	}

	public static void btnClick(WebElement e) {
		e.click();
	}

	public static void quit() {
		driver.quit();

	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;

	}

	public static String getTitle() {
		return driver.getTitle();

	}

	public static String getText(WebElement e) {
		return e.getText();

	}
	public static String getAttribute(WebElement e) {
		return e.getAttribute("value");
		
	}
	
	public static void selectByValue(WebElement element, String val) {
		Select s = new Select(element);
		s.selectByValue(val);
	}

	public static void takeScreenShot(String imagename) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File des = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\ScreenShots\\" + imagename + ".png");
		FileUtils.copyFile(src, des);

	}

	public static void jsSendKeys(WebElement e, String data) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + data + "')", e);

	}

	public static String getDataFromExcel(String filename, String sheetname, int rowno, int cellno) throws IOException {
		File loc = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\" + filename + ".xlsx");
		FileInputStream st = new FileInputStream(loc);
		// workbook
		Workbook w = new XSSFWorkbook(st);
		// cell
		Cell cell = w.getSheet(sheetname).getRow(rowno).getCell(cellno);
		// get cell type
		int type = cell.getCellType();
		// type 1--->String
		// type 0--->Numbers, Date
		String value = null;
		if (type == 1) {
			value = cell.getStringCellValue();
		} else {
			if (DateUtil.isCellDateFormatted(cell)) {
				value = new SimpleDateFormat("dd-MMM-yyyy").format(cell.getDateCellValue());

			} else {
				value = String.valueOf((long) cell.getNumericCellValue());

			}
		}
		return value;

	}

	public static String getPropertyValue(String key) throws IOException {
		Properties p = new Properties();
		p.load(new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties"));
		String st = p.get(key).toString();
		return st;

	}

}
