package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of home page
 */
public class HomePage extends BasePage<HomePage> {

    private final By qaAssessmentAccount = By.xpath("//*[@class='user-menu']");
    private final By editProfile = By.xpath("//*[@class='usrDropDown']//*[@href='/setting/user']");
    private final By logOutButton = By.xpath("//*[@class='usrDropDown']//*[@href='/site/logout']");
    private final By dashboardTab = By.xpath("//*[@class='menuBtnName']");

    @Step("Click on edit profile from top right corner of the page")
    public EditProfilePage clickEditProfile() {
        //Click on QA Assessment Account
        click(qaAssessmentAccount);
        //click on edit profile button
        click(editProfile);
        getWait().until(urlContains("user"));
        return new EditProfilePage();
    }

    @Step("Click on dashboard tab")
    public DashboardPage clickDashBoardTab() {
        //Click on dashboard tab
        click(dashboardTab);
        getWait().until(urlContains("index"));
        return new DashboardPage();
    }

    @Step("Click on logout button from top right corner of the page")
    public LoginPage clickLogOutButton() {
        //Click on QA Assessment Account
        click(qaAssessmentAccount);
        //click on edit profile button
        click(logOutButton);
        getWait().until(urlContains("login"));
        return new LoginPage();
    }
}
