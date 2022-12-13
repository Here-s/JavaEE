package com.example.onlinemusic.Mymusic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistTest{
    private static ChromeDriver driver;

    private static ChromeDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            //隐式等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return driver;
    }

    @Test
    @BeforeAll
    static void start() throws InterruptedException {
        driver = getDriver();
        driver.get("http://49.232.4.81:8080/login.html");
        driver.findElement(By.cssSelector("#body > div > div:nth-child(5) > a")).click();
        Thread.sleep(2000);
    }

    @Test
    @Order(1)
    void registUI(){
        String label = driver.findElement(By.cssSelector("#body > div > div:nth-child(2) > label")).getText();
        Assertions.assertEquals(label, "用户名：");

        String placeholder = driver.findElement(By.cssSelector("#user")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder, "请输入用户名");

        String label1 = driver.findElement(By.cssSelector("#body > div > div:nth-child(3) > label")).getText();
        Assertions.assertEquals(label1, "密码：");

        String placeholder1 = driver.findElement(By.cssSelector("#password1")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder1, "请输入密码");

        String label2 = driver.findElement(By.cssSelector("#body > div > div:nth-child(4) > label")).getText();
        Assertions.assertEquals(label2, "确认密码：");

        String placeholder2 = driver.findElement(By.cssSelector("#password2")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder2, "再次输入密码");

        WebElement element = driver.findElement(By.cssSelector("#body > div > h3"));
        Assertions.assertNotNull(element);
    }

    //测试注册功能 成功注册和输入两次密码不正确的用户
    @ParameterizedTest
    @CsvSource(value = {"qqq,123,123", "aaa,123,1234"})
    @Order(2)
    void registGN(String username, String password, String newPassword) throws InterruptedException {
        if (driver.findElement(By.cssSelector("#body > div > h3")).getText().equals("登录")){
            driver.findElement(By.cssSelector("#body > div > div:nth-child(5) > a")).click();
        }
        driver.findElement(By.cssSelector("#user")).sendKeys(username);
        driver.findElement(By.cssSelector("#password1")).sendKeys(password);
        driver.findElement(By.cssSelector("#password2")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}

