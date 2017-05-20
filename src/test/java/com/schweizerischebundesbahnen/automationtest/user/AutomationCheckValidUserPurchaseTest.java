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
 */
public class AutomationCheckValidUserPurchaseTest {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get(SwissrailwaysApplicationAutomationTests.HOME_URL);
    }

    @Test
    public void automaticCheckUserPurchase() throws Exception {

        WebDriverWait webDriverWait = new WebDriverWait(driver,10);

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("password")))
                .sendKeys("123");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("email")))
                .sendKeys("S@s");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@type = 'submit']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("accountuser")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("1013")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[text()='F01']")));

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
