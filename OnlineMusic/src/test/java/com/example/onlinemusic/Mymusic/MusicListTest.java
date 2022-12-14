package com.example.onlinemusic.Mymusic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MusicListTest {
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
    void ListUI() throws InterruptedException {
        String text = driver.findElement(By.xpath("/html/body/div[1]/h3")).getText();
        Assertions.assertNotNull(text);

        String text1 = driver.findElement(By.cssSelector("#body > div.container > div:nth-child(2) > form > div > label")).getText();
        Assertions.assertEquals(text1, "歌曲名");

        String text2 = driver.findElement(By.cssSelector("#submit1")).getText();
        Assertions.assertEquals(text2, "查询");

        String text3 = driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(1)")).getText();
        Assertions.assertEquals(text3, "喜欢列表");

        String text4 = driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(2)")).getText();
        Assertions.assertEquals(text4, "添加歌曲");

        String text5 = driver.findElement(By.cssSelector("#delete")).getText();
        Assertions.assertEquals(text5, "删除选中");
    }

    //模糊查询 和 全值匹配
    @ParameterizedTest
    @ValueSource(strings = {"C", "Crush", "h"})
    @Order(2)
    void search(String name) throws InterruptedException {

        driver.findElement(By.cssSelector("#exampleInputName2")).clear();
        driver.findElement(By.cssSelector("#exampleInputName2")).sendKeys(name);
        driver.findElement(By.cssSelector("#submit1")).click();

        String text = driver.findElement(By.cssSelector("#info > tr:nth-child(1) > td:nth-child(2)")).getText();
        Assertions.assertEquals(text, "Crush");

        Thread.sleep(1000);
        //检测一下搜索后的 UI
        String text1 = driver.findElement(By.cssSelector("#info > tr:nth-child(1) > td:nth-child(5) > button:nth-child(2)")).getText();
        Assertions.assertEquals(text1, "喜欢");

        String text2 = driver.findElement(By.cssSelector("#info > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")).getText();
        Assertions.assertEquals(text2, "删除");

        String text3 = driver.findElement(By.cssSelector("#info > tr:nth-child(1) > td:nth-child(4) > button")).getText();
        Assertions.assertEquals(text3, "播放歌曲");

    }

    @Test
    @Order(3)
    void deleteMusic() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#info > tr:nth-child(5) > td:nth-child(5) > button:nth-child(1)")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Thread.sleep(3000);
        System.out.println("删除成功");
    }

    @Test
    @Order(4)
    void loveMusic() throws InterruptedException {
        //喜欢音乐
        driver.findElement(By.cssSelector("#info > tr > td:nth-child(5) > button:nth-child(2)")).click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#body > div.container > div.nav > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#info > tr > td:nth-child(3) > button")).click();
        Thread.sleep(3000);
        String text = driver.findElement(By.cssSelector("#info > tr > td:nth-child(1)")).getText();
        Assertions.assertEquals(text, "Crush");
        driver.findElement(By.cssSelector("body > div.container > div:nth-child(3) > a")).click();
    }

    @Test
    @Order(5)
    void quit() {
        driver.quit();
    }
}


