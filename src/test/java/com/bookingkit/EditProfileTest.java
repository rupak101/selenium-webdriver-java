package com.bookingkit;

import com.base.BaseTest;
import com.pages.DashboardPage;
import com.pages.EditProfilePage;
import com.pages.HomePage;
import com.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.framework.driverconfiguration.DriverCreator.getDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test cases for edit user profile
 */
@Owner("Rupak Mansingh")
@Feature("Edit user profile")
public class EditProfileTest extends BaseTest {

    private static final String invalidEmail = "username123@bookingkit";

    @DataProvider(parallel = true)
    public Object[][] userNameData() {
        return new Object[][]{
                {"username1"},
                {"QA Assessment Account"}
        };
    }

    @Test(dataProvider = "userNameData")
    @Description("Edit user name on edit profile page")
    public void updateUserNameOnEditProfile(String userName) {
        EditProfilePage editProfilePage = loginAndEditProfile()
                .editUserName(userName)
                .clickSubmit();

        assertThat("Success message is not displayed", editProfilePage.isSuccessMessageDisplayed(), is(true));
        assertThat("User name didn't change", editProfilePage.getUserName(), is(userName));
    }

    @Test
    @Description("Edit profile with invalid email address")
    public void updateEmailOnEditProfile() {
        EditProfilePage editProfilePage = loginAndEditProfile()
                .editEmail(invalidEmail)
                .clickSubmit();

        assertThat("Error message is not displayed", editProfilePage.isErrorMessageDisplayed(), is(true));
        assertThat("Error summary is not displayed", editProfilePage.isErrorSummaryDisplayed(), is(true));
    }

    @Test
    @Description("Change language on edit profile")
    public void selectLanguageOnEditProfile() {
        int languageIndex = 0;
        EditProfilePage editProfilePage = loginAndEditProfile()
                .selectLanguage(languageIndex)
                .clickSubmit();

        assertThat("Success message is not displayed", editProfilePage.isSuccessMessageDisplayed(), is(true));
        assertThat("Language radio button is not selected", editProfilePage.isLanguageRadioButtonSelected(languageIndex), is(true));
    }

    @Test
    @Description("User logout from the application")
    public void logoutUser() {
        loginWithUserCredential().clickLogOutButton();

        assertThat("Page title is not correct", getDriver().getTitle(), is("Login - bookingkit"));
        assertThat("Url didn't have correct path", getDriver().getCurrentUrl(), containsString("login"));
    }

    @Test
    @Description("Open dashboard page")
    public void openDashBoard() {
        DashboardPage dashboardPage = loginWithUserCredential()
                .clickDashBoardTab();

        assertThat("Dashboard page didn't open", dashboardPage.validateDashBoardPage(), is(true));
        assertThat("Page title is not correct", getDriver().getTitle(), is("Dashboard - bookingkit"));
    }

    private EditProfilePage loginAndEditProfile() {
        return loginWithUserCredential()
                .clickEditProfile();
    }

    private HomePage loginWithUserCredential() {
        return new LoginPage()
                .openURL()
                .loginWithCredential();
    }
}
