package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class testChrome {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "//home//apium//workspace//selenium//chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:4000";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
        driver.get(baseUrl + "/en/login");
        driver.findElement(By.id("signin_username")).clear();
        driver.findElement(By.id("signin_username")).sendKeys("admin");
        driver.findElement(By.id("signin_password")).clear();
        driver.findElement(By.id("signin_password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.button")).click();
        driver.get(baseUrl + "/en/profile/edit");
        driver.findElement(By.cssSelector("img.ico")).click();
        assertTrue(closeAlertAndGetItsText().matches("^Are you sure[\\s\\S]$"));
        new Select(driver.findElement(By.id("profile_community_community"))).selectByVisibleText("CAPS");
        driver.findElement(By.cssSelector("button")).click();
       // Thread.sleep(2000);
       // assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*SIGN OUT[\\s\\S]*$"));
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    }



    @After
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
