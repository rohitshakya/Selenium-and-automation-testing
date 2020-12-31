package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Volt1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testVolt1() throws Exception {
    driver.get("https://volt.development.vivadevops.com/");
    driver.findElement(By.linkText("Sign In")).click();
    driver.findElement(By.id("Userid")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=Userid | ]]
    driver.findElement(By.id("Userid")).clear();
    driver.findElement(By.id("Userid")).sendKeys("a0001");
    driver.findElement(By.name("accountpassword")).clear();
    driver.findElement(By.name("accountpassword")).sendKeys("Abcd@1234");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.id("dd")).click();
    driver.findElement(By.xpath("//div[@id='dd']/ul/li[4]/a/div")).click();
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
