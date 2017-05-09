package com.schweizerischebundesbahnen.automationtest.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aleksandrprendota on 09.05.17.
 */
public class AutomationSmartSearch {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get("http://localhost:8080/home");
    }

    @Test
    public void automaticFindSmartWays() throws Exception {

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
                .presenceOfElementLocated(By.id("smartsearch")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("departure")))
                .sendKeys("Yaroslavl");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("arrival")))
                .sendKeys("Basel");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("datepicker")))
                .sendKeys("2017-06-01");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@type = 'submit']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("1165")));
    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
