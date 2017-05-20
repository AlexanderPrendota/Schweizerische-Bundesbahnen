package com.schweizerischebundesbahnen.automationtest.user;

import com.schweizerischebundesbahnen.SwissrailwaysApplicationAutomationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aleksandrprendota on 04.04.17.
 *
 * Correct changing user params in account page
 *
 */
public class AutomationChangeUserParams {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get(SwissrailwaysApplicationAutomationTests.HOME_URL);
    }

    @Test
    public void automaticChangeUserParams() throws Exception {

        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("password")))
                .sendKeys("123");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("email")))
                .sendKeys("A@a");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@type = 'submit']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("accountuser")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("firstname")))
                .clear();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("firstname")))
                .sendKeys("CHANGE");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("saveinfo")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[text()='OK!']")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='CHANGE']")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("firstname")))
                .clear();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("firstname")))
                .sendKeys("User");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("saveinfo")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[text()='OK!']")))
                .click();

        Thread.sleep(3000);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@value='User']")));

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
