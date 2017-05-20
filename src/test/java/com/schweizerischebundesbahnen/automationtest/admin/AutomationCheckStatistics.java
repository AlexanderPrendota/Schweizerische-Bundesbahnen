package com.schweizerischebundesbahnen.automationtest.admin;

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
public class AutomationCheckStatistics {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get(SwissrailwaysApplicationAutomationTests.ADMIN_URL);
    }

    @Test
    public void automaticCheckNotifyInAdminPage() {

        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("password")))
                .sendKeys("123");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("email")))
                .sendKeys("Prendota@mail.ru");

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//input[@type = 'submit']")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("statistics")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//h3[text()='Bought tickets by station departure']")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//h3[text()='Bought tickets by station arrival']")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//h3[text()='Money Statistics']")));

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//h3[text()='Attendance statistics']")));

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
