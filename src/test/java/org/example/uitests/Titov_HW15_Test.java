package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Set;

public class Titov_HW15_Test {
        private WebDriver driver;

        @BeforeClass
        public void beforeClass() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @AfterClass
        public void afterClass() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
        public void customerServicePageTest() {
            driver.get("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation");
            String currentWindow = driver.getWindowHandle();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            driver.findElement(By.linkText("Contact us")).click();
            Set<String> windowHandles = driver.getWindowHandles();

            for (String handle : windowHandles) {       //У нас тільки два хендли, тому беремо тільки той, який не дорівнює збереденому.
                if (!(handle.equals(currentWindow))) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText(), "CUSTOMER SERVICE - CONTACT US");

            driver.close();
//            driver.switchTo().window(currentWindow);
//            driver.findElement(By.linkText("Contact us")).isDisplayed();

        }
    }