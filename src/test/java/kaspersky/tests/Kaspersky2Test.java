package kaspersky.tests;


import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.util.Date;

import static utils.TestData.getValue;

public class Kaspersky2Test extends BaseTest {

    @Test
    public void sendEmailTest() throws MessagingException {
        Date sendTime = new Date();
        commonSteps.userOpensMainPage();
        commonSteps.userDoesLogin(getValue("kasperskyLogin2"), getValue("kasperskyPassword"));
        commonSteps.userGoesToDownloadPage();
        commonSteps.userGoesToOsTab("Mac");
        commonSteps.userSeesThatChosenProductHasCorrectDescription("Password Manager", "Remembers your passwords for you and stores them securely.");
        commonSteps.userSendsAppToHimself("Password Manager");
        commonSteps.userReceivesEmailWithSubject("Download links for Kaspersky Password Manager", sendTime);
        commonSteps.userSeesMessageContainsCorrectLink("Download links for Kaspersky Password Manager", sendTime, "https://my.kaspersky.com/MyLicenses?startDownload=https");
    }

}
