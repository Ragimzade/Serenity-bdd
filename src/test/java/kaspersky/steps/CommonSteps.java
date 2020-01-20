package kaspersky.steps;


import kaspersky.pages.DownloadPage;
import kaspersky.pages.LoggedInMainPage;
import kaspersky.pages.MainPage;
import net.thucydides.core.annotations.Step;

import javax.mail.MessagingException;

import browser.Browser;

import java.util.Date;

import utils.MailUtils;

import static org.assertj.core.api.Assertions.assertThat;


public class CommonSteps {
    MainPage mainPage;
    LoggedInMainPage loggedInMainPage;
    DownloadPage downloadPage;

    @Step
    //WHEN
    public void openMainPage() {
        mainPage.open();
        Browser.refreshPage();
        mainPage.assertPageIsOpened();
    }

    @Step
    //WHEN
    public void doLogin(String email, String password) {
        if (!mainPage.isSignedIn()) {
            mainPage.login(email, password);
            loggedInMainPage.assertPageIsOpened();
        }
    }

    @Step
    //WHEN
    public void goToDownloadPage() {
        loggedInMainPage.goToDownloadPage();
        downloadPage.assertPageIsOpened();
    }

    @Step
    //WHEN
    public void goToSelectedTab(String os) {
        downloadPage.goToSelectedOsTab(os);
    }

    @Step
    //THEN
    public void verifyProductDescription(String productName, String correctDescription) {
        assertThat(downloadPage.isProductHasCorrectDescription(productName, correctDescription))
                .isTrue();
    }

    @Step
    //WHEN
    public void sendAppToMyself(String product) {
        downloadPage.sendAppToMySelf(product);

    }

    @Step
    //THEN
    public void verifyMessageSubject(String subject, Date sentTime) throws MessagingException {
        assertThat(MailUtils.getMessageSubject(subject, sentTime))
                .isEqualTo(subject);
    }

    @Step
    //THEN
    public void verifyMessageContainsCorrectLink(String subject, Date sentTime, String emailLink) {
        assertThat(MailUtils.getMessageContent(subject, sentTime))
                .contains(emailLink)
                .as(String.format("Email doesn't contain link  %s", emailLink));
    }

    @Step
    //When
    public void logOut() {
        loggedInMainPage.logOut();
    }
}

