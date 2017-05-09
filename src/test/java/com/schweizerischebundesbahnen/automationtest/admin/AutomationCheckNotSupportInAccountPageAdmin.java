package com.schweizerischebundesbahnen.automationtest.admin;

import org.junit.After;
import org.junit.Assert;
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
public class AutomationCheckNotSupportInAccountPageAdmin {

    private WebDriver driver = null;

    @Before
    public void createDriver() {
        driver = new SafariDriver();
        driver.get("http://localhost:8080/account");
    }

    @Test
    public void automaticCheckNotSupportInAccount() {

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

        Boolean isPresent = driver.findElements(By.id("chatbutton")).size() > 0;

        Assert.assertFalse(isPresent);

    }

    @After
    public void closeDriver() throws Exception {
        driver.quit();
    }
}
