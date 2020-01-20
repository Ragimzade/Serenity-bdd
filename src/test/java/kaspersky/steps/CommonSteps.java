package kaspersky.steps;

import browser.Browser;
import kaspersky.pages.DownloadPage;
import kaspersky.pages.LoggedInMainPage;
import kaspersky.pages.MainPage;
import net.thucydides.core.annotations.Step;
import utils.MailUtils;

import javax.mail.MessagingException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class CommonSteps {
    MainPage mainPage;
    LoggedInMainPage loggedInMainPage;
    DownloadPage downloadPage;

    @Step
    //WHEN
    public void userOpensMainPage() {
        mainPage.open();
        Browser.refreshPage();
        mainPage.assertPageIsOpened();
    }

    @Step
    //WHEN
    public void userDoesLogin(String email, String password) {
        if (!mainPage.isSignedIn()) {
            mainPage.login(email, password);
            loggedInMainPage.assertPageIsOpened();
        }
    }

    @Step
    //WHEN
    public void userGoesToDownloadPage() {
        loggedInMainPage.goToDownloadPage();
        downloadPage.assertPageIsOpened();
    }

    @Step
    //WHEN
    public void userGoesToOsTab(String os) {
        downloadPage.goToSelectedOsTab(os);
    }

    @Step
    //THEN
    public void userSeesThatChosenProductHasCorrectDescription(String productName, String correctDescription) {
        assertThat(downloadPage.isProductHasCorrectDescription(productName, correctDescription))
                .isTrue();
    }

    @Step
    //WHEN
    public void userSendsAppToHimself(String product) {
        downloadPage.sendAppToMySelf(product);
    }

    @Step
    //THEN
    public void userReceivesEmailWithSubject(String subject, Date sentTime) throws MessagingException {
        assertThat(MailUtils.getMessageSubject(subject, sentTime))
                .isEqualTo(subject);
    }

    @Step
    //THEN
    public void userSeesMessageContainsCorrectLink(String subject, Date sentTime, String emailLink) {
        assertThat(MailUtils.getMessageContent(subject, sentTime))
                .contains(emailLink)
                .as(String.format("Email doesn't contain link %s", emailLink));
    }

    @Step
    //When
    public void userLogsOut() {
        loggedInMainPage.logOut();
    }
}

