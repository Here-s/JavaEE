package com.example.onlinemusic.Mymusic;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest{
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
        Thread.sleep(2000);
    }

    //测试正常情况下 页面的元素是否正常
    @Test
    @Order(1)
    public void appearance() throws InterruptedException {
        System.out.println("apprearance");
        String register = driver.findElement(By.cssSelector("#body > div > div:nth-child(5) > a")).getText();
        Assertions.assertEquals(register, "注册账号！");

        String login = driver.findElement(By.cssSelector("#submit")).getAttribute("value");
        Assertions.assertEquals(login, "登录");

        String mainText = driver.findElement(By.cssSelector("#body > div > h3")).getText();
        Assertions.assertEquals(mainText, "登录");

        driver.findElement(By.cssSelector("#body > div > div:nth-child(5) > a")).click();

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

        String button = driver.findElement(By.cssSelector("#submit")).getAttribute("value");
        Assertions.assertEquals(button, "注册");

        driver.navigate().back();

        String label3 = driver.findElement(By.cssSelector("#body > div > div:nth-child(2) > label")).getText();
        Assertions.assertEquals(label3, "用户名：");

        String placeholder3 = driver.findElement(By.cssSelector("#user")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder3, "请输入用户名");

        String label4 = driver.findElement(By.cssSelector("#body > div > div:nth-child(3) > label")).getText();
        Assertions.assertEquals(label4, "密码：");

        String placeholder4 = driver.findElement(By.cssSelector("#password")).getAttribute("placeholder");
        Assertions.assertEquals(placeholder4, "请输入密码");

        WebElement element1 = driver.findElement(By.cssSelector("#submit"));
        Assertions.assertNotNull(element1);
    }

    //测试窗口
    @Order(2)
    void windowSize() throws InterruptedException {
        driver.manage().window().maximize();
        appearance();
        driver.manage().window().minimize();
        appearance();
        Thread.sleep(2000);
    }

    //username都是错误的 4种长度密码 都不能登录 username对 4种长度密码
    @ParameterizedTest
    @CsvSource(value = {"a,123", "b,123", "c,123", "d,123",
            "e,123", "f,123", "g,123", "h,123"})
    @Order(3)
    void falseLogin(String username, String password) throws InterruptedException {
        driver.findElement(By.cssSelector("#user")).clear();
        driver.findElement(By.cssSelector("#user")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //正确登录
    @ParameterizedTest
    @CsvSource(value = {"gly,123", "ddd,123", "kkk,123"})
    @Order(4)
    void trueLogin(String username, String password) throws InterruptedException {
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
        driver.findElement(By.cssSelector("#user")).clear();
        driver.findElement(By.cssSelector("#user")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        alert.accept();

        Set<String> windowHandles = driver.getWindowHandles();
        for(String window : windowHandles){
            if(window != windowHandle){
                driver.switchTo().window(window);
                break;
            }
        }

        String text = driver.findElement(By.xpath("/html/body/div[1]/h3")).getText();
        Assertions.assertNotNull(text);
        String text2 = driver.findElement(By.cssSelector("#submit1")).getText();
        Assertions.assertEquals(text2, "查询");

        driver.navigate().back();
        driver.switchTo().window(windowHandle);

    }

    //账户名 密码为空
    @Test
    @Order(5)
    void nullLogin() throws InterruptedException {
        driver.findElement(By.cssSelector("#user")).clear();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //密码为空
    @Test
    @Order(6)
    void OneNoLogin1() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#user")).sendKeys("qqq");
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    //账号为空
    @Test
    @Order(7)
    void OneNoLogin2() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#user")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("123");
        driver.findElement(By.cssSelector("#submit")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}