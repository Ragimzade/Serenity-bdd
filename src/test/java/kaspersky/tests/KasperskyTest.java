package kaspersky.tests;

import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.mail.MessagingException;
import java.util.Date;

import static utils.TestData.getValue;

@Setter
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "src/main/resources/test-data.csv")
public class KasperskyTest extends BaseTest {

    private String os;
    private String product;
    private String description;
    private String emailSubject;
    private String emailLink;

    @After
    public void doAfter() {
        commonSteps.userLogsOut();
    }

    @Qualifier
    public String qualifier() {
        return os + "=>" + product + "=>" + description + "=>" + emailSubject + "=>" + emailLink;
    }

    @Test
    public void sendEmailTest() throws MessagingException {
        Date sendTime = new Date();
        commonSteps.userOpensMainPage();
        commonSteps.userDoesLogin(getValue("kasperskyLogin2"), getValue("kasperskyPassword"));
        commonSteps.userGoesToDownloadPage();
        commonSteps.userGoesToOsTab(os);
        commonSteps.userSeesThatChosenProductHasCorrectDescription(product, description);
        commonSteps.userSendsAppToHimself(product);
        commonSteps.userReceivesEmailWithSubject(emailSubject, sendTime);
        commonSteps.userSeesMessageContainsCorrectLink(emailSubject, sendTime, emailLink);
    }
}
