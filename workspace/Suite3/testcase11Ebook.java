//question generator true false
package Suite3;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class testcase11Ebook {
	private WebDriver driver;
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
	@Test
	public void ChapterTestCase() throws Exception {

		//LOGIN SCRIPT
		driver.get("https://sample.volt.development.vivadevops.com/master");
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("volt@vivadigitalindia.net");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("Volt@viva02");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

		File file = new File("C:\\Users\\editor\\eclipse-workspace\\s2\\src\\xlsdocs\\addEbook.xlsx"); // creating a new file
		// instance
		FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> itr = sheet.iterator();
		int rowTotal = sheet.getPhysicalNumberOfRows();
		System.out.println(rowTotal);
		String questionDesc = "";
		String ebookUrl="";
		int rowLimit=2;
		int rowInit=0;
		// iterating over excel file
		while (itr.hasNext()&&rowInit<rowLimit) {
			
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				rowInit=cell.getRowIndex();
				if(rowInit<1) continue;
				if (cell.getColumnIndex() == 4 && cell.getRowIndex() > 0) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						questionDesc = cell.getStringCellValue();
						break;
					default:
						break;
					}
				}
				else if (cell.getColumnIndex() == 5 && cell.getRowIndex() > 0) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						ebookUrl = cell.getStringCellValue();
						System.out.println(ebookUrl);
						break;
					default:
						break;
					}
				}
			}
			if(rowInit<1) continue;
		//LOGIN SCRIPT

		//main script for iteration
		driver.get("https://sample.volt.development.vivadevops.com/master/questionlist/create/171");
		driver.findElement(By.id("ebookUpload")).click();
		driver.findElement(By.xpath("//p")).click();
		driver.findElement(By.name("audiourl")).clear();
		driver.findElement(By.name("audiourl")).sendKeys(ebookUrl);
		driver.findElement(By.name("submit")).click();
		//System.out.println("");
		}
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

}