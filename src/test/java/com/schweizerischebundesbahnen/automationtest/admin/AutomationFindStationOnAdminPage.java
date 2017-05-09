package com.schweizerischebundesbahnen.automationtest.admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by aleksandrprendota on 20.04.17.
 */
public class AutomationFindStationOnAdminPage {
    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get("http://localhost:8080/admin");
    }

    @Test
    public void automaticCheckStationOnAdminPage() throws Exception {

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
                .presenceOfElementLocated(By.id("addattr")))
                .click();

        webDriverWait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[text()='Aarau']")));


    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
