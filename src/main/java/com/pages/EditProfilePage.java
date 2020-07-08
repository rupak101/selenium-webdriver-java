package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of edit profile page
 */
public class EditProfilePage extends BasePage<EditProfilePage> {

    private final By userNameField = By.cssSelector("#User_name");
    private final By userEmailField = By.cssSelector("#User_email");
    private final By languageRadioButton = By.xpath("//*[@type='radio']");
    private final By submitButton = By.xpath("//*[@type='submit']");
    private final By successMessage = By.xpath("//*[@class='flash-message flash-success ']");
    private final By errorSummary = By.xpath("//*[@class='errorSummary']");
    private final By errorMessage = By.xpath("//*[@class='errorMessage' and @id='User_email_em_']");

    @Step("Edit the email address with invalid email ID {invalidEmail}")
    public EditProfilePage editEmail(String invalidEmail) {
        waitForVisibilityOf(userEmailField);
        clearAndFill(userEmailField, invalidEmail);
        return this;
    }

    @Step("Edit user name {userName}")
    public EditProfilePage editUserName(String userName) {
        waitForVisibilityOf(userNameField);
        clearAndFill(userNameField, userName);
        return this;
    }

    @Step("Click on submit button")
    public EditProfilePage clickSubmit() {
        waitForVisibilityOf(submitButton);
        click(submitButton);
        return this;
    }

    @Step("Select language radio button")
    public EditProfilePage selectLanguage(int languageIndex) {
        findElements(languageRadioButton).get(languageIndex).click();
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        //get the success message after clicking on the submit button
        return findElement(successMessage).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return findElement(errorMessage).isDisplayed();
    }

    public String getUserName() {
        return findElement(userNameField).getAttribute("value");
    }

    public boolean isErrorSummaryDisplayed() {
        return findElement(errorSummary).isDisplayed();
    }

    public boolean isLanguageRadioButtonSelected(int languageIndex) {
        return findElements(languageRadioButton).get(languageIndex).isSelected();
    }
}
