
//module maker
package Suite3;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class testcase1 {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\selenium\\selenium-java-3.141.59\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@SuppressWarnings({ "resource", "deprecation" })
	@Test(priority=1)
	public void ModuleTestCase() throws Exception {


		File file = new File("C:\\Users\\editor\\eclipse-workspace\\s2\\src\\xlsdocs\\module.xlsx"); // creating a new file
		// instance
		FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> itr = sheet.iterator();

		String className = "";
		String subjectName = "";
		String moduleName = "";
		String themeName = "";
		String chapterName = "";
		String chapterDescription = "";
		// iterating over excel file
		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING: // field that represents string cell type
					String mycell = cell.getStringCellValue();
					if (cell.getColumnIndex() == 0 && cell.getRowIndex() == 1) {
						className = mycell;
					} else if (cell.getColumnIndex() == 1 && cell.getRowIndex() == 1) {
						subjectName = mycell;
					} else if (cell.getColumnIndex() == 2 && cell.getRowIndex() == 1) {
						moduleName = mycell;
					} else if (cell.getColumnIndex() == 3 && cell.getRowIndex() == 1) {
						themeName = mycell;
					} else if (cell.getColumnIndex() == 4 && cell.getRowIndex() == 1) {
						chapterName = mycell;
					} else if (cell.getColumnIndex() == 5 && cell.getRowIndex() == 1) {
						chapterDescription = mycell;
					}

					//System.out.print(mycell + "\t\t\t");

					break;
				case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
					System.out.print(cell.getNumericCellValue() + "\t\t\t");
					break;
				default:
				}
			}
			System.out.println("");
		}

		//System.out.println(className + subjectName + moduleName + themeName + chapterName + chapterDescription);
		//LOGIN SCRIPT
		driver.get("https://sample.volt.development.vivadevops.com/master");
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("volt@vivadigitalindia.net");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Volt@viva02");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		//MAIN SCRIPT START
		driver.get("https://sample.volt.development.vivadevops.com/master/admin/modulelist");
		driver.findElement(By.xpath("//li[4]/a/p")).click();
		driver.findElement(By.xpath("//a/h4")).click();
		driver.findElement(By.xpath("//form[@id='createcourse']/div/div/div[2]/div/div/button/span")).click();
		driver.findElement(By.xpath("//form[@id='createcourse']/div/div/div[2]/div/div/div/ul/li[4]/a")).click();
		new Select(driver.findElement(By.id("changeCls"))).selectByVisibleText(className);
		driver.findElement(By.xpath("//form[@id='createcourse']/div/div/div[2]/div[2]/div/button/span")).click();
		new Select(driver.findElement(By.id("getsubject"))).selectByVisibleText(subjectName);
		driver.findElement(By.name("mtitle")).click();
		driver.findElement(By.name("mtitle")).clear();
		driver.findElement(By.name("mtitle")).sendKeys(moduleName, Keys.TAB,Keys.TAB,Keys.ENTER); //module name
		/*
		 * driver.findElement(By.name("moduleImage")).click();
		 * driver.findElement(By.name("moduleImage")).clear();
		 * driver.findElement(By.name("moduleimage")).sendKeys(
		 * "C:\\Users\\editor\\eclipse-workspace\\s2\\src\\moduleImage.jpg");
		 */
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Module created successfully");
		driver.close();



	}


	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
