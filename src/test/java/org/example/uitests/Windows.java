package org.example.uitests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;

public class Windows extends BaseTestClass {

    @Test
    public void switchToWindowTest() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String currentWindow = driver.getWindowHandle();

        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {       //У нас тільки два хендли, тому беремо тільки той, який не дорівнює збереденому.
            if (!(handle.equals(currentWindow))) {
                driver.switchTo().window(handle);
                break;
            }
        }

        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "New Window");

        driver.close();
        driver.switchTo().window(currentWindow);
        driver.findElement(By.linkText("Click Here")).isDisplayed();

    }
}
