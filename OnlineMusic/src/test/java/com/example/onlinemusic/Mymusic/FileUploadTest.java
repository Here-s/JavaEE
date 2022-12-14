package com.example.onlinemusic.Mymusic;


import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileUploadTest {
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
        String windowHandle = driver.getWindowHandle();
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
    }

    @Test
    @Order(1)
    void uploadUI() throws InterruptedException {
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).click();
        Thread.sleep(3000);

        String text2 = driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).getAttribute("value");
        Assertions.assertEquals(text2, "上传");
    }

    //成功上传
    @Test
    @Order(2)
    void uploadFile1() throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector("body > form > input[type=file]:nth-child(1)"));
        element.sendKeys("D:\\下载\\小半.mp3");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > form > label > input[type=text]")).sendKeys("陈粒");
        driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).click();

        Thread.sleep(2000);
    }

    //同歌名不同歌手
    @Test
    @Order(3)
    void uploadFile2() throws InterruptedException {
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.cssSelector("body > form > input[type=file]:nth-child(1)"));
        element.sendKeys("D:\\下载\\小半.mp3");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > form > label > input[type=text]")).sendKeys("陈粒粒");
        driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).click();

        Thread.sleep(2000);

        try{
            String text = driver.findElement(By.cssSelector("#submit1")).getText();
            Assertions.assertEquals(text, "查询");
        }catch (Exception e){
            System.out.println("已用同名歌曲，上传失败");
        }
    }

    //不传文件 报错
    @Test
    @Order(4)
    void uploadFile3() throws InterruptedException {
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.cssSelector("body > form > input[type=file]:nth-child(1)"));
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > form > label > input[type=text]")).sendKeys("陈粒粒");
        driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).click();

        Thread.sleep(2000);

        try{
            String text = driver.findElement(By.cssSelector("#submit1")).getText();
            Assertions.assertEquals(text, "查询");
        }catch (Exception e){
            System.out.println("没有选择文件，上传失败！");
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    //上传的不是mp3文件
    @Test
    @Order(5)
    void uploadFile4() throws InterruptedException {
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.cssSelector("body > form > input[type=file]:nth-child(1)"));
        element.sendKeys("D:\\下载\\《乔治湖，自由习作》.jpg");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > form > label > input[type=text]")).sendKeys("陈粒粒");
        driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).click();

        Thread.sleep(2000);

        try{
            String text = driver.findElement(By.cssSelector("#submit1")).getText();
            Assertions.assertEquals(text, "查询");
        }catch (Exception e){
            System.out.println("不是MP3文件，上传失败！");
            driver.navigate().back();
            driver.navigate().back();
        }
    }

    //上传修改后缀名得到的 MP3 文件 格式错误
    @Test
    @Order(6)
    void uploadFile5() throws InterruptedException {
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).click();
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.cssSelector("body > form > input[type=file]:nth-child(1)"));
        element.sendKeys("D:\\下载\\修改后缀名.mp3");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("body > form > label > input[type=text]")).sendKeys("陈粒粒");
        driver.findElement(By.cssSelector("body > form > input[type=submit]:nth-child(3)")).click();

        Thread.sleep(2000);

        try{
            String text = driver.findElement(By.cssSelector("#submit1")).getText();
            Assertions.assertEquals(text, "查询");
        }catch (Exception e){
            System.out.println("不是mp3文件，上传失败！");
            driver.navigate().back();
            driver.navigate().back();
        }

        driver.quit();
    }
}


