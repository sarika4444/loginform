package edu.loginform;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void openLoginPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String appUrl = System.getProperty("login.app.url", "http://localhost:8080");
        driver.get(appUrl);
    }

    @Test
    void validCredentialsShowSuccess() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertEquals("Login Successful!", wait.until(d -> d.findElement(By.id("message")).getText()));
    }

    @Test
    void invalidCredentialsShowError() {
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertEquals("Invalid Username or Password", wait.until(d -> d.findElement(By.id("message")).getText()));
    }

    @Test
    void emptyFieldsShowValidationMessage() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertEquals("Please enter username and password", wait.until(d -> d.findElement(By.id("message")).getText()));
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
