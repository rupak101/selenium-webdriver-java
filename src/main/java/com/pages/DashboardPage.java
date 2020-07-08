package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of dashboard page
 */
public class DashboardPage extends BasePage<DashboardPage> {

    private final By overViewTab = By.xpath("//*[@class='tab site_index active']");

    @Step("Validate exam analysis page")
    public boolean validateDashBoardPage() {
        waitForVisibilityOf(overViewTab);
        getWait().until(ExpectedConditions.not(urlContains("user")));
        getWait().until(urlContains("index"));
        return true;
    }
}
