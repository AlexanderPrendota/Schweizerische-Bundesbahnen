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
 * Created by aleksandrprendota on 20.05.17.
 */
public class AutomationCheckCorrectMoveToMultiPurchase {
    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get(SwissrailwaysApplicationAutomationTests.HOME_URL);
    }

    @Test
    public void automaticCheckCorrectMoveToMultiPurchase(){

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
                .sendKeys("Basel");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("arrival")))
                .sendKeys("Riga");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("datepicker")))
                .sendKeys("2017-06-05");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@type = 'submit']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("1")))
                .click();

        // find first way
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[text()='Basel']")));

        // find second way
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[text()='Paris']")));


    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
