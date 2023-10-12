package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookiesTest extends BaseTestClass {

    private static final String USER_NAME = "tomsmith";
    private static final String USER_PASS = "SuperSecretPassword!";

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void cookiesTest() {
        driver.findElement(By.id("username")).sendKeys(USER_NAME);
        driver.findElement(By.id("password")).sendKeys(USER_PASS);
        driver.findElement(By.cssSelector(".radius")).click();

        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println(cookie.getName() + " --- " + cookie.getValue());
        }
        System.out.println();

        Cookie cookie = new Cookie("Some New Cookie", "new value");
        driver.manage().addCookie(cookie);

        Cookie someCookie = driver.manage().getCookieNamed("Some New Cookie");
//        System.out.println(someCookie.getName() + " --- " + someCookie.getValue());
        System.out.println(cookie.getName() + " --- " + cookie.getValue());
        driver.manage().deleteCookieNamed("Some New Cookie");
        someCookie = driver.manage().getCookieNamed("Some New Cookie");
        System.out.println(someCookie); // null
    }
}
